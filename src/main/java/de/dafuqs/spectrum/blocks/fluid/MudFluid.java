package de.dafuqs.spectrum.blocks.fluid;

import de.dafuqs.spectrum.particle.*;
import de.dafuqs.spectrum.registries.*;
import net.fabricmc.api.*;
import net.minecraft.block.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;

public abstract class MudFluid extends SpectrumFluid {
	
	@Override
	public Fluid getStill() {
		return SpectrumFluids.MUD;
	}
	
	@Override
	public Fluid getFlowing() {
		return SpectrumFluids.FLOWING_MUD;
	}
	
	@Override
	public Item getBucketItem() {
		return SpectrumItems.MUD_BUCKET;
	}
	
	@Override
	protected BlockState toBlockState(FluidState fluidState) {
		return SpectrumBlocks.MUD.getDefaultState().with(Properties.LEVEL_15, getBlockStateLevel(fluidState));
	}
	
	@Override
	public boolean matchesType(Fluid fluid) {
		return fluid == SpectrumFluids.MUD || fluid == SpectrumFluids.FLOWING_MUD;
	}
	
	@Override
	@Environment(EnvType.CLIENT)
	public void randomDisplayTick(World world, BlockPos pos, FluidState state, Random random) {
		BlockPos topPos = pos.up();
		BlockState topState = world.getBlockState(topPos);
		if (topState.isAir() && !topState.isOpaqueFullCube(world, topPos) && random.nextInt(1000) == 0) {
			world.playSound(pos.getX(), pos.getY(), pos.getZ(), SpectrumSoundEvents.MUD_AMBIENT, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
		}
	}
	
	@Override
	protected int getFlowSpeed(WorldView worldView) {
		return 1;
	}
	
	@Override
	protected int getLevelDecreasePerBlock(WorldView worldView) {
		return 3;
	}
	
	@Override
	public int getTickRate(WorldView worldView) {
		return 50;
	}
	
	@Override
	public ParticleEffect getParticle() {
		return SpectrumParticleTypes.DRIPPING_MUD;
	}
	
	@Override
	public ParticleEffect getSplashParticle() {
		return SpectrumParticleTypes.MUD_SPLASH;
	}
	
	public static class FlowingMud extends MudFluid {
		
		@Override
		protected void appendProperties(StateManager.Builder<Fluid, FluidState> builder) {
			super.appendProperties(builder);
			builder.add(LEVEL);
		}

		@Override
		protected boolean isInfinite(World world) {
			return false;
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return fluidState.get(LEVEL);
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return false;
		}
		
	}
	
	public static class StillMud extends MudFluid {

		@Override
		protected boolean isInfinite(World world) {
			return false;
		}

		@Override
		public int getLevel(FluidState fluidState) {
			return 8;
		}
		
		@Override
		public boolean isStill(FluidState fluidState) {
			return true;
		}
		
	}
}