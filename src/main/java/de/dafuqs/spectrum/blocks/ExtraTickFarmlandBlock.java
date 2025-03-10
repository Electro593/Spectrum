package de.dafuqs.spectrum.blocks;

import net.minecraft.block.*;
import net.minecraft.server.world.*;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

public class ExtraTickFarmlandBlock extends FarmlandBlock {
	
	public ExtraTickFarmlandBlock(Settings settings) {
		super(settings);
	}
	
	private static boolean hasCrop(@NotNull BlockView world, @NotNull BlockPos pos) {
		Block block = world.getBlockState(pos.up()).getBlock();
		return block instanceof CropBlock || block instanceof StemBlock || block instanceof AttachedStemBlock;
	}
	
	/**
	 * If there is a crop block on top of this block: tick it, too
	 * => the crop grows faster
	 */
	@Override
	public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
		BlockPos topPos = pos.up();
		BlockState topBlockState = world.getBlockState(topPos);
		if (hasCrop(world, pos)) {
			topBlockState.getBlock().randomTick(topBlockState, world, topPos, random);
		}
		
		super.randomTick(state, world, pos, random);
	}
	
}
