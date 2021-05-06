package com.slomaxonical.architectspalette.common.blocks;

import com.google.common.collect.ImmutableMap;
import com.slomaxonical.architectspalette.core.registry.APSounds;
import net.minecraft.block.*;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.ActionResult;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Map;
import java.util.Random;
import java.util.function.ToIntFunction;

public class CageLanternBlock extends Block implements Waterloggable {
    public static final BooleanProperty LIT = Properties.LIT;
    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
    public static final DirectionProperty FACING = FacingBlock.FACING;
    public static final BooleanProperty INVERTED = Properties.INVERTED;

    private static final Map<Direction, VoxelShape> SHAPES = new ImmutableMap.Builder<Direction, VoxelShape>()
            .put(Direction.DOWN, Block.createCuboidShape(5, 0, 5, 11, 6, 11))
            .put(Direction.UP, Block.createCuboidShape(5, 16, 6, 11, 10, 11))
            .put(Direction.NORTH, Block.createCuboidShape(5, 5, 0, 11, 11, 6))
            .put(Direction.SOUTH, Block.createCuboidShape(5, 5, 16, 11, 11, 10))
            .put(Direction.WEST, Block.createCuboidShape(0, 5, 5, 6, 11, 11))
            .put(Direction.EAST, Block.createCuboidShape(16, 5, 5, 10, 11, 11))
            .build();

    public CageLanternBlock(Settings properties, int poweredLightLevel) {
        super(properties.luminance(getLightValueLit(poweredLightLevel)));
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH).with(LIT, true).with(WATERLOGGED, false).with(INVERTED, false));
    }

    private static ToIntFunction<BlockState> getLightValueLit(int lightValue) {
        return (state) -> state.get(Properties.LIT) ? lightValue : 0;
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIT, WATERLOGGED, FACING, INVERTED);
    }

    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (state.get(WATERLOGGED)) {
            worldIn.getFluidTickScheduler().schedule(currentPos, Fluids.WATER, Fluids.WATER.getTickRate(worldIn));
        }
        if (state.get(FACING) == facing && !state.canPlaceAt(worldIn, currentPos)) {return Blocks.AIR.getDefaultState();}
        return state;
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView worldIn, BlockPos pos) {
        Direction direction = state.get(FACING);
        return Block.sideCoversSmallSquare(worldIn, pos.offset(direction), direction.getOpposite());
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        FluidState fluidstate = context.getWorld().getFluidState(context.getBlockPos());
        boolean flag = fluidstate.getFluid() == Fluids.WATER;
        boolean lit = getLitState(this.getDefaultState(), context.getWorld(), context.getBlockPos());
        return this.getDefaultState().with(FACING, context.getSide().getOpposite()).with(WATERLOGGED, flag).with(LIT, lit);
    }

    public BlockRenderType getRenderType(BlockState state) {
        return BlockRenderType.MODEL;
    }

    public PistonBehavior getPistonBehavior(BlockState state) {
        return PistonBehavior.DESTROY;
    }

    @Override
    public ActionResult onUse(BlockState state, World worldIn, BlockPos pos, PlayerEntity player, Hand handIn, BlockHitResult hit) {
        BlockState newState = state.with(INVERTED, !state.get(INVERTED));
        worldIn.setBlockState(pos, newState.with(LIT, getLitState(newState, worldIn, pos)), 2);

        SoundEvent click = state.get(INVERTED) ? APSounds.CAGE_LANTERN_TOGGLE_OFF : APSounds.CAGE_LANTERN_TOGGLE_ON;
        worldIn.playSound(player, pos, click, SoundCategory.BLOCKS, 1, 1);
        return ActionResult.success(worldIn.isClient);
    }

    private boolean getLitState(BlockState state, World world, BlockPos pos) {
        return state.get(INVERTED) ^ world.isReceivingRedstonePower(pos);
    }

    @Override
    public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        if (!worldIn.isClient) {
            boolean lit = state.get(LIT);
            boolean shouldBeLit = getLitState(state, worldIn, pos);
            if (lit != shouldBeLit) {
                if (lit) {
                    worldIn.setBlockState(pos, state.cycle(LIT), 2);
                    worldIn.getBlockTickScheduler().schedule(pos, this, 2);
                } else {
                    // fuck if i know what this does, i copied it from the redstone lamp
                    worldIn.setBlockState(pos, state.cycle(LIT), 2);
                }
            }
        }
    }

    public VoxelShape getOutlineShape(BlockState state, BlockView worldIn, BlockPos pos, ShapeContext context) {
        return SHAPES.get(state.get(FACING));
    }

    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        boolean shouldBeLit = getLitState(state, worldIn, pos);
        if (shouldBeLit != state.get(LIT)) {
            worldIn.setBlockState(pos, state.with(LIT, shouldBeLit), 2);
        }
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }


}
