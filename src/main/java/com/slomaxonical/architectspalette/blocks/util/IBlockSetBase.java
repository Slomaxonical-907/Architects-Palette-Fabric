package com.slomaxonical.architectspalette.blocks.util;

import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;

public interface IBlockSetBase {
    default Block getBlockForPart(StoneBlockSet.SetComponent part, FabricBlockSettings settings, Block base) {
        return StoneBlockSet.getBlockForPart(part, settings, base);
    }
}
