package dev.maksiks.amaranth.worldgen.biome.selector;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import dev.maksiks.amaranth.worldgen.biome.ModTerrablenderOverworldBiomeBuilder;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.List;

import static dev.maksiks.amaranth.worldgen.biome.ModBiomes.*;
import static dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectorUtils.P;
import static dev.maksiks.amaranth.worldgen.biome.selector.ModBiomeSelectorUtils.V;

/**
 * REFERENCE:
 * Copied from Terrablender
 *
 * <p>
 * oceans               - Appearing on terrain below sea level, here is the "ocean_biomes" layout:
 * [ DEEP-ICY, DEEP-COLD, DEEP-NEUTRAL, DEEP-WARM, DEEP-HOT ]
 * [ SHALLOW-ICY, SHALLOW-COLD, SHALLOW-NEUTRAL, SHALLOW-WARM, SHALLOW-HOT ],
 * <p>
 * middleBiomes         - Appearing on terrain BELOW weirdness 0 or in unfilled("NULL(nothing)") spots in "middle_biomes_variants", here is the "middle_biomes" layout:
 * middleBiomesVariant  - Appearing on terrain ABOVE weirdness 0, here is the "middle_biomes_variant" layout:
 *                        Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the middleBiomes array will be used.
 * plateauBiomes        - Appearing on elevated flat terrain BELOW weirdness 0 or in unfilled("NULL(nothing)") spots in "plateau_biome_variants", here is the "plateau_biomes" layout:
 * plateauBiomesVariant - Appearing on elevated flat terrain ABOVE weirdness 0, here is the "plateau_biomes_variant" layout:
 *                        Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the plateauBiomes array will be used.
 * shatteredBiomes      - Appearing on shattered terrain here is the "shattered_biomes" layout:
 * beachBiomes          - Appearing on terrain bordering oceans, here is the "beach_biomes" layout:
 * peakBiomes           - Appearing on mountainous terrain AND BELOW weirdness 0, here is the "peak_biomes" layout:
 * peakBiomesVariant    - Appearing on mountainous terrain AND ABOVE weirdness 0, here is the "peak_biome_variants" layout:
 *                        Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the peakBiomes array will be used.
 * slopeBiomes          - Appearing on sloped terrain, near mountainous terrain, AND BELOW weirdness 0, here is the "slope_biomes" layout:
 * slopeBiomesVariant   - Appearing on sloped terrain, near mountainous terrain, AND ABOVE weirdness 0, here is the "slope_biome_variants" layout:
 *                        Null values may be passed in, the equivalent biome at the equivalent temperature/humidity index in the slopeBiomes array will be used.
 * <p>
 * [ ARID-ICY, DRY-ICY, NEUTRAL-ICY, WET-ICY, HUMID-ICY ],
 * [ ARID-COLD, DRY-COLD, NEUTRAL-COLD, WET-COLD, HUMID-COLD ],
 * [ ARID-NEUTRAL, DRY-NEUTRAL, NEUTRAL-NEUTRAL, WET-NEUTRAL, HUMID-NEUTRAL ],
 * [ ARID-WARM, DRY-WARM, NEUTRAL-WARM, WET-WARM, HUMID-WARM ],
 * [ ARID-HOT, DRY-HOT, NEUTRAL-HOT, WET-HOT, HUMID-HOT ]
 * <p>
 */

///
/// underground biomes are in {@link ModTerrablenderOverworldBiomeBuilder}
/// the voids are nulls
///

