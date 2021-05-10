package com.slomaxonical.architectspalette.core;

import com.slomaxonical.architectspalette.core.crafting.WarpingRecipe;
import com.slomaxonical.architectspalette.core.integration.APBlockData;
import com.slomaxonical.architectspalette.core.registry.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.registry.FuelRegistry;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class ArchitectsPalette implements ModInitializer {

    public static final String MOD_ID = "architects_palette";
    public static final Logger LOGGER = LogManager.getLogger(MOD_ID.toUpperCase());



//    public static final RecipeType<WarpingRecipe> WARPING_RECIPE_TYPE ;
//    public static final RecipeSerializer<WarpingRecipe> WARPING_RECIPE_SERIALIZER;
//
//    static {
//        WARPING_RECIPE_TYPE = Registry.register(Registry.RECIPE_TYPE, new Identifier(MOD_ID, "warping"), new RecipeType<WarpingRecipe>() {
//            @Override
//            public String toString() {return ArchitectsPalette.MOD_ID.concat(":warping");}
//        });
//        WARPING_RECIPE_SERIALIZER = Registry.register(Registry.RECIPE_SERIALIZER, new Identifier(MOD_ID, "warping"), new RecipeSerializer<WarpingRecipe>() {
//            @Override
//            public WarpingRecipe read(Identifier id, JsonObject json) {
//                return null;
//            }
//
//            @Override
//            public WarpingRecipe read(Identifier id, PacketByteBuf buf) {
//                return null;
//            }
//
//            @Override
//            public void write(PacketByteBuf buf, WarpingRecipe recipe) {
//
//            }
//        });
//    }


    @Override
    public void onInitialize() {

        APBlocks.registerBlocks();
        APItems.registerItems();
        APItemgroup.registerItemgroup();

        APSounds.registerSounds();
        APBlockEntities.registerBlockEntity();
        WarpingRecipe.registerRecipe();
        APTrades.registerVillagerTrades();
        APTrades.registerWanderingTrades();

        APBlockData.addStrippables();
        APBlockData.getCutoutLayer();

        //todo: add to registry.util
        FuelRegistry.INSTANCE.add(APBlocks.CHARCOAL_BLOCK, 1600);

    }
}
