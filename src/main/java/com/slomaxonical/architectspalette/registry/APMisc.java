package com.slomaxonical.architectspalette.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;
import net.fabricmc.fabric.api.registry.OxidizableBlocksRegistry;
import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;

public class APMisc {
    public static void registerFuel(){
        FuelRegistry.INSTANCE.add(APBlocks.CHARCOAL_BLOCK, 1600);
    }

    public static void registerStrippables() {
        StrippableBlockRegistry.register(APBlocks.TWISTED_LOG, APBlocks.STRIPPED_TWISTED_LOG);
        StrippableBlockRegistry.register(APBlocks.TWISTED_WOOD, APBlocks.STRIPPED_TWISTED_WOOD);
    }

    public static final void registerOxidizables(){
        OxidizableBlocksRegistry.registerOxidizableBlockPair(APBlocks.COPPER_NUB,APBlocks.EXPOSED_COPPER_NUB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(APBlocks.EXPOSED_COPPER_NUB,APBlocks.WEATHERED_COPPER_NUB);
        OxidizableBlocksRegistry.registerOxidizableBlockPair(APBlocks.WEATHERED_COPPER_NUB,APBlocks.OXIDIZED_COPPER_NUB);
    }

    public static final void registerWaxables(){
        OxidizableBlocksRegistry.registerWaxableBlockPair(APBlocks.COPPER_NUB,APBlocks.WAXED_COPPER_NUB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(APBlocks.EXPOSED_COPPER_NUB,APBlocks.WAXED_EXPOSED_COPPER_NUB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(APBlocks.WEATHERED_COPPER_NUB,APBlocks.WAXED_WEATHERED_COPPER_NUB);
        OxidizableBlocksRegistry.registerWaxableBlockPair(APBlocks.OXIDIZED_COPPER_NUB,APBlocks.WAXED_OXIDIZED_COPPER_NUB);
    }
}