public class ModBiomeSelectors {
    public static final List<List<ResourceKey<Biome>>> MOD_OCEANS = ModBiomeSelectorUtils.create("oceans/mod_oceans", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES = ModBiomeSelectorUtils.create("middle_biomes/mod_middle_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, THRUMLETONS, SILVER_BIRCH_FOREST, P, P),
            List.of(MIXED_WOODS, P, MIXED_WOODS, SILVER_BIRCH_FOREST, P),
            List.of(STUBBY_WOODLAND, LUPINE_MEADOW, P, P, ANTHOCYANIN_FOREST),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES_2 = ModBiomeSelectorUtils.create("middle_biomes/mod_middle_biomes_2", "",
            List.of(P, P, P, P, P),
            List.of(P, P, THRUMLETONS, P, P),
            List.of(P, P, MIXED_WOODS, P, P),
            List.of(SHRUBLAND, SHRUBLAND, P, P, P),
            List.of(DUSTY_FLATS, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES_3 = ModBiomeSelectorUtils.create("middle_biomes/mod_middle_biomes_3", "",
            List.of(DESOLATE_ICE_FIELDS, P, P, P, P),
            List.of(P, P, MYSTIC_FOREST, P, P),
            List.of(P, P, MYSTIC_FOREST, WITCHY_WOODS, P),
            List.of(P, P, P, MYSTIC_FOREST, SHROOMLANDS),
            List.of(P, DUSTY_FLATS, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES_4 = ModBiomeSelectorUtils.create("middle_biomes/mod_middle_biomes_4", "",
            List.of(P, P, P, P, P),
            List.of(FIELDS_OF_PAIN, P, P, P, P),
            List.of(P, P, P, P, TREE_ON_TREE_FOREST),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES_VARIANT = ModBiomeSelectorUtils.create("middle_biomes_variant/mod_middle_biomes_variant", "",
            List.of(P, V, P, V, V),
            List.of(V, V, P, V, P),
            List.of(P, V, P, V, V),
            List.of(V, V, P, P, P),
            List.of(V, V, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_MIDDLE_BIOMES_VARIANT_2 = ModBiomeSelectorUtils.create("middle_biomes_variant/mod_middle_biomes_variant_2", "",
            List.of(DESOLATE_ICE_FIELDS, V, P, V, V),
            List.of(V, V, P, V, P),
            List.of(P, V, P, V, V),
            List.of(VIVID_SHRUBLAND, VIVID_SHRUBLAND, P, P, P),
            List.of(V, V, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_PLATEAU_BIOMES = ModBiomeSelectorUtils.create("plateau_biomes/mod_plateau_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, SPARSEY_SPEARS, ORDERLY_COURTS, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_PLATEAU_BIOMES_4 = ModBiomeSelectorUtils.create("plateau_biomes/mod_plateau_biomes_4", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, SATISFOREST, SATISFOREST, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_PLATEAU_BIOMES_VARIANT = ModBiomeSelectorUtils.create("plateau_biomes_variant/mod_plateau_biomes_variant", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, ORDERLY_COURTS_RUINS, P),
            List.of(P, P, P, P, P),
            List.of(P, V, P, P, V)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_PLATEAU_BIOMES_VARIANT_2 = ModBiomeSelectorUtils.create("plateau_biomes_variant/mod_plateau_biomes_variant_2", "",
            List.of(DESOLATE_ICE_FIELDS, DESOLATE_ICE_FIELDS, P, P, P),
            List.of(P, P, P, P, P),
            List.of(PASTEL_PARCEL, P, P, P, P),
            List.of(PASTEL_PARCEL, PASTEL_PARCEL, P, P, P),
            List.of(P, V, P, P, V)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_SHATTERED_BIOMES = ModBiomeSelectorUtils.create("shattered_biomes/mod_shattered_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(V, V, V, V, V),
            List.of(V, V, V, V, V)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_BEACH_BIOMES = ModBiomeSelectorUtils.create("beach_biomes/mod_beach_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    // in the builder
    public static final List<List<ResourceKey<Biome>>> MOD_PEAK_BIOMES = ModBiomeSelectorUtils.create("peak_biomes/mod_peak_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_PEAK_BIOMES_VARIANT = ModBiomeSelectorUtils.create("peak_biomes_variant/mod_peak_biomes_variant", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    // in the builder
    public static final List<List<ResourceKey<Biome>>> MOD_SLOPE_BIOMES = ModBiomeSelectorUtils.create("slope_biomes/mod_slope_biomes", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P)
    );

    public static final List<List<ResourceKey<Biome>>> MOD_SLOPE_BIOMES_VARIANT = ModBiomeSelectorUtils.create("slope_biomes/mod_slope_biomes_variant", "",
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(P, P, P, P, P),
            List.of(V, V, V, V, V),
            List.of(P, P, V, V, V)
    );
}