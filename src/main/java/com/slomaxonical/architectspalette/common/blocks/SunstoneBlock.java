package com.slomaxonical.architectspalette.common.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.IntProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;

import java.util.Random;
import java.util.function.Function;

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

    /*@Override
    public int getLightValue(BlockState state, BlockView world, BlockPos pos) {
        return state.get(LIGHT) * 7;
    }
*/
    //todo: fix this see if it has any meaning aslan
   /* @Override
    public int getLuminance(BlockState state) {
        return state.getLuminance()*7;
    }*/
    public void scheduledTick(BlockState state, ServerWorld worldIn, BlockPos pos, Random rand) {
        worldIn.getBlockTickScheduler().schedule(pos, this, 100 + rand.nextInt(5));
        Integer lightstate = this.lightSupplier.apply(worldIn);
        if (!lightstate.equals(state.get(LIGHT))) {
            worldIn.setBlockState(pos, state.with(LIGHT, lightstate));
        }
    }

    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getBlockTickScheduler().schedule(pos, this, 1);
    }

    public static Integer sunstoneLight(World world) {
        MinecraftServer s = world.getServer();
        if (s == null) { return 0; }
        ServerWorld overworld = s.getWorld(World.OVERWORLD);
        if (overworld != null) {
            long time = overworld.getTimeOfDay() % 24000;
            if (time >= 12000) return 0;
            if (time >= 3000 && time <= 9000) return 2;
            return 1;
        }
        return 0;
    }

    // its sloppy but i dont know if i care to clean it up rn.
    public static Integer moonstoneLight(World world) {
        MinecraftServer s = world.getServer();
        if (s == null) { return 0; }
        ServerWorld overworld = s.getWorld(World.OVERWORLD);
        if (overworld != null) {
            long time = (overworld.getTimeOfDay() + 12000L) % 24000;
            if (time >= 12000) return 0;
            if (time >= 3000 && time <= 9000) return 2;
            return 1;
        }
        return 0;
    }

}
