package com.slomaxonical.architectspalette.blocks.abyssaline;

import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager.Builder;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.function.ToIntFunction;

public class ChiseledAbyssalineBlock extends Block implements IAbyssalineChargeable {

	public static final BooleanProperty CHARGED = APBlockSettings.CHARGED;

	//	private static final Item KEY = Items.HEART_OF_THE_FUCKING_SEA;
	private static final BlockPos OFFSET = new BlockPos(0, 0, 0);

	public boolean outputsChargeFrom(BlockState stateIn, Direction faceIn) {
		return stateIn.get(CHARGED);
	}

	public boolean isCharged(BlockState stateIn) {
		return stateIn.get(CHARGED);
	}

	public BlockPos getSourceOffset(BlockState stateIn) {
		return OFFSET;
	}

	public ChiseledAbyssalineBlock(Settings settings) {
		super(settings);
		this.setDefaultState(this.stateManager.getDefaultState().with(CHARGED, false));
	}

	public static ToIntFunction<BlockState> getLuminance() {
		return blockState -> blockState.get(CHARGED) ? 14 : 0;
	}

	@Override
	public BlockState getStateForNeighborUpdate(BlockState state, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
		return state;
	}

	@Override
	public BlockState getPlacementState(ItemPlacementContext context) {
		return this.getDefaultState();
	}

	@Override
	protected void appendProperties(Builder<Block, BlockState> builder) {
		builder.add(CHARGED);
	}


	@Override
	public ActionResult onUse(BlockState state, World world, BlockPos pos, PlayerEntity player, Hand hand, BlockHitResult traceResult) {
		ItemStack stack = player.getStackInHand(hand);
		if (!this.isCharged(state) && stack.getItem() == Items.HEART_OF_THE_SEA) {
			if(!player.isCreative())
				stack.decrement(1);
			world.setBlockState(pos, this.getStateWithCharge(state, true));
			world.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_ACTIVATE, SoundCategory.BLOCKS, 0.5F, new Random().nextFloat() * 0.2F + 0.8F);
			return ActionResult.SUCCESS;
		}
		else if (this.isCharged(state) && stack.isEmpty()) {
			world.setBlockState(pos, this.getStateWithCharge(state, false));
			world.playSound(null, pos, SoundEvents.BLOCK_CONDUIT_DEACTIVATE, SoundCategory.BLOCKS, 0.5F, new Random().nextFloat() * 0.2F + 0.8F);
			if(!player.isCreative() || (player.getInventory().count(Items.HEART_OF_THE_SEA) <= 0))
				player.giveItemStack(new ItemStack(Items.HEART_OF_THE_SEA));
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}
}
