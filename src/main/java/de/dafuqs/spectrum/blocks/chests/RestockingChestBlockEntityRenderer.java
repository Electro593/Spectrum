package de.dafuqs.spectrum.blocks.chests;

import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.registries.*;
import net.fabricmc.api.*;
import net.minecraft.block.*;
import net.minecraft.client.model.*;
import net.minecraft.client.render.*;
import net.minecraft.client.render.block.entity.*;
import net.minecraft.client.texture.*;
import net.minecraft.client.util.*;
import net.minecraft.client.util.math.*;
import net.minecraft.util.math.*;
import net.minecraft.world.*;
import org.jetbrains.annotations.*;

@Environment(EnvType.CLIENT)
public class RestockingChestBlockEntityRenderer<RestockingChestBlockEntity extends SpectrumChestBlockEntity> implements BlockEntityRenderer<RestockingChestBlockEntity> {
	
	private static final SpriteIdentifier spriteIdentifier = new SpriteIdentifier(SpriteAtlasTexture.BLOCK_ATLAS_TEXTURE, SpectrumCommon.locate("entity/restocking_chest"));
	private final ModelPart root;
	private final ModelPart lid;
	
	public RestockingChestBlockEntityRenderer(BlockEntityRendererFactory.Context ctx) {
		TexturedModelData texturedModelData = getTexturedModelData();
		root = texturedModelData.createModel();
		lid = root.getChild("lid");
	}
	
	public static @NotNull TexturedModelData getTexturedModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		
		modelPartData.addChild("bottom", ModelPartBuilder.create().uv(0, 16).cuboid(1.0F, 0.0F, 1.0F, 14.0F, 10.0F, 14.0F), ModelTransform.pivot(0.0F, 0.0F, 0.0F));
		modelPartData.addChild("lid", ModelPartBuilder.create().uv(0, 0).cuboid(3.0F, 0.0F, 3.0F, 10.0F, 6.0F, 10.0F), ModelTransform.pivot(0.0F, 11.0F, 0.0F));
		
		return TexturedModelData.of(modelData, 64, 64);
	}
	
	@Override
	public void render(RestockingChestBlockEntity entity, float tickDelta, MatrixStack matrixStack, VertexConsumerProvider vertexConsumers, int light, int overlay) {
		World world = entity.getWorld();
		boolean bl = world != null;
		BlockState blockState = bl ? entity.getCachedState() : SpectrumBlocks.RESTOCKING_CHEST.getDefaultState().with(ChestBlock.FACING, Direction.SOUTH);
		
		matrixStack.push();
		float f = (blockState.get(ChestBlock.FACING)).asRotation();
		matrixStack.translate(0.5D, 0.5D, 0.5D);
		matrixStack.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(-f));
		matrixStack.translate(-0.5D, -0.5D, -0.5D);
		
		float openFactor = entity.getAnimationProgress(tickDelta);
		openFactor = 1.0F - openFactor;
		openFactor = 1.0F - openFactor * openFactor * openFactor;
		
		lid.pivotY = 5 + openFactor * 5;
		
		VertexConsumer vertexConsumer = spriteIdentifier.getVertexConsumer(vertexConsumers, RenderLayer::getEntityCutout);
		root.render(matrixStack, vertexConsumer, light, overlay);
		
		matrixStack.pop();
	}
	
}