package com.slomaxonical.architectspalette.blocks.abyssaline;

import com.slomaxonical.architectspalette.blocks.NubBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.function.ToIntFunction;

import static com.slomaxonical.architectspalette.blocks.util.APBlockSettings.CHARGED;

public class AbyssalineNubBlock extends NubBlock implements IAbyssalineChargeable {

    public AbyssalineNubBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(CHARGED, false));
    }

    public static ToIntFunction<BlockState> getLuminance() {
        return blockState -> blockState.get(CHARGED) ? 12 : 0;
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED);
    }


    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return AbyssalineHelper.getStateWithNeighborCharge(super.getPlacementState(context),context.getWorld(),context.getBlockPos());
    }

    @Override
    public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        AbyssalineHelper.abyssalineNeighborUpdate(this, state, worldIn, pos, blockIn, fromPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        AbyssalineHelper.abyssalineTick(state, worldIn, pos);
    }

    @Override
    public Direction getSourceDirection(BlockState stateIn) {
        //Nubs can only be charged from the block they are on, so they don't actually need the source direction state.
        return stateIn.get(FACING);
    }

    @Override
    public boolean outputsChargeTo(BlockState stateIn, Direction faceIn) {
        //The nub in theory should never output power since it only accepts it from one side, and charge isn't bidirectional.
        return false;
    }

    @Override
    public boolean acceptsChargeFrom(BlockState stateIn, Direction faceIn) {
        //Only accepts power from the block it is on.
        return faceIn == stateIn.get(FACING);
    }

    @Override
    public BlockState getStateWithChargeDirection(BlockState stateIn, Direction directionToSource) {
        //Can't get power from anywhere other than a single spot, so it should never change when receiving power.
        return stateIn;
    }

    @Override
    public boolean pullsPowerFrom(BlockState stateIn, Direction faceIn) {
        //Pulls power from its back end.
        return faceIn == stateIn.get(FACING);
    }
}
