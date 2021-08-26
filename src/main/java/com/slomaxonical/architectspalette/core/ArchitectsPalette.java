package com.slomaxonical.architectspalette.core;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.loot.LootTableModifications;
import com.slomaxonical.architectspalette.core.registry.*;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ArchitectsPalette implements ModInitializer {

    public static final String MOD_ID = "architects_palette";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID.toUpperCase());

    @Override
    public void onInitialize() {
        APBlocks.registerBlocks();
        APItems.registerItems();
        APFuel.registerFuel();
        APItems.registerItemgroup();

        APSounds.registerSounds();
        WarpingRecipe.registerRecipe();
        APTrades.registerVillagerTrades();
        APTrades.registerWanderingTrades();

        LootTableModifications.registerWitheredBones();
    }
}
