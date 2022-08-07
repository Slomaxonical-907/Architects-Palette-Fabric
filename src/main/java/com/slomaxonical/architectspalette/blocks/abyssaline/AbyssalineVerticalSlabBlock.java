package com.slomaxonical.architectspalette.blocks.abyssaline;

import com.slomaxonical.architectspalette.blocks.VerticalSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static com.slomaxonical.architectspalette.blocks.abyssaline.NewAbyssalineBlock.CHARGE_SOURCE;
import static com.slomaxonical.architectspalette.blocks.abyssaline.NewAbyssalineBlock.CHARGED;

public class AbyssalineVerticalSlabBlock extends VerticalSlabBlock implements IAbyssalineChargeable {
    public AbyssalineVerticalSlabBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getStateManager().getDefaultState().with(CHARGE_SOURCE, Direction.NORTH).with(CHARGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED, CHARGE_SOURCE, TYPE, WATERLOGGED);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        context.getWorld().createAndScheduleBlockTick(context.getBlockPos(), this, 1);
        return super.getPlacementState(context);
    }

    @Override
    public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        AbyssalineHelper.abyssalineNeighborUpdate(this, state, worldIn, pos, blockIn, fromPos);
    }

    @Override
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        AbyssalineHelper.abyssalineTick(state, worldIn, pos);
    }

    //Interface stuff
    @Override
    public boolean acceptsChargeFrom(BlockState stateIn, Direction faceIn) {
        VerticalSlabType type = stateIn.get(TYPE);
        return type == VerticalSlabType.DOUBLE || faceIn != type.direction;
    }

    @Override
    public boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
        return IAbyssalineChargeable.super.outputsChargeFrom(stateIn, faceIn) && this.acceptsChargeFrom(stateIn, faceIn);
    }

    // Slabs should never transfer power through the faces that don't collide, so don't provide a state here that can.
    @Override
    public BlockState getStateWithChargeDirection(BlockState stateIn, Direction faceOut) {
        VerticalSlabType type = stateIn.get(TYPE);
        if(type.direction == faceOut)
            return stateIn;

        return stateIn.with(CHARGE_SOURCE, faceOut);
    }
}
