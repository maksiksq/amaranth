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

import static dev.maksiks.amaranth.worldgen.biome.regions.ModRegionHelpers.NORMAL_BIOME_DEPTH;

public class ModRegion4 extends Region {
    public ModRegion4(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // desolate
        biome(mapper, ModBiomes.DESOLATE_ICE_FIELDS,
                ParameterUtils.Temperature.span(
                        ParameterUtils.Temperature.ICY,
                        ParameterUtils.Temperature.COOL
                ),
                ParameterUtils.Humidity.span(
                        ParameterUtils.Humidity.ARID,
                        ParameterUtils.Humidity.DRY
                ),
                ParameterUtils.Continentalness.FULL_RANGE.parameter(),
                ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_5,
                        ParameterUtils.Erosion.EROSION_6
                ),
                ParameterUtils.Weirdness.span(
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                )
        );

        // shroom
        biome(mapper, ModBiomes.SHROOMLANDS,
                ParameterUtils.Temperature.NEUTRAL.parameter(),
                Climate.Parameter.span(-0.4F, 0.4F),
                ParameterUtils.Continentalness.span(
                        ParameterUtils.Continentalness.MID_INLAND,
                        ParameterUtils.Continentalness.FAR_INLAND
                ),
                ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_4,
                        ParameterUtils.Erosion.EROSION_5
                ),
                ParameterUtils.Weirdness.span(
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                )
        );

        // stubby 3
        biome(mapper, ModBiomes.STUBBY_WOODLAND,
                Climate.Parameter.span(0.55F, 0.9F),
                Climate.Parameter.span(-0.35F, -0.1F),
                ParameterUtils.Continentalness.span(
                        ParameterUtils.Continentalness.MID_INLAND,
                        ParameterUtils.Continentalness.FAR_INLAND
                ),
                ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_3,
                        ParameterUtils.Erosion.EROSION_5
                ),
                ParameterUtils.Weirdness.span(
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                        ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                )
        );
    }

    private void biome(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper,
                       ResourceKey<Biome> biome,
                       Climate.Parameter temperature,
                       Climate.Parameter humidity,
                       Climate.Parameter continentalness,
                       Climate.Parameter erosion,
                       Climate.Parameter weirdness) {
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, ParameterUtils.Depth.FLOOR.parameter(), weirdness, 0F), biome));
        mapper.accept(Pair.of(Climate.parameters(temperature, humidity, continentalness, erosion, ParameterUtils.Depth.SURFACE.parameter(), weirdness, 0F), biome));
    }
}
