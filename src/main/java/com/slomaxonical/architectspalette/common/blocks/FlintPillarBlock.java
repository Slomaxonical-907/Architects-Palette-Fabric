package com.slomaxonical.architectspalette.common.blocks;

import net.minecraft.block.PillarBlock;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlintPillarBlock extends PillarBlock {
    public FlintPillarBlock(Settings properties) {
        super(properties);
    }

    public void onLandedUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.handleFallDamage(fallDistance, 1.25f);
    }
}