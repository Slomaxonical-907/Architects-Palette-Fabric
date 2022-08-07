package com.slomaxonical.architectspalette.blocks.entrails;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

public class DrippySlabBlock extends SlabBlock {
    public DrippySlabBlock(Settings settings) {
        super(settings);
    }
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        DrippyBlock.doParticleEffect(stateIn, worldIn, pos, rand);
    }
}
