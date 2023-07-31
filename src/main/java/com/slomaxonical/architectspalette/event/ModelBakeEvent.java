package com.slomaxonical.architectspalette.event;


import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.util.Identifier;

import java.util.Map;

// credits to @AlphaMode/Star
public interface ModelBakeEvent {
    Event<ModelBakeEvent> ON_MODELS_BAKED = EventFactory.createArrayBacked(ModelBakeEvent.class, callbacks -> (modelManager, existingModels, loader) -> {
        for(ModelBakeEvent e : callbacks)
            e.onModelsBaked(modelManager, existingModels, loader);
    });

    void onModelsBaked(BakedModelManager modelManager, Map<Identifier, BakedModel> existingModels, ModelLoader loader);
}

