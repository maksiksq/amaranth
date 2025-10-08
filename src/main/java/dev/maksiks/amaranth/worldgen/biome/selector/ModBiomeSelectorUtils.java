package dev.maksiks.amaranth.worldgen.biome.selector;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;

import java.util.*;

public class ModBiomeSelectorUtils {
    public static final Map<String, Pair<Map<String, String>, List<List<ResourceKey<Biome>>>>> BIOME_LAYOUTS = new HashMap<>();

    protected static List<List<ResourceKey<Biome>>> create(String id, String header, ResourceKey<Biome>[][] biomeKeys) {
        return create(id, biomeKeys, Map.of("", header));
    }

    protected static List<List<ResourceKey<Biome>>> create(String id, ResourceKey<Biome>[][] biomeKeys) {
        return create(id, biomeKeys, new HashMap<>());
    }

    protected static List<List<ResourceKey<Biome>>> create(String id, ResourceKey<Biome>[][] biomeKeys, Map<String, String> comments) {
        List<List<ResourceKey<Biome>>> layout = convert2DArray(biomeKeys);
        BIOME_LAYOUTS.put(id, Pair.of(comments, layout));
        return layout;
    }

    private static List<List<ResourceKey<Biome>>> convert2DArray(ResourceKey<Biome>[][] biomeKeys) {
        List<List<ResourceKey<Biome>>> result = new ArrayList<>(biomeKeys.length);
        for (ResourceKey<Biome>[] row : biomeKeys) {
            List<ResourceKey<Biome>> listRow = new ArrayList<>(row.length);
            Collections.addAll(listRow, row);
            result.add(listRow);
        }
        return result;
    }
}
