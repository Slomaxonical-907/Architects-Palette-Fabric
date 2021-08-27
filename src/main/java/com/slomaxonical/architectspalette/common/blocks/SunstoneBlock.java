package com.slomaxonical.architectspalette.common.blocks;

import com.slomaxonical.architectspalette.common.blocks.abyssaline.AbyssalineBlock;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.function.Function;
import java.util.function.ToIntFunction;

@SuppressWarnings("deprecation")
public class SunstoneBlock extends Block {
    public static final IntProperty LIGHT = IntProperty.of("light", 0, 2);

    public Function<World, Integer> lightSupplier;

    public SunstoneBlock(Settings properties, Function<World, Integer> getLightState) {
        super(properties);
        this.lightSupplier = getLightState;
        this.setDefaultState(this.stateManager.getDefaultState().with(LIGHT, 0));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(LIGHT);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext context) {
        return this.getDefaultState().with(LIGHT, this.lightSupplier.apply(context.getWorld()));
    }

    @Override
    public boolean hasRandomTicks(BlockState state) {
        return true;
    }
//
//    public static ToIntFunction<BlockState> getLuminance() {
//        return blockState -> blockState.get(SunstoneBlock.LIGHT) * 7;
//    }
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        if (!worldIn.isClient) {
            Integer lightstate = this.lightSupplier.apply(worldIn);
            if (!lightstate.equals(state.get(LIGHT))) {
                worldIn.setBlockState(pos, state.with(LIGHT, lightstate), 2 | 4);
            }
        }
    }
    @Override
    public BlockState getStateForNeighborUpdate(BlockState stateIn, Direction facing, BlockState facingState, WorldAccess worldIn, BlockPos currentPos, BlockPos facingPos) {
        if (facingState.getBlock() instanceof SunstoneBlock) {
//            Random rand = worldIn.getRandom();
//            if (rand.nextBoolean() && rand.nextBoolean()) {
                worldIn.getBlockTickScheduler().schedule(currentPos, this, 8);
//            }
        }
        return super.getStateForNeighborUpdate(stateIn, facing, facingState, worldIn, currentPos, facingPos);
    }

//    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
//        worldIn.getBlockTickScheduler().schedule(pos, this, 1);
//    }

    public static Integer sunstoneLight(World world) {
        return getLightFromTime(world, 0);
    }
    public static Integer moonstoneLight(World world) {
        return getLightFromTime(world, 12000L);
    }

    private static Integer getLightFromTime(World world, long offset) {
        MinecraftServer s = world.getServer();
        if (s == null) { return 0; }
        ServerWorld overworld = s.getWorld(World.OVERWORLD);
        if (overworld != null) {
            long time = (overworld.getTimeOfDay() + offset) % 24000;
            if (time >= 13000 && time <= 23000) return 0;
            if (time >= 3000 && time <= 9000) return 2;
            return 1;
        }
        return 0;
    }
    //not yet used
    public static boolean isSolid(BlockState state, BlockView view, BlockPos pos) {
        return state.get(LIGHT) == 0;
    }
    public static int lightValue(BlockState state) {
        return state.get(LIGHT) * 7;
    }

}
