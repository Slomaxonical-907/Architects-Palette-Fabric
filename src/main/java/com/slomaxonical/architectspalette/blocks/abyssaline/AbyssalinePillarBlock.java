package com.slomaxonical.architectspalette.blocks.abyssaline;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Random;

import static com.slomaxonical.architectspalette.blocks.util.APBlockSettings.CHARGED;
import static com.slomaxonical.architectspalette.blocks.abyssaline.NewAbyssalineBlock.CHARGE_SOURCE;

public class AbyssalinePillarBlock extends PillarBlock implements IAbyssalineChargeable {
//	public static final EnumProperty<PillarSide> CHARGE_SIDE = EnumProperty.create("charge_side", PillarSide.class);

	public AbyssalinePillarBlock(Settings properties) {
		super(properties);
		this.setDefaultState(this.getDefaultState().with(CHARGE_SOURCE, Direction.NORTH).with(CHARGED, false));
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(AXIS, CHARGED, CHARGE_SOURCE);
	}

//	@Override
//	public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//		return this.isCharged(state) ? AbyssalineHelper.CHARGE_LIGHT : 0;
//	}

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
		return faceIn.getAxis() == stateIn.get(AXIS);
	}

	@Override
	public boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
		return this.isCharged(stateIn) &&
				!(faceIn == this.getSourceDirection(stateIn)) &&
				faceIn.getAxis() == stateIn.get(AXIS);

//		return this.isCharged(stateIn) &&
//				!(faceIn.getAxisDirection().getOffset() == stateIn.get(CHARGE_SIDE).toScalar()) &&
//				faceIn.getAxis() == stateIn.get(AXIS);
	}

	@Override
	public boolean pushesPower(BlockState stateIn) {
		return true;
	}

	@Override
	public boolean pullsPowerFrom(BlockState stateIn, Direction faceIn) {
		return faceIn.getAxis() == stateIn.get(AXIS);
	}

//	@Override
//	public Direction getSourceDirection(BlockState stateIn) {
//		return directionFromAxis(stateIn.get(AXIS), stateIn.get(CHARGE_SIDE).toScalar());
//	}

//	@Override
//	public BlockState getStateWithChargeDirection(BlockState stateIn, Direction faceOut) {
//		if (faceOut.getAxis() == stateIn.get(AXIS)) {
//			return stateIn.with(CHARGE_SIDE, fromScalar(faceOut.getAxisDirection().getOffset()));
//		}
//		return stateIn;
//	}

	private static Direction directionFromAxis(Direction.Axis axis, Integer i) {
		switch(axis) {
			case X:
				return i > 0 ? Direction.EAST : Direction.WEST;
			case Y:
				return i > 0 ? Direction.UP : Direction.DOWN;
			case Z:
				return i > 0 ? Direction.SOUTH : Direction.NORTH;
		}
		return Direction.UP;
	}

	private PillarSide fromScalar(int integer) {
		return integer > 0 ? PillarSide.FRONT : PillarSide.BACK;
	}

	private enum PillarSide implements StringIdentifiable {
		FRONT,
		BACK;

		public int toScalar() {
			return this == FRONT ? 1 : -1;
		}

		public String toString() { return this.asString(); }

		public String asString() {
			return this == FRONT ? "front" : "back";
		}
	}

}