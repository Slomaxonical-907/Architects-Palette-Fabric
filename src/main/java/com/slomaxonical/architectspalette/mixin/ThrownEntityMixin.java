package com.slomaxonical.architectspalette.mixin;

import com.slomaxonical.architectspalette.registry.APTags;
import net.minecraft.block.BlockState;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.projectile.ProjectileUtil;
import net.minecraft.entity.projectile.thrown.ThrownEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ThrownEntity.class)
public abstract class ThrownEntityMixin extends ProjectileEntity {

    public ThrownEntityMixin(EntityType<?> type, World world) {
        super(null,null);

    }

    @Inject(method = "tick", at = @At(value="INVOKE",target = "Lnet/minecraft/entity/projectile/thrown/ThrownEntity;onCollision(Lnet/minecraft/util/hit/HitResult;)V"), cancellable = true)
    private void skippingRestOfTick(CallbackInfo cir) {
        HitResult hitResult = ProjectileUtil.getCollision(this, this::canHit);
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            BlockHitResult blockHitResult = (BlockHitResult) hitResult;
            BlockState state = this.world.getBlockState(blockHitResult.getBlockPos());
            if (state.isIn(APTags.WIZARD_BLOCKS) &&  this.getVelocity().length() > 0.25){
                super.onCollision(hitResult);
                cir.cancel();
            }
        }
    }
}
