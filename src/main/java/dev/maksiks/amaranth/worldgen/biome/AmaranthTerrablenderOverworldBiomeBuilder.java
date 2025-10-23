package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.TerrablenderOverworldBiomeBuilder;

import java.util.function.Consumer;

public class AmaranthTerrablenderOverworldBiomeBuilder extends TerrablenderOverworldBiomeBuilder {
    public AmaranthTerrablenderOverworldBiomeBuilder(ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middle, ResourceKey<Biome>[][] middleVariant,
                                                     ResourceKey<Biome>[][] plateau, ResourceKey<Biome>[][] plateauVariant, ResourceKey<Biome>[][] shattered,
                                                     ResourceKey<Biome>[][] beach, ResourceKey<Biome>[][] peak, ResourceKey<Biome>[][] peakVariant,
                                                     ResourceKey<Biome>[][] slope, ResourceKey<Biome>[][] slopeVariant) {
        super(oceans, middle, middleVariant, plateau, plateauVariant, shattered, beach, peak, peakVariant, slope, slopeVariant);
    }

    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    public void addBiomesPublic(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> wrappedMapper = pair -> {
            Climate.ParameterPoint point = pair.getFirst();
            ResourceKey<Biome> biome = pair.getSecond();

            Climate.ParameterPoint modified = adjustParameters(point, biome);
            mapper.accept(Pair.of(modified, biome));
        };

        super.addBiomes(wrappedMapper);

        addUndergroundBiomes(wrappedMapper);
    }

    private Climate.ParameterPoint adjustParameters(Climate.ParameterPoint point, ResourceKey<Biome> biome) {
        if (biome == null) return point;

        if (biome.equals(ModBiomes.PASTEL_PARCEL)) {
            return new Climate.ParameterPoint(
                    point.temperature(),
                    point.humidity(),
                    point.continentalness(),
                    point.erosion(),
                    point.depth(),
                    point.weirdness(),
                    5L
            );
        }

        return point;
    }

    private void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        mapper.accept(Pair.of(
                new Climate.ParameterPoint(
                        this.FULL_RANGE,
                        this.FULL_RANGE,
                        this.FULL_RANGE,
                        this.FULL_RANGE,
                        Climate.Parameter.span(0.2F, 0.9F),
                        Climate.Parameter.span(0.8F, 0.9F),
                        0L
                ),
                ModBiomes.DWARVEN_LEFTOVERS
        ));

//        mapper.accept(Pair.of(
//                new Climate.ParameterPoint(
//                        Climate.Parameter.span(-1.0F, -0.5F),
//                        this.FULL_RANGE,
//                        this.FULL_RANGE,
//                        Climate.Parameter.span(this.erosions[0], this.erosions[1]),
//                        Climate.Parameter.point(1.1F),
//                        this.FULL_RANGE,
//                        0L
//                ),
//                ModBiomes.CUSTOM_DEEP_BIOME
//        ));
    }


    @Override
    public ResourceKey<Biome> pickBeachBiome(int temp, int humidity) {
        return super.pickBeachBiome(temp, humidity);
    }

    @Override
    public ResourceKey<Biome> pickPeakBiome(int temp, int humidity, Climate.Parameter weirdness) {
        return super.pickPeakBiome(temp, humidity, weirdness);
    }

    @Override
    public ResourceKey<Biome> pickSlopeBiome(int temp, int humidity, Climate.Parameter weirdness) {
        return super.pickSlopeBiome(temp, humidity, weirdness);
    }
}