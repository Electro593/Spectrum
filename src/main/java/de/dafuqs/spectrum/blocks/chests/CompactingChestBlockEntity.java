package de.dafuqs.spectrum.blocks.chests;

import de.dafuqs.spectrum.helpers.InventoryHelper;
import de.dafuqs.spectrum.inventories.AutoCompactingInventory;
import de.dafuqs.spectrum.inventories.CompactingChestScreenHandler;
import de.dafuqs.spectrum.registries.SpectrumBlockEntityRegistry;
import net.fabricmc.fabric.api.screenhandler.v1.ExtendedScreenHandlerFactory;
import net.fabricmc.fabric.api.transfer.v1.item.ItemVariant;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.recipe.CraftingRecipe;
import net.minecraft.recipe.RecipeType;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.text.TranslatableText;
import net.minecraft.util.Pair;
import net.minecraft.util.collection.DefaultedList;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.*;

public class CompactingChestBlockEntity extends SpectrumChestBlockEntity implements ExtendedScreenHandlerFactory {
	
	AutoCompactingInventory autoCompactingInventory = new AutoCompactingInventory();
	AutoCompactingInventory.AutoCraftingMode autoCraftingMode;
	ItemStack lastCraftingItemStack;// cache
	CraftingRecipe lastCraftingRecipe; // cache
	boolean hasToCraft;

	private static final Map<AutoCompactingInventory.AutoCraftingMode, Map<ItemVariant, Optional<CraftingRecipe>>> cache = new EnumMap<>(AutoCompactingInventory.AutoCraftingMode.class);
	
	public CompactingChestBlockEntity(BlockPos blockPos, BlockState blockState) {
		super(SpectrumBlockEntityRegistry.COMPACTING_CHEST, blockPos, blockState);
		this.autoCraftingMode = AutoCompactingInventory.AutoCraftingMode.ThreeXTree;
		this.lastCraftingItemStack = ItemStack.EMPTY;
		this.lastCraftingRecipe = null;
		this.hasToCraft = false;
	}
	
	protected Text getContainerName() {
		return new TranslatableText("block.spectrum.compacting_chest");
	}
	
	public static void tick(World world, BlockPos pos, BlockState state, CompactingChestBlockEntity compactingChestBlockEntity) {
		if (world.isClient) {
			compactingChestBlockEntity.lidAnimator.step();
		} else {
			if (compactingChestBlockEntity.hasToCraft) {
				boolean couldCraft = compactingChestBlockEntity.tryCraftOnce();
				if (!couldCraft) {
					compactingChestBlockEntity.hasToCraft = false;
				}
			}
		}
	}
	
	public void readNbt(NbtCompound tag) {
		super.readNbt(tag);
		if(tag.contains("AutoCraftingMode", NbtElement.INT_TYPE)) {
			int autoCraftingModeInt = tag.getInt("AutoCraftingMode");
			this.autoCraftingMode = AutoCompactingInventory.AutoCraftingMode.values()[autoCraftingModeInt];
		}
	}
	
	public void writeNbt(NbtCompound tag) {
		super.writeNbt(tag);
		tag.putInt("AutoCraftingMode", this.autoCraftingMode.ordinal());
	}
	
	@Override
	public int size() {
		return 27;
	}
	
	@Override
	public void setStack(int slot, ItemStack stack) {
		super.setStack(slot, stack);
		this.hasToCraft = true;
	}
	
	public void inventoryChanged() {
		this.hasToCraft = true;
	}
	
