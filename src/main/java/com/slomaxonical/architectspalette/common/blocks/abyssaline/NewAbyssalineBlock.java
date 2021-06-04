package com.slomaxonical.architectspalette.common.blocks.abyssaline;

import com.slomaxonical.architectspalette.common.APBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

import java.util.Random;

public class NewAbyssalineBlock extends Block implements IAbyssalineChargeable {
    public static final BooleanProperty CHARGED = APBlockSettings.CHARGED;
    public static final DirectionProperty CHARGE_SOURCE = DirectionProperty.of("charge_source",
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);

    public NewAbyssalineBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getStateManager().getDefaultState().with(CHARGED, false).with(CHARGE_SOURCE, Direction.NORTH));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED, CHARGE_SOURCE);
    }

//    @Override
//    public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//        return state.get(CHARGED) ? AbyssalineHelper.CHARGE_LIGHT : 0;
//    }

    @Override
    public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
        AbyssalineHelper.abyssalineNeighborUpdate(this, state, worldIn, pos, blockIn, fromPos);
    }

    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        AbyssalineHelper.abyssalineTick(state, worldIn, pos);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        context.getWorld().getBlockTickScheduler().schedule(context.getBlockPos(), this, 1);
        return this.getDefaultState();
//        return getStateWithNeighborCharge(this.getDefaultState(), context.getWorld(), context.getPos());
    }


}