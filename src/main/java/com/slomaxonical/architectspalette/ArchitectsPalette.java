package com.slomaxonical.architectspalette;

import com.slomaxonical.architectspalette.compat.cloth_config.ApConfigs;
import com.slomaxonical.architectspalette.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.features.TwistedTree;
import com.slomaxonical.architectspalette.loot.LootTableModifications;
import com.slomaxonical.architectspalette.registry.*;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ArchitectsPalette implements ModInitializer {

    public static final String MOD_ID = "architects_palette";
    public static final Logger LOGGER = LogManager.getLogger("Architect's Palette");

    @Override
    public void onInitialize() {
        AutoConfig.register(ApConfigs.class, GsonConfigSerializer::new);
        APBlocks.registerBlocks();
        ConfigResourceCondition.init();

        APItems.registerItems();
        APItemgroup.registerItemgroup();
        APBlocks.registerFuel();

        StrippableBlockRegistry.register(APBlocks.TWISTED_LOG, APBlocks.STRIPPED_TWISTED_LOG);
        StrippableBlockRegistry.register(APBlocks.TWISTED_WOOD, APBlocks.STRIPPED_TWISTED_WOOD);

        APSounds.registerSounds();
        WarpingRecipe.registerRecipe();
        APTrades.registerVillagerTrades();
        APTrades.registerWanderingTrades();

        TwistedTree.registerTreeFeature();
        LootTableModifications.registerWitheredBones();
    }
}
