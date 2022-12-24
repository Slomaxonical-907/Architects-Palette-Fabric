package com.slomaxonical.architectspalette.models;

import com.slomaxonical.architectspalette.models.util.SpriteShift;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.renderer.v1.model.ForwardingBakedModel;
import net.fabricmc.fabric.api.renderer.v1.render.RenderContext;
import net.minecraft.block.BlockState;
import net.minecraft.client.render.model.*;
import net.minecraft.client.render.model.json.ModelOverrideList;
import net.minecraft.client.render.model.json.ModelTransformation;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.BlockRenderView;

import java.util.function.Supplier;

@Environment(EnvType.CLIENT)
public class BoardModel extends ForwardingBakedModel {
    //TODO: Check why reloading the resource pack(f3+T) causes the world to break
    private final SpriteShift spriteShift;
    public BoardModel(BakedModel parent,SpriteShift shift){
        wrapped = parent;
        this.spriteShift= shift;
    }
    @Override
    public ModelTransformation getTransformation() {
        return ModelTransformation.NONE;
    }

    @Override
    public ModelOverrideList getOverrides() {
        return null;
    }

    @Override
    public boolean isVanillaAdapter() {
        return false;
    }

    @Override
    public void emitBlockQuads(BlockRenderView blockView, BlockState state, BlockPos pos, Supplier<Random> randomSupplier, RenderContext context) {
        context.pushTransform(quad -> {
                boolean shift;
                //Check for being horizontal.
                if (quad.lightFace().getId() >= 2) {
                    //If the horizontal faces should be shifted
                    shift = getHorizontal(pos);
                    //Check for north/south
                    if ((quad.lightFace().getId() < 4)) {
                        shift = !shift;
                    }
                }
                //Vertical face.
                else {
                    //If the vertical faces should be shifted
                    shift = getVertical(pos);
                    if (quad.lightFace().getId() == 0) {
                        shift = !shift;
                    }
                }

                if (shift) {
                    float uShift = spriteShift.getUShift();
                    float vShift = spriteShift.getVShift();

                    for (int vertex = 0; vertex < 4; vertex++) {
                        float u = quad.spriteU(vertex,0);
                        float v = quad.spriteV(vertex,0);
                        quad.sprite(vertex,0,u+uShift , v+vShift);
                    }
                }
                return true;
            });
        super.emitBlockQuads(blockView,state,pos,randomSupplier,context);
        context.popTransform();
    }

//    @Override
//    public void emitItemQuads(ItemStack stack, Supplier<Random> randomSupplier, RenderContext context) {
//    }
    private boolean getVertical(BlockPos pos){
        return isOdd(pos.getX());
    }
    private boolean getHorizontal(BlockPos pos){
        return isOdd(pos.getZ()) ^ isOdd(pos.getX());
    }
    private static boolean isOdd(int num) {
        return (Math.abs(num) % 2) == 1;
    }

}
