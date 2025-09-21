package dev.maksiks.amaranth.worldgen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MYSTIC_TREE_PLACED_KEY = registerKey("mystic_tree_placed");
    public static final ResourceKey<PlacedFeature> MYSTIC_FLOWER_PLACED_KEY = registerKey("mystic_flower_placed");
    public static final ResourceKey<PlacedFeature> MYSTIC_AMETHYST_PLACED_KEY = registerKey("mystic_amethyst_placed");

    public static final ResourceKey<PlacedFeature> STUBBY_TREE_PLACED_KEY = registerKey("stubby_tree_placed");

    public static final ResourceKey<PlacedFeature> SILVER_BIRCH_TREE_PLACED_KEY = registerKey("silver_birch_tree_placed");
    public static final ResourceKey<PlacedFeature> SILVER_BIRCH_FLOWER_PLACED_KEY = registerKey("silver_birch_flower_placed");
    public static final ResourceKey<PlacedFeature> GOLDEN_LEAF_LITTER_PLACED_KEY = registerKey("golden_leaf_litter_placed");

    public static final ResourceKey<PlacedFeature> DESOLATE_SPIKE_PLACED_KEY = registerKey("desolate_spike_placed");

    public static final ResourceKey<PlacedFeature> PURPLE_MIXED_OAK_TREE_PLACED_KEY = registerKey("purple_mixed_oak_tree_placed");
    public static final ResourceKey<PlacedFeature> RED_MIXED_OAK_TREE_PLACED_KEY = registerKey("red_mixed_oak_tree_placed");
    public static final ResourceKey<PlacedFeature> YELLOW_MIXED_OAK_TREE_PLACED_KEY = registerKey("yellow_mixed_oak_tree_placed");
    public static final ResourceKey<PlacedFeature> MIXED_OAK_PLACED_KEY = registerKey("mixed_oak_placed_key");

    public static void bootstrap(BootstrapContext<PlacedFeature> context) {
        var configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, MYSTIC_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYSTIC_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1),
                        ModBlocks.MYSTIC_SAPLING.get()));

        register(context, MYSTIC_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYSTIC_FLOWER_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, MYSTIC_AMETHYST_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MYSTIC_AMETHYST_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(context, STUBBY_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.STUBBY_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(4, 0.1F, 1),
                        ModBlocks.STUBBY_SAPLING.get()));

        register(context, SILVER_BIRCH_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_BIRCH_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(14, 0.1F, 1),
                        ModBlocks.SILVER_BIRCH_SAPLING.get()));

        register(context, SILVER_BIRCH_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.SILVER_BIRCH_FLOWER_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(
                context,
                GOLDEN_LEAF_LITTER_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.GOLDEN_LEAF_LITTER_KEY),
                List.of(
                        NoiseThresholdCountPlacement.of(-0.8, 5, 10),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome()));

        register(
                context,
                DESOLATE_SPIKE_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.DESOLATE_SPIKE_KEY),
                List.of(
                        CountPlacement.of(5),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG)
                )
        );

        // mixed
        register(context, PURPLE_MIXED_OAK_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.PURPLE_MIXED_OAK_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1),
                        ModBlocks.PURPLE_MIXED_OAK_SAPLING.get()));
        register(context, RED_MIXED_OAK_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_MIXED_OAK_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1),
                        ModBlocks.RED_MIXED_OAK_SAPLING.get()));
        register(context, YELLOW_MIXED_OAK_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_MIXED_OAK_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(5, 0.1F, 1),
                        ModBlocks.YELLOW_MIXED_OAK_SAPLING.get()));
        register(context, MIXED_OAK_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.MIXED_OAK_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1),
                        ModBlocks.PURPLE_MIXED_OAK_SAPLING.get()));
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
