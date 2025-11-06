package dev.maksiks.amaranth.sound;

import net.minecraft.client.resources.sounds.AbstractTickableSoundInstance;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.util.RandomSource;
import net.minecraft.client.Minecraft;

public class DesolateWindSoundInstance extends AbstractTickableSoundInstance {
    private static final float FADE_IN_TIME = 0.1f;
    private static final float FADE_OUT_TIME = 3.0f;
    private static final float TICKS_PER_SECOND = 20f;

    private final float fadeInStep;
    private final float fadeOutStep;
    private boolean fadingIn = true;
    private boolean fadingOut = false;
    private float currentVolume = 0.0f;

    public DesolateWindSoundInstance(SoundEvent sound) {
        super(sound, SoundSource.AMBIENT,
                Minecraft.getInstance().level != null
                        ? Minecraft.getInstance().level.random
                        : RandomSource.create());
        this.looping = true;
        this.delay = 0;
        this.volume = 0.001f;

        this.fadeInStep = 1.0f / (FADE_IN_TIME * TICKS_PER_SECOND);
        this.fadeOutStep = 1.0f / (FADE_OUT_TIME * TICKS_PER_SECOND);

        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            this.x = mc.player.getX();
            this.y = mc.player.getY();
            this.z = mc.player.getZ();
        }
    }

    @Override
    public void tick() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player != null) {
            this.x = mc.player.getX();
            this.y = mc.player.getY();
            this.z = mc.player.getZ();
        }

        if (fadingIn && !fadingOut) {
            currentVolume += fadeInStep;
            if (currentVolume >= 1.0f) {
                currentVolume = 1.0f;
                fadingIn = false;
            }
            this.volume = currentVolume;
        }

        if (fadingOut) {
            currentVolume -= fadeOutStep;
            if (currentVolume <= 0.0f) {
                currentVolume = 0.0f;
                this.stop();
            }
            this.volume = currentVolume;
        }
    }

    public void startFadeOut() {
        fadingOut = true;
        fadingIn = false;
    }

    @Override
    public boolean isStopped() {
        return super.isStopped();
    }
}
