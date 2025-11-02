package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import dev.maksiks.amaranth.Amaranth;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.TerrablenderOverworldBiomeBuilder;
import terrablender.worldgen.RegionUtils;

import java.util.Optional;
import java.util.function.Consumer;

public class AmaranthTerrablenderOverworldBiomeBuilder extends TerrablenderOverworldBiomeBuilder {
    private final Integer regionId;

    public AmaranthTerrablenderOverworldBiomeBuilder(ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middle, ResourceKey<Biome>[][] middleVariant,
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
    /// vanilla biomes as deferred placeholders
    ///
    private Pair<Climate.ParameterPoint, ResourceKey<Biome>> adjustParameters(Pair<Climate.ParameterPoint, ResourceKey<Biome>> pair, Integer regionId) {
        Climate.ParameterPoint point = pair.getFirst();
        ResourceKey<Biome> biome = pair.getSecond();
        if (biome == null) return pair;

        if (regionId == null) return pair;

        // RegionUtils.getVanillaParameterPoints() for something, maybe?

        if (regionId == 1) {
            // replacing swamps with mushlands in region 1
            if (biome.equals(Biomes.SWAMP)) {
                return Pair.of(point, ModBiomes.MUSHLAND);
            }
        }

        return pair;
    }

    @Override
    public void addUndergroundBiomes(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
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