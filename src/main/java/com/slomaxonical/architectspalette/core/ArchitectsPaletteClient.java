package com.slomaxonical.architectspalette.core;

import com.slomaxonical.architectspalette.core.integration.APBlockData;
import net.fabricmc.api.ClientModInitializer;

public class ArchitectsPaletteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        APBlockData.getCutoutLayer();
    }
}
