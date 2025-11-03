package dev.maksiks.amaranth.worldgen.biome.selector;

import dev.maksiks.amaranth.worldgen.biome.AmaranthTerrablenderOverworldBiomeBuilder;
import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import net.neoforged.fml.common.Mod;
import terrablender.api.Region;

import java.util.List;
import java.util.function.Consumer;

public class ModBiomeSelectors {
    /**
     * Copied from Terrablender !!!
     *
     * @param oceans               - Appearing on terrain below sea level, here is the "ocean_biomes" layout:
     * [ DEEP-ICY, DEEP-COLD, DEEP-NEUTRAL, DEEP-WARM, DEEP-HOT ]
     * [ SHALLOW-ICY, SHALLOW-COLD, SHALLOW-NEUTRAL, SHALLOW-WARM, SHALLOW-HOT ],
     * @param middleBiomes         - Appearing on terrain BELOW weirdness 0 or in unfilled("NULL(nothing)") spots in "middle_biomes_variants", here is the "middle_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param middleBiomesVariant  - Appearing on terrain ABOVE weirdness 0, here is the "middle_biomes_variant" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the middleBiomes array will be used.
     * @param plateauBiomes        - Appearing on elevated flat terrain BELOW weirdness 0 or in unfilled("NULL(nothing)") spots in "plateau_biome_variants", here is the "plateau_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param plateauBiomesVariant - Appearing on elevated flat terrain ABOVE weirdness 0, here is the "plateau_biomes_variant" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the plateauBiomes array will be used.
     * @param shatteredBiomes      - Appearing on shattered terrain here is the "shattered_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param beachBiomes          - Appearing on terrain bordering oceans, here is the "beach_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param peakBiomes           - Appearing on mountainous terrain AND BELOW weirdness 0, here is the "peak_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param peakBiomesVariant    - Appearing on mountainous terrain AND ABOVE weirdness 0, here is the "peak_biome_variants" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the peakBiomes array will be used.
     * @param slopeBiomes          - Appearing on sloped terrain, near mountainous terrain, AND BELOW weirdness 0, here is the "slope_biomes" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * @param slopeBiomesVariant   - Appearing on sloped terrain, near mountainous terrain, AND ABOVE weirdness 0, here is the "slope_biome_variants" layout:
     * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
     * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
     * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
     * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
     * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
     * Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the slopeBiomes array will be used.
     */



    /**
     * underground biomes are in {@link AmaranthTerrablenderOverworldBiomeBuilder}
     * the voids are nulls
     */


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
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.THRUMLETONS, ModBiomes.SILVER_BIRCH_FOREST, Region.DEFERRED_PLACEHOLDER},
            // mid
            {ModBiomes.MIXED_WOODS, ModBiomes.SILVER_BIRCH_FOREST, ModBiomes.MIXED_WOODS, ModBiomes.SILVER_BIRCH_FOREST, Region.DEFERRED_PLACEHOLDER},
            // warm
            {ModBiomes.STUBBY_WOODLAND, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.ANTHOCYANIN_FOREST, ModBiomes.ANTHOCYANIN_FOREST},
            // hot
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER}
    });

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_2_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_2_amaranth", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.MYSTIC_FOREST, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {ModBiomes.DUSTY_FLATS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
    });

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_3_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_2_amaranth", "", new ResourceKey[][]{
            {ModBiomes.DESOLATE_ICE_FIELDS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.FIELDS_OF_PAIN, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.SHROOMLANDS},
            {Region.DEFERRED_PLACEHOLDER, ModBiomes.DUSTY_FLATS, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
    });

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_4_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes/middle_biomes_3_amaranth", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.TREE_ON_TREE_FOREST},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
    });

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_VARIANT_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes_variant/middle_biomes_variant_amaranth", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
    });

    public static final List<List<ResourceKey<Biome>>> MIDDLE_BIOMES_VARIANT_2_AMARANTH = ModBiomeSelectorUtils.create("middle_biomes_variant/middle_biomes_variant_2_amaranth", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Biomes.THE_VOID, Biomes.THE_VOID},
            {Biomes.THE_VOID, Biomes.THE_VOID, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID, Biomes.THE_VOID},
    });

    public static final List<List<ResourceKey<Biome>>> PLATEAU_BIOMES_AMARANTH = ModBiomeSelectorUtils.create("plateau_biomes/plateau_biomes_amaranth", "", new ResourceKey[][]{
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, ModBiomes.SPARSEY_SPEARS, ModBiomes.ORDERLY_COURTS, Region.DEFERRED_PLACEHOLDER},
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
            {ModBiomes.PASTEL_PARCEL, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER, Region.DEFERRED_PLACEHOLDER},
            {ModBiomes.PASTEL_PARCEL, ModBiomes.PASTEL_PARCEL, Region.DEFERRED_PLACEHOLDER, ModBiomes.ORDERLY_COURTS_RUINS, Region.DEFERRED_PLACEHOLDER},
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

    // TODO: MAYBE REMOVE VOIDS FROM SHATTERED AND ETC TO PREVENT SMALL OUTCRPOPPINGS

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