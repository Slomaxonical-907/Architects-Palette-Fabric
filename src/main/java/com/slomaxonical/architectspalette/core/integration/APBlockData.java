package com.slomaxonical.architectspalette.core.integration;

import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.item.AxeItem;

import java.util.HashMap;

public class APBlockData {
    public static void addStrippables() {
//        AxeItem.STRIPPED_BLOCKS = new HashMap<>(AxeItem.STRIPPED_BLOCKS);
//        AxeItem.STRIPPED_BLOCKS.put(APBlocks.TWISTED_LOG, APBlocks.STRIPPED_TWISTED_LOG);
//        AxeItem.STRIPPED_BLOCKS.put(APBlocks.TWISTED_WOOD, APBlocks.STRIPPED_TWISTED_WOOD);
    }
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
