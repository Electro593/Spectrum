package de.dafuqs.spectrum.mixin;

import com.google.common.collect.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.inventories.slots.*;
import de.dafuqs.spectrum.items.*;
import de.dafuqs.spectrum.mixin.accessors.*;
import de.dafuqs.spectrum.registries.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.entity.attribute.*;
import net.minecraft.entity.player.*;
import net.minecraft.item.*;
import net.minecraft.screen.slot.*;
import net.minecraft.registry.tag.*;
import net.minecraft.util.*;
import org.spongepowered.asm.mixin.*;
import org.spongepowered.asm.mixin.injection.*;
import org.spongepowered.asm.mixin.injection.callback.*;

import java.util.*;

@Mixin(ItemStack.class)
public abstract class ItemStackMixin {
	
	@Shadow
	public abstract boolean isIn(TagKey<Item> tag);
	
	@Shadow
	public abstract boolean isOf(Item item);
	
	@Shadow
	public abstract Item getItem();
	
	// Injecting into onStackClicked instead of onClicked because onStackClicked is called first
	@Inject(at = @At("HEAD"), method = "onStackClicked", cancellable = true)
	public void spectrum$onStackClicked(Slot slot, ClickType clickType, PlayerEntity player, CallbackInfoReturnable<Boolean> cir) {
		if (slot instanceof SlotWithOnClickAction slotWithOnClickAction) {
			if (slotWithOnClickAction.onClicked((ItemStack) (Object) this, clickType, player)) {
				cir.setReturnValue(true);
			}
		}
	}

	@Inject(at = @At("RETURN"), method = "getAttributeModifiers(Lnet/minecraft/entity/EquipmentSlot;)Lcom/google/common/collect/Multimap;", cancellable = true)
	public void spectrum$applyTightGripEnchantment(EquipmentSlot slot, CallbackInfoReturnable<Multimap<EntityAttribute, EntityAttributeModifier>> cir) {

		var stack = (ItemStack) (Object) this;
		int tightGripLevel = EnchantmentHelper.getLevel(SpectrumEnchantments.TIGHT_GRIP, stack);

		if (tightGripLevel > 0) {

			ImmutableMultimap.Builder<EntityAttribute, EntityAttributeModifier> builder = ImmutableMultimap.builder();
			for (Map.Entry<EntityAttribute, EntityAttributeModifier> attributeEntry : cir.getReturnValue().entries()) {

				if (attributeEntry.getKey().equals(EntityAttributes.GENERIC_ATTACK_SPEED)) {
					double newAttackSpeed = attributeEntry.getValue().getValue();

					newAttackSpeed *= Math.max(0.25, 1 - tightGripLevel * SpectrumCommon.CONFIG.TightGripAttackSpeedBonusPercentPerLevel);

					builder.put(attributeEntry.getKey(), new EntityAttributeModifier(ItemAccessor.getAttackSpeedModifierId(), "Weapon modifier", newAttackSpeed, EntityAttributeModifier.Operation.ADDITION));

					cir.setReturnValue(builder.build());
				} else {
					builder.put(attributeEntry.getKey(), attributeEntry.getValue());

				}

			}

		}
	}

	@Inject(at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;getNbt()Lnet/minecraft/nbt/NbtCompound;"), method = "isDamageable()Z", cancellable = true)
	public void spectrum$checkIndestructibleEnchantment(CallbackInfoReturnable<Boolean> cir) {
		if (SpectrumCommon.CONFIG.IndestructibleEnchantmentEnabled && EnchantmentHelper.getLevel(SpectrumEnchantments.INDESTRUCTIBLE, (ItemStack) (Object) this) > 0) {
			cir.setReturnValue(false);
		}
	}
	
	// thank you so, so much @williewillus / @Botania for this snippet of code
	// https://github.com/VazkiiMods/Botania/blob/1.18.x/Fabric/src/main/java/vazkii/botania/fabric/mixin/FabricMixinItemStack.java
	@Inject(at = @At("HEAD"), method = "isOf(Lnet/minecraft/item/Item;)Z", cancellable = true)
	private void spectrum$isSpectrumShears(Item item, CallbackInfoReturnable<Boolean> cir) {
		if (item == Items.SHEARS) {
			if (isOf(SpectrumItems.BEDROCK_SHEARS)) {
				cir.setReturnValue(true);
			}
		}
	}
	
	// The enchantment table does not allow enchanting items that already have enchantments applied
	// This mixin changes items, that only got their DefaultEnchantments to still be enchantable
	@Inject(method = "isEnchantable()Z", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;hasEnchantments()Z"), cancellable = true)
	public void spectrum$isEnchantable(CallbackInfoReturnable<Boolean> cir) {
		if (this.getItem() instanceof Preenchanted preencahnted && preencahnted.onlyHasPreEnchantments((ItemStack) (Object) this)) {
			cir.setReturnValue(true);
		}
	}
	
}