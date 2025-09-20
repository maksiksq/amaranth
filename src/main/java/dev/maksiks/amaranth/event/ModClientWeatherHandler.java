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
import net.neoforged.neoforge.client.event.RenderLevelStageEvent;

@EventBusSubscriber(modid = Amaranth.MOD_ID, value = Dist.CLIENT)
public class ModClientWeatherHandler {
    private static final double WIND_X = 1.5;
    private static final double WIND_Y = -0.3;
    private static final double WIND_Z = 0.0;

    @SubscribeEvent
    public static void onClientTick(ClientTickEvent.Post event) {
        Minecraft mc = Minecraft.getInstance();
        if (mc.level == null || mc.player == null) return;

        ResourceKey<Biome> biomeKey = mc.level.getBiome(mc.player.blockPosition())
                .unwrapKey().orElse(null);

        if (biomeKey == null || !biomeKey.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
            return;
        }

        for (int i = 0; i < 100; i++) {
            double x = mc.player.getX() + mc.level.random.nextGaussian() * 10;
            double y = mc.player.getY() + mc.level.random.nextDouble() * 8;
            double z = mc.player.getZ() + mc.level.random.nextGaussian() * 10;

            double vx = WIND_X + (mc.level.random.nextDouble() - 0.5) * 0.2;
            double vy = WIND_Y + (mc.level.random.nextDouble() - 0.5) * 0.1;
            double vz = WIND_Z + (mc.level.random.nextDouble() - 0.5) * 0.2;

            mc.level.addParticle(ParticleTypes.SNOWFLAKE, x, y, z, vx, vy, vz);
        }
    }
}