	private boolean tryCraftOnce() {
		Optional<CraftingRecipe> optionalCraftingRecipe = Optional.empty();
		List<ItemStack> craftingStacks = null;
		DefaultedList<ItemStack> inventory = this.getInvStackList();
		
		// try craft the last recipe (faster)
		if (lastCraftingRecipe != null) {
			int requiredItemCount = this.autoCraftingMode.getItemCount();
			Pair<Integer, List<ItemStack>> stackPair = InventoryHelper.getStacksInInventory(lastCraftingItemStack, inventory, requiredItemCount);
			if (stackPair.getLeft() >= requiredItemCount) {
				craftingStacks = stackPair.getRight();
				optionalCraftingRecipe = Optional.ofNullable(lastCraftingRecipe);
			} else {
				lastCraftingRecipe = null;
				lastCraftingItemStack = ItemStack.EMPTY;
			}
		}
		
		// check the recipe cache
		if (optionalCraftingRecipe.isEmpty()) {
			Map<ItemVariant, Optional<CraftingRecipe>> currentCache;
			ItemVariant itemKey;
			
			for (ItemStack itemStack : inventory) {
				if (itemStack.isEmpty()) {
					continue;
				}
				int requiredItemCount = this.autoCraftingMode.getItemCount();
				Pair<Integer, List<ItemStack>> stackPair = InventoryHelper.getStacksInInventory(itemStack, inventory, requiredItemCount);
				craftingStacks = stackPair.getRight();
				if (craftingStacks != null && stackPair.getLeft() >= requiredItemCount) {
					currentCache = cache.computeIfAbsent(autoCraftingMode, mode -> new HashMap<>());
					itemKey = ItemVariant.of(itemStack);
					if(!currentCache.containsKey(itemKey)) {
						autoCompactingInventory.setCompacting(autoCraftingMode, craftingStacks.get(0).copy());
						optionalCraftingRecipe = world.getRecipeManager().getFirstMatch(RecipeType.CRAFTING, autoCompactingInventory, world);
						currentCache.put(itemKey, optionalCraftingRecipe);
					} else {
						optionalCraftingRecipe = currentCache.get(itemKey);
					}
					if(optionalCraftingRecipe.isPresent()) {
						break;
					}
				}
			}
		}
		
		// craft the recipe
		if (optionalCraftingRecipe.isPresent()) {
			ItemStack craftingInput = craftingStacks.get(0).copy();
			craftingInput.setCount(this.autoCraftingMode.getItemCount());
			ItemStack craftingOutput = optionalCraftingRecipe.get().getOutput().copy();
			
			if (tryCraftInInventory(inventory, craftingInput, craftingOutput)) {
				this.lastCraftingRecipe = optionalCraftingRecipe.get();
				this.lastCraftingItemStack = craftingInput;
				return true;
			}
		}
		return false;
	}
	
	public boolean tryCraftInInventory(DefaultedList<ItemStack> inventory, ItemStack removalItemStack, ItemStack additionItemStack) {
		InventoryHelper.removeFromInventory(removalItemStack, inventory);
		
		boolean spaceInInventory;
		
		List<ItemStack> additionItemStacks = new ArrayList<>();
		additionItemStacks.add(additionItemStack);
		
		// room for output?
		ItemStack remainderStack;
		Item recipeRemainderItem = removalItemStack.getItem().getRecipeRemainder();
		if (recipeRemainderItem != null) {
			remainderStack = recipeRemainderItem.getDefaultStack();
			remainderStack.setCount(9);
			additionItemStacks.add(remainderStack);
		}
		
		spaceInInventory = InventoryHelper.smartAddToInventory(additionItemStacks, inventory, true);
		
		if (spaceInInventory) {
			// craft
			InventoryHelper.smartAddToInventory(additionItemStacks, inventory, false);
			this.setInvStackList(inventory);
			
			// cache
			return true;
		} else {
			InventoryHelper.smartAddToInventory(List.of(removalItemStack), inventory, false);
			return false;
		}
	}
	
	@Override
	public SoundEvent getOpenSound() {
		return SoundEvents.BLOCK_PISTON_EXTEND;
	}
	
	@Override
	public SoundEvent getCloseSound() {
		return SoundEvents.BLOCK_PISTON_CONTRACT;
	}
	
	public AutoCompactingInventory.AutoCraftingMode getAutoCraftingMode() {
		return this.autoCraftingMode;
	}
	
	public void applySettings(PacketByteBuf buf) {
		int autoCraftingModeInt = buf.readInt();
		this.autoCraftingMode = AutoCompactingInventory.AutoCraftingMode.values()[autoCraftingModeInt];
		this.lastCraftingRecipe = null;
	}
	
	@Override
	protected ScreenHandler createScreenHandler(int syncId, PlayerInventory playerInventory) {
		return new CompactingChestScreenHandler(syncId, playerInventory, this);
	}
	
	@Override
	public void writeScreenOpeningData(ServerPlayerEntity player, PacketByteBuf buf) {
		buf.writeBlockPos(this.pos);
		buf.writeInt(this.autoCraftingMode.ordinal());
	}

	public static void clearCache() {
		cache.clear();
	}

}
