package com.slomaxonical.architectspalette.blocks.entrails;

import com.slomaxonical.architectspalette.blocks.VerticalSlabBlock;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Random;

public class DrippyVerticalSlabBlock extends VerticalSlabBlock {
    public DrippyVerticalSlabBlock(Settings settings) {
        super(settings);
    }
    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        DrippyBlock.doParticleEffect(stateIn, worldIn, pos, rand);
    }
}
