package dev.maksiks.amaranth.worldgen.biome.selector;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import terrablender.api.Region;

import java.util.List;

public class ModBiomeSelectors {
        public static final List<List<ResourceKey<Biome>>> OCEANS_AMARANTH = ModBiomeSelectorUtils.create("oceans/oceans_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> OCEANS_2_AMARANTH = ModBiomeSelectorUtils.create("oceans/oceans_2_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_amaranth", "", new ResourceKey[][]{
                // freezing
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                // cool
                {ModBiomes.MYSTIC_FOREST, ModBiomes.SILVER_BIRCH_FOREST, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                // mid
                {ModBiomes.ANTHOCYANIN_FOREST, ModBiomes.ORDERLY_COURTS, ModBiomes.SILVER_BIRCH_FOREST, ModBiomes.SILVER_BIRCH_FOREST, ModBiomes.MIXED_WOODS, Region.DEFERRED_PLACEHOLDER},
                // warm
                {ModBiomes.STUBBY_WOODLAND, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                // hot
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER}
        });

        public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_2_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_2_amaranth", "", new ResourceKey[][]{
                {ModBiomes.DESOLATE_ICE_FIELDS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {ModBiomes.SHROOMLANDS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {ModBiomes.DUSTY_FLATS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_3_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_3_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {ModBiomes.TREE_ON_TREE_FOREST, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_VARIANT_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes_variant/middle_biomes_variant_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
                {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, ModBiomes.ORDERLY_COURTS_RUINS, Biomes.THE_VOID, Biomes.THE_VOID},
                {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
        });
        public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("plateau_biomes/plateau_biomes_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_2_AMARANTH = ModBiomeSelectorUtils.create("plateau_biomes/plateau_biomes_2_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_3_AMARANTH = ModBiomeSelectorUtils.create("plateau_biomes/plateau_biomes_3_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_VARIANT_AMARANTH = ModBiomeSelectorUtils.create("plateau_biomes_variant/plateau_biomes_variant_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID},
        });

        public static final List<List<ResourceKey<Biome>>> PEAK_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("peak_biomes/peak_biomes_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
        });

        public static final List<List<ResourceKey<Biome>>> SHATTERED_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("shattered_biomes/shattered_biomes_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
                {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID}
        });

        public static final List<List<ResourceKey<Biome>>> BEACH_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("beach_biomes/beach_biomes_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> PEAK_BIOMES_VARIANT_AMARANTH = ModBiomeSelectorUtils.create("peak_biomes_variant/peak_biomes_variant_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });

        public static final List<List<ResourceKey<Biome>>> SLOPE_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("slope_biomes/slope_biomes_amaranth", "", new ResourceKey[][]{
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
                {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
        });
    }