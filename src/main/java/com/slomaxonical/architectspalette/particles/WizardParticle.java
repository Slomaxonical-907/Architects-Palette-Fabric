package com.slomaxonical.architectspalette.particles;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.particle.*;
import net.minecraft.client.render.Camera;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.texture.Sprite;
import net.minecraft.client.world.ClientWorld;
import net.minecraft.particle.BlockStateParticleEffect;
import net.minecraft.particle.DefaultParticleType;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Quaternion;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3f;
import org.jetbrains.annotations.Nullable;

@Environment(EnvType.CLIENT)
public class WizardParticle extends SpriteBillboardParticle {
        protected SpriteProvider sprites;
        private final Quaternion rotation;
        private static final Vec3d DEFAULT_PARTICLE_DIRECTION = new Vec3d(0, 0, -1);
        protected WizardParticle(ClientWorld clientWorld,  double xPos, double yPos, double zPos, double xSpeed, double ySpeed, double zSpeed, SpriteProvider sprite) {
            super(clientWorld, xPos, yPos, zPos, xSpeed, ySpeed, zSpeed);
            velocityX = 0;
            velocityY = 0;
            velocityZ = 0;
            sprites = sprite;
            setSpriteForAge(sprite);
            setMaxAge(6);
            scale(2);

            Vec3d facing = new Vec3d(xSpeed, ySpeed, zSpeed).normalize();
            Vec3d def = DEFAULT_PARTICLE_DIRECTION;
            Vec3d cross = def.crossProduct(facing).normalize();

            double rotationAngle = Math.acos(def.dotProduct(facing));
            Vec3f rotationAxis;
            //Fixes particles facing towards +-Z
            if (cross.length() == 0) {
                rotationAxis = new Vec3f(1, 0, 0);
            }
            else {
                rotationAxis = new Vec3f((float) cross.x, (float) cross.y, (float) cross.z);
            }
            rotation = new Quaternion(rotationAxis, (float) rotationAngle, false);
        }

    @Override
    public void tick() {
        super.tick();
        setSpriteForAge(sprites);
    }
    @Override
    public ParticleTextureSheet getType() {
        return ParticleTextureSheet.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void buildGeometry(VertexConsumer vertexConsumer, Camera camera, float partialTicks) {
        Vec3d pos = camera.getPos();
        Vec3f center = new Vec3f(
                (float) (MathHelper.lerp(partialTicks, prevPosX, x) - pos.x),
                (float) (MathHelper.lerp(partialTicks, prevPosY, y) - pos.y),
                (float) (MathHelper.lerp(partialTicks, prevPosZ, z) - pos.z)
        );
        Quaternion quat;
        if (angle == 0) {
            quat = rotation;
        }
        else {
            quat = rotation.copy();
            quat.hamiltonProduct(Vec3f.POSITIVE_Z.getRadialQuaternion(MathHelper.lerp(partialTicks, prevAngle, angle)));//!hereA
        }

        //I could clean this up, but I don't want to. It's just copied from the default particle.
        Vec3f[] aVec3f = new Vec3f[]{new Vec3f(-1.0F, -1.0F, 0.0F), new Vec3f(-1.0F, 1.0F, 0.0F), new Vec3f(1.0F, 1.0F, 0.0F), new Vec3f(1.0F, -1.0F, 0.0F)};
        float size = this.getSize(partialTicks);

        for(int i = 0; i < 4; ++i) {
            Vec3f Vec3f = aVec3f[i];
            Vec3f.rotate(quat);//here!
            Vec3f.scale(size);
            Vec3f.add(center);
        }

        float f7 = this.getMinU();
        float f8 = this.getMaxU();
        float f5 = this.getMinV();
        float f6 = this.getMaxV();
        int j = this.getBrightness(partialTicks);
        vertexConsumer.vertex(aVec3f[0].getX(), aVec3f[0].getY(), aVec3f[0].getZ()).texture(f8, f6).color(this.red, this.green, this.blue, this.alpha).light(j).next();
        vertexConsumer.vertex(aVec3f[1].getX(), aVec3f[1].getY(), aVec3f[1].getZ()).texture(f8, f5).color(this.red, this.green, this.blue, this.alpha).light(j).next();
        vertexConsumer.vertex(aVec3f[2].getX(), aVec3f[2].getY(), aVec3f[2].getZ()).texture(f7, f5).color(this.red, this.green, this.blue, this.alpha).light(j).next();
        vertexConsumer.vertex(aVec3f[3].getX(), aVec3f[3].getY(), aVec3f[3].getZ()).texture(f7, f6).color(this.red, this.green, this.blue, this.alpha).light(j).next();
    }

    @Environment(EnvType.CLIENT)
    public static class Factory implements ParticleFactory<DefaultParticleType> {
        private final SpriteProvider spriteProvider;

        public Factory(SpriteProvider spriteProvider) {
            this.spriteProvider = spriteProvider;
        }

        @Override
        public Particle createParticle(DefaultParticleType parameters, ClientWorld world, double x, double y, double z, double velocityX, double velocityY, double velocityZ) {
            return new WizardParticle(world,x, y, z, velocityX, velocityY, velocityZ, spriteProvider);
        }
    }
}
