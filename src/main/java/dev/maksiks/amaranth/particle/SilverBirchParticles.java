package dev.maksiks.amaranth.particle;

import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;

import javax.annotation.Nullable;

public class SilverBirchParticles extends TextureSheetParticle {
    private static final float ACCELERATION_SCALE = 0.0025F;
    private static final int INITIAL_LIFETIME = 600;
    private static final int CURVE_ENDPOINT_TIME = 300;
    private static final float FALL_ACC = 0.25F;
    private static final float WIND_BIG = 2.0F;
    private float rotSpeed;
    private final float particleRandom;
    private final float spinAcceleration;

    private static long nextFlipTime = 0;
    private static long lastFlipTime = 0;
    private static double windDirection = 1.0;

    private static double getGlobalWindFactor(ClientLevel level) {
        long gameTime = level.getGameTime();

        if (gameTime >= nextFlipTime) {
            lastFlipTime = gameTime;
            int duration = 80 + level.random.nextInt(60); // 80–140 ticks = ~4–7s
            nextFlipTime = gameTime + duration;

            windDirection = level.random.nextBoolean() ? 1.0 : -1.0;
        }

        double progress = (double)(gameTime - lastFlipTime) / (nextFlipTime - lastFlipTime);
        double smooth = Math.sin(progress * Math.PI); // starts at 0, peaks at 1, back to 0

        return windDirection * smooth;
    }

    protected SilverBirchParticles(ClientLevel level, double x, double y, double z, SpriteSet spriteSet, double pXSpeed, double pYSpeed, double pZSpeed) {
        super(level, x, y, z);
        this.setSprite(spriteSet.get(this.random.nextInt(12), 12));
        this.rotSpeed = (float)Math.toRadians(this.random.nextBoolean() ? -30.0 : 30.0);
        this.particleRandom = this.random.nextFloat();
        this.spinAcceleration = (float)Math.toRadians(this.random.nextBoolean() ? -5.0 : 5.0);
        this.lifetime = 400 + this.random.nextInt(200);
        this.gravity = 7.5E-4F;
        float f = this.random.nextBoolean() ? 0.05F : 0.075F;
        this.quadSize = f;
        this.setSize(f, f);
        this.friction = 0.9F;
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_OPAQUE;
    }

    @Override
    public void tick() {
        this.xo = this.x;
        this.yo = this.y;
        this.zo = this.z;
        if (this.lifetime-- <= 0) {
            this.remove();
        }

        if (!this.removed) {
            float f = (float)(INITIAL_LIFETIME - this.lifetime);
            float f1 = Math.min(f / (float)INITIAL_LIFETIME, 1.0F);
            double d0 = Math.cos(Math.toRadians((double)(this.particleRandom * 60.0F))) * 2.0 * Math.pow((double)f1, 1.25);
            double d1 = Math.sin(Math.toRadians((double)(this.particleRandom * 60.0F))) * 2.0 * Math.pow((double)f1, 1.25);

            double wind = getGlobalWindFactor(this.level);

            this.xd += d0 * 0.0025F * wind;
            this.zd += d1 * 0.0025F * wind;
            this.yd = this.yd - (double)this.gravity;
            this.rotSpeed = this.rotSpeed + this.spinAcceleration / 20.0F;
            this.oRoll = this.roll;
            this.roll = this.roll + this.rotSpeed / 20.0F;
            this.move(this.xd, this.yd, this.zd);
            if (this.onGround || this.lifetime < 599 && (this.xd == 0.0 || this.zd == 0.0)) {
                this.remove();
            }

            if (!this.removed) {
                this.xd = this.xd * (double)this.friction;
                this.yd = this.yd * (double)this.friction;
                this.zd = this.zd * (double)this.friction;
            }
        }
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet spriteSet;

        public Provider(SpriteSet spriteSet) {
            this.spriteSet = spriteSet;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType simpleParticleType, ClientLevel clientLevel,
                                       double pX, double pY, double pZ, double pXSpeed, double pYSpeed, double pZSpeed) {
            return new SilverBirchParticles(clientLevel, pX, pY, pZ, this.spriteSet, pXSpeed, pYSpeed, pZSpeed);
        }
    }
}
