package com.slomaxonical.architectspalette.common.blocks.abyssaline;

import com.slomaxonical.architectspalette.core.registry.APBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;

public class AbyssalineBlock extends Block {
	public static final BooleanProperty CHARGED = BooleanProperty.of("charged");

	public AbyssalineBlock(Settings properties) {
		super(properties);
		this.setDefaultState(this.stateManager.getDefaultState().with(AbyssalineBlock.CHARGED, false));
	}
	
//	@Override
//	public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
//		return state.get(CHARGED) ? 7 : 0;
//	}
	
	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		return this.getDefaultState().with(CHARGED, checkForNearbyChargedBlocks(worldIn, currentPos));
	}
	
	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState().with(CHARGED, checkForNearbyChargedBlocks(context.getWorld(), context.getBlockPos()));
	}
	
	public static boolean checkForNearbyChargedBlocks(WorldAccess world, BlockPos pos) {
		boolean powered = false;
		
		for(Direction sides : Direction.values()) {
			BlockPos offset = pos.offset(sides);
			BlockState state = world.getBlockState(offset);
			if(state.getBlock() == APBlocks.CHISELED_ABYSSALINE_BRICKS && state.get(CHARGED)) {
				powered = true;
				break;
			}
		}
		
		if(!powered) {
			for(Direction sides : Direction.values()) {
				BlockPos offset = pos.offset(sides);
				BlockState state = world.getBlockState(offset);
				if(isChargedBlock(state)) {
					return checkForNearbySource(world, offset, sides.getOpposite(), 1);
				}
			}
		}
		
		return powered;
	}
	
	private static boolean checkForNearbySource(WorldAccess world, BlockPos pos, Direction blacklistedDirection, int cycles) {
		if(cycles == 2) return false;
		boolean powered = false;
		
		for(Direction sides : Direction.values()) {
			if(sides != blacklistedDirection) {
				BlockPos offset = pos.offset(sides);
				BlockState state = world.getBlockState(offset);
				if(state.getBlock() == APBlocks.CHISELED_ABYSSALINE_BRICKS && state.get(CHARGED)) {
					powered = true;
					break;
				}
			}
		}
		
		if(!powered) {
			for(Direction sides : Direction.values()) {
				BlockPos offset = pos.offset(sides);
				BlockState state = world.getBlockState(offset);
				if(isChargedBlock(state)) {
					return checkForNearbySource(world, offset, sides.getOpposite(), cycles + 1);
				}
			}
		}
		
		return powered;
	}
	
	public static boolean isChargedBlock(BlockState state) {
		return state.getBlock() != APBlocks.CHISELED_ABYSSALINE_BRICKS && state.contains(CHARGED) && state.get(CHARGED);
	}
	
	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(CHARGED);
	}
}