package com.slomaxonical.architectspalette.features;

import com.mojang.serialization.Codec;
import com.slomaxonical.architectspalette.data.provider.APBlockTagProvider;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3f;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.util.math.random.Random;

import java.util.ArrayList;
import java.util.List;

public class CrystalClusterFeature extends Feature<CrystalClusterConfig> {
    public CrystalClusterFeature(Codec<CrystalClusterConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<CrystalClusterConfig> context) {
        //Check for free space
        if (!context.getWorld().isAir(context.getOrigin())) return false;
        if (context.getConfig().hanging()){
            //Check for being on a ceiling
            if (context.getWorld().isAir(context.getOrigin().up())) return false;
        }else {
            //check for being on a floor
            if (context.getWorld().isAir(context.getOrigin().down())) return false;
        }

        CrystalClusterConfig config = context.getConfig();
        Random random = context.getRandom();

        //Set horizontal angle used to skew the shelves of crystals
        Vec3f shelfAngle = Vec3f.NEGATIVE_X.copy();
        shelfAngle.rotate(Vec3f.POSITIVE_Y. getDegreesQuaternion(random.nextFloat(360)));

        //Set horizontal angle that determines the spaces between shelves
        Vec3f formationAngle = shelfAngle.copy();
        formationAngle.rotate(Vec3f.POSITIVE_Y.getDegreesQuaternion(fRandomRange(random,-15,15) + 90));

        Vec3f placePos = new Vec3f(context.getOrigin().getX(), context.getOrigin().getY(), context.getOrigin().getZ());

        List<BlockPos> posList = new ArrayList<>(List.of());
        //4-7 shelves
        int shelves = iRandomRange(random,4,7);
        for (int i = 0; i < shelves; i++) {
            float scale = ((float)i+1)/shelves;
            //1-7 pillars each shelf
            int pillars = iRandomRange(random,1,7);
            placeShelf(new BlockPos(placePos.getX(), placePos.getY(), placePos.getZ()), pillars, shelfAngle, scale, context,posList);

            //Offset next shelf position
            formationAngle.normalize();
            formationAngle.scale(fRandomRange(random,0.5F,2));
            placePos.add(formationAngle);
        }
        for (BlockPos pos: posList){
            tryPlaceExtrusion(pos, context.getWorld(), config.extrusionState(), config.crystalState().getBlock(), config.hanging()? 1 : -1, random);
        }
        return true;
    }
    private static void placeShelf(BlockPos startPos, int crystals, Vec3f shelfAngle, float shelfScale, FeatureContext<CrystalClusterConfig> context, List<BlockPos> crystalList) {
        Random random = context.getRandom();
        CrystalClusterConfig config = context.getConfig();
        StructureWorldAccess world = context.getWorld();

        int flip = config.hanging() ? 1 : -1;
        Vec3f placePos = new Vec3f(startPos.getX(), startPos.getY(), startPos.getZ());
        placePos.add(0, -2*flip, 0);

        for (int i = 0; i < crystals; i++) {

            Vec3f offset = shelfAngle.copy();
            offset.scale(fRandomRange(random,0.5F,2.5F));

            placePos.add(offset);

            int pillarLength = config.minLength() + random.nextInt((int)(Math.floor((config.maxLength()- config.minLength())*shelfScale)) + 1);
            placePillar(new BlockPos.Mutable(placePos.getX(), placePos.getY(), placePos.getZ()), pillarLength, world, context, flip, crystalList);

            if (pillarLength > (config.maxLength() - config.minLength())/2 && random.nextBoolean()) {
                BlockPos.Mutable pos = new BlockPos.Mutable(
                        placePos.getX() + iRandomRange(random,-1,1),
                        placePos.getY() + iRandomRange(random,-1,1),
                        placePos.getZ() + iRandomRange(random,-1,1));
                placePillar(pos,pillarLength/2, world, context,flip,crystalList);
            }
        }
    }

    private static void placePillar(BlockPos.Mutable placePos, int length, StructureWorldAccess world, FeatureContext<CrystalClusterConfig> context , int flip,List<BlockPos> crystalList) {
        CrystalClusterConfig config = context.getConfig();
        // Go up until a ceiling is found, give up if no ceiling found
        int tries = 10;
        while (canReplaceAt(world,placePos)) {
            placePos.setY(placePos.getY() + flip);
            if (world.getBlockState(placePos).isOf(Blocks.LAVA)) return;
            if (tries-- == 0) {
                return;
            }
        }
        //Move out of ceilings
        tries = 5;
        while (!canReplaceAt(world,placePos)) {
            placePos.setY(placePos.getY() - flip);
            if (tries-- == 0) {
                return;
            }
        }
        //Place Pillar
        boolean doExtrusion = true;
        while (--length >= 0) {
            if (!canReplaceAt(world,placePos)) return;
            if (doExtrusion){
                crystalList.add(placePos.toImmutable());
//                tryPlaceExtrusion(placePos.toImmutable(), world, config.extrusionState(), config.crystalState().getBlock(), flip, context.getRandom());
                doExtrusion = false;
            }
            world.setBlockState(placePos, config.crystalState(), 2);
            placePos.setY(placePos.getY() - flip);
        }
    }
    private static void tryPlaceExtrusion(BlockPos startPos, StructureWorldAccess world, BlockState placeState, Block avoidBlock, int flip, Random random) {
        for (Direction dir : Direction.values()) {
            if (dir.getAxis() != Direction.Axis.Y) {
                if (random.nextInt(3) != 1) continue;
                BlockPos pos = startPos.offset(dir, 1);
                while (world.getBlockState(pos).isOf(placeState.getBlock()) && Math.abs(startPos.getY() - pos.getY()) <= 4) pos = pos.down(flip);
                BlockPos above = pos.up();
                BlockPos below = pos.down();
                if (canReplaceAt(world, above) ^ canReplaceAt(world, below)) {
                    if (!world.getBlockState(above).isOf(avoidBlock) && !world.getBlockState(below).isOf(avoidBlock)) {
                        for (int i = iRandomRange(random, 1, 2); i > 0; i--) {
                            if (canReplaceAt(world, pos)) {
                                world.setBlockState(pos, placeState, 2);
                                pos = pos.down(flip);
                            }
                            else break;
                        }
                    }
                }
            }
        }
    }


    private static int iRandomRange(Random random, int min, int max) {
        return min + (random.nextInt((max - min) + 1));
    }

    private static float fRandomRange(Random random, float min, float max) {
        return min + (random.nextFloat((max - min)));
    }

    private static boolean canReplaceAt(StructureWorldAccess level, BlockPos pos) {
        return canReplace(level.getBlockState(pos));
    }

    private static boolean canReplace(BlockState state) {
        return state.isAir() || state.getMaterial().isReplaceable() || state.isIn(APBlockTagProvider.CRYSTAL_REPLACEABLE);
    }
}
