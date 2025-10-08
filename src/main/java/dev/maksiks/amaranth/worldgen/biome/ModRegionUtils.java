package dev.maksiks.amaranth.worldgen.biome;

import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.IntStream;

public class ModRegionUtils {
    @SafeVarargs
    public static void forEachBiome(Consumer<ResourceKey<Biome>> consumer, ResourceKey<Biome>[][]... arrays) {
        for (ResourceKey<Biome>[][] outer : arrays) {
            for (ResourceKey<Biome>[] inner : outer) {
                for (ResourceKey<Biome> key : inner) {
                    consumer.accept(key);
                }
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static ResourceKey<Biome>[][] sanitize(
            String configName,
            ResourceLocation regionId,
            int regionIndex,
            ResourceKey<Biome>[][] input,
            Predicate<ResourceKey<Biome>> validator,
            boolean failOnInvalid
    ) {
        return IntStream.range(0, input.length)
                .mapToObj(i -> {
                    ResourceKey<Biome>[] row = input[i];
                    return IntStream.range(0, row.length)
                            .mapToObj(j -> {
                                ResourceKey<Biome> key = row[j];
                                if (!validator.test(key)) {
                                    if (failOnInvalid) {
                                        throw new IllegalArgumentException(String.format(
                                                "\"%s\" is not an allowed entry, specify a valid biome key!\n" +
                                                        "Amaranth OverworldRegion: \"%s\" failed in biome array: \"%s\" in region %s.\n" +
                                                        "Current value:\n%s",
                                                key != null ? key.location() : "null",
                                                regionId, configName, regionIndex, arrayToString(input)
                                        ));
                                    }
                                    // Return null for invalid entries when not throwing exception
                                    // This is important - THE_VOID gets replaced with null in variant arrays
                                    return null;
                                }
                                return key;
                            })
                            .toArray(ResourceKey[]::new);
                })
                .toArray(ResourceKey[][]::new);
    }
    public static String arrayToString(ResourceKey<Biome>[][] array) {
        if (array == null) return "null";

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < array.length; i++) {
            sb.append("[");
            ResourceKey<Biome>[] row = array[i];
            if (row != null) {
                for (int j = 0; j < row.length; j++) {
                    sb.append(row[j] != null ? row[j].location() : "null");
                    if (j < row.length - 1) sb.append(", ");
                }
            } else {
                sb.append("null");
            }
            sb.append("]");
            if (i < array.length - 1) sb.append("\n");
        }
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public static ResourceKey<Biome>[][] to2DArray(List<List<ResourceKey<Biome>>> listOfLists) {
        return listOfLists.stream()
                .map(inner -> inner.toArray(ResourceKey[]::new))
                .toArray(ResourceKey[][]::new);
    }
}
