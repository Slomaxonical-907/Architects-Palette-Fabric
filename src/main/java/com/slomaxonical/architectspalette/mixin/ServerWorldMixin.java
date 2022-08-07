package com.slomaxonical.architectspalette.mixin;
import com.slomaxonical.architectspalette.event.ChangeDimensionHandler;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ItemEntity;
import net.minecraft.server.world.ServerWorld;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerWorld.class)
public class ServerWorldMixin {

    @Inject(method = "onDimensionChanged", at = @At("HEAD"))
    public void warpingCheck(Entity entity, CallbackInfo callbackInfo) {
        ServerWorld world = (ServerWorld) (Object) this;
        if (entity instanceof ItemEntity) {
            ChangeDimensionHandler.warpItem((ItemEntity) entity, world);
        }
    }
}
