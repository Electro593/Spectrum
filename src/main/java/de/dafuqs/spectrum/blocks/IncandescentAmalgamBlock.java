package de.dafuqs.spectrum.blocks;

import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.block.entity.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.player.*;
import net.minecraft.entity.projectile.*;
import net.minecraft.fluid.*;
import net.minecraft.item.*;
import net.minecraft.particle.*;
import net.minecraft.sound.*;
import net.minecraft.state.*;
import net.minecraft.state.property.*;
import net.minecraft.util.hit.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import net.minecraft.world.explosion.*;
import org.jetbrains.annotations.*;

public class IncandescentAmalgamBlock extends Block implements Waterloggable {
	
	public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
	
	protected static final VoxelShape SHAPE = Block.createCuboidShape(4.0D, 0.0D, 4.0D, 12.0D, 9.0D, 12.0D);
	
	public IncandescentAmalgamBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(WATERLOGGED, false));
	}
	
	@Override
	protected void appendProperties(StateManager.@NotNull Builder<Block, BlockState> builder) {
		builder.add(WATERLOGGED);
	}
	
	@Override
	public FluidState getFluidState(@NotNull BlockState state) {
		return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
	}
	
	@Override
	public BlockState getStateForNeighborUpdate(@NotNull BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
		if (state.get(WATERLOGGED)) {
			world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
		}
		return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
	}
	
	@Override
	public BlockState getPlacementState(@NotNull ItemPlacementContext ctx) {
		FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
		return this.getDefaultState().with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
	@Override
	public void randomDisplayTick(BlockState state, World world, BlockPos pos, Random random) {
		super.randomDisplayTick(state, world, pos, random);
		if (!state.get(WATERLOGGED)) {
			int r = random.nextInt(50);
			if (r < 10) {
				double posX = (double) pos.getX() + 0.25D + random.nextDouble() * 0.5D;
				double posY = (double) pos.getY() + random.nextDouble() * 0.5D;
				double posZ = (double) pos.getZ() + 0.25D + random.nextDouble() * 0.5D;
				world.addParticle(ParticleTypes.LAVA, posX, posY, posZ, 0.0D, 0.0D, 0.0D);
				if (r == 0) {
					world.playSound(posX, posY, posZ, SoundEvents.BLOCK_LAVA_POP, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
				}
			}
			if (random.nextInt(100) == 0) {
				world.playSound(pos.getX(), pos.getY(), pos.getZ(), SoundEvents.BLOCK_FIRE_EXTINGUISH, SoundCategory.BLOCKS, 0.2F + random.nextFloat() * 0.2F, 0.9F + random.nextFloat() * 0.15F, false);
			}
		}
	}
	
	@Override
	public boolean shouldDropItemsOnExplosion(Explosion explosion) {
		return false;
	}
	
	@Override
	public void onDestroyedByExplosion(World world, BlockPos pos, Explosion explosion) {
		super.onDestroyedByExplosion(world, pos, explosion);
		explode(world, pos);
	}
	
	@Override
	public void onSteppedOn(World world, BlockPos pos, BlockState state, Entity entity) {
		super.onSteppedOn(world, pos, state, entity);
		if (!state.get(WATERLOGGED)) {
			explode(world, pos);
		}
	}
	
	@Override
	public void onLandedUpon(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance) {
		super.onLandedUpon(world, state, pos, entity, fallDistance);
		if (!state.get(WATERLOGGED)) {
			explode(world, pos);
		}
	}
	
	@Override
	public void onProjectileHit(World world, BlockState state, BlockHitResult hit, ProjectileEntity projectile) {
		super.onProjectileHit(world, state, hit, projectile);
		if (!state.get(WATERLOGGED)) {
			explode(world, hit.getBlockPos());
		}
	}
	
	@Override
	public void neighborUpdate(BlockState state, World world, BlockPos pos, Block block, BlockPos fromPos, boolean notify) {
		super.neighborUpdate(state, world, pos, block, fromPos, notify);
		if (!state.get(WATERLOGGED) && world.random.nextInt(10) == 0) {
			explode(world, pos);
		}
	}
	
	// does not run in creative
	// => creative players can easily break it without causing an explosion
	@Override
	public void afterBreak(World world, PlayerEntity player, BlockPos pos, BlockState state, @Nullable BlockEntity blockEntity, ItemStack stack) {
		if (state.get(WATERLOGGED) || EnchantmentHelper.getLevel(SpectrumEnchantments.RESONANCE, stack) > 0) {
			super.afterBreak(world, player, pos, state, blockEntity, stack);
		} else {
			explode(world, pos);
		}
	}
	
	protected static void explode(World world, BlockPos pos) {
		if (!world.isClient) {
			world.createExplosion(null, SpectrumDamageSources.incandescence(world), new ExplosionBehavior(), pos.getX() + 0.5, pos.getY() + 0.5, pos.getZ() + 0.5, 8.0F, true, World.ExplosionSourceType.BLOCK);
		}
	}
	
}
