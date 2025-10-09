package dev.maksiks.amaranth.worldgen.biome.regions;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.ParameterUtils;
import terrablender.api.Region;
import terrablender.api.RegionType;
import terrablender.api.VanillaParameterOverlayBuilder;

import java.util.function.Consumer;

import static dev.maksiks.amaranth.worldgen.biome.regions.ModRegionHelpers.*;

public class ModRegion4 extends Region {
    public ModRegion4(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        // desolate
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(
                        ParameterUtils.Temperature.ICY,
                        ParameterUtils.Temperature.COOL
                ))
                .humidity(ParameterUtils.Humidity.span(
                        ParameterUtils.Humidity.ARID,
                        ParameterUtils.Humidity.DRY
                ))
                .continentalness(NORMAL_BIOME_CONTINENTALNESS)
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_5,
                        ParameterUtils.Erosion.EROSION_6
                ))
                .weirdness(ParameterUtils.Weirdness.span(
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                ))
                .depth(NORMAL_BIOME_DEPTH)
                .build().forEach(point -> builder.add(point, ModBiomes.DESOLATE_ICE_FIELDS));

        // shroom
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.NEUTRAL)
                .humidity(Climate.Parameter.span(-0.4F, 0.4F))
                .continentalness(
                        ParameterUtils.Continentalness.MID_INLAND,
                        ParameterUtils.Continentalness.FAR_INLAND)
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_4,
                        ParameterUtils.Erosion.EROSION_5
                ))

                .depth(NORMAL_BIOME_DEPTH)
                .weirdness(ParameterUtils.Weirdness.span(
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                        )
                )
                .build().forEach(point -> builder.add(point, ModBiomes.SHROOMLANDS));

        // stubby 3
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(Climate.Parameter.span(0.55F, 0.9F))
                .humidity(Climate.Parameter.span(-0.35F, -0.1F))
                .continentalness(ParameterUtils.Continentalness.span(
                        ParameterUtils.Continentalness.MID_INLAND,
                        ParameterUtils.Continentalness.FAR_INLAND
                ))
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_3,
                        ParameterUtils.Erosion.EROSION_5
                ))

                .depth(NORMAL_BIOME_DEPTH)
                .weirdness(ParameterUtils.Weirdness.span(
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                        )
                )
                .build().forEach(point -> builder.add(point, ModBiomes.STUBBY_WOODLAND));

        // vanilla filling
        addWaterAndRiverFilling(builder);

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
