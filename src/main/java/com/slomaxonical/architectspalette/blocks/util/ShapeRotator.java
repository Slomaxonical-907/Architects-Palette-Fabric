package com.slomaxonical.architectspalette.blocks.util;

import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;

public class ShapeRotator {
    // cuts out voxel regions from a cube
    public static VoxelShape cutout(VoxelShape... cutouts){
        VoxelShape shape = VoxelShapes.fullCube();
        for (VoxelShape cutout : cutouts) {
            shape = VoxelShapes.combine(shape, cutout, BooleanBiFunction.ONLY_FIRST);
        }
        return shape.simplify();
    }
//    public static VoxelShape join(VoxelShape base, ) {
//
//    }

    public static VoxelShape rotateShapeHorizontal(VoxelShape shape, Direction from, Direction to) {
        VoxelShape[] buffer = new VoxelShape[]{shape, VoxelShapes.empty()};

        int times = (to.getHorizontal() - from.getHorizontal() + 4) % 4;
        for (int i = 0; i < times; i++) {
            buffer[0].forEachBox((minX, minY, minZ, maxX, maxY, maxZ) -> buffer[1] = VoxelShapes.union(buffer[1], VoxelShapes.cuboid(1 - maxZ, minY, minX, 1 - minZ, maxY, maxX)));
            buffer[0] = buffer[1];
            buffer[1] = VoxelShapes.empty();
        }
        return buffer[0];
    }
}
