package com.slomaxonical.architectspalette.core.mixin;

import com.google.common.collect.ImmutableMap;
import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;

import java.util.Map;

@Mixin(AxeItem.class)
public class StrippedBlocksMixin {
    @Mutable
    @Shadow
    @Final
    protected static Map<Block, Block> STRIPPED_BLOCKS;

    static {
        STRIPPED_BLOCKS = ImmutableMap.<Block, Block>builder()
                .putAll(STRIPPED_BLOCKS)
                .put(APBlocks.TWISTED_LOG, APBlocks.STRIPPED_TWISTED_LOG)
                .put(APBlocks.TWISTED_WOOD, APBlocks.STRIPPED_TWISTED_WOOD)
                .build();
    }
}