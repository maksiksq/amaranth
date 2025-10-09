package dev.maksiks.amaranth.event;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.LightLayer;

public class ModEventUtils {
    public static boolean isPlayerUnderground(Player player, ClientLevel level) {
        return player.getY() < 32 || level.getBrightness(LightLayer.SKY, player.blockPosition()) == 0;
    }

    public static void stopSpecificSound(ResourceLocation soundLocation, SoundSource source) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        mc.getSoundManager().stop(soundLocation, source);
    }
}
