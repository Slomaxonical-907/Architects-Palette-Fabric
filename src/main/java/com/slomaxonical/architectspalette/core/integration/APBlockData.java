package com.slomaxonical.architectspalette.core.integration;

import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.AxeItem;

import java.util.HashMap;

public class APBlockData {
    public static void getCutoutLayer(){
        BlockRenderLayerMap.INSTANCE.putBlocks(RenderLayer.getCutout(),
                APBlocks.ENTWINE_BARS,
                APBlocks.SUNMETAL_BARS,

                APBlocks.REDSTONE_CAGE_LANTERN,
                APBlocks.GLOWSTONE_CAGE_LANTERN,
                APBlocks.ALGAL_CAGE_LANTERN,

                APBlocks.TWISTED_DOOR,
                APBlocks.TWISTED_TRAPDOOR,
                APBlocks.TWISTED_SAPLING,
                APBlocks.POTTED_TWISTED_SAPLING,

                APBlocks.ACACIA_TOTEM_WING
        );
    }
}
