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

public class ModRegion5 extends Region {
    public ModRegion5(ResourceLocation name, int weight) {
        super(name, RegionType.OVERWORLD, weight);
    }


    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        VanillaParameterOverlayBuilder builder = new VanillaParameterOverlayBuilder();

        // treeontree
        new ParameterUtils.ParameterPointListBuilder()
                .temperature(ParameterUtils.Temperature.NEUTRAL)
                .humidity(Climate.Parameter.span(-0.2F, 0.2F))
                .continentalness(ParameterUtils.Continentalness.span(
                        ParameterUtils.Continentalness.NEAR_INLAND,
                        ParameterUtils.Continentalness.MID_INLAND
                ))
                .erosion(ParameterUtils.Erosion.span(
                        ParameterUtils.Erosion.EROSION_2,
                        ParameterUtils.Erosion.EROSION_4
                ))

                .depth(Climate.Parameter.span(-0.25F, 0))
                .weirdness(ParameterUtils.Weirdness.span(
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_ASCENDING,
                                ParameterUtils.Weirdness.MID_SLICE_NORMAL_DESCENDING
                        )
                )
                .build().forEach(point -> builder.add(point, ModBiomes.TREE_ON_TREE_FOREST));

        // Add our points to the mapper
        builder.build().forEach(mapper);
    }
}
