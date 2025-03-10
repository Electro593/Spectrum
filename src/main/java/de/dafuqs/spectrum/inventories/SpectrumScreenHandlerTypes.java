package de.dafuqs.spectrum.inventories;

import net.fabricmc.fabric.api.screenhandler.v1.*;
import net.minecraft.client.gui.screen.ingame.*;
import net.minecraft.resource.featuretoggle.FeatureSet;
import net.minecraft.screen.*;
import net.minecraft.util.*;
import net.minecraft.registry.*;

public class SpectrumScreenHandlerTypes {
	
	public static ScreenHandlerType<PaintbrushScreenHandler> PAINTBRUSH;
	public static ScreenHandlerType<WorkstaffScreenHandler> WORKSTAFF;

    public static ScreenHandlerType<PedestalScreenHandler> PEDESTAL;
    public static ScreenHandlerType<CraftingTabletScreenHandler> CRAFTING_TABLET;
    public static ScreenHandlerType<RestockingChestScreenHandler> RESTOCKING_CHEST;
    public static ScreenHandlerType<BedrockAnvilScreenHandler> BEDROCK_ANVIL;
    public static ScreenHandlerType<ParticleSpawnerScreenHandler> PARTICLE_SPAWNER;
    public static ScreenHandlerType<CompactingChestScreenHandler> COMPACTING_CHEST;
    public static ScreenHandlerType<BlackHoleChestScreenHandler> BLACK_HOLE_CHEST;
    public static ScreenHandlerType<PotionWorkshopScreenHandler> POTION_WORKSHOP;
    public static ScreenHandlerType<ColorPickerScreenHandler> COLOR_PICKER;
    public static ScreenHandlerType<CinderhearthScreenHandler> CINDERHEARTH;
    public static ScreenHandlerType<FilteringScreenHandler> FILTERING;
	public static ScreenHandlerType<BagOfHoldingScreenHandler> BAG_OF_HOLDING;

    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER1_9X3;
    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER2_9X3;
    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER3_9X3;

    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER1_9X6;
    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER2_9X6;
    public static ScreenHandlerType<GenericSpectrumContainerScreenHandler> GENERIC_TIER3_9X6;

    public static ScreenHandlerType<Spectrum3x3ContainerScreenHandler> GENERIC_TIER1_3X3;
	public static ScreenHandlerType<Spectrum3x3ContainerScreenHandler> GENERIC_TIER2_3X3;
	public static ScreenHandlerType<Spectrum3x3ContainerScreenHandler> GENERIC_TIER3_3X3;
	
	public static <T extends ScreenHandler> ScreenHandlerType<T> registerSimple(Identifier id, ScreenHandlerType.Factory<T> factory) {
		// TODO - Should this feature set be empty?
        ScreenHandlerType<T> type = new ScreenHandlerType<>(factory, FeatureSet.empty());
		return Registry.register(Registries.SCREEN_HANDLER, id, type);
	}
	
	public static <T extends ScreenHandler> ScreenHandlerType<T> registerExtended(Identifier id, ExtendedScreenHandlerType.ExtendedFactory<T> factory) {
		ScreenHandlerType<T> type = new ExtendedScreenHandlerType<>(factory);
		return Registry.register(Registries.SCREEN_HANDLER, id, type);
	}
	
