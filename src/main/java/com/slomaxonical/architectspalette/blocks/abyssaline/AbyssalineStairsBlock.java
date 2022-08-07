package com.slomaxonical.architectspalette.blocks.abyssaline;

import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.StairsBlock;
import net.minecraft.block.enums.BlockHalf;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

public class AbyssalineStairsBlock extends StairsBlock implements IAbyssalineChargeable {

	public AbyssalineStairsBlock(BlockState state, Settings properties) {
		super(state, properties);
		this.setDefaultState(this.getStateManager().getDefaultState().with(APBlockSettings.CHARGED, false));
	}
	
//	@Override
//	public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//		return this.isCharged(state) ? AbyssalineHelper.CHARGE_LIGHT : 0;
//	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(APBlockSettings.CHARGED, StairsBlock.SHAPE, StairsBlock.FACING, StairsBlock.HALF, StairsBlock.WATERLOGGED);
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		return super.getStateForNeighborUpdate(state, facing, facingState, worldIn, currentPos, facingPos);
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
	public BlockState getPlacementState(ItemPlacementContext context) {
		context.getWorld().createAndScheduleBlockTick(context.getBlockPos(), this, 1);
		return super.getPlacementState(context);
	}

	//Interface things
	@Override
	public boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
		boolean topface = (stateIn.get(StairsBlock.HALF) == BlockHalf.TOP);
		boolean directionmatches = (topface && faceIn == Direction.UP) || (!topface && faceIn == Direction.DOWN);
		return this.isCharged(stateIn) && directionmatches;
	}

	@Override
	public boolean acceptsChargeFrom(BlockState stateIn, Direction faceIn) {
		return faceIn == stateIn.get(StairsBlock.FACING);
	}

	@Override
	public Direction getSourceDirection(BlockState stateIn) {
		return stateIn.get(StairsBlock.FACING);
	}

	@Override
	// The stairs shouldn't rotate just to recieve charge.
	public BlockState getStateWithChargeDirection(BlockState stateIn, Direction faceOut) {
		return stateIn;
	}
}