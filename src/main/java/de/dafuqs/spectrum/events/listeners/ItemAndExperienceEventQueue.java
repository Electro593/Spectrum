package de.dafuqs.spectrum.events.listeners;

import de.dafuqs.spectrum.events.*;
import net.minecraft.entity.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import net.minecraft.world.event.*;
import net.minecraft.world.event.listener.*;

/**
 * Since Sucking chests can react to both spawned items and experience
 * this class is a wrapper around those two
 * (Each Block can only ever have a single event listener)
 */
public class ItemAndExperienceEventQueue implements GameEventListener {
	
	protected final EventQueue.Callback listener;
	protected final ItemEntityEventQueue itemQueue;
	protected final ExperienceOrbEventQueue experienceQueue;
	
	public ItemAndExperienceEventQueue(PositionSource positionSource, int range, EventQueue.Callback listener) {
		this.listener = listener;
		this.itemQueue = new ItemEntityEventQueue(positionSource, range, listener);
		this.experienceQueue = new ExperienceOrbEventQueue(positionSource, range, listener);
	}
	
	@Override
	public PositionSource getPositionSource() {
		return this.itemQueue.getPositionSource();
	}
	
	@Override
	public int getRange() {
		return this.itemQueue.getRange();
	}

	@Override
	public boolean listen(ServerWorld world, GameEvent event, GameEvent.Emitter emitter, Vec3d emitterPos) {
		if (event != SpectrumGameEvents.ENTITY_SPAWNED) {
			return false;
		}
		Entity entity = emitter.sourceEntity();
		if (entity instanceof ItemEntity && itemQueue.listen(world, event, emitter, emitterPos)) {
			return true;
		}
		return entity instanceof ExperienceOrbEntity && experienceQueue.listen(world, event, emitter, emitterPos);
	}

	public void tick(World world) {
		this.itemQueue.tick(world);
		this.experienceQueue.tick(world);
	}
	
}
