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
        super.addBiomes(mapper);
    }
}
