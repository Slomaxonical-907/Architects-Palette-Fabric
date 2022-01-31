package com.slomaxonical.architectspalette.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.util.math.BlockPos;

public class BoardBlock extends Block {
    private static final BooleanProperty ODD_X = BooleanProperty.of("odd_x");
    private static final BooleanProperty ODD_Z = BooleanProperty.of("odd_z");

    public BoardBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.getDefaultState().with(ODD_X, false).with(ODD_Z,false));
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(ODD_X, ODD_Z);
    }
    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {

        return getStateForPosition(getDefaultState(),context.getBlockPos());
    }
    private static BlockState getStateForPosition(BlockState stateIn, BlockPos pos) {
        return stateIn.with(ODD_X, isOdd(pos.getX())).with(ODD_Z, isOdd(pos.getZ()));
    }
    private static boolean isOdd(int num) {
        return (Math.abs(num) % 2) == 1;
    }
}
