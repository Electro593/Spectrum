package de.dafuqs.spectrum.inventories;

import com.mojang.blaze3d.systems.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.blocks.chests.*;
import de.dafuqs.spectrum.networking.*;
import net.fabricmc.fabric.api.client.networking.v1.*;
import net.fabricmc.fabric.api.networking.v1.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.client.gui.widget.*;
import net.minecraft.client.render.*;
import net.minecraft.client.util.math.*;
import net.minecraft.entity.player.*;
import net.minecraft.network.*;
import net.minecraft.text.*;
import net.minecraft.util.*;

public class CompactingChestScreen extends HandledScreen<CompactingChestScreenHandler> {
	
	public static final Identifier BACKGROUND = SpectrumCommon.locate("textures/gui/container/compacting_chest.png");
	private AutoCompactingInventory.AutoCraftingMode autoCraftingMode;
	
	public CompactingChestScreen(CompactingChestScreenHandler handler, PlayerInventory playerInventory, Text title) {
		super(handler, playerInventory, title);
		this.backgroundHeight = 178;
		this.autoCraftingMode = handler.getCurrentCraftingMode();
	}
	
	@Override
	protected void init() {
		super.init();
		
		//client.keyboard.setRepeatEvents(true);
		setupInputFields(handler.getBlockEntity());
	}
	
	protected void setupInputFields(CompactingChestBlockEntity compactingChestBlockEntity) {
		int x = (this.width - this.backgroundWidth) / 2 + 3;
		int y = (this.height - this.backgroundHeight) / 2 + 3;
		
		ButtonWidget craftingModeButton = ButtonWidget.builder(Text.literal("Mode"), this::craftingModeButtonPressed)
				.size(16, 16)
				.position(x+154, y+6)
				.build();
				//new ButtonWidget(x + 154, y + 6, 16, 16, Text.literal("Mode"), this::craftingModeButtonPressed);
		addSelectableChild(craftingModeButton);
	}
	
	private void craftingModeButtonPressed(ButtonWidget buttonWidget) {
		autoCraftingMode = AutoCompactingInventory.AutoCraftingMode.values()[(autoCraftingMode.ordinal() + 1) % AutoCompactingInventory.AutoCraftingMode.values().length];
		this.onValuesChanged();
	}
	
	@Override
	protected void drawForeground(MatrixStack matrices, int mouseX, int mouseY) {
		// draw "title" and "inventory" texts
		int titleX = (backgroundWidth - textRenderer.getWidth(title)) / 2; // 8;
		int titleY = 6;
		Text title = this.title;
		int inventoryX = 8;
		int intInventoryY = 83;
		
		this.textRenderer.draw(matrices, title, titleX, titleY, 3289650);
		this.textRenderer.draw(matrices, this.playerInventoryTitle, inventoryX, intInventoryY, 3289650);
	}
	
	@Override
	protected void drawBackground(MatrixStack matrices, float delta, int mouseX, int mouseY) {
		RenderSystem.setShader(GameRenderer::getPositionTexProgram);
		RenderSystem.setShaderTexture(0, BACKGROUND);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		
		int x = (width - backgroundWidth) / 2;
		int y = (height - backgroundHeight) / 2;
		drawTexture(matrices, x, y, 0, 0, backgroundWidth, backgroundHeight);
		
		// the selected crafting mode
		drawTexture(matrices, x + 154, y + 6, 176, 16 * autoCraftingMode.ordinal(), 16, 16);
		
	}
	
	@Override
	public void render(MatrixStack matrices, int mouseX, int mouseY, float delta) {
		renderBackground(matrices);
		super.render(matrices, mouseX, mouseY, delta);
		
		if (mouseX > x + 153 && mouseX < x + 153 + 16 && mouseY > y + 5 && mouseY < y + 5 + 16) {
			this.renderTooltip(matrices, Text.translatable("block.spectrum.compacting_chest.toggle_crafting_mode"), mouseX, mouseY);
		} else {
			drawMouseoverTooltip(matrices, mouseX, mouseY);
		}
	}
	
	private void onValuesChanged() {
		PacketByteBuf packetByteBuf = PacketByteBufs.create();
		packetByteBuf.writeInt(autoCraftingMode.ordinal());
		ClientPlayNetworking.send(SpectrumC2SPackets.CHANGE_COMPACTING_CHEST_SETTINGS_PACKET_ID, packetByteBuf);
	}
	
}