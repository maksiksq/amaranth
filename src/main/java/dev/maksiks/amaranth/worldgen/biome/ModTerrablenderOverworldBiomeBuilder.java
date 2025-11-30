package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectors;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.OverworldBiomeBuilder;
import terrablender.api.TerrablenderOverworldBiomeBuilder;

import java.util.function.Consumer;

public class ModTerrablenderOverworldBiomeBuilder extends TerrablenderOverworldBiomeBuilder {
    private final Integer regionId;

    public ModTerrablenderOverworldBiomeBuilder(ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middle, ResourceKey<Biome>[][] middleVariant,
                                                ResourceKey<Biome>[][] plateau, ResourceKey<Biome>[][] plateauVariant, ResourceKey<Biome>[][] shattered,
                                                ResourceKey<Biome>[][] beach, ResourceKey<Biome>[][] peak, ResourceKey<Biome>[][] peakVariant,
                                                ResourceKey<Biome>[][] slope, ResourceKey<Biome>[][] slopeVariant,
                                                Integer regionId) {
        super(oceans, middle, middleVariant, plateau, plateauVariant, shattered, beach, peak, peakVariant, slope, slopeVariant);
        this.regionId = regionId;
    }

    private final Climate.Parameter FULL_RANGE = Climate.Parameter.span(-1.0F, 1.0F);

    public void addBiomesPublic(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // passing in biomes for modification
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> wrappedMapper = pair -> {
            Pair<Climate.ParameterPoint, ResourceKey<Biome>> modified = adjustParameters(pair, regionId);
            mapper.accept(Pair.of(modified.getFirst(), modified.getSecond()));
        };

        super.addBiomes(wrappedMapper);

        addUndergroundBiomes(wrappedMapper);
    }

    ///
    /// Adjusting parameter points / biomes
    /// tho, the builder takes in most
    /// vanilla biomes as deferred placeholders,
    /// so those have to be replaced in {@link ModBiomeSelectors}
    /// matching (or not) with {@link OverworldBiomeBuilder}
    ///
    private Pair<Climate.ParameterPoint, ResourceKey<Biome>> adjustParameters(Pair<Climate.ParameterPoint, ResourceKey<Biome>> pair, Integer regionId) {
        Climate.ParameterPoint point = pair.getFirst();
        ResourceKey<Biome> biome = pair.getSecond();
        if (biome == null) return pair;

        if (regionId == null) return pair;

        // RegionUtils.getVanillaParameterPoints() for something, maybe?

        if (regionId == 0 || regionId == 1) {
            if (biome.equals(Biomes.SWAMP)) {
                
                return Pair.of(point, ModBiomes.MUSHLAND);
            }
        }

        return pair;
    }

    @Override
    public void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        // currently unused

//        mapper.accept(Pair.of(
//                new Climate.ParameterPoint(
//                        this.FULL_RANGE,
//                        this.FULL_RANGE,
//                        this.FULL_RANGE,
//                        this.FULL_RANGE,
//                        Climate.Parameter.span(0.2F, 0.9F),
//                        Climate.Parameter.span(0.8F, 0.9F),
//                        0L
//                ),
//                ModBiomes.DWARVEN_LEFTOVERS
//        ));

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
//                ModBiomes.SOME_SORTA_DEEP_BIOME
//        ));
    }

    @Override
    public ResourceKey<Biome> pickBeachBiome(int temperature, int humidity) {
        return super.pickBeachBiome(temperature, humidity);
    }
    @Override
    public ResourceKey<Biome> pickPeakBiome(int temperature, int humidity, Climate.Parameter weirdness) {
        if (temperature <= 2) {
            return super.pickPeakBiome(temperature, humidity, weirdness);
        } else {
            if (regionId == 0 || regionId == 1) {
                // no idea if it varies so just using an average
                if (Climate.unquantizeCoord((weirdness.min() + weirdness.max()) / 2L) > 0) {
                    return temperature == 3 ? ModBiomes.VOLCANIC_ASHEN_PEAKS : this.pickBadlandsBiome(humidity, weirdness);
                } else {
                    return temperature == 3 ? ModBiomes.ASHEN_PEAKS : this.pickBadlandsBiome(humidity, weirdness);
                }
            }
            return super.pickPeakBiome(temperature, humidity, weirdness);
        }
    }

    @Override
    public ResourceKey<Biome> pickSlopeBiome(int temperature, int humidity, Climate.Parameter weirdness) {
        if (regionId == 1) {
            if (temperature > 3) {
                return this.pickPlateauBiome(temperature, humidity, weirdness);
            } else {
                return ModBiomes.ALPINE_RANGE;
            }
        }
        if (regionId == 0) {
            if (temperature >= 3) {
                return this.pickPlateauBiome(temperature, humidity, weirdness);
            } else {
                return temperature > 1 ? ModBiomes.STEPPED_SPRINGS : Biomes.GROVE;
            }
        }
        return super.pickSlopeBiome(temperature, humidity, weirdness);
    }
}