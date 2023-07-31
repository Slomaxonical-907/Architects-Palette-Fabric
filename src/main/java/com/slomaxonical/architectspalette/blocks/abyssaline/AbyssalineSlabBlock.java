package com.slomaxonical.architectspalette.blocks.abyssaline;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.SlabBlock;
import net.minecraft.block.enums.SlabType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import static com.slomaxonical.architectspalette.blocks.util.APBlockSettings.CHARGED;
import static com.slomaxonical.architectspalette.blocks.abyssaline.AbyssalineBlock.CHARGE_SOURCE;

public class AbyssalineSlabBlock extends SlabBlock implements IAbyssalineChargeable {

	public AbyssalineSlabBlock(Settings properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(CHARGE_SOURCE, Direction.NORTH).with(CHARGED, false));
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(CHARGED, CHARGE_SOURCE, TYPE, WATERLOGGED);
	}

//	@Override
//	public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//		return this.isCharged(state) ? AbyssalineHelper.CHARGE_LIGHT : 0;
//	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return AbyssalineHelper.getStateWithNeighborCharge(super.getPlacementState(context), context.getWorld(), context.getBlockPos());
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
		switch(stateIn.get(TYPE)) {
			case TOP: return faceIn != Direction.DOWN;
			case BOTTOM: return faceIn != Direction.UP;
			default: return true;
		}
	}

	@Override
	public boolean outputsChargeTo(BlockState stateIn, Direction faceIn) {
		return IAbyssalineChargeable.super.outputsChargeTo(stateIn, faceIn) && this.acceptsChargeFrom(stateIn, faceIn);
	}

	// Slabs should never transfer power through the faces that don't collide, so don't provide a state here that can.
	@Override
	public BlockState getStateWithChargeDirection(BlockState stateIn, Direction directionToSource) {
		SlabType type = stateIn.get(TYPE);
		if (type == SlabType.TOP && directionToSource == Direction.DOWN) return stateIn;
		if (type == SlabType.BOTTOM && directionToSource == Direction.UP) return stateIn;
		return stateIn.with(CHARGE_SOURCE, directionToSource);
	}

}