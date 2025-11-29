package dev.maksiks.amaranth;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue HIDE_ALL_BIOME_PARTICLES = BUILDER
            .comment("")
            .comment(" Noooo MY PRETTY PARTICLES HOW RUDE (jk).")
            .define("hideAllBiomeParticles", false);

    public static final ModConfigSpec.BooleanValue HIDE_BIOME_LEAF_PARTICLES = BUILDER
            .comment(" # These are global biome particle settings.")
            .comment(" All of them can be disabled by setting particles to minimal in vanilla settings as well.")
            .comment(" If you experience lag you might also want to try the minimize options, they make the biomes have less particles instead of removing them completely.")
            .comment("")
            .comment(" Whether to hide leaf particles e.g. in the silver birch biome.")
            .define("hideBiomeLeafParticles", false);

    public static final ModConfigSpec.BooleanValue HIDE_BIOME_AMBIENCE_PARTICLES = BUILDER
            .comment(" Whether to hide ambience particles e.g. in the anthocyanin forest biome.")
            .define("hideBiomeAmbienceParticles", false);
    public static final ModConfigSpec.BooleanValue MINIMIZE_BIOME_AMBIENCE_PARTICLES = BUILDER
            .define("minimizeBiomeAmbienceParticles", false);

    public static final ModConfigSpec.BooleanValue HIDE_CUSTOM_BIOME_WEATHER_PARTICLES = BUILDER
            .comment(" Whether to hide biome's custom weather particles e.g. in desolate ice fields.")
            .define("hideCustomBiomeWeatherParticles", false);
    public static final ModConfigSpec.BooleanValue MINIMIZE_CUSTOM_BIOME_WEATHER_PARTICLES = BUILDER
            .define("minimizeCustomBiomeWeatherParticles", false);

    public static final ModConfigSpec.BooleanValue HIDE_DESOLATE_ICE_FIELDS_FOG = BUILDER
            .comment("")
            .comment(" # Biome-specific")
            .comment(" Whether to hide the dark fog in desolate ice fields.")
            .define("hideDesolateIceFieldsFog", false);


    public static final ModConfigSpec.IntValue SNOW_DIRECTION_CHANGE_TIME_MIN = BUILDER
            .comment("")
            .comment(" # Dev stuff, you probably shouldn't care/touch")
            .comment(" Fog direction change minimal bound in ticks")
            .defineInRange("snowDirectionChangeTimeMin", 30000, 1, Integer.MAX_VALUE);

    public static final ModConfigSpec.IntValue SNOW_DIRECTION_CHANGE_TIME_ADDED_RANGE = BUILDER
            .comment(" Random amount added to the direction change timer in ticks")
            .defineInRange("snowDirectionChangeTimeAddedRange", 60000, 1, Integer.MAX_VALUE);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
