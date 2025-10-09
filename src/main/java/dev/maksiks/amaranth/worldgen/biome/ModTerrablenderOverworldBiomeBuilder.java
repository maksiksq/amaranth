package dev.maksiks.amaranth.worldgen.biome;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.TerrablenderOverworldBiomeBuilder;

import java.util.function.Consumer;

public class ModTerrablenderOverworldBiomeBuilder extends TerrablenderOverworldBiomeBuilder {
    public ModTerrablenderOverworldBiomeBuilder(ResourceKey<Biome>[][] oceans, ResourceKey<Biome>[][] middle, ResourceKey<Biome>[][] middleVariant,
                                                ResourceKey<Biome>[][] plateau, ResourceKey<Biome>[][] plateauVariant, ResourceKey<Biome>[][] shattered,
                                                ResourceKey<Biome>[][] beach, ResourceKey<Biome>[][] peak, ResourceKey<Biome>[][] peakVariant,
                                                ResourceKey<Biome>[][] slope, ResourceKey<Biome>[][] slopeVariant) {
        super(oceans, middle, middleVariant, plateau, plateauVariant, shattered, beach, peak, peakVariant, slope, slopeVariant);
    }

    public void addBiomesPublic(Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> wrappedMapper = pair -> {
            Climate.ParameterPoint point = pair.getFirst();
            ResourceKey<Biome> biome = pair.getSecond();

            Climate.ParameterPoint modified = adjustParameters(point, biome);
            mapper.accept(Pair.of(modified, biome));
        };

        super.addBiomes(wrappedMapper);
    }

    private Climate.ParameterPoint adjustParameters(Climate.ParameterPoint point, ResourceKey<Biome> biome) {
        if (biome == null) return point;

// unused for now but experimenting if i do need it
//        if (biome.equals(ModBiomes.DESOLATE_ICE_FIELDS)) {
//            return new Climate.ParameterPoint(
//                    point.temperature(),
//                    point.humidity(),
//                    point.continentalness(),
//                    point.erosion(),
//                    Climate.Parameter.span(-0.25F, 0.0F),
//                    point.weirdness(),
//                    point.offset()
//            );
//        }

        return point;
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