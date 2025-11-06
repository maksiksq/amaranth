package dev.maksiks.amaranth.worldgen.biome.selector;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

import static dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectorUtils.P;
import static dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectorUtils.V;

public class TerrablenderBiomeSelectors {
    public static final List<List<ResourceKey<Biome>>> OCEANS_TERRABLENDER = ModBiomeSelectorUtils.create("oceans/oceans_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_VARIANT_TERRABLENDER = ModBiomeSelectorUtils.create("middle_biomes_variant/middle_biomes_variant_terrablender", "",
            List.of(P, V, P, V, V),
            List.of(V, V, V, V, P),
            List.of(P, V, V, P, V),
            List.of(V, V, P, P, P),
            List.of(V, V, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("plateau_biomes/plateau_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_VARIANT_TERRABLENDER = ModBiomeSelectorUtils.create("plateau_biomes_variant/plateau_biomes_variant_terrablender", "",
            List.of(P, V, V, V, V),
            List.of(P, V, P, P, P),
            List.of(P, P, P, P, V),
            List.of(V, V, V, V, V),
            List.of(P, P, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> PEAK_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("peak_biomes/peak_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> SHATTERED_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("shattered_biomes/shattered_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(V, V, V, V, V),
            List.of(V, V, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> BEACH_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("beach_biomes/beach_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> PEAK_BIOMES_VARIANT_TERRABLENDER = ModBiomeSelectorUtils.create("peak_biomes_variant/peak_biomes_variant_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> SLOPE_BIOMES_TERRABLENDER = ModBiomeSelectorUtils.create("slope_biomes/slope_biomes_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> SLOPE_BIOMES_VARIANT_TERRABLENDER = ModBiomeSelectorUtils.create("slope_biomes_variant/slope_biomes_variant_terrablender", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(V, V, V, V, V),
            List.of(P, P, V, V, V)
    );
}