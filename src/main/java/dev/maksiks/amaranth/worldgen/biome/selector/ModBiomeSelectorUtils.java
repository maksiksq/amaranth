package dev.maksiks.amaranth.worldgen.biome.selector;

import com.mojang.datafixers.util.Pair;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Biomes;
import terrablender.api.Region;

import java.util.*;

public class ModBiomeSelectorUtils {
    public static final Map<String, Pair<Map<String, String>, List<List<ResourceKey<Biome>>>>> BIOME_LAYOUTS = new HashMap<>();
    public static final ResourceKey<Biome> P = Region.DEFERRED_PLACEHOLDER;
    public static final ResourceKey<Biome> V = Biomes.THE_VOID;

    @SafeVarargs
    protected static List<List<ResourceKey<Biome>>> create(String id, String header, List<ResourceKey<Biome>>... rows) {
        return create(id, Arrays.asList(rows), Map.of("", header));
    }

    @SafeVarargs
    protected static List<List<ResourceKey<Biome>>> create(String id, List<ResourceKey<Biome>>... rows) {
        return create(id, Arrays.asList(rows), new HashMap<>());
    }

    protected static List<List<ResourceKey<Biome>>> create(String id, List<List<ResourceKey<Biome>>> layout, Map<String, String> comments) {
        BIOME_LAYOUTS.put(id, Pair.of(comments, layout));
        return layout;
    }
}
