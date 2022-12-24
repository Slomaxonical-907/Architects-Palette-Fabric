package com.slomaxonical.architectspalette.mixin;

import net.minecraft.client.texture.SpriteAtlasTexture;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import com.slomaxonical.architectspalette.event.UploadSpritesStitchCallback;

// credits to @AlphaMode/Star
@Mixin(SpriteAtlasTexture.class)
public class SpriteAtlasTextureMixin { //currently unused
    @Inject(method = "upload", at = @At("TAIL"))
    public void star$onUploadStitch(SpriteAtlasTexture.Data data, CallbackInfo ci) {
        UploadSpritesStitchCallback.STITCH.invoker().onSpritesStitch(data, (SpriteAtlasTexture) (Object) this);
    }
}