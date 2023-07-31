package com.slomaxonical.architectspalette.mixin;

import com.slomaxonical.architectspalette.event.ModelBakeEvent;
import net.minecraft.client.render.model.BakedModel;
import net.minecraft.client.render.model.BakedModelManager;
import net.minecraft.client.render.model.ModelLoader;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

// credits to @AlphaMode/Star
@Mixin(BakedModelManager.class)
public class BakedModelManagerMixin {
    @Shadow
    private Map<Identifier, BakedModel> models;

    @Inject(method = "apply(Lnet/minecraft/client/render/model/ModelLoader;Lnet/minecraft/resource/ResourceManager;Lnet/minecraft/util/profiler/Profiler;)V", at = @At(value = "INVOKE_STRING", target = "Lnet/minecraft/util/profiler/Profiler;swap(Ljava/lang/String;)V", args = "ldc=cache", shift = At.Shift.BEFORE))
    public void AP$modelLoad(ModelLoader modelLoader, ResourceManager resourceManager, Profiler profiler, CallbackInfo ci) {
        ModelBakeEvent.ON_MODELS_BAKED.invoker().onModelsBaked((BakedModelManager) (Object) this, models, modelLoader);
    }
}