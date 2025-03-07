package de.dafuqs.spectrum.blocks.shooting_star;

import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.entity.entity.*;
import de.dafuqs.spectrum.helpers.ColorHelper;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.block.*;
import net.minecraft.block.dispenser.*;
import net.minecraft.block.entity.*;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.shape.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;
import org.joml.*;

public class ShootingStarBlock extends BlockWithEntity {
	
	protected static final VoxelShape SHAPE = Block.createCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	public final Type shootingStarType;
	
	public ShootingStarBlock(Settings settings, ShootingStarBlock.Type shootingStarType) {
		super(settings);
		this.shootingStarType = shootingStarType;
	}
	
	@Nullable
	@Override
	public BlockEntity createBlockEntity(BlockPos pos, BlockState state) {
		return new ShootingStarBlockEntity(pos, state);
	}
	
	@Override
	public BlockRenderType getRenderType(BlockState state) {
		return BlockRenderType.MODEL;
	}
	
	@Override
	public boolean canPathfindThrough(BlockState state, BlockView world, BlockPos pos, NavigationType type) {
		return false;
	}
	
	@Override
	public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
		ItemStack itemStack = super.getPickStack(world, pos, state);
		world.getBlockEntity(pos, SpectrumBlockEntities.SHOOTING_STAR).ifPresent((blockEntity) -> blockEntity.setStackNbt(itemStack));
		return itemStack;
	}
	
	@Override
	public void onBreak(@NotNull World world, BlockPos pos, BlockState state, PlayerEntity player) {
		if (!world.isClient && !player.isCreative()) {
			ItemStack itemStack = this.shootingStarType.getBlock().asItem().getDefaultStack();
			world.getBlockEntity(pos, SpectrumBlockEntities.SHOOTING_STAR).ifPresent((blockEntity) -> ShootingStarItem.getWithRemainingHits(itemStack, blockEntity.remainingHits, blockEntity.hardened));
			
			ItemEntity itemEntity = new ItemEntity(world, (double) pos.getX() + 0.5D, (double) pos.getY() + 0.5D, (double) pos.getZ() + 0.5D, itemStack);
			itemEntity.setToDefaultPickupDelay();
			world.spawnEntity(itemEntity);
		}
		
		super.onBreak(world, pos, state, player);
	}
	
	@Override
	public void onPlaced(@NotNull World world, BlockPos pos, BlockState state, LivingEntity placer, ItemStack itemStack) {
		if (!world.isClient) {
			BlockEntity blockEntity = world.getBlockEntity(pos);
			if (blockEntity instanceof ShootingStarBlockEntity shootingStarBlockEntity) {
				shootingStarBlockEntity.setData(ShootingStarItem.getRemainingHits(itemStack), ShootingStarItem.isHardened(itemStack));
			}
		}
	}
	
	@Override
	public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
		return SHAPE;
	}
	
	public enum Type {
		GLISTERING("glistering"),
		FIERY("fiery"),
		COLORFUL("colorful"),
		PRISTINE("pristine"),
		GEMSTONE("gemstone");
		
		public static final Identifier BOUNCE_LOOT_TABLE = SpectrumCommon.locate("entity/shooting_star/shooting_star_bounce");
		
		private final String name;
		
		Type(String name) {
			this.name = name;
		}
		
		public static ShootingStarBlock.Type getWeightedRandomType(@NotNull Random random) {
			int r = random.nextInt(8);
			if (r == 0) {
				return FIERY;
			} else if (r == 1) {
				return PRISTINE;
			} else if (r < 3) {
				return GLISTERING;
			} else if (r < 5) {
				return COLORFUL;
			} else {
				return GEMSTONE;
			}
		}
		
		public static ShootingStarBlock.Type getType(int type) {
			ShootingStarBlock.Type[] types = values();
			if (type < 0 || type >= types.length) {
				type = 0;
			}
			
			return types[type];
		}
		
		public static ShootingStarBlock.Type getType(String name) {
			ShootingStarBlock.Type[] types = values();
			
			for (Type type : types) {
				if (type.getName().equals(name)) {
					return type;
				}
			}
			
			return types[0];
		}
		
		@Contract("_ -> new")
		public static @NotNull Identifier getLootTableIdentifier(int index) {
			return getLootTableIdentifier(values()[index]);
		}
		
		@Contract("_ -> new")
		public static @NotNull Identifier getLootTableIdentifier(@NotNull Type type) {
			switch (type) {
				case FIERY -> {
					return SpectrumCommon.locate("entity/shooting_star/shooting_star_fiery");
				}
				case COLORFUL -> {
					return SpectrumCommon.locate("entity/shooting_star/shooting_star_colorful");
				}
				case GEMSTONE -> {
					return SpectrumCommon.locate("entity/shooting_star/shooting_star_gemstone");
				}
				case PRISTINE -> {
					return SpectrumCommon.locate("entity/shooting_star/shooting_star_pristine");
				}
				default -> {
					return SpectrumCommon.locate("entity/shooting_star/shooting_star_glistering");
				}
			}
		}
		
		public String getName() {
			return this.name;
		}
		
		public Block getBlock() {
			switch (this) {
				case PRISTINE -> {
					return SpectrumBlocks.PRISTINE_SHOOTING_STAR;
				}
				case GEMSTONE -> {
					return SpectrumBlocks.GEMSTONE_SHOOTING_STAR;
				}
				case FIERY -> {
					return SpectrumBlocks.FIERY_SHOOTING_STAR;
				}
				case COLORFUL -> {
					return SpectrumBlocks.COLORFUL_SHOOTING_STAR;
				}
				default -> {
					return SpectrumBlocks.GLISTERING_SHOOTING_STAR;
				}
			}
		}
		
		public @NotNull Vector3f getRandomParticleColor(Random random) {
			switch (this) {
				case GLISTERING -> {
					int r = random.nextInt(5);
					if (r == 0) {
						return ColorHelper.getRGBVec(DyeColor.YELLOW);
					} else if (r == 1) {
						return ColorHelper.getRGBVec(DyeColor.WHITE);
					} else if (r == 2) {
						return ColorHelper.getRGBVec(DyeColor.ORANGE);
					} else if (r == 3) {
						return ColorHelper.getRGBVec(DyeColor.LIME);
					} else {
						return ColorHelper.getRGBVec(DyeColor.BLUE);
					}
				}
				case COLORFUL -> {
					return ColorHelper.getRGBVec(DyeColor.values()[random.nextInt(DyeColor.values().length)]);
				}
				case FIERY -> {
					int r = random.nextInt(2);
					if (r == 0) {
						return ColorHelper.getRGBVec(DyeColor.ORANGE);
					} else {
						return ColorHelper.getRGBVec(DyeColor.RED);
					}
				}
				case PRISTINE -> {
					int r = random.nextInt(3);
					if (r == 0) {
						return ColorHelper.getRGBVec(DyeColor.BLUE);
					} else if (r == 1) {
						return ColorHelper.getRGBVec(DyeColor.LIGHT_BLUE);
					} else {
						return ColorHelper.getRGBVec(DyeColor.CYAN);
					}
				}
				default -> {
					int r = random.nextInt(4);
					if (r == 0) {
						return ColorHelper.getRGBVec(DyeColor.CYAN);
					} else if (r == 1) {
						return ColorHelper.getRGBVec(DyeColor.MAGENTA);
					} else if (r == 2) {
						return ColorHelper.getRGBVec(DyeColor.WHITE);
					} else {
						return ColorHelper.getRGBVec(DyeColor.YELLOW);
					}
				}
			}
		}
	}
	
	public static class ShootingStarBlockDispenserBehavior extends ItemDispenserBehavior {
		
		@Override
		public ItemStack dispenseSilently(@NotNull BlockPointer pointer, @NotNull ItemStack stack) {
			Direction direction = pointer.getBlockState().get(DispenserBlock.FACING);
			World world = pointer.getWorld();
			double d = pointer.getX() + direction.getOffsetX() * 1.125F;
			double e = pointer.getY() + direction.getOffsetY() * 1.125F;
			double f = pointer.getZ() + direction.getOffsetZ() * 1.125F;
			
			ShootingStarEntity shootingStarEntity = new ShootingStarEntity(world, d, e + 0.05, f);
			ShootingStarBlock.Type type = ((ShootingStarItem) stack.getItem()).getType();
			shootingStarEntity.setShootingStarType(type, true, ShootingStarItem.isHardened(stack));
			shootingStarEntity.setAvailableHits(ShootingStarItem.getRemainingHits(stack));
			shootingStarEntity.setYaw(direction.asRotation());
			shootingStarEntity.addVelocity(direction.getOffsetX() * 0.4, direction.getOffsetY() * 0.4, direction.getOffsetZ() * 0.4);
			world.spawnEntity(shootingStarEntity);
			
			stack.decrement(1);
			return stack;
		}
	}
	
}
