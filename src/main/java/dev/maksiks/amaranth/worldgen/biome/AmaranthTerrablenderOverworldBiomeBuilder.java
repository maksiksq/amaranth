package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
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
        // passing in biomes for modification
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> wrappedMapper = pair -> {
            Pair<Climate.ParameterPoint, ResourceKey<Biome>> modified = adjustParameters(pair);
            mapper.accept(Pair.of(modified.getFirst(), modified.getSecond()));
        };

        super.addBiomes(wrappedMapper);

        addUndergroundBiomes(wrappedMapper);
    }

    private Pair<Climate.ParameterPoint, ResourceKey<Biome>> adjustParameters(Pair<Climate.ParameterPoint, ResourceKey<Biome>> pair) {
        Climate.ParameterPoint point = pair.getFirst();
        ResourceKey<Biome> biome = pair.getSecond();
        if (biome == null) return pair;

        Amaranth.LOGGER.info("Adjusting parameters for biome: " + biome.location());

        if (biome.equals(ModBiomes.PASTEL_PARCEL)) {
            Amaranth.LOGGER.info("Adjusting parcel" + biome.location());
            return Pair.of(new Climate.ParameterPoint(
                    point.temperature(),
                    point.humidity(),
                    point.continentalness(),
                    point.erosion(),
                    point.depth(),
                    point.weirdness(),
                    5L
            ), biome);
        }

        if (biome.equals(Biomes.CHERRY_GROVE)) {
            Amaranth.LOGGER.info("Replacing c w mushland");
            return Pair.of(pair.getFirst(), ModBiomes.MUSHLAND);
        }

        if (biome.equals(Biomes.SWAMP)) {
            Amaranth.LOGGER.info("Replacing w mushland");
            return Pair.of(pair.getFirst(), ModBiomes.MUSHLAND);
        }

        return pair;
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

//    public ResourceKey<Biome> pickMiddleBiome(int temp, int humidity, Climate.Parameter param) {
//        Amaranth.LOGGER.info("IM INSIDE THE PIPES");
//        return ModBiomes.TREE_ON_TREE_FOREST;
//    }

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