package de.dafuqs.spectrum.items.tools;

import de.dafuqs.spectrum.items.*;
import net.minecraft.enchantment.*;
import net.minecraft.item.*;
import de.dafuqs.spectrum.items.Preenchanted;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.item.ItemStack;
import net.minecraft.item.SwordItem;
import net.minecraft.item.ToolMaterial;

import java.util.*;

public class BedrockSwordItem extends SwordItem implements Preenchanted {
	
	public BedrockSwordItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
		super(material, attackDamage, attackSpeed, settings);
	}
	
	@Override
	public boolean isDamageable() {
		return false;
	}
	
	@Override
	public Map<Enchantment, Integer> getDefaultEnchantments() {
		return Map.of(Enchantments.SHARPNESS, 6);
	}
	
	@Override
	public ItemStack getDefaultStack() {
		return getDefaultEnchantedStack(this);
	}

	@Override
	public boolean isEnchantable(ItemStack stack) {
		return true;
	}
	
}