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
import net.minecraft.util.valueproviders.UniformInt;
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
    public static final ResourceKey<PlacedFeature> MIXED_OAK_PLACED_KEY = registerKey("mixed_oak_placed");

    public static final ResourceKey<PlacedFeature> TRIMMED_TREE_PLACED_KEY = registerKey("trimmed_tree_placed");
    public static final ResourceKey<PlacedFeature> ORDERLY_FLOWER_PLACED_KEY = registerKey("orderly_flower_placed");
    public static final ResourceKey<PlacedFeature> ORDERLY_COURTS_RUINS_PLACED_KEY = registerKey("orderly_courts_ruins_placed");

    public static final ResourceKey<PlacedFeature> TREE_ON_TREE_TREE_PLACED_KEY = registerKey("tree_on_tree_tree_placed");

    public static final ResourceKey<PlacedFeature> ANTHOCYANIN_PLACED_KEY = registerKey("anthocyanin_placed");
    public static final ResourceKey<PlacedFeature> ANTHOCYANIN_FLOWER_PLACED_KEY = registerKey("anthocyanin_flower_placed");

    public static final ResourceKey<PlacedFeature> FIELDS_OF_PAIN_FILL_PLACED_KEY = registerKey("fields_of_pain_fill_placed");

    public static final ResourceKey<PlacedFeature> THICK_PUMPKIN_PLACED_KEY = registerKey("thick_pumpkin_placed");
    public static final ResourceKey<PlacedFeature> THRUMLETONS_FLOWER_PLACED_KEY = registerKey("thrumletons_flower_placed");

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

        // desolate
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

        register(context, TRIMMED_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TRIMMED_TREE_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(3, 0.1F, 1),
                        ModBlocks.TRIMMED_TREE_SAPLING.get()));


        // orderly
        register(context, ORDERLY_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ORDERLY_FLOWER_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        register(
                context,
                ORDERLY_COURTS_RUINS_PLACED_KEY,
                configuredFeatures.getOrThrow(ModConfiguredFeatures.ORDERLY_COURTS_RUINS_KEY),
                List.of(
                        CountPlacement.of(1),
                        RarityFilter.onAverageOnceEvery(4),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP_WORLD_SURFACE,
                        BiomeFilter.biome()
                )
        );

        // tree on tree
        register(context, TREE_ON_TREE_TREE_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.TREE_ON_TREE_TREE_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(100, 0.1F, 1),
                        ModBlocks.PURPLE_MIXED_OAK_SAPLING.get()));

        // anthocyanin
        register(context, ANTHOCYANIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ANTHOCYANIN_KEY),
                // 1 / chance has to be integer mojang why
                VegetationPlacements.treePlacement(PlacementUtils.countExtra(10, 0.1F, 1),
                        ModBlocks.ANTHOCYANIN_SAPLING.get()));

        register(context, ANTHOCYANIN_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.ANTHOCYANIN_FLOWER_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

        // pain
        register(context, FIELDS_OF_PAIN_FILL_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.FIELDS_OF_PAIN_FILL_KEY),
                List.of(InSquarePlacement.spread(), CountPlacement.of(40), PlacementUtils.HEIGHTMAP, BiomeFilter.biome())
        );

        // thrumletons
        register(context, THICK_PUMPKIN_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.THICK_PUMPKIN_KEY),
                List.of(
                        CountPlacement.of(2),
                        InSquarePlacement.spread(),
                        PlacementUtils.HEIGHTMAP,
                        BiomeFilter.biome(),
                        HeightmapPlacement.onHeightmap(Heightmap.Types.WORLD_SURFACE_WG)
                )
        );

        register(context, THRUMLETONS_FLOWER_PLACED_KEY, configuredFeatures.getOrThrow(ModConfiguredFeatures.THRUMLETONS_FLOWER_KEY),
                List.of(InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
