package com.slomaxonical.architectspalette;

import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.loot.LootTableModifications;
import com.slomaxonical.architectspalette.registry.*;
import net.fabricmc.api.ModInitializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArchitectsPalette implements ModInitializer {

    public static final String MOD_ID = "architects_palette";
    public static final Logger LOGGER = LogManager.getLogger("Architect's Palette");

    @Override
    public void onInitialize() {
        APBlocks.registerBlocks();
        APItems.registerItems();
        APItemgroup.registerItemgroup();
        APBlocks.registerFuel();

        APSounds.registerSounds();
        WarpingRecipe.registerRecipe();
        APTrades.registerVillagerTrades();
        APTrades.registerWanderingTrades();

        LootTableModifications.registerWitheredBones();
    }
}
