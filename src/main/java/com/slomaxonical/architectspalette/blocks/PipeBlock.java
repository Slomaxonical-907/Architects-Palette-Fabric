package com.slomaxonical.architectspalette.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.EnumProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.BooleanBiFunction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.util.shape.VoxelShapes;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

import static com.slomaxonical.architectspalette.blocks.util.ShapeRotator.cutout;

public class PipeBlock extends PillarBlock implements Waterloggable {

    public static final EnumProperty<PipeBlockPart> PART = EnumProperty.of("part", PipeBlockPart.class);
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    protected static final VoxelShape Y_AXIS_SHAPE = cutout(Block.createCuboidShape(2, 0, 2, 14, 16, 14));
    protected static final VoxelShape Z_AXIS_SHAPE = cutout(Block.createCuboidShape(2, 2, 0, 14, 14, 16));
    protected static final VoxelShape X_AXIS_SHAPE = cutout(Block.createCuboidShape(0, 2, 2, 16, 14, 14));

    public PipeBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(AXIS, Direction.Axis.Y).with(PART, PipeBlockPart.MIDDLE).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, PART, WATERLOGGED);
    }
    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        switch(state.get(AXIS)) {
            case X:
            default:
                return X_AXIS_SHAPE;
            case Z:
                return Z_AXIS_SHAPE;
            case Y:
                return Y_AXIS_SHAPE;
        }
    }

    @Override
    public VoxelShape getRaycastShape(BlockState state, BlockView worldIn, BlockPos pos) {
        return VoxelShapes.fullCube();
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            worldIn.createAndScheduleFluidTick(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }

        return state.with(PART, checkNearbyPipes(state, worldIn, currentPos));
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        //stole this from chains, dunno if im supposed to.
        FluidState fluidstate = context.getWorld().getFluidState(context.getBlockPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;

        BlockState b = super.getPlacementState(context);
        return b.with(PART, checkNearbyPipes(b, context.getWorld(), context.getBlockPos())).with(WATERLOGGED, flag);
    }
    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public PipeBlockPart checkNearbyPipes(BlockState state, WorldAccess world, BlockPos pos) {
        Direction.Axis facing = state.get(AXIS);
        // this function is just offset, but for axis
        BlockPos forward = pos.offset(facing, 1);
        BlockPos reverse = pos.offset(facing, -1);
        boolean ffound = pipeMatches(state, world.getBlockState(forward));
        boolean rfound = pipeMatches(state, world.getBlockState(reverse));

        //swap alignment checks for z axis, since they dont line up on that one correctly for some reason
        if (facing == Direction.Axis.Z) {
            boolean e = ffound;
            ffound = rfound;
            rfound = e;
        }

        if (ffound && rfound) return PipeBlockPart.MIDDLE;
        if (ffound) return PipeBlockPart.BOTTOM;
        return PipeBlockPart.TOP;
    }

    private boolean pipeMatches(BlockState base, BlockState checking) {
        return checking.getBlock() instanceof PipeBlock && checking.get(AXIS) == base.get(AXIS);
    }



    public enum PipeBlockPart implements StringIdentifiable {
        TOP,
        MIDDLE,
        BOTTOM;

        @Override
        public String toString() { return this.asString(); }

        @Override
        public String asString() {
            return switch (this) {
                case TOP -> "top";
                case BOTTOM -> "bottom";
                default -> "middle";
            };
        }
    }
}
