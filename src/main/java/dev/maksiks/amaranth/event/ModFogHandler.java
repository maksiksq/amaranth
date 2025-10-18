package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LightLayer;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.EventPriority;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;
import net.neoforged.neoforge.client.event.ViewportEvent;

import static dev.maksiks.amaranth.ClientConfig.HIDE_DESOLATE_ICE_FIELDS_FOG;
import static dev.maksiks.amaranth.event.ModEventUtils.isPlayerUnderground;

@EventBusSubscriber(modid = Amaranth.MOD_ID)
public class ModFogHandler {
    private static float currentRed = 1f, currentGreen = 1f, currentBlue = 1f;
    private static float currentNear = 0f, currentFar = 192f;

    private static float targetRed = 1f, targetGreen = 1f, targetBlue = 1f;
    private static float targetNear = 0f, targetFar = 192f;

    private static final float TRANSITION_SPEED_IN = 0.005f;
    private static final float TRANSITION_SPEED_OUT = 0.0005f;
    private static final float TRANSITION_THRESHOLD = 0.001f;

    private static void stepTowardTarget() {
        float speed = isInDesolateIceFieldsAndValid() ? TRANSITION_SPEED_IN : TRANSITION_SPEED_OUT;

        currentRed = Mth.lerp(speed, currentRed, targetRed);
        currentGreen = Mth.lerp(speed, currentGreen, targetGreen);
        currentBlue = Mth.lerp(speed, currentBlue, targetBlue);
        currentNear = Mth.lerp(speed, currentNear, targetNear);
        currentFar = Mth.lerp(speed, currentFar, targetFar);
    }

    private static boolean isInDesolateIceFieldsAndValid() {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return false;

        // checking if the player is underground/not skylit
        if (isPlayerUnderground(mc.player, mc.level)) return false;

        ResourceKey<Biome> biome = mc.level.getBiome(mc.player.blockPosition()).unwrapKey().orElse(null);
        return biome != null && biome.equals(ModBiomes.DESOLATE_ICE_FIELDS);
    }

    private static boolean isTransitioning() {
        return Math.abs(currentRed - targetRed) > TRANSITION_THRESHOLD ||
                Math.abs(currentGreen - targetGreen) > TRANSITION_THRESHOLD ||
                Math.abs(currentBlue - targetBlue) > TRANSITION_THRESHOLD ||
                Math.abs(currentNear - targetNear) > TRANSITION_THRESHOLD ||
                Math.abs(currentFar - targetFar) > TRANSITION_THRESHOLD;
    }

    @SubscribeEvent
    public static void onFogColor(ViewportEvent.ComputeFogColor event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        float vanillaR = event.getRed();
        float vanillaG = event.getGreen();
        float vanillaB = event.getBlue();

        if (isInDesolateIceFieldsAndValid()) {
            targetRed = 0.03f;
            targetGreen = 0.04f;
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
        if (HIDE_DESOLATE_ICE_FIELDS_FOG.getAsBoolean()) return;
//        if (event.isCanceled()) return;

        Minecraft mc = Minecraft.getInstance();
        if (mc.player == null || mc.level == null) return;

        float vanillaNear = event.getNearPlaneDistance();
        float vanillaFar = event.getFarPlaneDistance();

        if (isInDesolateIceFieldsAndValid()) {
            targetNear = -1.0f;
            targetFar = 7.0f;
        } else {
            targetNear = vanillaNear;
            targetFar = vanillaFar;
        }

        stepTowardTarget();

        event.setNearPlaneDistance(currentNear);
        event.setFarPlaneDistance(currentFar);

//        if (isInDesolateIceFieldsAndValid() || isTransitioning()) {
//            event.setCanceled(true);
//        }
    }
}