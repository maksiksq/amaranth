package dev.maksiks.amaranth;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import dev.maksiks.amaranth.worldgen.biome.ModBiomes;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.biome.Biome;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.EventBusSubscriber;
import net.neoforged.fml.event.config.ModConfigEvent;
import net.neoforged.neoforge.common.ModConfigSpec;

import static dev.maksiks.amaranth.worldgen.biome.ModBiomes.MOD_OVERWORLD_CAVE_BIOMES;
import static dev.maksiks.amaranth.worldgen.biome.ModBiomes.MOD_OVERWORLD_SURFACE_BIOMES;

// An example config class. This is not required, but it's a good idea to have one to keep your config organized.
// Demonstrates how to use Neo's config APIs
public class Config {
    private static final ModConfigSpec.Builder BUILDER = new ModConfigSpec.Builder();
    public static final HashMap<ResourceKey<Biome>, ModConfigSpec.BooleanValue> ALL_BIOME_CONFIGS = new LinkedHashMap<>();

    static {
        BUILDER.comment(" Toggle each biome, only applies after you rejoin the world!");
        BUILDER.push("biome_toggles");

        Amaranth.LOGGER.info("biome_: {}", MOD_OVERWORLD_SURFACE_BIOMES);
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
