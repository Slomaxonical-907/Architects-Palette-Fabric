package com.slomaxonical.architectspalette.event;

import net.fabricmc.fabric.api.event.Event;
import net.fabricmc.fabric.api.event.EventFactory;
import net.minecraft.client.texture.SpriteAtlasTexture;

// credits to @AlphaMode/Star
public interface UploadSpritesStitchCallback {//currently unused
    Event<UploadSpritesStitchCallback> STITCH = EventFactory.createArrayBacked(UploadSpritesStitchCallback.class, callbacks -> (data, atlasTexture) -> {
        for(UploadSpritesStitchCallback e : callbacks)
            e.onSpritesStitch(data, atlasTexture);
    });

    void onSpritesStitch(SpriteAtlasTexture.Data data, SpriteAtlasTexture atlasTexture);
}
