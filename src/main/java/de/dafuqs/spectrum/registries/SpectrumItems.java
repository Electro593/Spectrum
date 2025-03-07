package de.dafuqs.spectrum.registries;

import de.dafuqs.fractal.api.*;
import de.dafuqs.revelationary.api.revelations.*;
import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.blocks.bottomless_bundle.*;
import de.dafuqs.spectrum.blocks.conditional.*;
import de.dafuqs.spectrum.blocks.gravity.*;
import de.dafuqs.spectrum.blocks.jade_vines.*;
import de.dafuqs.spectrum.blocks.rock_candy.*;
import de.dafuqs.spectrum.energy.*;
import de.dafuqs.spectrum.energy.color.*;
import de.dafuqs.spectrum.entity.*;
import de.dafuqs.spectrum.enums.*;
import de.dafuqs.spectrum.helpers.*;
import de.dafuqs.spectrum.items.*;
import de.dafuqs.spectrum.items.armor.*;
import de.dafuqs.spectrum.items.conditional.CloakedItem;
import de.dafuqs.spectrum.items.conditional.*;
import de.dafuqs.spectrum.items.energy.*;
import de.dafuqs.spectrum.items.food.*;
import de.dafuqs.spectrum.items.food.beverages.*;
import de.dafuqs.spectrum.items.item_frame.*;
import de.dafuqs.spectrum.items.magic_items.*;
import de.dafuqs.spectrum.items.tools.*;
import de.dafuqs.spectrum.items.tooltip.*;
import de.dafuqs.spectrum.items.trinkets.*;
import de.dafuqs.spectrum.particle.*;
import de.dafuqs.spectrum.recipe.*;
import de.dafuqs.spectrum.recipe.enchantment_upgrade.*;
import de.dafuqs.spectrum.recipe.titration_barrel.*;
import de.dafuqs.spectrum.registries.color.*;
import net.fabricmc.fabric.api.item.v1.*;
import net.fabricmc.fabric.api.registry.*;
import net.minecraft.enchantment.*;
import net.minecraft.entity.*;
import net.minecraft.item.*;
import net.minecraft.text.*;
import net.minecraft.util.*;
import net.minecraft.registry.*;

import java.util.*;

import static de.dafuqs.spectrum.registries.SpectrumFluids.*;

public class SpectrumItems {
	
	public enum Tab {
		EQUIPMENT(SpectrumItemGroups.EQUIPMENT),
		FUNCTIONAL(SpectrumItemGroups.FUNCTIONAL),
		CUISINE(SpectrumItemGroups.CUISINE),
		RESOURCES(SpectrumItemGroups.RESOURCES),
		PURE_RESOURCES(SpectrumItemGroups.PURE_RESOURCES),
		BLOCKS(SpectrumItemGroups.BLOCKS),
		DECORATION(SpectrumItemGroups.DECORATION),
		COLORED_WOOD(SpectrumItemGroups.COLORED_WOOD),
		MOB_HEADS(SpectrumItemGroups.MOB_HEADS),
		CREATURES(SpectrumItemGroups.CREATURES),
		ENERGY(SpectrumItemGroups.ENERGY),
		CREATIVE(SpectrumItemGroups.CREATIVE),
		NONE(null);
		
		private final ItemSubGroup subGroup;
		
		Tab(ItemSubGroup subGroup) {
			this.subGroup = subGroup;
		}
		
		public FabricItemSettings settings() {
			if (subGroup == null) {
				return new FabricItemSettings();
			} else {
				return new FabricItemSettings().group(subGroup);
			}
		}
		
		public FabricItemSettings settings(int maxCount) {
			return settings().maxCount(maxCount);
		}
		
		public FabricItemSettings settings(Rarity rarity) {
			return settings().rarity(rarity);
		}
		
		public FabricItemSettings settings(int maxCount, Rarity rarity) {
			return settings().maxCount(maxCount).rarity(rarity);
		}
		
	}
	
	// Main items
	public static final Item GUIDEBOOK = new GuidebookItem(Tab.EQUIPMENT.settings(1));
	public static final Item PAINTBRUSH = new PaintbrushItem(Tab.EQUIPMENT.settings(1));
	public static final Item CRAFTING_TABLET = new CraftingTabletItem(Tab.EQUIPMENT.settings(1));
	