	public static void register() {
        PAINTBRUSH = registerSimple(SpectrumScreenHandlerIDs.PAINTBRUSH, PaintbrushScreenHandler::new);
        WORKSTAFF = registerSimple(SpectrumScreenHandlerIDs.WORKSTAFF, WorkstaffScreenHandler::new);

        PEDESTAL = registerExtended(SpectrumScreenHandlerIDs.PEDESTAL, PedestalScreenHandler::new);
        PARTICLE_SPAWNER = registerExtended(SpectrumScreenHandlerIDs.PARTICLE_SPAWNER, ParticleSpawnerScreenHandler::new);
        COMPACTING_CHEST = registerExtended(SpectrumScreenHandlerIDs.COMPACTING_CHEST, CompactingChestScreenHandler::new);
        BLACK_HOLE_CHEST = registerExtended(SpectrumScreenHandlerIDs.BLACK_HOLE_CHEST, BlackHoleChestScreenHandler::new);
        COLOR_PICKER = registerExtended(SpectrumScreenHandlerIDs.COLOR_PICKER, ColorPickerScreenHandler::new);
        CINDERHEARTH = registerExtended(SpectrumScreenHandlerIDs.CINDERHEARTH, CinderhearthScreenHandler::new);
		FILTERING = registerExtended(SpectrumScreenHandlerIDs.FILTERING, FilteringScreenHandler::new);
		BAG_OF_HOLDING = registerSimple(SpectrumScreenHandlerIDs.BAG_OF_HOLDING, BagOfHoldingScreenHandler::new);

        CRAFTING_TABLET = registerSimple(SpectrumScreenHandlerIDs.CRAFTING_TABLET, CraftingTabletScreenHandler::new);
        RESTOCKING_CHEST = registerSimple(SpectrumScreenHandlerIDs.RESTOCKING_CHEST, RestockingChestScreenHandler::new);
        BEDROCK_ANVIL = registerSimple(SpectrumScreenHandlerIDs.BEDROCK_ANVIL, BedrockAnvilScreenHandler::new);
        POTION_WORKSHOP = registerSimple(SpectrumScreenHandlerIDs.POTION_WORKSHOP, PotionWorkshopScreenHandler::new);

        GENERIC_TIER1_9X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER1_9x3, GenericSpectrumContainerScreenHandler::createGeneric9x3_Tier1);
        GENERIC_TIER2_9X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER2_9x3, GenericSpectrumContainerScreenHandler::createGeneric9x3_Tier2);
        GENERIC_TIER3_9X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER3_9x3, GenericSpectrumContainerScreenHandler::createGeneric9x3_Tier3);
		
		GENERIC_TIER1_9X6 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER1_9x6, GenericSpectrumContainerScreenHandler::createGeneric9x6_Tier1);
		GENERIC_TIER2_9X6 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER2_9x6, GenericSpectrumContainerScreenHandler::createGeneric9x6_Tier2);
		GENERIC_TIER3_9X6 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER3_9x6, GenericSpectrumContainerScreenHandler::createGeneric9x6_Tier3);
		
		GENERIC_TIER1_3X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER1_3X3, Spectrum3x3ContainerScreenHandler::createTier1);
		GENERIC_TIER2_3X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER2_3X3, Spectrum3x3ContainerScreenHandler::createTier2);
		GENERIC_TIER3_3X3 = registerSimple(SpectrumScreenHandlerIDs.GENERIC_TIER3_3X3, Spectrum3x3ContainerScreenHandler::createTier3);
	}
	
	public static void registerClient() {
		HandledScreens.register(SpectrumScreenHandlerTypes.PAINTBRUSH, PaintbrushScreen::new);
		HandledScreens.register(SpectrumScreenHandlerTypes.WORKSTAFF, WorkstaffScreen::new);

        HandledScreens.register(SpectrumScreenHandlerTypes.PEDESTAL, PedestalScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.CRAFTING_TABLET, CraftingTabletScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.RESTOCKING_CHEST, RestockingChestScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.BEDROCK_ANVIL, BedrockAnvilScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.PARTICLE_SPAWNER, ParticleSpawnerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.COMPACTING_CHEST, CompactingChestScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.BLACK_HOLE_CHEST, BlackHoleChestScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.POTION_WORKSHOP, PotionWorkshopScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.COLOR_PICKER, ColorPickerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.CINDERHEARTH, CinderhearthScreen::new);
		HandledScreens.register(SpectrumScreenHandlerTypes.FILTERING, FilteringScreen::new);
		HandledScreens.register(SpectrumScreenHandlerTypes.BAG_OF_HOLDING, GenericContainerScreen::new);

        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER1_9X3, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER2_9X3, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER3_9X3, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER1_9X6, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER2_9X6, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER3_9X6, SpectrumGenericContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER1_3X3, Spectrum3x3ContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER2_3X3, Spectrum3x3ContainerScreen::new);
        HandledScreens.register(SpectrumScreenHandlerTypes.GENERIC_TIER3_3X3, Spectrum3x3ContainerScreen::new);
	}
	
}
