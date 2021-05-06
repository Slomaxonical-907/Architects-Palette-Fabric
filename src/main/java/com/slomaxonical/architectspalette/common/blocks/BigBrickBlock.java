package com.slomaxonical.architectspalette.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.BlockMirror;
import net.minecraft.util.BlockRotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

// This code was heavily referenced from Farmer's Delight's Tatami mat, with the permission of .vectorwing
// Thanks!
public class BigBrickBlock extends Block {
    public static final DirectionProperty FACING = Properties.FACING;
    private static final BooleanProperty PAIRED = BooleanProperty.of("paired");
    public final BrickType TYPE;

    public BigBrickBlock(Settings properties) {
        this(properties, BrickType.STONE);
    }

    public BigBrickBlock(Settings properties, BrickType type) {
        super(properties);
        this.TYPE = type;
        this.setDefaultState(this.getStateManager().getDefaultState().with(FACING, Direction.DOWN).with(PAIRED, false));
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        Direction face = context.getSide();
        BlockPos targetPos = context.getBlockPos().offset(face.getOpposite());
        BlockState targetState = context.getWorld().getBlockState(targetPos);
        boolean pairing = false;

        if (context.getPlayer() != null && !context.getPlayer().isSneaking() && BrickMatches(this, targetState) && !targetState.get(PAIRED)) {
            pairing = true;
        }

        return this.getDefaultState().with(FACING, context.getSide().getOpposite()).with(PAIRED, pairing);
    }

    public void onPlaced(World worldIn, BlockPos pos, BlockState state, @Nullable LivingEntity placer, ItemStack stack) {
        super.onPlaced(worldIn, pos, state, placer, stack);
        if (!worldIn.isClient) {
            if (placer != null && placer.isSneaking()) {
                return;
            }
            BlockPos blockpos = pos.offset(state.get(FACING));
            BlockState blockstate = worldIn.getBlockState(blockpos);
            if (BrickMatches(this, blockstate) && !blockstate.get(PAIRED)) {
                worldIn.setBlockState(blockpos, blockstate.with(FACING, state.get(FACING).getOpposite()).with(PAIRED, true), 3);
                worldIn.updateNeighbors(pos, Blocks.AIR);
                state.updateNeighbors(worldIn, pos, 3);
            }
        }
    }

    private boolean BrickMatches(BigBrickBlock thisBlock, BlockState suspect) {
        if (suspect.getBlock() instanceof BigBrickBlock) {
            BigBrickBlock b = (BigBrickBlock)suspect.getBlock();
            return b.TYPE == thisBlock.TYPE;
        }
        return false;
    }

    public BlockState rotate(BlockState state, BlockRotation rot) {
        return state.with(FACING, rot.rotate(state.get(FACING)));
    }

    public BlockState mirror(BlockState state, BlockMirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.get(FACING)));
    }

    @Override
    public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facing.equals(stateIn.get(FACING)) && stateIn.get(PAIRED) && !(BrickMatches(this, worldIn.getBlockState(facingPos)))) {
            return stateIn.with(PAIRED, false);
        }
        return super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING, PAIRED);
    }

    public enum BrickType {
        STONE,
        END_STONE
    }
}

