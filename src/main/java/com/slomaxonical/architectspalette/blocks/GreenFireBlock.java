package com.slomaxonical.architectspalette.blocks;

import com.slomaxonical.architectspalette.registry.APTags;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.AbstractFireBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

public class GreenFireBlock extends AbstractFireBlock {

    //More or less a copy of SoulFireBlock. Just appropriated for its new purpose.
    public GreenFireBlock(AbstractBlock.Settings settings) {
        //Does three damage because I think it's funny
        super(settings, 3.0F);
    }

    //It's the little things. Pick block on the fire will give you flint and steel to light one. Vanilla doesn't do this.
    @Override
    public ItemStack getPickStack(BlockView world, BlockPos pos, BlockState state) {
        return new ItemStack(Items.FLINT_AND_STEEL);
    }


    @Override
    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos) {
        return this.canPlaceAt(state,world, pos) ? this.getDefaultState() : Blocks.AIR.getDefaultState();
    }

    @Override
    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        return canHeGreen(world, pos.down());
    }

    public static boolean canHeGreen(BlockView world,BlockPos pos) {
        BlockState state = world.getBlockState(pos);
        return state.isIn(APTags.GREEN_FIRE_SUPPORTING) && state.isSideSolidFullSquare(world,pos,Direction.UP);
    }

    @Override
    protected boolean isFlammable(BlockState state) {
        return true;
    }
}
