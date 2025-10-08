package dev.maksiks.amaranth.worldgen.biome.regions;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.worldgen.RegionUtils;

import java.util.List;
import java.util.function.Consumer;

public class ModRegionHelpers {
    // probably better below
    public static final Climate.Parameter NORMAL_BIOME_DEPTH = Climate.Parameter.span(-0.25F, 0);

    public static void waterFilling(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        biome(mapper, Biomes.OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.OCEAN));
        biome(mapper, Biomes.COLD_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.COLD_OCEAN));
        biome(mapper, Biomes.DEEP_FROZEN_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.DEEP_FROZEN_OCEAN));
        biome(mapper, Biomes.LUKEWARM_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.LUKEWARM_OCEAN));
        biome(mapper, Biomes.FROZEN_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.FROZEN_OCEAN));
        biome(mapper, Biomes.WARM_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.WARM_OCEAN));
        biome(mapper, Biomes.DEEP_COLD_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.DEEP_COLD_OCEAN));
        biome(mapper, Biomes.DEEP_LUKEWARM_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.DEEP_LUKEWARM_OCEAN));
        biome(mapper, Biomes.DEEP_OCEAN,
                RegionUtils.getVanillaParameterPoints(Biomes.DEEP_OCEAN));

        biome(mapper, Biomes.RIVER,
                RegionUtils.getVanillaParameterPoints(Biomes.RIVER));
        biome(mapper, Biomes.FROZEN_RIVER,
                RegionUtils.getVanillaParameterPoints(Biomes.FROZEN_RIVER));
    }

    public static void biome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper,
                             ResourceKey<Biome> biome,
                             Climate.Parameter temperature,
                             Climate.Parameter humidity,
                             Climate.Parameter continentalness,
                             Climate.Parameter erosion,
                             Climate.Parameter weirdness) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, ParameterUtils.Depth.FLOOR.parameter(), weirdness, 0F), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, ParameterUtils.Depth.SURFACE.parameter(), weirdness, 0F), biome));
    }

    public static void biome(
            Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper,
            ResourceKey<Biome> biome,
            List<Climate.ParameterPoint> points) {
        // points is a list of 1 element, what??
        Amaranth.LOGGER.info("POINTS: " + points);
        Climate.ParameterPoint point = points.getFirst();
        mapper.accept(Pair.of(Climate.parameters(point.temperature(), point.humidity(), point.continentalness(), point.erosion(), point.depth(), point.weirdness(), point.offset()), biome));
    }
}