	public static final Item PEDESTAL_TIER_1_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("pedestal_simple_structure_place"));
	public static final Item PEDESTAL_TIER_2_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("pedestal_advanced_structure_place"));
	public static final Item PEDESTAL_TIER_3_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("pedestal_complex_structure_place"));
	public static final Item FUSION_SHRINE_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("fusion_shrine_structure"));
	public static final Item ENCHANTER_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("enchanter_structure"));
	public static final Item SPIRIT_INSTILLER_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("spirit_instiller_structure"));
	public static final Item CINDERHEARTH_STRUCTURE_PLACER = new StructurePlacerItem(Tab.CREATIVE.settings(1), SpectrumCommon.locate("cinderhearth_structure"));
	
	// Gem shards
	public static final Item TOPAZ_SHARD = new Item(Tab.RESOURCES.settings());
	public static final Item CITRINE_SHARD = new Item(Tab.RESOURCES.settings());
	public static final Item ONYX_SHARD = new CloakedItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("collect_all_basic_pigments_besides_brown"), Items.BLACK_DYE);
	public static final Item MOONSTONE_SHARD = new CloakedItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("midgame/break_decayed_bedrock"), Items.WHITE_DYE);
	public static final Item SPECTRAL_SHARD = new CloakedItem(Tab.RESOURCES.settings(Rarity.RARE), SpectrumCommon.locate("lategame/build_complex_pedestal_structure"), Items.LIGHT_GRAY_DYE);
	
	public static final Item TOPAZ_POWDER = new GemstonePowderItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("hidden/collect_shards/collect_topaz_shard"), BuiltinGemstoneColor.CYAN);
	public static final Item AMETHYST_POWDER = new GemstonePowderItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("hidden/collect_shards/collect_amethyst_shard"), BuiltinGemstoneColor.MAGENTA);
	public static final Item CITRINE_POWDER = new GemstonePowderItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("hidden/collect_shards/collect_citrine_shard"), BuiltinGemstoneColor.YELLOW);
	public static final Item ONYX_POWDER = new GemstonePowderItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("create_onyx_shard"), BuiltinGemstoneColor.BLACK);
	public static final Item MOONSTONE_POWDER = new GemstonePowderItem(Tab.RESOURCES.settings(), SpectrumCommon.locate("lategame/collect_moonstone_shard"), BuiltinGemstoneColor.WHITE);
	
	// Pigment
	public static final Item BLACK_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.BLACK);
	public static final Item BLUE_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.BLUE);
	public static final Item BROWN_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.BROWN);
	public static final Item CYAN_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.CYAN);
	public static final Item GRAY_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.GRAY);
	public static final Item GREEN_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.GREEN);
	public static final Item LIGHT_BLUE_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.LIGHT_BLUE);
	public static final Item LIGHT_GRAY_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.LIGHT_GRAY);
	public static final Item LIME_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.LIME);
	public static final Item MAGENTA_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.MAGENTA);
	public static final Item ORANGE_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.ORANGE);
	public static final Item PINK_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.PINK);
	public static final Item PURPLE_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.PURPLE);
	public static final Item RED_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.RED);
	public static final Item WHITE_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.WHITE);
	public static final Item YELLOW_PIGMENT = new PigmentItem(Tab.RESOURCES.settings(), DyeColor.YELLOW);
	
	// Preenchanted tools
	public static final Item MULTITOOL = new PreenchantedMultiToolItem(ToolMaterials.IRON, 2, -2.4F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(ToolMaterials.IRON.getDurability()));
	public static final Item TENDER_PICKAXE = new SpectrumPickaxeItem(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH.getDurability())) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.SILK_TOUCH, 1);
		}
	};
	public static final Item LUCKY_PICKAXE = new SpectrumPickaxeItem(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH.getDurability())) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.FORTUNE, 3);
		}
	};
	public static final Item RAZOR_FALCHION = new RazorFalchionItem(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH, 4, -2.2F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH.getDurability()));
	public static final Item OBLIVION_PICKAXE = new OblivionPickaxeItem(SpectrumToolMaterials.ToolMaterial.VOIDING, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.VOIDING.getDurability()));
	public static final Item RESONANT_PICKAXE = new SpectrumPickaxeItem(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.UNCOMMON).maxDamage(SpectrumToolMaterials.ToolMaterial.LOW_HEALTH.getDurability())) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(SpectrumEnchantments.RESONANCE, 1);
		}
	};
	public static final Item DRAGONRENDING_PICKAXE = new SpectrumPickaxeItem(SpectrumToolMaterials.ToolMaterial.DRACONIC, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.RARE).maxDamage(SpectrumToolMaterials.ToolMaterial.DRACONIC.getDurability())) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(SpectrumEnchantments.RAZING, 1);
		}
	};
	public static final SpectrumFishingRodItem LAGOON_ROD = new LagoonRodItem(Tab.EQUIPMENT.settings().maxDamage(256));
	public static final SpectrumFishingRodItem MOLTEN_ROD = new MoltenRodItem(Tab.EQUIPMENT.settings().maxDamage(256));
	
	// Bedrock Tools
	public static final SpectrumToolMaterials.ToolMaterial BEDROCK_MATERIAL = SpectrumToolMaterials.ToolMaterial.BEDROCK;
	public static final Item BEDROCK_PICKAXE = new BedrockPickaxeItem(BEDROCK_MATERIAL, 1, -2.8F, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_AXE = new BedrockAxeItem(BEDROCK_MATERIAL, 5, -3.0F, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_SHOVEL = new BedrockShovelItem(BEDROCK_MATERIAL, 1, -3.0F, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_SWORD = new BedrockSwordItem(BEDROCK_MATERIAL, 4, -2.4F, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_HOE = new BedrockHoeItem(BEDROCK_MATERIAL, -2, -0.0F, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_BOW = new BedrockBowItem(Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_CROSSBOW = new BedrockCrossbowItem(Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_SHEARS = new BedrockShearsItem(Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));
	public static final Item BEDROCK_FISHING_ROD = new BedrockFishingRodItem(Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(SpectrumToolMaterials.ToolMaterial.BEDROCK.getDurability()));

	public static final SpectrumToolMaterials.ToolMaterial MALACHITE = SpectrumToolMaterials.ToolMaterial.MALACHITE;
	public static final Item MALACHITE_WORKSTAFF = new WorkstaffItem(MALACHITE, 1, -3.2F, Tab.EQUIPMENT.settings(1, Rarity.RARE));
	public static final Item MALACHITE_ULTRA_GREATSWORD = new GreatswordItem(MALACHITE, 4, -3.0F, 1.0F, Tab.EQUIPMENT.settings(1, Rarity.RARE));
	public static final Item MALACHITE_CROSSBOW = new MalachiteCrossbowItem(Tab.EQUIPMENT.settings(1, Rarity.RARE).fireproof().maxDamage(MALACHITE.getDurability()));
	public static final Item MALACHITE_BIDENT = new MalachiteBidentItem(Tab.EQUIPMENT.settings(1, Rarity.RARE).maxDamage(MALACHITE.getDurability()));
	
	// variants by socketing a moonstone core
	public static final SpectrumToolMaterials.ToolMaterial GLASS_CREST = SpectrumToolMaterials.ToolMaterial.GLASS_CREST;
	public static final Item GLASS_CREST_WORKSTAFF = new GlassCrestWorkstaffItem(GLASS_CREST, 1, -2.8F, Tab.EQUIPMENT.settings(1, Rarity.RARE));
	public static final Item GLASS_CREST_ULTRA_GREATSWORD = new GlassCrestGreatswordItem(GLASS_CREST, 4, -2.8F, 1.0F, Tab.EQUIPMENT.settings(1, Rarity.RARE));
	public static final Item GLASS_CREST_CROSSBOW = new GlassCrestCrossbowItem(Tab.EQUIPMENT.settings(1, Rarity.RARE).fireproof().maxDamage(GLASS_CREST.getDurability()));
	public static final Item FEROCIOUS_GLASS_CREST_BIDENT = new FerociousBidentItem(Tab.EQUIPMENT.settings(1, Rarity.RARE).maxDamage(GLASS_CREST.getDurability()));
	public static final Item FRACTAL_GLASS_CREST_BIDENT = new FractalBidentItem(Tab.EQUIPMENT.settings(1, Rarity.RARE).maxDamage(GLASS_CREST.getDurability()));
	
	public static final Item MALACHITE_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.MALACHITE, SpectrumParticleTypes.LIME_CRAFTING);
	public static final Item TOPAZ_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.TOPAZ, SpectrumParticleTypes.CYAN_CRAFTING);
	public static final Item AMETHYST_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.AMETHYST, SpectrumParticleTypes.MAGENTA_CRAFTING);
	public static final Item CITRINE_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.CITRINE, SpectrumParticleTypes.YELLOW_CRAFTING);
	public static final Item ONYX_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.ONYX, SpectrumParticleTypes.BLACK_CRAFTING);
	public static final Item MOONSTONE_GLASS_ARROW = new GlassArrowItem(Tab.EQUIPMENT.settings(Rarity.RARE), GlassArrowVariant.MOONSTONE, SpectrumParticleTypes.WHITE_CRAFTING);
	
	public static final Item GLASS_AMPOULE = new GlassAmpouleItem(Tab.EQUIPMENT.settings(Rarity.RARE).maxCount(8));
	public static final Item FRACTAL_GLASS_AMPOULE = new FractalGlassAmpouleItem(Tab.EQUIPMENT.settings(Rarity.RARE).maxCount(8));
	public static final Item FEROCIOUS_GLASS_AMPOULE = new FerociousGlassAmpouleItem(Tab.EQUIPMENT.settings(Rarity.RARE).maxCount(8));
	public static final Item CRYSTALLIZED_DRAGON_FANG = new CrystallizedDragonFangItem(Tab.EQUIPMENT.settings(Rarity.RARE).maxCount(8));

	
	// Special tools
	public static final Item DREAMFLAYER = new DreamflayerItem(SpectrumToolMaterials.ToolMaterial.DREAMFLAYER, 3, -1.8F, Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item NIGHTFALLS_BLADE = new NightfallsBladeItem(SpectrumToolMaterials.ToolMaterial.NIGHTFALL, 0, -3.4F, Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	
	// Bedrock Armor
	public static final Item BEDROCK_HELMET = new BedrockArmorItem(SpectrumArmorMaterials.BEDROCK, ArmorItem.Type.HELMET, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(-1)) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.PROJECTILE_PROTECTION, 5);
		}
	};
	public static final Item BEDROCK_CHESTPLATE = new BedrockArmorItem(SpectrumArmorMaterials.BEDROCK, ArmorItem.Type.CHESTPLATE, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(-1)) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.PROTECTION, 5);
		}
	};
	public static final Item BEDROCK_LEGGINGS = new BedrockArmorItem(SpectrumArmorMaterials.BEDROCK, ArmorItem.Type.LEGGINGS, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(-1)) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.BLAST_PROTECTION, 5);
		}
	};
	public static final Item BEDROCK_BOOTS = new BedrockArmorItem(SpectrumArmorMaterials.BEDROCK, ArmorItem.Type.BOOTS, Tab.EQUIPMENT.settings(Rarity.RARE).fireproof().maxDamage(-1)) {
		@Override
		public Map<Enchantment, Integer> getDefaultEnchantments() {
			return Map.of(Enchantments.FIRE_PROTECTION, 5);
		}
	};
	
	// Armor
	public static final Item FETCHLING_HELMET = new GemstoneArmorItem(SpectrumArmorMaterials.GEMSTONE, ArmorItem.Type.HELMET, Tab.EQUIPMENT.settings(Rarity.RARE).maxDamage(SpectrumArmorMaterials.GEMSTONE.getDurability(ArmorItem.Type.HELMET)));
	public static final Item FEROCIOUS_CHESTPLATE = new GemstoneArmorItem(SpectrumArmorMaterials.GEMSTONE, ArmorItem.Type.CHESTPLATE, Tab.EQUIPMENT.settings(Rarity.RARE).maxDamage(SpectrumArmorMaterials.GEMSTONE.getDurability(ArmorItem.Type.CHESTPLATE)));
	public static final Item SYLPH_LEGGINGS = new GemstoneArmorItem(SpectrumArmorMaterials.GEMSTONE, ArmorItem.Type.LEGGINGS, Tab.EQUIPMENT.settings(Rarity.RARE).maxDamage(SpectrumArmorMaterials.GEMSTONE.getDurability(ArmorItem.Type.LEGGINGS)));
	public static final Item OREAD_BOOTS = new GemstoneArmorItem(SpectrumArmorMaterials.GEMSTONE, ArmorItem.Type.BOOTS, Tab.EQUIPMENT.settings(Rarity.RARE).maxDamage(SpectrumArmorMaterials.GEMSTONE.getDurability(ArmorItem.Type.BOOTS)));
	
	// Decay drops
	public static final Item VEGETAL = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(), SpectrumCommon.locate("craft_bottle_of_fading"), Items.GUNPOWDER, SpectrumBannerPatterns.VEGETAL);
	public static final Item NEOLITH = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/craft_bottle_of_failing"), Items.GUNPOWDER, SpectrumBannerPatterns.NEOLITH);
	public static final Item BEDROCK_DUST = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/break_decayed_bedrock"), Items.GUNPOWDER, SpectrumBannerPatterns.BEDROCK_DUST);
	
	public static final MidnightAberrationItem MIDNIGHT_ABERRATION = new MidnightAberrationItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/create_midnight_aberration"), SpectrumItems.SPECTRAL_SHARD);
	public static final Item MIDNIGHT_CHIP = new CloakedItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/create_midnight_aberration"), Items.GRAY_DYE);
	
	public static final Item BISMUTH_FLAKE = new CloakedItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/enter_dimension"), Items.CYAN_DYE);
	public static final Item BISMUTH_CRYSTAL = new CloakedItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("midgame/enter_dimension"), Items.CYAN_DYE);
	
	public static final Item RAW_MALACHITE = new CloakedItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("milestones/reveal_malachite"), Items.GREEN_DYE);
	public static final Item MALACHITE_CRYSTAL = new CloakedItem(Tab.RESOURCES.settings(Rarity.UNCOMMON), SpectrumCommon.locate("milestones/reveal_malachite"), Items.GREEN_DYE);
	
	// Fluid Buckets
	public static final Item LIQUID_CRYSTAL_BUCKET = new BucketItem(LIQUID_CRYSTAL, Tab.RESOURCES.settings(1).recipeRemainder(Items.BUCKET));
	public static final Item MUD_BUCKET = new BucketItem(MUD, Tab.RESOURCES.settings(1).recipeRemainder(Items.BUCKET));
	public static final Item MIDNIGHT_SOLUTION_BUCKET = new BucketItem(MIDNIGHT_SOLUTION, Tab.RESOURCES.settings(1).recipeRemainder(Items.BUCKET));
	public static final Item DRAGONROT_BUCKET = new BucketItem(DRAGONROT, Tab.RESOURCES.settings(1).recipeRemainder(Items.BUCKET));
	
	// Decay bottles
	public static final Item BOTTLE_OF_FADING = new DecayPlacerItem(SpectrumBlocks.FADING, Tab.EQUIPMENT.settings(16), List.of(Text.translatable("item.spectrum.bottle_of_fading.tooltip")));
	public static final Item BOTTLE_OF_FAILING = new DecayPlacerItem(SpectrumBlocks.FAILING, Tab.EQUIPMENT.settings(16), List.of(Text.translatable("item.spectrum.bottle_of_failing.tooltip")));
	public static final Item BOTTLE_OF_RUIN = new DecayPlacerItem(SpectrumBlocks.RUIN, Tab.EQUIPMENT.settings(16), List.of(Text.translatable("item.spectrum.bottle_of_ruin.tooltip")));
	public static final Item BOTTLE_OF_FORFEITURE = new DecayPlacerItem(SpectrumBlocks.FORFEITURE, Tab.EQUIPMENT.settings(16), List.of(CreativeOnlyItem.DESCRIPTION, Text.translatable("item.spectrum.bottle_of_forfeiture.tooltip")));
	public static final Item BOTTLE_OF_DECAY_AWAY = new DecayPlacerItem(SpectrumBlocks.DECAY_AWAY, Tab.EQUIPMENT.settings(16), List.of(Text.translatable("item.spectrum.bottle_of_decay_away.tooltip")));
	
	// Resources
	public static final Item SHIMMERSTONE_GEM = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(), ((RevelationAware) SpectrumBlocks.SHIMMERSTONE_ORE).getCloakAdvancementIdentifier(), Items.YELLOW_DYE, SpectrumBannerPatterns.SHIMMERSTONE);
	public static final Item RAW_AZURITE = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(), ((RevelationAware) SpectrumBlocks.AZURITE_ORE).getCloakAdvancementIdentifier(), Items.BLUE_DYE, SpectrumBannerPatterns.RAW_AZURITE);
	public static final Item REFINED_AZURITE = new CloakedItem(Tab.RESOURCES.settings(), ((RevelationAware) SpectrumBlocks.AZURITE_ORE).getCloakAdvancementIdentifier(), Items.BLUE_DYE);
	public static final CloakedFloatItem STRATINE_FRAGMENTS = new CloakedFloatItem(Tab.RESOURCES.settings(64).fireproof(), 1.003F, ((RevelationAware) SpectrumBlocks.STRATINE_ORE).getCloakAdvancementIdentifier(), Items.RED_DYE);
	public static final CloakedFloatItem STRATINE_GEM = new CloakedFloatItem(Tab.RESOURCES.settings(16).fireproof(), 1.02F, ((RevelationAware) SpectrumBlocks.STRATINE_ORE).getCloakAdvancementIdentifier(), Items.RED_DYE);
	public static final CloakedFloatItem PALTAERIA_FRAGMENTS = new CloakedFloatItem(Tab.RESOURCES.settings(), 0.997F, ((RevelationAware) SpectrumBlocks.PALTAERIA_ORE).getCloakAdvancementIdentifier(), Items.CYAN_DYE);
	public static final CloakedFloatItem PALTAERIA_GEM = new CloakedFloatItem(Tab.RESOURCES.settings(16), 0.98F, ((RevelationAware) SpectrumBlocks.PALTAERIA_ORE).getCloakAdvancementIdentifier(), Items.CYAN_DYE);
	public static final Item PYRITE_CHUNK = new Item(Tab.RESOURCES.settings());
	public static final Item DRAGONBONE_CHUNK = new Item(Tab.RESOURCES.settings(Rarity.UNCOMMON));
	public static final Item EFFULGENT_FEATHER = new Item(Tab.RESOURCES.settings(Rarity.UNCOMMON));
	public static final Item REFINED_BLOODSTONE = new Item(Tab.RESOURCES.settings(Rarity.UNCOMMON));
	public static final Item DOWNSTONE_FRAGMENTS = new Item(Tab.RESOURCES.settings(16, Rarity.UNCOMMON));
	public static final Item RESONANCE_SHARD = new Item(Tab.RESOURCES.settings(16, Rarity.UNCOMMON));
	
	public static final Item QUITOXIC_POWDER = new CloakedItem(Tab.RESOURCES.settings(), ((RevelationAware) SpectrumBlocks.QUITOXIC_REEDS).getCloakAdvancementIdentifier(), Items.PURPLE_DYE);
	public static final Item STORM_STONE = new StormStoneItem(Tab.RESOURCES.settings(16), ((RevelationAware) SpectrumBlocks.STUCK_STORM_STONE).getCloakAdvancementIdentifier(), Items.YELLOW_DYE);
	public static final Item MERMAIDS_GEM = new MermaidsGemItem(SpectrumBlocks.MERMAIDS_BRUSH, Tab.RESOURCES.settings(16));
	public static final CloakedItem STAR_FRAGMENT = new CloakedItem(Tab.RESOURCES.settings(16), SpectrumCommon.locate("milestones/unlock_shooting_stars"), Items.PURPLE_DYE);
	public static final Item STARDUST = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(), SpectrumCommon.locate("milestones/unlock_shooting_stars"), Items.PURPLE_DYE, SpectrumBannerPatterns.SHIMMER);
	public static final ItemWithTooltip FRIGID_STARDUST = new ItemWithTooltip(Tab.RESOURCES.settings(), "item.spectrum.frigid_stardust.tooltip");
	
	public static final Item HIBERNATING_JADE_VINE_BULB = new ItemWithTooltip(Tab.RESOURCES.settings(16), "item.spectrum.hibernating_jade_vine_seeds.tooltip");
	public static final Item GERMINATED_JADE_VINE_BULB = new GerminatedJadeVineBulbItem(Tab.RESOURCES.settings(16), SpectrumCommon.locate("hidden/collect_hibernating_jade_vine_seeds"), Items.LIME_DYE);
	public static final Item JADE_VINE_PETALS = new CloakedItemWithLoomPattern(Tab.RESOURCES.settings(), SpectrumCommon.locate("midgame/build_spirit_instiller_structure"), Items.LIME_DYE, SpectrumBannerPatterns.JADE_VINE);
	
	public static final Item NEPHRITE_BLOSSOM_BULB = new BlockItemWithTooltip(SpectrumBlocks.NEPHRITE_BLOSSOM_BULB, Tab.RESOURCES.settings(16), "item.spectrum.jade_vine_parents.tooltip");
	
	public static final Item JADEITE_LOTUS_BULB = new BlockItemWithTooltip(SpectrumBlocks.JADEITE_LOTUS_BULB, Tab.RESOURCES.settings(16), "item.spectrum.jade_vine_parents.tooltip");
	public static final Item JADEITE_PETALS = new Item(Tab.RESOURCES.settings(Rarity.UNCOMMON));
	
	
	public static final Item BLOOD_ORCHID_PETAL = new Item(Tab.RESOURCES.settings());
	
	public static final Item ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.ROCK_CANDY), RockCandy.RockCandyVariant.SUGAR);
	public static final Item TOPAZ_ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.TOPAZ_ROCK_CANDY), RockCandy.RockCandyVariant.TOPAZ);
	public static final Item AMETHYST_ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.AMETHYST_ROCK_CANDY), RockCandy.RockCandyVariant.AMETHYST);
	public static final Item CITRINE_ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.CITRINE_ROCK_CANDY), RockCandy.RockCandyVariant.CITRINE);
	public static final Item ONYX_ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.ONYX_ROCK_CANDY), RockCandy.RockCandyVariant.ONYX);
	public static final Item MOONSTONE_ROCK_CANDY = new RockCandyItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.MOONSTONE_ROCK_CANDY), RockCandy.RockCandyVariant.MOONSTONE);
	
	public static final Item BLOODBOIL_SYRUP = new DrinkItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.BLOODBOIL_SYRUP).recipeRemainder(Items.GLASS_BOTTLE));
	
	// Food & drinks
	public static final Item MOONSTRUCK_NECTAR = new MoonstruckNectarItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.MOONSTRUCK_NECTAR).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item JADE_JELLY = new ItemWithTooltip(Tab.CUISINE.settings(Rarity.UNCOMMON).food(SpectrumFoodComponents.JADE_JELLY), "item.spectrum.jade_jelly.tooltip");
	public static final Item GLASS_PEACH = new ItemWithTooltip(Tab.CUISINE.settings(Rarity.UNCOMMON).food(SpectrumFoodComponents.GLASS_PEACH), "item.spectrum.glass_peach.tooltip");
	public static final Item RESTORATION_TEA = new RestorationTeaItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.RESTORATION_TEA).recipeRemainder(Items.GLASS_BOTTLE), SpectrumFoodComponents.RESTORATION_TEA_SCONE_BONUS);
	public static final Item KIMCHI = new KimchiItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.KIMCHI));
	public static final Item CLOTTED_CREAM = new ClottedCreamItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.CLOTTED_CREAM), new String[]{"item.spectrum.clotted_cream.tooltip", "item.spectrum.clotted_cream.tooltip2"});
	public static final Item FRESH_CHOCOLATE = new CustomUseTimeItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.FRESH_CHOCOLATE), 10);
	public static final Item HOT_CHOCOLATE = new TeaItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.HOT_CHOCOLATE), SpectrumFoodComponents.HOT_CHOCOLATE_SCONE_BONUS);
	public static final Item BOCACIOUS_BERRY_BAR = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.BOCACIOUS_BERRY_BAR));
	public static final Item DEMON_TEA = new TeaItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.DEMON_TEA), SpectrumFoodComponents.DEMON_TEA_SCONE_BONUS);
	public static final Item SCONE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SCONE));

	public static final Item INFUSED_BEVERAGE = new InfusedBeverageItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.BEVERAGE).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item SUSPICIOUS_BREW = new SuspiciousBrewItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.BEVERAGE).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item REPRISE = new RepriseItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.BEVERAGE).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item PURE_ALCOHOL = new PureAlcoholItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.PURE_ALCOHOL).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item JADE_WINE = new JadeWineItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.BEVERAGE).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item CHRYSOCOLLA = new PureAlcoholItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.PURE_ALCOHOL).recipeRemainder(Items.GLASS_BOTTLE));

	public static final Item HONEY_PASTRY = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.HONEY_PASTRY));
	public static final Item LUCKY_ROLL = new Item(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.LUCKY_ROLL));
	public static final Item TRIPLE_MEAT_POT_PIE = new CustomUseTimeItem(Tab.CUISINE.settings(8).food(SpectrumFoodComponents.TRIPLE_MEAT_POT_PIE), 96);
	public static final Item GLISTERING_JELLY_TEA = new TeaItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.GLISTERING_JELLY_TEA).recipeRemainder(Items.GLASS_BOTTLE), SpectrumFoodComponents.GLISTERING_JELLY_TEA_SCONE_BONUS);
	public static final Item FREIGEIST = new FreigeistItem(Tab.CUISINE.settings(16).food(SpectrumFoodComponents.FREIGEIST).recipeRemainder(Items.GLASS_BOTTLE));
	public static final Item DIVINATION_HEART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.DIVINATION_HEART));

	public static final Item STAR_CANDY = new StarCandyItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.STAR_CANDY));
	public static final Item PURPLE_STAR_CANDY = new EnchantedStarCandyItem(Tab.CUISINE.settings(16, Rarity.UNCOMMON).food(SpectrumFoodComponents.PURPLE_STAR_CANDY));
	
	public static final Item JARAMEL = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.JARAMEL));
	
	public static final Item JARAMEL_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.JARAMEL_TART));
	public static final Item SALTED_JARAMEL_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SALTED_JARAMEL_TART));
	public static final Item ASHEN_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.ASHEN_TART));
	public static final Item WEEPING_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.WEEPING_TART));
	public static final Item WHISPY_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.WHISPY_TART));
	public static final Item PUFF_TART = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.PUFF_TART));

	public static final Item JARAMEL_TRIFLE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.JARAMEL_TRIFLE));
	public static final Item SALTED_JARAMEL_TRIFLE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SALTED_JARAMEL_TRIFLE));
	public static final Item MONSTER_TRIFLE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MONSTER_TRIFLE));
	public static final Item DEMON_TRIFLE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.DEMON_TRIFLE));

	public static final Item MYCEYLON = new Item(Tab.CUISINE.settings());
	public static final Item MYCEYLON_APPLE_PIE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MYCEYLON_APPLE_PIE));
	public static final Item MYCEYLON_PUMPKIN_PIE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MYCEYLON_PUMPKIN_PIE));
	public static final Item MYCEYLON_COOKIE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MYCEYLON_COOKIE));
	public static final Item ALOE_LEAF = new AliasedBlockItem(SpectrumBlocks.ALOE, Tab.CUISINE.settings().food(SpectrumFoodComponents.ALOE_LEAF));
	public static final Item SAWBLADE_HOLLY_BERRY = new AliasedBlockItem(SpectrumBlocks.SAWBLADE_HOLLY_BUSH, Tab.CUISINE.settings().food(FoodComponents.SWEET_BERRIES));
	public static final Item PRICKLY_BAYLEAF = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.PRICKLY_BAYLEAF));
	public static final Item TRIPLE_MEAT_POT_STEW = new StewItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.TRIPLE_MEAT_POT_STEW));
	public static final Item DRAGONBONE_BROTH = new StewItem(Tab.CUISINE.settings().food(SpectrumFoodComponents.DRAGONBONE_BROTH));
	public static final Item DOOMBLOOM_SEED = new AliasedBlockItem(SpectrumBlocks.DOOMBLOOM, Tab.RESOURCES.settings());
	
	public static final Item GLISTERING_MELON_SEEDS = new AliasedBlockItem(SpectrumBlocks.GLISTERING_MELON_STEM, Tab.RESOURCES.settings());
	public static final Item AMARANTH_GRAINS = new AliasedBlockItem(SpectrumBlocks.AMARANTH, Tab.RESOURCES.settings());

	public static final Item MELOCHITES_COOKBOOK_VOL_1 = new CookbookItem(Tab.CUISINE.settings().maxCount(1), "cuisine/melochites_cookbook_vol_1");
	public static final Item MELOCHITES_COOKBOOK_VOL_2 = new CookbookItem(Tab.CUISINE.settings().maxCount(1), "cuisine/melochites_cookbook_vol_2");
	public static final Item IMBRIFER_COOKBOOK = new CookbookItem(Tab.CUISINE.settings().maxCount(1), "cuisine/imbrifer_cookbook");
	public static final Item IMPERIAL_COOKBOOK = new CookbookItem(Tab.CUISINE.settings().maxCount(1), "cuisine/imperial_cookbook");
	public static final Item BREWERS_HANDBOOK = new CookbookItem(Tab.CUISINE.settings().maxCount(1), "cuisine/brewers_handbook");

	public static final Item AQUA_REGIA = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.AQUA_REGIA));
	public static final Item BAGNUN = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.BAGNUN));
	public static final Item BANYASH = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.BANYASH));
	public static final Item BERLINER = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.BERLINER));
	public static final Item BRISTLE_MEAD = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.BRISTLE_MEAD));
	public static final Item CHAUVE_SOURIS_AU_VIN = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.CHAUVE_SOURIS_AU_VIN));
	public static final Item CRAWFISH = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.CRAWFISH));
	public static final Item CRAWFISH_COCKTAIL = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.CRAWFISH_COCKTAIL));
	public static final Item CREAM_PASTRY = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.CREAM_PASTRY));
	public static final Item FADED_KOI = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.FADED_KOI));
	public static final Item FISHCAKE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.FISHCAKE));
	public static final Item LIZARD_MEAT = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.LIZARD_MEAT));
	public static final Item GOLDEN_BRISTLE_TEA = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.GOLDEN_BRISTLE_TEA));
	public static final Item HARE_ROAST = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.GOLDEN_BRISTLE_TEA));
	public static final Item JUNKET = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.JUNKET));
	public static final Item KOI_FISH = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.KOI_FISH));
	public static final Item MEATLOAF = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MEATLOAF));
	public static final Item MEATLOAF_SANDWICH = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MEATLOAF_SANDWICH));
	public static final Item MELLOW_SHALLOT_SOUP = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MELLOW_SHALLOT_SOUP));
	public static final Item MORCHELLA = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.MORCHELLA));
	public static final Item NECTERED_VIOGNIER = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.NECTERED_VIOGNIER));
	public static final Item PEACHES_FLAMBE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.PEACHES_FLAMBE));
	public static final Item PEACH_CREAM = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.PEACH_CREAM));
	public static final Item PEACH_JAM = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.PEACH_JAM));
	public static final Item RABBIT_CREAM_PIE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.RABBIT_CREAM_PIE));
	public static final Item SEDATIVES = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SEDATIVES));
	public static final Item SLUSHSLIDE = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SLUSHSLIDE));
	public static final Item SURSTROMMING = new Item(Tab.CUISINE.settings().food(SpectrumFoodComponents.SURSTROMMING));

	// Banner Patterns
	public static final Item LOGO_BANNER_PATTERN = new SpectrumBannerPatternItem(Tab.DECORATION.settings(1, Rarity.RARE), SpectrumBannerPatterns.SPECTRUM_LOGO_TAG, "item.spectrum.logo_banner_pattern.desc");
	public static final Item AMETHYST_SHARD_BANNER_PATTERN = new SpectrumBannerPatternItem(Tab.DECORATION.settings(1), SpectrumBannerPatterns.AMETHYST_SHARD_TAG, "item.minecraft.amethyst_shard");
	public static final Item AMETHYST_CLUSTER_BANNER_PATTERN = new SpectrumBannerPatternItem(Tab.DECORATION.settings(1), SpectrumBannerPatterns.AMETHYST_CLUSTER_TAG, "block.minecraft.amethyst_cluster");

	public static final Item EGG_LAYING_WOOLY_PIG_SPAWN_EGG = new SpawnEggItem(SpectrumEntityTypes.EGG_LAYING_WOOLY_PIG, 0x4e3842, 0xffe6c2, Tab.CREATURES.settings()); // TODO: colors
	public static final Item GUARDIAN_TURRET_SPAWN_EGG = new SpawnEggItem(SpectrumEntityTypes.GUARDIAN_TURRET, 0x4e3842, 0xffe6c2, Tab.CREATURES.settings()); // TODO: colors
	public static final Item KINDLING_SPAWN_EGG = new SpawnEggItem(SpectrumEntityTypes.KINDLING, 0x4e3842, 0xffe6c2, Tab.CREATURES.settings()); // TODO: colors
	public static final Item LIZARD_SPAWN_EGG = new SpawnEggItem(SpectrumEntityTypes.LIZARD, 0x4e3842, 0xffe6c2, Tab.CREATURES.settings()); // TODO: colors
	
	// Magical Tools
	public static final Item BAG_OF_HOLDING = new BagOfHoldingItem(Tab.EQUIPMENT.settings(1));
	public static final Item RADIANCE_STAFF = new RadianceStaffItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item NATURES_STAFF = new NaturesStaffItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item HERDING_STAFF = new HerdingStaffItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item CONSTRUCTORS_STAFF = new ConstructorsStaffItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item EXCHANGING_STAFF = new ExchangeStaffItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item BLOCK_FLOODER = new BlockFlooderItem(Tab.EQUIPMENT.settings(Rarity.UNCOMMON));
	public static final EnderSpliceItem ENDER_SPLICE = new EnderSpliceItem(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item PERTURBED_EYE = new PerturbedEyeItem(Tab.EQUIPMENT.settings(Rarity.RARE));
	public static final Item CRESCENT_CLOCK = new Item(Tab.EQUIPMENT.settings(1));

	public static final Item FIERY_POWDER = new Item(Tab.RESOURCES.settings());
	public static final Item BLIZZARD_POWDER = new Item(Tab.RESOURCES.settings());
	public static final Item BONE_ASH = new Item(Tab.RESOURCES.settings());
	public static final Item MOONSTONE_CORE = new Item(Tab.RESOURCES.settings(8, Rarity.RARE));

	// Catkin
	public static final Item VIBRANT_CYAN_CATKIN = new CatkinItem(BuiltinGemstoneColor.CYAN, false, Tab.NONE.settings());
	public static final Item VIBRANT_MAGENTA_CATKIN = new CatkinItem(BuiltinGemstoneColor.MAGENTA, false, Tab.NONE.settings());
	public static final Item VIBRANT_YELLOW_CATKIN = new CatkinItem(BuiltinGemstoneColor.YELLOW, false, Tab.NONE.settings());
	public static final Item VIBRANT_BLACK_CATKIN = new CatkinItem(BuiltinGemstoneColor.BLACK, false, Tab.NONE.settings());
	public static final Item VIBRANT_WHITE_CATKIN = new CatkinItem(BuiltinGemstoneColor.WHITE, false, Tab.NONE.settings());
	
	public static final Item LUCID_CYAN_CATKIN = new CatkinItem(BuiltinGemstoneColor.CYAN, true, Tab.NONE.settings(Rarity.UNCOMMON));
	public static final Item LUCID_MAGENTA_CATKIN = new CatkinItem(BuiltinGemstoneColor.MAGENTA, true, Tab.NONE.settings(Rarity.UNCOMMON));
	public static final Item LUCID_YELLOW_CATKIN = new CatkinItem(BuiltinGemstoneColor.YELLOW, true, Tab.NONE.settings(Rarity.UNCOMMON));
	public static final Item LUCID_BLACK_CATKIN = new CatkinItem(BuiltinGemstoneColor.BLACK, true, Tab.NONE.settings(Rarity.UNCOMMON));
	public static final Item LUCID_WHITE_CATKIN = new CatkinItem(BuiltinGemstoneColor.WHITE, true, Tab.NONE.settings(Rarity.UNCOMMON));
	
	// Misc
	public static final Item MUSIC_DISC_SPECTRUM_THEME = new SpectrumMusicDiscItem(1, SpectrumSoundEvents.SPECTRUM_THEME, Tab.DECORATION.settings(1, Rarity.RARE), 120);
	public static final Item MUSIC_DISC_DIMENSION_THEME = new SpectrumMusicDiscItem(2, SpectrumSoundEvents.BOSS_THEME, Tab.DECORATION.settings(1, Rarity.RARE), 265);
	public static final Item MUSIC_DISC_EVERREFLECTIVE = new SpectrumMusicDiscItem(3, SpectrumSoundEvents.DIVINITY, Tab.DECORATION.settings(1, Rarity.RARE), 289);
	
	public static final Item SPAWNER = new SpectrumMobSpawnerItem(Tab.CREATURES.settings(1, Rarity.EPIC));
	public static final Item PHANTOM_FRAME = new PhantomFrameItem(SpectrumEntityTypes.PHANTOM_FRAME, Tab.DECORATION.settings());
	public static final Item GLOW_PHANTOM_FRAME = new PhantomGlowFrameItem(SpectrumEntityTypes.GLOW_PHANTOM_FRAME, Tab.DECORATION.settings());
	
	public static final Item BOTTOMLESS_BUNDLE = new BottomlessBundleItem(Tab.EQUIPMENT.settings(1));
	public static final Item KNOWLEDGE_GEM = new KnowledgeGemItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON), 10000);
	public static final Item CELESTIAL_POCKETWATCH = new CelestialPocketWatchItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item GILDED_BOOK = new GildedBookItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item ENCHANTMENT_CANVAS = new EnchantmentCanvasItem(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item EVERPROMISE_RIBBON = new EverpromiseRibbonItem(Tab.EQUIPMENT.settings());
	
	// Lore
	public static final Item MYSTERIOUS_LOCKET = new MysteriousLocketItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item MYSTERIOUS_COMPASS = new StructureCompassItem(Tab.EQUIPMENT.settings(1, Rarity.RARE), SpectrumStructureTags.MYSTERIOUS_COMPASS_LOCATED);
	
	// Trinkets
	public static final Item FANCIFUL_BELT = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item FANCIFUL_PENDANT = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item FANCIFUL_STONE_RING = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item FANCIFUL_CIRCLET = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item FANCIFUL_GLOVES = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	public static final Item FANCIFUL_BISMUTH_RING = new Item(Tab.EQUIPMENT.settings(16, Rarity.UNCOMMON));
	
	public static final Item GLOW_VISION_GOGGLES = new GlowVisionGogglesItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item JEOPARDANT = new AttackRingItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item SEVEN_LEAGUE_BOOTS = new SevenLeagueBootsItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item COTTON_CLOUD_BOOTS = new CottonCloudBootsItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item RADIANCE_PIN = new RadiancePinItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item TOTEM_PENDANT = new TotemPendantItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item TAKE_OFF_BELT = new TakeOffBeltItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item AZURE_DIKE_BELT = new AzureDikeBeltItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item AZURE_DIKE_RING = new AzureDikeRingItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final AzureDikeAmuletItem SHIELDGRASP_AMULET = new AzureDikeAmuletItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final ExtraHealthRingItem HEARTSINGERS_REWARD_RING = new ExtraHealthRingItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final ExtraReachGlovesItem GLOVES_OF_DAWNS_GRASP = new ExtraReachGlovesItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final ExtraMiningSpeedRingItem RING_OF_PURSUIT = new ExtraMiningSpeedRingItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	
	public static final InkFlaskItem INK_FLASK = new InkFlaskItem(Tab.ENERGY.settings(1), 64 * 64 * 100); // 64 stacks of pigments (1 pigment => 100 energy)
	public static final InkAssortmentItem INK_ASSORTMENT = new InkAssortmentItem(Tab.ENERGY.settings(1), 64 * 100);
	public static final PigmentPaletteItem PIGMENT_PALETTE = new PigmentPaletteItem(Tab.ENERGY.settings(1, Rarity.UNCOMMON), 64 * 64 * 100);
	public static final ArtistsPaletteItem ARTISTS_PALETTE = new ArtistsPaletteItem(Tab.ENERGY.settings(1, Rarity.UNCOMMON), 64 * 64 * 64 * 64 * 100);
	public static final CreativeInkAssortmentItem CREATIVE_INK_ASSORTMENT = new CreativeInkAssortmentItem(Tab.ENERGY.settings(1, Rarity.EPIC));
	
	public static final Item GLEAMING_PIN = new GleamingPinItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item LESSER_POTION_PENDANT = new PotionPendantItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON), 1, SpectrumCommon.CONFIG.MaxLevelForEffectsInLesserPotionPendant - 1, SpectrumCommon.locate("unlocks/trinkets/lesser_potion_pendant"));
	public static final Item GREATER_POTION_PENDANT = new PotionPendantItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON), 3, SpectrumCommon.CONFIG.MaxLevelForEffectsInGreaterPotionPendant - 1, SpectrumCommon.locate("unlocks/trinkets/greater_potion_pendant"));
	public static final Item ASHEN_CIRCLET = new AshenCircletItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON).fireproof());
	public static final Item WEEPING_CIRCLET = new WeepingCircletItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item PUFF_CIRCLET = new PuffCircletItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item WHISPY_CIRCLET = new WhispyCircletItem(Tab.EQUIPMENT.settings(1, Rarity.UNCOMMON));
	public static final Item CIRCLET_OF_ARROGANCE = new CircletOfArroganceItem(Tab.EQUIPMENT.settings(1, Rarity.RARE));
	public static final Item NEAT_RING = new NeatRingItem(Tab.EQUIPMENT.settings(1, Rarity.RARE));
	
	// Pure Clusters
	public static final Item PURE_EMERALD = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_PRISMARINE = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_COAL = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_REDSTONE = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_GLOWSTONE = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_LAPIS = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_COPPER = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_QUARTZ = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_GOLD = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_DIAMOND = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_IRON = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_NETHERITE_SCRAP = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_ECHO = new Item(Tab.PURE_RESOURCES.settings());
	public static final Item PURE_NETHERITE = new Item(Tab.PURE_RESOURCES.settings());
	
	public static void register(String name, Item item, DyeColor dyeColor) {
		Registry.register(Registries.ITEM, SpectrumCommon.locate(name), item);
		ItemColors.ITEM_COLORS.registerColorMapping(item, dyeColor);
	}
	
	public static void register() {
		register("guidebook", GUIDEBOOK, DyeColor.WHITE);
		register("paintbrush", PAINTBRUSH, DyeColor.WHITE);
		
		registerGemstoneItems();
		registerPigments();
		registerCatkin();
		registerResources();
		registerDecayBottles();
		registerPreEnchantedTools();
		registerConsumables();
		registerInkStorage();
		registerTrinkets();
		registerMagicalTools();
		registerFluidBuckets();
		registerBannerPatterns();
		registerPureClusters();
		registerStructurePlacers();
		registerSpawningStuff();
		registerMusicDisks();
	}

	public static void registerMusicDisks() {
		register("music_disc_spectrum_theme", MUSIC_DISC_SPECTRUM_THEME, DyeColor.GREEN);
		register("music_disc_dimension_theme", MUSIC_DISC_DIMENSION_THEME, DyeColor.GREEN);
		register("music_disc_everreflective", MUSIC_DISC_EVERREFLECTIVE, DyeColor.GREEN);
	}

	public static void registerSpawningStuff() {
		register("spawner", SPAWNER, DyeColor.LIGHT_GRAY);

		register("egg_laying_wooly_pig_spawn_egg", EGG_LAYING_WOOLY_PIG_SPAWN_EGG, DyeColor.WHITE);
		register("guardian_turret_spawn_egg", GUARDIAN_TURRET_SPAWN_EGG, DyeColor.WHITE);
		register("kindling_spawn_egg", KINDLING_SPAWN_EGG, DyeColor.WHITE);
		register("lizard_spawn_egg", LIZARD_SPAWN_EGG, DyeColor.WHITE);
	}
	
	public static void registerPureClusters() {
		register("pure_coal", PURE_COAL, DyeColor.BROWN);
		register("pure_iron", PURE_IRON, DyeColor.BROWN);
		register("pure_gold", PURE_GOLD, DyeColor.BROWN);
		register("pure_diamond", PURE_DIAMOND, DyeColor.CYAN);
		register("pure_emerald", PURE_EMERALD, DyeColor.CYAN);
		register("pure_redstone", PURE_REDSTONE, DyeColor.RED);
		register("pure_lapis", PURE_LAPIS, DyeColor.PURPLE);
		register("pure_copper", PURE_COPPER, DyeColor.BROWN);
		register("pure_quartz", PURE_QUARTZ, DyeColor.BROWN);
		register("pure_glowstone", PURE_GLOWSTONE, DyeColor.YELLOW);
		register("pure_prismarine", PURE_PRISMARINE, DyeColor.CYAN);
		register("pure_netherite_scrap", PURE_NETHERITE_SCRAP, DyeColor.BROWN);
		register("pure_echo", PURE_ECHO, DyeColor.BROWN);
		register("pure_netherite", PURE_NETHERITE, DyeColor.BROWN);
	}
	
	public static void registerStructurePlacers() {
		register("pedestal_tier_1_structure_placer", PEDESTAL_TIER_1_STRUCTURE_PLACER, DyeColor.WHITE);
		register("pedestal_tier_2_structure_placer", PEDESTAL_TIER_2_STRUCTURE_PLACER, DyeColor.WHITE);
		register("pedestal_tier_3_structure_placer", PEDESTAL_TIER_3_STRUCTURE_PLACER, DyeColor.WHITE);
		register("fusion_shrine_structure_placer", FUSION_SHRINE_STRUCTURE_PLACER, DyeColor.WHITE);
		register("enchanter_structure_placer", ENCHANTER_STRUCTURE_PLACER, DyeColor.WHITE);
		register("spirit_instiller_structure_placer", SPIRIT_INSTILLER_STRUCTURE_PLACER, DyeColor.WHITE);
		register("cinderhearth_structure_placer", CINDERHEARTH_STRUCTURE_PLACER, DyeColor.WHITE);
	}
	
	public static void registerBannerPatterns() {
		register("logo_banner_pattern", LOGO_BANNER_PATTERN, DyeColor.LIGHT_BLUE);
		register("amethyst_shard_banner_pattern", AMETHYST_SHARD_BANNER_PATTERN, DyeColor.LIGHT_BLUE);
		register("amethyst_cluster_banner_pattern", AMETHYST_CLUSTER_BANNER_PATTERN, DyeColor.LIGHT_BLUE);
	}
	
	public static void registerGemstoneItems() {
		register("topaz_shard", TOPAZ_SHARD, DyeColor.CYAN);
		register("citrine_shard", CITRINE_SHARD, DyeColor.YELLOW);
		register("onyx_shard", ONYX_SHARD, DyeColor.BLACK);
		register("moonstone_shard", MOONSTONE_SHARD, DyeColor.WHITE);
		register("spectral_shard", SPECTRAL_SHARD, DyeColor.WHITE);
		
		register("topaz_powder", TOPAZ_POWDER, DyeColor.CYAN);
		register("amethyst_powder", AMETHYST_POWDER, DyeColor.MAGENTA);
		register("citrine_powder", CITRINE_POWDER, DyeColor.YELLOW);
		register("onyx_powder", ONYX_POWDER, DyeColor.BLACK);
		register("moonstone_powder", MOONSTONE_POWDER, DyeColor.WHITE);
	}
	
	public static void registerPigments() {
		register("white_pigment", WHITE_PIGMENT, DyeColor.WHITE);
		register("orange_pigment", ORANGE_PIGMENT, DyeColor.ORANGE);
		register("magenta_pigment", MAGENTA_PIGMENT, DyeColor.MAGENTA);
		register("light_blue_pigment", LIGHT_BLUE_PIGMENT, DyeColor.LIGHT_BLUE);
		register("yellow_pigment", YELLOW_PIGMENT, DyeColor.YELLOW);
		register("lime_pigment", LIME_PIGMENT, DyeColor.LIME);
		register("pink_pigment", PINK_PIGMENT, DyeColor.PINK);
		register("gray_pigment", GRAY_PIGMENT, DyeColor.GRAY);
		register("light_gray_pigment", LIGHT_GRAY_PIGMENT, DyeColor.LIGHT_GRAY);
		register("cyan_pigment", CYAN_PIGMENT, DyeColor.CYAN);
		register("purple_pigment", PURPLE_PIGMENT, DyeColor.PURPLE);
		register("blue_pigment", BLUE_PIGMENT, DyeColor.BLUE);
		register("brown_pigment", BROWN_PIGMENT, DyeColor.BROWN);
		register("green_pigment", GREEN_PIGMENT, DyeColor.GREEN);
		register("red_pigment", RED_PIGMENT, DyeColor.RED);
		register("black_pigment", BLACK_PIGMENT, DyeColor.BLACK);
	}
	
	public static void registerCatkin() {
		register("vibrant_cyan_catkin", VIBRANT_CYAN_CATKIN, DyeColor.CYAN);
		register("vibrant_magenta_catkin", VIBRANT_MAGENTA_CATKIN, DyeColor.MAGENTA);
		register("vibrant_yellow_catkin", VIBRANT_YELLOW_CATKIN, DyeColor.YELLOW);
		register("vibrant_black_catkin", VIBRANT_BLACK_CATKIN, DyeColor.BLACK);
		register("vibrant_white_catkin", VIBRANT_WHITE_CATKIN, DyeColor.WHITE);
		
		register("lucid_cyan_catkin", LUCID_CYAN_CATKIN, DyeColor.CYAN);
		register("lucid_magenta_catkin", LUCID_MAGENTA_CATKIN, DyeColor.MAGENTA);
		register("lucid_yellow_catkin", LUCID_YELLOW_CATKIN, DyeColor.YELLOW);
		register("lucid_black_catkin", LUCID_BLACK_CATKIN, DyeColor.BLACK);
		register("lucid_white_catkin", LUCID_WHITE_CATKIN, DyeColor.WHITE);
	}
	
	public static void registerResources() {
		register("shimmerstone_gem", SHIMMERSTONE_GEM, DyeColor.YELLOW);
		register("raw_azurite", RAW_AZURITE, DyeColor.BLUE);
		register("refined_azurite", REFINED_AZURITE, DyeColor.BLUE);
		register("paltaeria_fragments", PALTAERIA_FRAGMENTS, DyeColor.LIGHT_BLUE);
		register("paltaeria_gem", PALTAERIA_GEM, DyeColor.LIGHT_BLUE);
		register("stratine_fragments", STRATINE_FRAGMENTS, DyeColor.RED);
		register("stratine_gem", STRATINE_GEM, DyeColor.RED);
		register("pyrite_chunk", PYRITE_CHUNK, DyeColor.PURPLE);
		register("dragonbone_chunk", DRAGONBONE_CHUNK, DyeColor.GRAY);
		register("effulgent_feather", EFFULGENT_FEATHER, DyeColor.YELLOW);
		register("refined_bloodstone", REFINED_BLOODSTONE, DyeColor.RED);
		register("downstone_fragments", DOWNSTONE_FRAGMENTS, DyeColor.LIGHT_GRAY);
		register("resonance_shard", RESONANCE_SHARD, DyeColor.WHITE);
		
		register("quitoxic_powder", QUITOXIC_POWDER, DyeColor.PURPLE);
		register("mermaids_gem", MERMAIDS_GEM, DyeColor.LIGHT_BLUE);
		register("storm_stone", STORM_STONE, DyeColor.YELLOW);
		register("star_fragment", STAR_FRAGMENT, DyeColor.PURPLE);
		register("stardust", STARDUST, DyeColor.PURPLE);
		register("frigid_stardust", FRIGID_STARDUST, DyeColor.PURPLE);
		register("blood_orchid_petal", BLOOD_ORCHID_PETAL, DyeColor.RED);
		
		register("hibernating_jade_vine_seeds", HIBERNATING_JADE_VINE_BULB, DyeColor.GRAY);
		register("germinated_jade_vine_seeds", GERMINATED_JADE_VINE_BULB, DyeColor.LIME);
		register("jade_vine_petals", JADE_VINE_PETALS, DyeColor.LIME);
		
		register("nephrite_blossom_bulb", NEPHRITE_BLOSSOM_BULB, DyeColor.PINK);
		
		register("jadeite_lotus_bulb", JADEITE_LOTUS_BULB, DyeColor.BROWN);
		register("jadeite_petals", JADEITE_PETALS, DyeColor.BROWN);
		
		register("glistering_melon_seeds", GLISTERING_MELON_SEEDS, DyeColor.LIME);
		register("amaranth_grains", AMARANTH_GRAINS, DyeColor.LIME);

		register("vegetal", VEGETAL, DyeColor.LIME);
		register("neolith", NEOLITH, DyeColor.PINK);
		register("bedrock_dust", BEDROCK_DUST, DyeColor.BLACK);
		register("midnight_aberration", MIDNIGHT_ABERRATION, DyeColor.GRAY);
		register("midnight_chip", MIDNIGHT_CHIP, DyeColor.GRAY);
		
		register("bismuth_flake", BISMUTH_FLAKE, DyeColor.CYAN);
		register("bismuth_crystal", BISMUTH_CRYSTAL, DyeColor.CYAN);
		
		register("raw_malachite", RAW_MALACHITE, DyeColor.GREEN);
		register("malachite_crystal", MALACHITE_CRYSTAL, DyeColor.GREEN);
		
		register("fiery_powder", FIERY_POWDER, DyeColor.ORANGE);
		register("blizzard_powder", BLIZZARD_POWDER, DyeColor.LIGHT_BLUE);
		register("bone_ash", BONE_ASH, DyeColor.GRAY);
		
		register("moonstone_core", MOONSTONE_CORE, DyeColor.WHITE);
	}
	
	public static void registerDecayBottles() {
		register("bottle_of_fading", BOTTLE_OF_FADING, DyeColor.GRAY);
		register("bottle_of_failing", BOTTLE_OF_FAILING, DyeColor.GRAY);
		register("bottle_of_ruin", BOTTLE_OF_RUIN, DyeColor.GRAY);
		register("bottle_of_forfeiture", BOTTLE_OF_FORFEITURE, DyeColor.GRAY);
		register("bottle_of_decay_away", BOTTLE_OF_DECAY_AWAY, DyeColor.PINK);
	}
	
	public static void registerPreEnchantedTools() {
		register("multitool", MULTITOOL, DyeColor.BROWN);
		register("tender_pickaxe", TENDER_PICKAXE, DyeColor.BLUE);
		register("lucky_pickaxe", LUCKY_PICKAXE, DyeColor.LIGHT_BLUE);
		register("razor_falchion", RAZOR_FALCHION, DyeColor.RED);
		register("oblivion_pickaxe", OBLIVION_PICKAXE, DyeColor.GRAY);
		register("resonant_pickaxe", RESONANT_PICKAXE, DyeColor.WHITE);
		register("dragonrending_pickaxe", DRAGONRENDING_PICKAXE, DyeColor.WHITE);
		register("lagoon_rod", LAGOON_ROD, DyeColor.LIGHT_BLUE);
		register("molten_rod", MOLTEN_ROD, DyeColor.ORANGE);
		
		register("fetchling_helmet", FETCHLING_HELMET, DyeColor.BLUE);
		register("ferocious_chestplate", FEROCIOUS_CHESTPLATE, DyeColor.BLUE);
		register("sylph_leggings", SYLPH_LEGGINGS, DyeColor.BLUE);
		register("oread_boots", OREAD_BOOTS, DyeColor.BLUE);
		
		register("bedrock_pickaxe", BEDROCK_PICKAXE, DyeColor.BLACK);
		register("bedrock_axe", BEDROCK_AXE, DyeColor.BLACK);
		register("bedrock_shovel", BEDROCK_SHOVEL, DyeColor.BLACK);
		register("bedrock_sword", BEDROCK_SWORD, DyeColor.BLACK);
		register("bedrock_hoe", BEDROCK_HOE, DyeColor.BLACK);
		register("bedrock_bow", BEDROCK_BOW, DyeColor.BLACK);
		register("bedrock_crossbow", BEDROCK_CROSSBOW, DyeColor.BLACK);
		register("bedrock_shears", BEDROCK_SHEARS, DyeColor.BLACK);
		register("bedrock_fishing_rod", BEDROCK_FISHING_ROD, DyeColor.BLACK);

		register("bedrock_helmet", BEDROCK_HELMET, DyeColor.BLACK);
		register("bedrock_chestplate", BEDROCK_CHESTPLATE, DyeColor.BLACK);
		register("bedrock_leggings", BEDROCK_LEGGINGS, DyeColor.BLACK);
		register("bedrock_boots", BEDROCK_BOOTS, DyeColor.BLACK);

		register("malachite_workstaff", MALACHITE_WORKSTAFF, DyeColor.GREEN);
		register("malachite_ultra_greatsword", MALACHITE_ULTRA_GREATSWORD, DyeColor.GREEN);
		register("malachite_crossbow", MALACHITE_CROSSBOW, DyeColor.GREEN);
		register("malachite_bident", MALACHITE_BIDENT, DyeColor.GREEN);

		register("glass_crest_workstaff", GLASS_CREST_WORKSTAFF, DyeColor.WHITE);
		register("glass_crest_ultra_greatsword", GLASS_CREST_ULTRA_GREATSWORD, DyeColor.WHITE);
		register("ferocious_glass_crest_bident", FEROCIOUS_GLASS_CREST_BIDENT, DyeColor.WHITE);
		register("fractal_glass_crest_bident", FRACTAL_GLASS_CREST_BIDENT, DyeColor.WHITE);
		register("glass_crest_crossbow", GLASS_CREST_CROSSBOW, DyeColor.WHITE);
		
		register("malachite_glass_arrow", MALACHITE_GLASS_ARROW, DyeColor.GREEN);
		register("topaz_glass_arrow", TOPAZ_GLASS_ARROW, DyeColor.CYAN);
		register("amethyst_glass_arrow", AMETHYST_GLASS_ARROW, DyeColor.MAGENTA);
		register("citrine_glass_arrow", CITRINE_GLASS_ARROW, DyeColor.YELLOW);
		register("onyx_glass_arrow", ONYX_GLASS_ARROW, DyeColor.BLACK);
		register("moonstone_glass_arrow", MOONSTONE_GLASS_ARROW, DyeColor.WHITE);
		
		register("glass_ampoule", GLASS_AMPOULE, DyeColor.WHITE);
		register("ferocious_glass_ampoule", FEROCIOUS_GLASS_AMPOULE, DyeColor.WHITE);
		register("fractal_glass_ampoule", FRACTAL_GLASS_AMPOULE, DyeColor.WHITE);
		register("crystallized_dragon_fang", CRYSTALLIZED_DRAGON_FANG, DyeColor.WHITE);

		
		register("dreamflayer", DREAMFLAYER, DyeColor.RED);
		register("nightfalls_blade", NIGHTFALLS_BLADE, DyeColor.GRAY);
	}
	
	public static void registerMagicalTools() {
		register("crafting_tablet", CRAFTING_TABLET, DyeColor.LIGHT_GRAY);
		register("bottomless_bundle", BOTTOMLESS_BUNDLE, DyeColor.LIGHT_GRAY);
		register("phantom_frame", PHANTOM_FRAME, DyeColor.YELLOW);
		register("glow_phantom_frame", GLOW_PHANTOM_FRAME, DyeColor.YELLOW);
		register("knowledge_gem", KNOWLEDGE_GEM, DyeColor.PURPLE);
		register("celestial_pocketwatch", CELESTIAL_POCKETWATCH, DyeColor.MAGENTA);
		register("gilded_book", GILDED_BOOK, DyeColor.PURPLE);
		register("enchantment_canvas", ENCHANTMENT_CANVAS, DyeColor.PURPLE);
		register("everpromise_ribbon", EVERPROMISE_RIBBON, DyeColor.PINK);
		register("bag_of_holding", BAG_OF_HOLDING, DyeColor.PURPLE);
		register("radiance_staff", RADIANCE_STAFF, DyeColor.YELLOW);
		register("natures_staff", NATURES_STAFF, DyeColor.LIME);
		register("herding_staff", HERDING_STAFF, DyeColor.LIME);
		register("constructors_staff", CONSTRUCTORS_STAFF, DyeColor.LIGHT_GRAY);
		register("exchanging_staff", EXCHANGING_STAFF, DyeColor.LIGHT_GRAY);
		register("block_flooder", BLOCK_FLOODER, DyeColor.LIGHT_GRAY);
		register("ender_splice", ENDER_SPLICE, DyeColor.PURPLE);
		register("perturbed_eye", PERTURBED_EYE, DyeColor.RED);
		register("crescent_clock", CRESCENT_CLOCK, DyeColor.MAGENTA);
		
		register("mysterious_locket", MYSTERIOUS_LOCKET, DyeColor.GRAY);
		register("mysterious_compass", MYSTERIOUS_COMPASS, DyeColor.GRAY);
	}
	
	public static void registerConsumables() {
		register("rock_candy", ROCK_CANDY, DyeColor.PINK);
		register("topaz_rock_candy", TOPAZ_ROCK_CANDY, DyeColor.CYAN);
		register("amethyst_rock_candy", AMETHYST_ROCK_CANDY, DyeColor.MAGENTA);
		register("citrine_rock_candy", CITRINE_ROCK_CANDY, DyeColor.YELLOW);
		register("onyx_rock_candy", ONYX_ROCK_CANDY, DyeColor.BLACK);
		register("moonstone_rock_candy", MOONSTONE_ROCK_CANDY, DyeColor.WHITE);
		
		register("triple_meat_pot_pie", TRIPLE_MEAT_POT_PIE, DyeColor.PINK);
		register("kimchi", KIMCHI, DyeColor.PINK);
		
		register("clotted_cream", CLOTTED_CREAM, DyeColor.PINK);
		register("fresh_chocolate", FRESH_CHOCOLATE, DyeColor.PINK);
		register("bodacious_berry_bar", BOCACIOUS_BERRY_BAR, DyeColor.PINK);
		
		register("hot_chocolate", HOT_CHOCOLATE, DyeColor.PINK);
		register("restoration_tea", RESTORATION_TEA, DyeColor.PINK);
		register("glistering_jelly_tea", GLISTERING_JELLY_TEA, DyeColor.PINK);
		register("demon_tea", DEMON_TEA, DyeColor.RED);

		register("jade_jelly", JADE_JELLY, DyeColor.LIME);
		register("jaramel", JARAMEL, DyeColor.PINK);
		register("moonstruck_nectar", MOONSTRUCK_NECTAR, DyeColor.LIME);
		register("glass_peach", GLASS_PEACH, DyeColor.PINK);
		register("bloodboil_syrup", BLOODBOIL_SYRUP, DyeColor.RED);
		
		register("scone", SCONE, DyeColor.PINK);
		register("star_candy", STAR_CANDY, DyeColor.PINK);
		register("purple_star_candy", PURPLE_STAR_CANDY, DyeColor.PINK);
		register("lucky_roll", LUCKY_ROLL, DyeColor.PINK);
		register("honey_pastry", HONEY_PASTRY, DyeColor.PINK);
		
		register("jaramel_tart", JARAMEL_TART, DyeColor.PINK);
		register("salted_jaramel_tart", SALTED_JARAMEL_TART, DyeColor.PINK);
		register("ashen_tart", ASHEN_TART, DyeColor.PINK);
		register("weeping_tart", WEEPING_TART, DyeColor.PINK);
		register("whispy_tart", WHISPY_TART, DyeColor.PINK);
		register("puff_tart", PUFF_TART, DyeColor.PINK);

		register("jaramel_trifle", JARAMEL_TRIFLE, DyeColor.PINK);
		register("salted_jaramel_trifle", SALTED_JARAMEL_TRIFLE, DyeColor.PINK);
		register("monster_trifle", MONSTER_TRIFLE, DyeColor.PINK);
		register("demon_trifle", DEMON_TRIFLE, DyeColor.PINK);

		register("myceylon", MYCEYLON, DyeColor.PINK);
		register("myceylon_apple_pie", MYCEYLON_APPLE_PIE, DyeColor.PINK);
		register("myceylon_pumpkin_pie", MYCEYLON_PUMPKIN_PIE, DyeColor.PINK);
		register("myceylon_cookie", MYCEYLON_COOKIE, DyeColor.PINK);
		register("aloe_leaf", ALOE_LEAF, DyeColor.PINK);
		register("sawblade_holly_berry", SAWBLADE_HOLLY_BERRY, DyeColor.PINK);
		register("prickly_bayleaf", PRICKLY_BAYLEAF, DyeColor.PINK);
		register("triple_meat_pot_stew", TRIPLE_MEAT_POT_STEW, DyeColor.PINK);
		register("dragonbone_broth", DRAGONBONE_BROTH, DyeColor.GRAY);
		register("doombloom_seed", DOOMBLOOM_SEED, DyeColor.BLACK);

		register("infused_beverage", INFUSED_BEVERAGE, DyeColor.PINK);
		register("pure_alcohol", PURE_ALCOHOL, DyeColor.WHITE);
		register("reprise", REPRISE, DyeColor.PINK);
		register("suspicious_brew", SUSPICIOUS_BREW, DyeColor.LIME);
		register("jade_wine", JADE_WINE, DyeColor.LIME);
		register("chrysocolla", CHRYSOCOLLA, DyeColor.LIME);
		register("freigeist", FREIGEIST, DyeColor.RED);
		register("divination_heart", DIVINATION_HEART, DyeColor.RED);

		register("imbrifer_cookbook", IMBRIFER_COOKBOOK, DyeColor.PURPLE);
		register("imperial_cookbook", IMPERIAL_COOKBOOK, DyeColor.PURPLE);
		register("melochites_cookbook_vol_1", MELOCHITES_COOKBOOK_VOL_1, DyeColor.PURPLE);
		register("melochites_cookbook_vol_2", MELOCHITES_COOKBOOK_VOL_2, DyeColor.PURPLE);
		register("brewers_handbook", BREWERS_HANDBOOK, DyeColor.PURPLE);

		register("aqua_regia", AQUA_REGIA, DyeColor.PINK);
		register("bagnun", BAGNUN, DyeColor.PINK);
		register("banyash", BANYASH, DyeColor.PINK);
		register("berliner", BERLINER, DyeColor.PINK);
		register("bristle_mead", BRISTLE_MEAD, DyeColor.PINK);
		register("chauve_souris_au_vin", CHAUVE_SOURIS_AU_VIN, DyeColor.PINK);
		register("crawfish", CRAWFISH, DyeColor.PINK);
		register("crawfish_cocktail", CRAWFISH_COCKTAIL, DyeColor.PINK);
		register("cream_pastry", CREAM_PASTRY, DyeColor.PINK);
		register("faded_koi", FADED_KOI, DyeColor.PINK);
		register("fishcake", FISHCAKE, DyeColor.PINK);
		register("lizard_meat", LIZARD_MEAT, DyeColor.PINK);
		register("golden_bristle_tea", GOLDEN_BRISTLE_TEA, DyeColor.PINK);
		register("hare_roast", HARE_ROAST, DyeColor.PINK);
		register("junket", JUNKET, DyeColor.PINK);
		register("koi_fish", KOI_FISH, DyeColor.PINK);
		register("meatloaf", MEATLOAF, DyeColor.PINK);
		register("meatloaf_sandwich", MEATLOAF_SANDWICH, DyeColor.PINK);
		register("mellow_shallot_soup", MELLOW_SHALLOT_SOUP, DyeColor.PINK);
		register("morchella", MORCHELLA, DyeColor.PINK);
		register("nectered_viognier", NECTERED_VIOGNIER, DyeColor.PINK);
		register("peaches_flambe", PEACHES_FLAMBE, DyeColor.PINK);
		register("peach_cream", PEACH_CREAM, DyeColor.PINK);
		register("peach_jam", PEACH_JAM, DyeColor.PINK);
		register("rabbit_cream_pie", RABBIT_CREAM_PIE, DyeColor.PINK);
		register("sedatives", SEDATIVES, DyeColor.PINK);
		register("slushslide", SLUSHSLIDE, DyeColor.PINK);
		register("surstromming", SURSTROMMING, DyeColor.PINK);
	}
	
	public static void registerInkStorage() {
		register("ink_flask", INK_FLASK, DyeColor.WHITE);
		register("ink_assortment", INK_ASSORTMENT, DyeColor.WHITE);
		register("pigment_palette", PIGMENT_PALETTE, DyeColor.WHITE);
		register("artists_palette", ARTISTS_PALETTE, DyeColor.WHITE);
		register("creative_ink_assortment", CREATIVE_INK_ASSORTMENT, DyeColor.WHITE);
	}
	
	public static void registerTrinkets() {
		register("fanciful_stone_ring", FANCIFUL_STONE_RING, DyeColor.GREEN);
		register("fanciful_belt", FANCIFUL_BELT, DyeColor.GREEN);
		register("fanciful_pendant", FANCIFUL_PENDANT, DyeColor.GREEN);
		register("fanciful_circlet", FANCIFUL_CIRCLET, DyeColor.GREEN);
		register("fanciful_gloves", FANCIFUL_GLOVES, DyeColor.GREEN);
		register("fanciful_bismuth_ring", FANCIFUL_BISMUTH_RING, DyeColor.GREEN);
		
		register("glow_vision_goggles", GLOW_VISION_GOGGLES, DyeColor.WHITE);
		register("jeopardant", JEOPARDANT, DyeColor.RED);
		register("seven_league_boots", SEVEN_LEAGUE_BOOTS, DyeColor.PURPLE);
		register("cotton_cloud_boots", COTTON_CLOUD_BOOTS, DyeColor.PURPLE);
		register("radiance_pin", RADIANCE_PIN, DyeColor.BLUE);
		register("totem_pendant", TOTEM_PENDANT, DyeColor.BLUE);
		register("take_off_belt", TAKE_OFF_BELT, DyeColor.YELLOW);
		register("azure_dike_belt", AZURE_DIKE_BELT, DyeColor.BLUE);
		register("azure_dike_ring", AZURE_DIKE_RING, DyeColor.BLUE);
		register("shieldgrasp_amulet", SHIELDGRASP_AMULET, DyeColor.BLUE);
		register("heartsingers_reward", HEARTSINGERS_REWARD_RING, DyeColor.PINK);
		register("gloves_of_dawns_grasp", GLOVES_OF_DAWNS_GRASP, DyeColor.YELLOW);
		register("ring_of_pursuit", RING_OF_PURSUIT, DyeColor.MAGENTA);
		register("gleaming_pin", GLEAMING_PIN, DyeColor.YELLOW);
		register("lesser_potion_pendant", LESSER_POTION_PENDANT, DyeColor.PINK);
		register("greater_potion_pendant", GREATER_POTION_PENDANT, DyeColor.PINK);
		register("ashen_circlet", ASHEN_CIRCLET, DyeColor.ORANGE);
		register("weeping_circlet", WEEPING_CIRCLET, DyeColor.LIGHT_BLUE);
		register("puff_circlet", PUFF_CIRCLET, DyeColor.WHITE);
		register("whispy_circlet", WHISPY_CIRCLET, DyeColor.BROWN);
		register("circlet_of_arrogance", CIRCLET_OF_ARROGANCE, DyeColor.RED);
		register("neat_ring", NEAT_RING, DyeColor.GREEN);
	}
	
	public static void registerFluidBuckets() {
		register("liquid_crystal_bucket", LIQUID_CRYSTAL_BUCKET, DyeColor.LIGHT_GRAY);
		register("mud_bucket", MUD_BUCKET, DyeColor.BROWN);
		register("midnight_solution_bucket", MIDNIGHT_SOLUTION_BUCKET, DyeColor.GRAY);
		register("dragonrot_bucket", DRAGONROT_BUCKET, DyeColor.LIGHT_GRAY);
	}
	
	public static void registerFuelRegistry() {
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WET_LAVA_SPONGE.asItem(), 12800);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_LEVEL_DETECTOR.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WEATHER_DETECTOR.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ITEM_DETECTOR.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PLAYER_DETECTOR.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ENTITY_DETECTOR.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_LOG.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_SAPLING.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_PLANKS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_STAIRS.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_PRESSURE_PLATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_FENCE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_FENCE_GATE.asItem(), 300);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_BUTTON.asItem(), 100);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLACK_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BLUE_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.BROWN_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.CYAN_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GRAY_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.GREEN_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_BLUE_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIGHT_GRAY_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.LIME_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.MAGENTA_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.ORANGE_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PINK_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.PURPLE_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.RED_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.WHITE_SLAB.asItem(), 150);
		FuelRegistry.INSTANCE.add(SpectrumBlocks.YELLOW_SLAB.asItem(), 150);
	}
	
}
