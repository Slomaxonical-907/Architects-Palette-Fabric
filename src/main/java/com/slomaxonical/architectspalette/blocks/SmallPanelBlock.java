package com.slomaxonical.architectspalette.blocks;

import com.google.common.collect.ImmutableMap;
import com.slomaxonical.architectspalette.blocks.util.ShapeRotator;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Map;

public class SmallPanelBlock extends Block implements Waterloggable {
    public static final DirectionProperty FACING = Properties.HORIZONTAL_FACING;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final Map<Direction, VoxelShape> SHAPES;
    static {
        VoxelShape north = Block.createCuboidShape(2, 2, 0, 14, 14, 1);

        ImmutableMap.Builder<Direction, VoxelShape> builder = new ImmutableMap.Builder<>();

        for (Direction dir : new Direction[]{Direction.NORTH, Direction.SOUTH, Direction.WEST, Direction.EAST}) {
            builder.put(dir, ShapeRotator.rotateShapeHorizontal(north, Direction.NORTH, dir));
        }
        SHAPES = builder.build();
    }

    public SmallPanelBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(FACING, Direction.NORTH).with(WATERLOGGED,false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING,WATERLOGGED);
    }

    @Override
    public BlockState rotate(BlockState state, BlockRotation rotation) {
        return state.with(FACING,rotation.rotate(state.get(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, BlockMirror mirror) {
        return state.rotate(mirror.getRotation(state.get(FACING)));
    }

    @Override
    public VoxelShape getOutlineShape(BlockState state, BlockView world, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Nullable
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getBlockPos());
        Direction face = context.getSide();
        if (face == Direction.UP || face == Direction.DOWN) return null;
        BlockState state = this.getDefaultState().with(FACING, face.getOpposite());
        if (canPlaceAt(state, context.getWorld(), context.getBlockPos())) {
            return state.with(WATERLOGGED, fluidstate.getFluid() == Fluids.WATER);
        }
        return null;
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        if (state.get(WATERLOGGED)) {
            world.createAndScheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        if (state.get(FACING) == facing && !state.canPlaceAt(world, pos)) {return Blocks.AIR.getDefaultState();}
        return state;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        Direction direction = state.get(FACING);
        return !world.getBlockState(pos.offset(direction)).isAir();
    }

    @Override
    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }
}
