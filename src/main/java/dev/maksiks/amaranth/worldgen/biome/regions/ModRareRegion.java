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

public class ModRareRegion extends Region {
    public ModRareRegion(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.span(
                        ParameterUtils.Temperature.ICY,
                        ParameterUtils.Temperature.COOL
                ))
                .humidity(ParameterUtils.Humidity.span(
                        ParameterUtils.Humidity.ARID,
                        ParameterUtils.Humidity.DRY
                ))
                .continentalness(ParameterUtils.Continentalness.FULL_RANGE)
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_5,
                        ParameterUtils.Erosion.EROSION_6
                ))
                .weirdness(ParameterUtils.Weirdness.span(
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                ))
                .depth(Climate.Parameter.span(-0.25F, 0))
                .build().forEach(point -> builder.add(point, ModBiomes.DESOLATE_ICE_FIELDS));


        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.NEUTRAL)
                .humidity(Climate.Parameter.span(-0.4F, 0.4F))
                .continentalness(ParameterUtils.Continentalness.NEAR_INLAND)
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_4,
                        ParameterUtils.Erosion.EROSION_5
                ))

                .depth(Climate.Parameter.span(-0.25F, 0))
                .weirdness(ParameterUtils.Weirdness.span(
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                        )
                )
                .build().forEach(point -> builder.add(point, ModBiomes.SHROOMLANDS));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
