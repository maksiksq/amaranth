package dev.maksiks.amaranth.particle;

import net.minecraft.client.Minecraft;
import net.minecraft.client.ParticleStatus;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.particle.*;
import net.minecraft.core.particles.SimpleParticleType;
import net.minecraft.util.Mth;
import org.jetbrains.annotations.Nullable;

import static dev.maksiks.amaranth.ClientConfig.*;

public class AnthocyaninParticles extends TextureSheetParticle {

    private static final float[] COLOR = {0.65f, 0.85f, 1.0f};

    protected AnthocyaninParticles(ClientLevel level, double x, double y, double z,
                                   double vx, double vy, double vz, SpriteSet spriteSet) {
        super(level, x, y, z, vx, vy, vz);

        this.setSprite(spriteSet.get(level.random.nextInt(4), 4));

        this.setColor(COLOR[0], COLOR[1], COLOR[2]);
        this.alpha = 0.9f;
        this.quadSize = 0.08f + level.random.nextFloat() * 0.05f;
        this.lifetime = 200 + level.random.nextInt(100);

        this.gravity = 0.0F;
        this.xd = vx * 0.01D;
        this.yd = vy * 0.01D + 0.002D;
        this.zd = vz * 0.01D;
        this.friction = 0.96F;
    }

    @Override
    public void tick() {
        super.tick();

        ParticleStatus setting = Minecraft.getInstance().options.particles().get();

        // if you have minimal particles, you won't see these
        // i think the override being false should remove them but uhm, I guess it doesn't?
        // + config
        if (setting == ParticleStatus.MINIMAL ||
                HIDE_BIOME_AMBIENCE_PARTICLES.getAsBoolean() ||
                HIDE_ALL_BIOME_PARTICLES.getAsBoolean()) {
            // also see the minimize one somewhere else
            this.remove();
        }

        if (!this.removed) {
            double t = (this.age + this.random.nextFloat()) * 0.1;
            this.xd += Mth.sin((float) t) * 0.0005;
            this.zd += Mth.cos((float) t) * 0.0005;
            this.yd += Math.sin(t * 0.5) * 0.0002;

            float fadeStart = 0.7f;
            if ((float) this.age / this.lifetime > fadeStart) {
                this.alpha = Mth.lerp(((float) this.age / this.lifetime - fadeStart) / (1.0f - fadeStart), 0.9f, 0.0f);
            }
        }
    }

    @Override
    public ParticleRenderType getRenderType() {
        return ParticleRenderType.PARTICLE_SHEET_TRANSLUCENT;
    }

    public static class Provider implements ParticleProvider<SimpleParticleType> {
        private final SpriteSet sprites;

        public Provider(SpriteSet sprites) {
            this.sprites = sprites;
        }

        @Nullable
        @Override
        public Particle createParticle(SimpleParticleType type, ClientLevel level,
                                       double x, double y, double z,
                                       double vx, double vy, double vz) {
            return new AnthocyaninParticles(level, x, y, z, vx, vy, vz, sprites);
        }
    }
}
