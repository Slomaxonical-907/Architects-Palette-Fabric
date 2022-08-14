package com.slomaxonical.architectspalette.mixin;

import com.slomaxonical.architectspalette.registry.APBlocks;
import com.slomaxonical.architectspalette.registry.APParticles;
import net.minecraft.block.BlockState;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(ProjectileEntity.class)
public abstract class ProjectileEntityMixin extends Entity {

    public ProjectileEntityMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    @Inject(method = "onCollision", at = @At(value="INVOKE",target = "Lnet/minecraft/entity/projectile/ProjectileEntity;onBlockHit(Lnet/minecraft/util/hit/BlockHitResult;)V"), cancellable = true)
    private void skippingCollisionInject(HitResult hit, CallbackInfo cir) {
        BlockHitResult hitResult = (BlockHitResult)hit;
        BlockState state = this.world.getBlockState(hitResult.getBlockPos());
        if (state.isOf(APBlocks.WARDSTONE)){
            //Get normal
            Vec3d normal = Vec3d.of(hitResult.getSide().getVector());
            //Get velocity
            Vec3d motion = this.getVelocity();
            if (motion.length()>0.2){
                double dot = motion.dotProduct(normal);

                Vec3d a = normal.multiply(-dot * 2);
                this.setVelocity(motion.add(a));

                Vec3d vec3 = this.getVelocity();
                double d0 = vec3.horizontalLength();
                float xrot = ((float) (MathHelper.atan2(vec3.y, d0) * (180F / Math.PI)));
                float yrot = ((float) (MathHelper.atan2(vec3.x, vec3.z) * (180F / Math.PI)));

                Vec3d hitPos = this.getPos();
                this.updatePositionAndAngles(hitPos.x, hitPos.y, hitPos.z, yrot, xrot);

                hitPos = hitResult.getPos().add(normal.multiply(.02));
                if (this.world.isClient()) {
                    this.world.addParticle(APParticles.WIZARDLY_DEFENSE_BLAST, hitPos.x, hitPos.y, hitPos.z, normal.x, normal.y, normal.z);
                }
                BlockHitResult check = this.world.raycast(new RaycastContext(this.getPos(), this.getPos().add(vec3), RaycastContext.ShapeType.COLLIDER, RaycastContext.FluidHandling.NONE, this));
                if (check.getType() != HitResult.Type.MISS && !check.isInsideBlock() && (check.getBlockPos() != hitResult.getBlockPos())) {
                    this.setPosition(check.getPos().subtract(vec3.multiply(1.1)));
                }
            }
            cir.cancel();
        }
    }
}
