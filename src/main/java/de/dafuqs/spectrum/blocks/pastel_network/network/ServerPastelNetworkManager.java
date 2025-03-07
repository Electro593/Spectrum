package de.dafuqs.spectrum.blocks.pastel_network.network;

import de.dafuqs.spectrum.blocks.pastel_network.nodes.*;
import net.minecraft.nbt.*;
import net.minecraft.server.world.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

import java.util.*;

// Persisted together with the overworld
// resetting the overworld will also reset all networks
public class ServerPastelNetworkManager extends PersistentState implements PastelNetworkManager {

    private static final String NAME = "spectrum_pastel_network_manager";

    protected final List<ServerPastelNetwork> networks = new ArrayList<>();

    public ServerPastelNetworkManager() {
        super();
    }

    @Override
    public boolean isDirty() {
        return true;
    }

    public static ServerPastelNetworkManager get(ServerWorld world) {
        return world.getPersistentStateManager().getOrCreate(ServerPastelNetworkManager::fromNbt, ServerPastelNetworkManager::new, NAME);
    }

    @Override
    public NbtCompound writeNbt(NbtCompound tag) {
        NbtList networkList = new NbtList();
        for (ServerPastelNetwork network : this.networks) {
            NbtCompound compound = network.toNbt();
            networkList.add(compound);
        }
        tag.put("Networks", networkList);
        return tag;
    }
	
	public static ServerPastelNetworkManager fromNbt(NbtCompound nbt) {
		ServerPastelNetworkManager manager = new ServerPastelNetworkManager();
		for (NbtElement element : nbt.getList("Networks", NbtElement.LIST_TYPE)) {
			NbtCompound compound = (NbtCompound) element;
			manager.networks.add(ServerPastelNetwork.fromNbt(compound));
		}
		return manager;
	}
	
	@Override
	public void remove(PastelNetwork network) {
		this.networks.remove(network);
	}
	
	public void tick() {
		for (ServerPastelNetwork network : this.networks) {
			network.tick();
		}
	}
	
	@Override
	public @Nullable PastelNetwork getNetwork(UUID uuid) {
		for (PastelNetwork network : this.networks) {
			if (network.getUUID() == uuid) {
				return network;
			}
		}
		return null;
	}
	
	@Override
	public PastelNetwork joinNetwork(PastelNodeBlockEntity node, @Nullable UUID uuid) {
		if (uuid == null) {
			for (ServerPastelNetwork network : this.networks) {
				if (network.canConnect(node)) {
					network.addNode(node);
					network.checkNetworkMergesForNewNode(node);
					return network;
				}
			}
		} else {
			//noinspection ForLoopReplaceableByForEach
            for (int i = 0; i < this.networks.size(); i++) {
                PastelNetwork network = this.networks.get(i);
                if (network.getUUID().equals(uuid)) {
                    network.addNode(node);
                    return network;
                }
            }
        }

        PastelNetwork network = createNetwork(node.getWorld(), uuid);
        network.addNode(node);
        return network;
    }

    @Override
    public PastelNetwork createNetwork(World world, @Nullable UUID uuid) {
        ServerPastelNetwork network = new ServerPastelNetwork(world, uuid);
        this.networks.add(network);
        return network;
    }

}
