package dev.maksiks.amaranth;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.List;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class ClientConfig {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();

    public static final ModConfigSpec.BooleanValue HIDE_BIOME_LEAF_PARTICLES = BUILDER
            .comment(" Whether to show leaf particles e.g. in the silver birch biome. You can disable them by setting particles to minimal too, but you might want to keep all other particles if it's just these biomes causing lag when loaded.")
            .define("hideBiomeLeafParticles", false);

    public static final ModConfigSpec.BooleanValue HIDE_CUSTOM_BIOME_WEATHER_PARTICLES = BUILDER
            .comment(" Whether to create biome's custom weather particles e.g. in desolate ice fields. This one might prevent a lot of lag when you're in the biome. You could also set particles to minimal in the settings as well.")
            .define("hideCustomBiomeWeatherParticles", false);
    public static final ModConfigSpec.BooleanValue MINIMIZE_CUSTOM_BIOME_WEATHER_PARTICLES = BUILDER
            .comment(" If you don't want to disable them whole you can keep just a few for the immersion at least. Probably no lag included.")
            .define("minimizeCustomBiomeWeatherParticles", false);

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
