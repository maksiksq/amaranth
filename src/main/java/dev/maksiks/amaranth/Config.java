package dev.maksiks.amaranth;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.neoforge.common.ModConfigSpec;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import static dev.maksiks.amaranth.worldgen.biome.ModBiomes.MOD_OVERWORLD_CAVE_BIOMES;
import static dev.maksiks.amaranth.worldgen.biome.ModBiomes.MOD_OVERWORLD_SURFACE_BIOMES;

public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final HashMap<ResourceKey<Biome>, ModConfigSpec.BooleanValue> ALL_BIOME_CONFIGS = new LinkedHashMap<>();

    static {
        BUILDER.comment(" Toggle each biome. Only applies after you rejoin the world!");
        BUILDER.push("biome_toggles");

        Stream.concat(
                MOD_OVERWORLD_SURFACE_BIOMES.stream(),
                MOD_OVERWORLD_CAVE_BIOMES.stream()
        ).forEach(biome -> {
            ALL_BIOME_CONFIGS.put(
                    biome,
                    BUILDER.define("toggle_" + biome.location().getPath(), true)
            );
        });
        BUILDER.pop();
    }

    static final ModConfigSpec SPEC = BUILDER.build();


    public static boolean isBiomeEnabled(ResourceKey<Biome> biomeKey) {
        ModConfigSpec.BooleanValue toggle = ALL_BIOME_CONFIGS.get(biomeKey);
        return toggle != null && toggle.get();
    }

    public static List<ResourceKey<Biome>> getEnabledBiomes() {
        return ALL_BIOME_CONFIGS.entrySet().stream()
                .filter(entry -> entry.getValue().get())
                .map(Map.Entry::getKey)
                .toList();
    }

    private static boolean validateItemName(final Object obj) {
        return obj instanceof String itemName && BuiltInRegistries.ITEM.containsKey(ResourceLocation.parse(itemName));
    }
}
