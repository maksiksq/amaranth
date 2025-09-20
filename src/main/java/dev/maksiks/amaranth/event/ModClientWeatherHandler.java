package dev.maksiks.amaranth.event;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.client.Minecraft;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.neoforge.client.event.ClientTickEvent;

import static dev.maksiks.amaranth.ClientConfig.*;

@EventBusSubscriber(modid = Amaranth.MOD_ID, value = Dist.CLIENT)
public class ModClientWeatherHandler {
    private static double currentWindX = 1.5;
    private static double currentWindY = -0.3;
    private static double currentWindZ = 0.0;

    private static double targetWindX = 1.5;
    private static double targetWindY = -0.3;
    private static double targetWindZ = 0.0;

    private static long lastDirectionChange = 0;
    private static long nextChangeInterval = -1;
    private static final double TRANSITION_SPEED = 0.02;

    private static final double MIN_WIND_STRENGTH = 0.5;
    private static final double MAX_WIND_STRENGTH = 2.5;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        if (HIDE_CUSTOM_BIOME_WEATHER_PARTICLES.getAsBoolean()) {
            return;
        }

        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        ResourceKey<Biome> biomeKey = mc.level.getBiome(mc.player.blockPosition())
                .unwrapKey().orElse(null);

        if (biomeKey == null || !biomeKey.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            return;
        }

        updateWindDirection();

        int snowCount = MINIMIZE_CUSTOM_BIOME_WEATHER_PARTICLES.getAsBoolean() ? 10 : 100;

        for (int i = 0; i < snowCount; i++) {
            double x = mc.player.getX() + mc.level.random.nextGaussian() * 10;
            double y = mc.player.getY() + mc.level.random.nextDouble() * 8;
            double z = mc.player.getZ() + mc.level.random.nextGaussian() * 10;

            double vx = currentWindX + (mc.level.random.nextDouble() - 0.5) * 0.2;
            double vy = currentWindY + (mc.level.random.nextDouble() - 0.5) * 0.1;
            double vz = currentWindZ + (mc.level.random.nextDouble() - 0.5) * 0.2;

            mc.level.addParticle(ParticleTypes.SNOWFLAKE, x, y, z, vx, vy, vz);
        }
    }

    private static void updateWindDirection() {
        long currentTime = System.currentTimeMillis();

        if (nextChangeInterval < 0) {
            nextChangeInterval = getRandomInterval();
        }

        if (currentTime - lastDirectionChange >= nextChangeInterval) {
            generateNewWindDirection();
            lastDirectionChange = currentTime;
            nextChangeInterval = getRandomInterval();
        }

        currentWindX = lerp(currentWindX, targetWindX, TRANSITION_SPEED);
        currentWindY = lerp(currentWindY, targetWindY, TRANSITION_SPEED);
        currentWindZ = lerp(currentWindZ, targetWindZ, TRANSITION_SPEED);
    }

    private static void generateNewWindDirection() {
        double angle = Math.random() * Math.PI * 2;
        double strength = MIN_WIND_STRENGTH + Math.random() * (MAX_WIND_STRENGTH - MIN_WIND_STRENGTH);

        targetWindX = Math.cos(angle) * strength;
        targetWindZ = Math.sin(angle) * strength;

        targetWindY = -0.3 + (Math.random() - 0.5) * 0.4;
    }

    private static long getRandomInterval() {
        return SNOW_DIRECTION_CHANGE_TIME_MIN.getAsInt() + (long)(Math.random() * SNOW_DIRECTION_CHANGE_TIME_ADDED_RANGE.getAsInt());
    }

    private static double lerp(double current, double target, double speed) {
        return current + (target - current) * speed;
    }
}