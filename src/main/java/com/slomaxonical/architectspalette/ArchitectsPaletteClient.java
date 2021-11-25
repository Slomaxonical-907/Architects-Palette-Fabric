package com.slomaxonical.architectspalette;

import com.slomaxonical.architectspalette.integration.APBlockData;
import net.fabricmc.api.ClientModInitializer;

public class ArchitectsPaletteClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        APBlockData.getCutoutLayer();
    }
}
