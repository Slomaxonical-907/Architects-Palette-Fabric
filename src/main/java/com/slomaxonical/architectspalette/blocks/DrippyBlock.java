package com.slomaxonical.architectspalette.blocks;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

import java.util.Random;

public class DrippyBlock extends Block {
    public DrippyBlock(Settings properties) {
        super(properties);
    }

    @Environment(EnvType.CLIENT)
    public void randomDisplayTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
         doParticleEffect(stateIn, worldIn, pos, rand);

    }
    public static void doParticleEffect(BlockState stateIn, World worldIn, BlockPos pos, Random rand){
        if (rand.nextInt(6) == 0) {
            Direction direction = Direction.random(rand);
            if (direction != Direction.UP && direction != Direction.DOWN) {
                BlockPos posBelow = pos.offset(Direction.DOWN);
                BlockState stateBelow = worldIn.getBlockState(posBelow);
                if (!stateIn.isOpaque() || !stateBelow.isSideSolidFullSquare(worldIn, posBelow, Direction.UP)) {
                    double xOffset = direction.getOffsetX() == 0 ? rand.nextDouble() : 0.5D + (double)direction.getOffsetX() * 0.45D;
                    double zOffset = direction.getOffsetZ() == 0 ? rand.nextDouble() : 0.5D + (double)direction.getOffsetZ() * 0.45D;
                    Vec3d dropPos = new Vec3d(pos.getX() + xOffset, pos.getY() - .1, pos.getZ() + zOffset);
                    BlockHitResult hitResult = stateIn.getCullingShape(worldIn, pos).raycast(dropPos, dropPos.add(0,1,0), pos);
                    if (hitResult != null && hitResult.getType() != HitResult.Type.MISS) {
                        Vec3d result = hitResult.getPos();
                        worldIn.addParticle(ParticleTypes.DRIPPING_WATER, result.x, result.y - .1, result.z, 0.0D, 0.0D, 0.0D);
                    }
                }
            }
        }
    }

}
