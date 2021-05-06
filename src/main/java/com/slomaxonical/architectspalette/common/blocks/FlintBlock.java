package com.slomaxonical.architectspalette.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class FlintBlock extends Block {

    public FlintBlock(Settings properties) {
        super(properties);
    }

    @Override
    public void onLandedUpon(World worldIn, BlockPos pos, Entity entityIn, float fallDistance) {
        entityIn.handleFallDamage(fallDistance, 1.25f);
    }
}
