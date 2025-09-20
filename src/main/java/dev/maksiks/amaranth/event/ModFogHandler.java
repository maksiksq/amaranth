package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

@EventBusSubscriber(modid = Amaranth.MOD_ID)
public class ModFogHandler {
    private static float currentRed = 1f, currentGreen = 1f, currentBlue = 1f;
    private static float currentNear = 0f, currentFar = 192f;

    private static float targetRed = 1f, targetGreen = 1f, targetBlue = 1f;
    private static float targetNear = 0f, targetFar = 192f;

    private static final float TRANSITION_SPEED = 0.005f;

    private static void stepTowardTarget() {
        currentRed = Mth.lerp(TRANSITION_SPEED, currentRed, targetRed);
        currentGreen = Mth.lerp(TRANSITION_SPEED, currentGreen, targetGreen);
        currentBlue = Mth.lerp(TRANSITION_SPEED, currentBlue, targetBlue);
        currentNear = Mth.lerp(TRANSITION_SPEED, currentNear, targetNear);
        currentFar = Mth.lerp(TRANSITION_SPEED, currentFar, targetFar);
    }

    private static boolean isInDesolateIceFields() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return false;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);
        return biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS);
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        float vanillaR = event.getRed();
        float vanillaG = event.getGreen();
        float vanillaB = event.getBlue();

        if (isInDesolateIceFields()) {
            targetRed = 0.05f;
            targetGreen = 0.05f;
            targetBlue = 0.05f;
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

    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onFogRender(ViewportEvent.RenderFog event) {
        if (event.isCanceled()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        float vanillaNear = event.getNearPlaneDistance();
        float vanillaFar = event.getFarPlaneDistance();

        if (isInDesolateIceFields()) {
            targetNear = -1.0f;
            targetFar = 7.0f;
        } else {
            targetNear = vanillaNear;
            targetFar = vanillaFar;
        }

        stepTowardTarget();

        event.setNearPlaneDistance(currentNear);
        event.setFarPlaneDistance(currentFar);

        // only cancelling if the player in the right biome
        // hopefully this might stop a conflict or two
        if (isInDesolateIceFields()) {
            event.setCanceled(true);
        }
    }
}