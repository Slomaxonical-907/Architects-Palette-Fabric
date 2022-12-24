package com.slomaxonical.architectspalette.models.util;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.texture.Sprite;
import net.minecraft.screen.PlayerScreenHandler;
import net.minecraft.util.Identifier;

import java.util.function.Function;

public class SpriteShift {
    protected Sprite from;
    protected Sprite to;

    public SpriteShift(Identifier from_block, Identifier to_block) {
        Function<Identifier, Sprite> atlas = MinecraftClient.getInstance().getSpriteAtlas(PlayerScreenHandler.BLOCK_ATLAS_TEXTURE);
        from = atlas.apply(from_block);
        to = atlas.apply(to_block);
    }

    public float getUShift() {
        return to.getMinU() - from.getMinU();
    }

    public float getVShift() {
        return to.getMinV() - from.getMinV();
    }

//    public float getTargetU(float localU) {
//        return to.getFrameU(getUnInterpolatedU(from, localU));
//    }
//
//    public float getTargetV(float localV) {
//        return to.getFrameV(getUnInterpolatedV(from, localV));
//    }
//    public static float getUnInterpolatedU(Sprite sprite, float u) {
//        float f = sprite.getMaxU() - sprite.getMinU();
//        return (u - sprite.getMinU()) / f * 16.0F;
//    }
//
//    public static float getUnInterpolatedV(Sprite sprite, float v) {
//        float f = sprite.getMaxV() - sprite.getMinV();
//        return (v - sprite.getMinV()) / f * 16.0F;
//    }
}
