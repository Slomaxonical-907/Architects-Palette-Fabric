package com.slomaxonical.architectspalette.common.blocks.abyssaline;

import com.slomaxonical.architectspalette.common.APBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;
import java.util.function.ToIntFunction;

import static com.slomaxonical.architectspalette.common.APBlockSettings.CHARGED;
import static com.slomaxonical.architectspalette.common.blocks.abyssaline.NewAbyssalineBlock.CHARGE_SOURCE;

public class AbyssalineLampBlock extends PillarBlock implements IAbyssalineChargeable {

    public AbyssalineLampBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(CHARGE_SOURCE, Direction.NORTH).with(CHARGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(AXIS, CHARGED, CHARGE_SOURCE);
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

    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        AbyssalineHelper.abyssalineTick(state, worldIn, pos);
    }

    public static ToIntFunction<BlockState> getLuminance() {
        return blockState -> blockState.get(APBlockSettings.CHARGED) ? 16 : 0;
    }

}
