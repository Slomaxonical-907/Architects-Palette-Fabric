package com.slomaxonical.architectspalette.core.registry;

import net.fabricmc.fabric.api.registry.FuelRegistry;

public class APFuel {

    public static void registerFuel(){
        FuelRegistry.INSTANCE.add(APBlocks.CHARCOAL_BLOCK, 1600);

    }
}
