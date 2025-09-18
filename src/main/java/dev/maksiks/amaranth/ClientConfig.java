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

    static final ModConfigSpec SPEC = BUILDER.build();

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
