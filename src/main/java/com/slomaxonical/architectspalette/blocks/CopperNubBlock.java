package com.slomaxonical.architectspalette.blocks;

import net.minecraft.block.BlockState;
import net.minecraft.block.Oxidizable;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class CopperNubBlock extends NubBlock implements Oxidizable {
    private final Oxidizable.OxidationLevel oxidationLevel;

    public CopperNubBlock(Oxidizable.OxidationLevel oxidationLevel,Settings settings) {
        super(settings);
        this.oxidationLevel = oxidationLevel;
    }
    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        this.tickDegradation(state, world, pos, random);
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return Oxidizable.getIncreasedOxidationBlock(state.getBlock()).isPresent();
    }
    @Override
    public OxidationLevel getDegradationLevel() {
        return this.oxidationLevel;
    }

}
