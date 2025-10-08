package dev.maksiks.amaranth.worldgen.biome.regions;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.VanillaParameterOverlayBuilder;
import terrablender.worldgen.RegionUtils;

import java.util.List;
import java.util.function.Consumer;

public class ModRegionHelpers {
    // dont want to bother with whatever is below surface
    public static final Climate.Parameter NORMAL_BIOME_DEPTH = Climate.Parameter.span(-0.25F, 0);

    // in terrablender's helpers FAR_INLAND has 1.0F while just INLAND has 0.55F,
    // is it some weird thing or a typo?
    public static final Climate.Parameter NORMAL_BIOME_CONTINENTALNESS = Climate.Parameter.span(-0.19F, 1.0F);

    public static void addWaterAndRiverFilling(VanillaParameterOverlayBuilder builder) {
        RegionUtils.getVanillaParameterPoints(Biomes.OCEAN).forEach(point -> builder.add(point, Biomes.OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.COLD_OCEAN).forEach(point -> builder.add(point, Biomes.COLD_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.DEEP_FROZEN_OCEAN).forEach(point -> builder.add(point, Biomes.DEEP_FROZEN_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.WARM_OCEAN).forEach(point -> builder.add(point, Biomes.WARM_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.FROZEN_OCEAN).forEach(point -> builder.add(point, Biomes.FROZEN_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.DEEP_OCEAN).forEach(point -> builder.add(point, Biomes.DEEP_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.DEEP_LUKEWARM_OCEAN).forEach(point -> builder.add(point, Biomes.DEEP_LUKEWARM_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.DEEP_COLD_OCEAN).forEach(point -> builder.add(point, Biomes.DEEP_COLD_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.DEEP_FROZEN_OCEAN).forEach(point -> builder.add(point, Biomes.DEEP_FROZEN_OCEAN));
        RegionUtils.getVanillaParameterPoints(Biomes.LUKEWARM_OCEAN).forEach(point -> builder.add(point, Biomes.LUKEWARM_OCEAN));

        RegionUtils.getVanillaParameterPoints(Biomes.RIVER).forEach(point -> builder.add(point, Biomes.RIVER));
        RegionUtils.getVanillaParameterPoints(Biomes.FROZEN_RIVER).forEach(point -> builder.add(point, Biomes.FROZEN_RIVER));
    }


}
