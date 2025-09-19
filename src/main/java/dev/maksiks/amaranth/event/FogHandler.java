package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

public class FogHandler {

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);
        if (biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            event.setRed(0.05f);
            event.setGreen(0.05f);
            event.setBlue(0.08f);
        }
    }

    @SubscribeEvent
    public static void onFogRender(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);
        if (biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            event.setNearPlaneDistance(-1.0F);
            event.setFarPlaneDistance(12.0F);
            event.setCanceled(true); // preventing vanilla from overriding the fog hopefully
        }
    }
}