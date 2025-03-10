package de.dafuqs.spectrum.progression.toast;

import com.mojang.blaze3d.systems.*;
import de.dafuqs.spectrum.*;
import net.fabricmc.api.*;
import net.minecraft.client.*;
import net.minecraft.client.render.*;
import net.minecraft.client.sound.*;
import net.minecraft.client.toast.*;
import net.minecraft.client.util.math.*;
import net.minecraft.item.*;
import net.minecraft.sound.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.util.math.*;

import java.util.*;

@Environment(EnvType.CLIENT)
public class RevelationToast implements Toast {
	
	private final Identifier TEXTURE = SpectrumCommon.locate("textures/gui/toasts.png");
	private final ItemStack itemStack;
	private final SoundEvent soundEvent;
	private boolean soundPlayed;
	
	public RevelationToast(ItemStack itemStack, SoundEvent soundEvent) {
		this.itemStack = itemStack;
		this.soundEvent = soundEvent;
		this.soundPlayed = false;
	}
	
	public static void showRevelationToast(MinecraftClient client, ItemStack itemStack, SoundEvent soundEvent) {
		client.getToastManager().add(new RevelationToast(itemStack, soundEvent));
	}
	
	@Override
	public Visibility draw(MatrixStack matrices, ToastManager manager, long startTime) {
		Text title = Text.translatable("spectrum.toast.revelation.title");
		Text text = Text.translatable("spectrum.toast.revelation.text");
		
		RenderSystem.setShader(GameRenderer::getPositionTexProgram);
		RenderSystem.setShaderTexture(0, TEXTURE);
		RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
		
		manager.drawTexture(matrices, 0, 0, 0, 0, this.getWidth(), this.getHeight());
		
		List<OrderedText> wrappedText = manager.getClient().textRenderer.wrapLines(text, 125);
		List<OrderedText> wrappedTitle = manager.getClient().textRenderer.wrapLines(title, 125);
		int l;
		long toastTimeMilliseconds = SpectrumCommon.CONFIG.ToastTimeMilliseconds;
		if (startTime < toastTimeMilliseconds / 2) {
			l = MathHelper.floor(MathHelper.clamp((float) (toastTimeMilliseconds / 2 - startTime) / 300.0F, 0.0F, 1.0F) * 255.0F) << 24 | 67108864;
			int halfHeight = this.getHeight() / 2;
			int titleSize = wrappedTitle.size();
			int m = halfHeight - titleSize * 9 / 2;
			
			for (Iterator<OrderedText> var12 = wrappedTitle.iterator(); var12.hasNext(); m += 9) {
				OrderedText orderedText = var12.next();
				manager.getClient().textRenderer.draw(matrices, orderedText, 30.0F, (float) m, 3289650 | l);
			}
		} else {
			l = MathHelper.floor(MathHelper.clamp((float) (startTime - toastTimeMilliseconds / 2) / 300.0F, 0.0F, 1.0F) * 252.0F) << 24 | 67108864;
			int halfHeight = this.getHeight() / 2;
			int textSize = wrappedText.size();
			int m = halfHeight - textSize * 9 / 2;
			
			for (Iterator<OrderedText> var12 = wrappedText.iterator(); var12.hasNext(); m += 9) {
				OrderedText orderedText = var12.next();
				manager.getClient().textRenderer.draw(matrices, orderedText, 30.0F, (float) m, l);
			}
		}
		
		if (!this.soundPlayed && startTime > 0L) {
			this.soundPlayed = true;
			if (this.soundEvent != null) {
				manager.getClient().getSoundManager().play(PositionedSoundInstance.master(this.soundEvent, 1.0F, 0.6F));
			}
		}
		
		manager.getClient().getItemRenderer().renderInGui(matrices, itemStack, 8, 8);
		return startTime >= toastTimeMilliseconds ? Visibility.HIDE : Visibility.SHOW;
	}
	
}
