package com.slomaxonical.architectspalette.blocks.abyssaline;

import com.slomaxonical.architectspalette.blocks.util.APBlockSettings;
import com.slomaxonical.architectspalette.registry.util.IBlockSetBase;
import com.slomaxonical.architectspalette.registry.util.StoneBlockSet;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;

import java.util.function.ToIntFunction;

public class AbyssalineBlock extends Block implements IAbyssalineChargeable, IBlockSetBase {
    public static final BooleanProperty CHARGED = APBlockSettings.CHARGED;

    public static final DirectionProperty CHARGE_SOURCE = DirectionProperty.of("charge_source",
            Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST, Direction.UP, Direction.DOWN);

    public AbyssalineBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getStateManager().getDefaultState().with(CHARGED, false).with(CHARGE_SOURCE, Direction.NORTH));
    }
    //IBlockSetBase method
    public Block getBlockForPart(StoneBlockSet.SetComponent part, FabricBlockSettings settings, Block base) {
        return switch (part) {
            case SLAB -> new AbyssalineSlabBlock(settings);
            case VERTICAL_SLAB -> new AbyssalineVerticalSlabBlock(settings);
            default -> IBlockSetBase.super.getBlockForPart(part, settings, base);
        };
    }
    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(CHARGED, CHARGE_SOURCE);
    }

    public static ToIntFunction<BlockState> getLuminance() {
        return blockState -> blockState.get(CHARGED) ? 1 : 0;
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
//        context.getWorld().createAndScheduleBlockTick(context.getBlockPos(), this, 1);
//        return this.getDefaultState();
        return AbyssalineHelper.getStateWithNeighborCharge(this.getDefaultState(), context.getWorld(), context.getBlockPos());
    }


}