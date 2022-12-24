package com.slomaxonical.architectspalette.models;

import com.slomaxonical.architectspalette.ArchitectsPalette;
import com.slomaxonical.architectspalette.event.ModelBakeEvent;
import com.slomaxonical.architectspalette.models.util.SpriteShift;
import com.slomaxonical.architectspalette.registry.APBlocks;
import net.minecraft.block.Block;
import net.minecraft.client.render.block.BlockModels;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.util.ModelIdentifier;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.function.Function;

//credits to create & @AlphaMode/Star
public class ModelSwapper {
    private static final HashMap<Block, Function<BakedModel, BakedModel>> customBlockModels = new HashMap<>();

    public static void init() {
        ModelBakeEvent.ON_MODELS_BAKED.register((bakedModelManager, existingModels, modelLoader) -> {
           //test for now
            SpriteShift birchShift = new SpriteShift(new Identifier(ArchitectsPalette.MOD_ID,"block/birch_boards"),new Identifier(ArchitectsPalette.MOD_ID, "block/birch_boards_odd"));
            swapBlockModel(APBlocks.BIRCH_BOARDS,bakedModel -> new BoardModel(bakedModel,birchShift));

            customBlockModels.forEach((block, swapFunction) -> {
                getAllBlockStateModelLocations(block).forEach(modelId -> {
                    existingModels.put(modelId, swapFunction.apply(existingModels.get(modelId)));
                });
            });
        });
    }

    public static void swapBlockModel(Block block, Function<BakedModel, BakedModel> oldToNewFunction) {
        customBlockModels.put(block, oldToNewFunction);
    }

    public static List<ModelIdentifier> getAllBlockStateModelLocations(Block block) {
        List<ModelIdentifier> models = new ArrayList<>();
        Identifier blockRl = Registry.BLOCK.getId(block);
        block.getStateManager()
                .getStates()
                .forEach(state -> {
                    models.add(BlockModels.getModelId(blockRl, state));
                });
        return models;
    }
}
