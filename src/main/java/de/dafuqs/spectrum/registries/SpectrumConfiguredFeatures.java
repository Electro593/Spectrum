package de.dafuqs.spectrum.registries;

import de.dafuqs.spectrum.*;
import de.dafuqs.spectrum.blocks.conditional.colored_tree.*;
import de.dafuqs.spectrum.blocks.dd_deco.*;
import net.fabricmc.fabric.api.biome.v1.*;
import net.fabricmc.fabric.api.tag.convention.v1.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.*;
import net.minecraft.util.*;
import net.minecraft.util.math.intprovider.*;
import net.minecraft.registry.*;
import net.minecraft.world.gen.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.*;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.*;
import net.minecraft.world.gen.trunk.*;
import org.jetbrains.annotations.*;

import java.util.*;

import static de.dafuqs.spectrum.helpers.WorldgenHelper.*;

public class SpectrumConfiguredFeatures {
	
	// OVERWORLD
	public static final Identifier CLOVER_PATCH = SpectrumCommon.locate("clover_patch");
	public static final HashMap<DyeColor, RegistryEntry<ConfiguredFeature<TreeFeatureConfig, ?>>> COLORED_TREE_CONFIGURED_FEATURES = new HashMap<>(); // for sapling growing
	
	// DEEPER DOWN
	public static final Identifier SNAPPING_IVY_PATCH = SpectrumCommon.locate("snapping_ivy_patch");
	public static final Identifier BRISTLE_SPROUT_PATCH = SpectrumCommon.locate("bristle_sprouts");
	public static final Identifier JADEITE_LOTUS = SpectrumCommon.locate("jadeite_lotus");
	public static final Identifier NEPHRITE_BLOSSOM_BULB = SpectrumCommon.locate("nephrite_blossom");
	public static final Map<Dragonjag.Variant, Identifier> DRAGONJAGS = new HashMap<>() {{
		put(Dragonjag.Variant.PINK, SpectrumCommon.locate("dragonjags/pink"));
		put(Dragonjag.Variant.RED, SpectrumCommon.locate("dragonjags/red"));
		put(Dragonjag.Variant.BLACK, SpectrumCommon.locate("dragonjags/black"));
		put(Dragonjag.Variant.YELLOW, SpectrumCommon.locate("dragonjags/yellow"));
		put(Dragonjag.Variant.PURPLE, SpectrumCommon.locate("dragonjags/purple"));
	}};
	
	public static void register() {
		// Geodes
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_OVERWORLD), GenerationStep.Feature.UNDERGROUND_STRUCTURES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("citrine_geode")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_OVERWORLD), GenerationStep.Feature.UNDERGROUND_STRUCTURES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("topaz_geode")));
		
		// Ores
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_OVERWORLD), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("shimmerstone_ore")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_OVERWORLD), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("azurite_ore")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_NETHER), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("stratine_ore")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.IN_THE_END), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("paltaeria_ore")));

		BiomeModifications.addFeature(BiomeSelectors.tag(SpectrumBiomeTags.COLORED_TREES_GENERATING_IN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("colored_tree_patch")));
		
		// Plants
		BiomeModifications.addFeature(BiomeSelectors.tag(BiomeTags.IS_OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("mermaids_brushes")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("quitoxic_reeds")));
		BiomeModifications.addFeature(BiomeSelectors.tag(ConventionalBiomeTags.PLAINS), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, SpectrumCommon.locate("clover_patch")));
	}
	
}
