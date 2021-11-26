package com.slomaxonical.architectspalette.blocks.abyssaline;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.WallBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;

public class AbyssalineBrickWallBlock extends WallBlock {

	public AbyssalineBrickWallBlock(Settings properties) {
		super(properties);
	}
	
//	@Override
//	public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//		return state.get(AbyssalineBlock.CHARGED) ? 7 : 0;
//	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		return super.getStateForNeighborUpdate(state, facing, facingState, worldIn, currentPos, facingPos).with(AbyssalineBlock.CHARGED, AbyssalineBlock.checkForNearbyChargedBlocks(worldIn, currentPos));
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return super.getPlacementState(context).with(AbyssalineBlock.CHARGED, AbyssalineBlock.checkForNearbyChargedBlocks(context.getWorld(), context.getBlockPos()));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		super.appendProperties(builder);
		builder.add(AbyssalineBlock.CHARGED);
	}

}