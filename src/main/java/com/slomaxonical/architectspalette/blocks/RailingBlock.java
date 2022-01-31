package com.slomaxonical.architectspalette.blocks;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.Property;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class RailingBlock extends HorizontalConnectingBlock {
    private final VoxelShape[] cullingShapes;

    public RailingBlock(Settings settings) {
        super(2.0F,2.0F, 8.0F,8.0F, 10.0F, settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(NORTH, false).with(EAST, false).with(SOUTH, false).with(WEST, false).with(WATERLOGGED, false));
        this.cullingShapes = this.createShapes(2.0F,1.0F,8.0F,8.0F,8.0F);
    }
    @Override
    public VoxelShape getCullingShape(BlockState state, BlockView world, BlockPos pos) {
        return this.cullingShapes[this.getShapeIndex(state)];
    }
    //this is diffrent from fences
    public boolean canConnect(BlockState checkedState, boolean isFullSquare, Direction direction) {
        return (!cannotConnect(checkedState) && isFullSquare) || checkedState.getBlock() instanceof RailingBlock;
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        World blockView = ctx.getWorld();
        BlockPos blockPos = ctx.getBlockPos();
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        BlockPos pos2 = blockPos.north();
        BlockPos pos3 = blockPos.east();
        BlockPos pos4 = blockPos.south();
        BlockPos pos5 = blockPos.west();
        BlockState blockState = blockView.getBlockState(pos2);
        BlockState blockState2 = blockView.getBlockState(pos3);
        BlockState blockState3 = blockView.getBlockState(pos4);
        BlockState blockState4 = blockView.getBlockState(pos5);
        return (super.getPlacementState(ctx)
                .with(NORTH, this.canConnect(blockState, blockState.isSideSolidFullSquare(blockView, pos2, Direction.SOUTH), Direction.SOUTH)))
                .with(EAST, this.canConnect(blockState2, blockState2.isSideSolidFullSquare(blockView, pos3, Direction.WEST), Direction.WEST))
                .with(SOUTH, this.canConnect(blockState3, blockState3.isSideSolidFullSquare(blockView, pos4, Direction.NORTH), Direction.NORTH))
                .with(WEST, this.canConnect(blockState4, blockState4.isSideSolidFullSquare(blockView, pos5, Direction.EAST), Direction.EAST))
                .with(WATERLOGGED, fluidState.getFluid() == Fluids.WATER);
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED).booleanValue()) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (direction.getAxis().getType() == Direction.Type.HORIZONTAL) {
            return (BlockState)state.with((Property)FACING_PROPERTIES.get(direction), this.canConnect(neighborState, neighborState.isSideSolidFullSquare(world, neighborPos, direction.getOpposite()), direction.getOpposite()));
        }
        return super.getStateForNeighborUpdate(state, direction, neighborState, world, pos, neighborPos);
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(NORTH, EAST, WEST, SOUTH, WATERLOGGED);
    }
}
