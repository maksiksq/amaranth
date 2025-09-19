package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

public class FogHandler {
    private static float currentRed = 1f, currentGreen = 1f, currentBlue = 1f;
    private static float currentNear = 0f, currentFar = 192f;

    private static float targetRed = 1f, targetGreen = 1f, targetBlue = 1f;
    private static float targetNear = 0f, targetFar = 192f;

    private static final float TRANSITION_SPEED = 0.01f;

    private static void updateTarget(ResourceKey<Biome> biome) {
        if (biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            targetRed = 0.05f;
            targetGreen = 0.05f;
            targetBlue = 0.08f;
            targetNear = -1.0f;
            targetFar = 12.0f;
        } else {
            targetRed = 1f;
            targetGreen = 1f;
            targetBlue = 1f;
            targetNear = 0f;
            targetFar = 192f;
        }
    }

    private static void stepTowardTarget() {
        currentRed = Mth.lerp(TRANSITION_SPEED, currentRed, targetRed);
        currentGreen = Mth.lerp(TRANSITION_SPEED, currentGreen, targetGreen);
        currentBlue = Mth.lerp(TRANSITION_SPEED, currentBlue, targetBlue);
        currentNear = Mth.lerp(TRANSITION_SPEED, currentNear, targetNear);
        currentFar = Mth.lerp(TRANSITION_SPEED, currentFar, targetFar);
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);

        float vanillaR = event.getRed();
        float vanillaG = event.getGreen();
        float vanillaB = event.getBlue();

        if (biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            targetRed = 0.05f;
            targetGreen = 0.05f;
            targetBlue = 0.08f;
        } else {
            targetRed = vanillaR;
            targetGreen = vanillaG;
            targetBlue = vanillaB;
        }

        stepTowardTarget();

        event.setRed(currentRed);
        event.setGreen(currentGreen);
        event.setBlue(currentBlue);
    }

    @SubscribeEvent
    public static void onFogRender(ViewportEvent.RenderFog event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);

        float vanillaNear = event.getNearPlaneDistance();
        float vanillaFar = event.getFarPlaneDistance();

        if (biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            targetNear = -1.0f;
            targetFar = 7.0f;
        } else {
            targetNear = vanillaNear;
            targetFar = vanillaFar;
        }

        stepTowardTarget();

        event.setNearPlaneDistance(currentNear);
        event.setFarPlaneDistance(currentFar);
        event.setCanceled(true);
    }
}