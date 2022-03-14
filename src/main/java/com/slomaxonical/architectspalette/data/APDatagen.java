package com.slomaxonical.architectspalette.data;

import com.slomaxonical.architectspalette.data.provider.APBlockLootTableProvider;
import com.slomaxonical.architectspalette.data.provider.APBlockTagProvider;
import com.slomaxonical.architectspalette.data.provider.APItemTagProvider;
import com.slomaxonical.architectspalette.data.provider.APRecipeProvider;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;

public class APDatagen implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        dataGenerator.addProvider(APRecipeProvider::new);
        FabricTagProvider.BlockTagProvider blockTagProvider = new APBlockTagProvider(dataGenerator);
        dataGenerator.addProvider(blockTagProvider);
        dataGenerator.addProvider(new APItemTagProvider(dataGenerator,blockTagProvider));
        dataGenerator.addProvider(APBlockLootTableProvider::new);
    }
}
