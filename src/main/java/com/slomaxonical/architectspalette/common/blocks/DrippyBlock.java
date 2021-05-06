package com.slomaxonical.architectspalette.common.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

public class DrippyBlock extends Block {
    public DrippyBlock(Settings properties) {
        super(properties);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        if (rand.nextInt(6) == 0) {
            Direction direction = Direction.random(rand);
            if (direction != Direction.UP && direction != Direction.DOWN) {
                BlockPos blockpos = pos.offset(Direction.DOWN);
                BlockState blockstate = worldIn.getBlockState(blockpos);
                if (!stateIn.isOpaque() || !blockstate.isSideSolidFullSquare(worldIn, blockpos, direction.getOpposite())) {
                    double d0 = direction.getOffsetX() == 0 ? rand.nextDouble() : 0.5D + (double)direction.getOffsetX() * 0.6D;
                    double d2 = direction.getOffsetZ() == 0 ? rand.nextDouble() : 0.5D + (double)direction.getOffsetZ() * 0.6D;
                    worldIn.addParticle(ParticleTypes.DRIPPING_WATER, (double)pos.getX() + d0, (double)pos.getY() - 0.1, (double)pos.getZ() + d2, 0.0D, 0.0D, 0.0D);
                }
            }
        }
    }

}
