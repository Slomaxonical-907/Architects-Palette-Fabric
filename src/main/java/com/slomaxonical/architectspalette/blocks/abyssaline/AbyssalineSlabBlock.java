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
import net.minecraft.world.World;

import java.util.Random;

import static com.slomaxonical.architectspalette.blocks.util.APBlockSettings.CHARGED;
import static com.slomaxonical.architectspalette.blocks.abyssaline.NewAbyssalineBlock.CHARGE_SOURCE;

public class AbyssalineSlabBlock extends SlabBlock implements IAbyssalineChargeable {

	public AbyssalineSlabBlock(Settings properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(CHARGE_SOURCE, Direction.NORTH).with(CHARGED, false));
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(CHARGED, CHARGE_SOURCE, TYPE, WATERLOGGED);
	}

	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		context.getWorld().getBlockTickScheduler().schedule(context.getBlockPos(), this, 1);
		return super.getPlacementState(context);
	}

	@Override
	public void neighborUpdate(BlockState state, World worldIn, BlockPos pos, Block blockIn, BlockPos fromPos, boolean isMoving) {
		AbyssalineHelper.abyssalineNeighborUpdate(this, state, worldIn, pos, blockIn, fromPos);
	}

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
	public boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
		return IAbyssalineChargeable.super.outputsChargeFrom(stateIn, faceIn) && this.acceptsChargeFrom(stateIn, faceIn);
	}

	// Slabs should never transfer power through the faces that don't collide, so don't provide a state here that can.
	@Override
	public BlockState getStateWithChargeDirection(BlockState stateIn, Direction faceOut) {
		SlabType type = stateIn.get(TYPE);
		if (type == SlabType.TOP && faceOut == Direction.DOWN) return stateIn;
		if (type == SlabType.BOTTOM && faceOut == Direction.UP) return stateIn;
		return stateIn.with(CHARGE_SOURCE, faceOut);
	}

}