package dev.maksiks.amaranth.worldgen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.block.custom.GoldenLeafLitterBlock;
import dev.maksiks.amaranth.block.custom.SpikyArchesBlock;
import dev.maksiks.amaranth.worldgen.features.ModFeatures;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom_placer.*;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer.*;
import net.minecraft.core.Direction;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

import java.util.List;

public class ModConfiguredFeatures {
    public static ResourceKey<ConfiguredFeature<?, ?>> MYSTIC_KEY = registerKey("mystic");
    public static ResourceKey<ConfiguredFeature<?, ?>> MYSTIC_FLOWER_KEY = registerKey("mystic_flower");
    public static ResourceKey<ConfiguredFeature<?, ?>> MYSTIC_AMETHYST_KEY = registerKey("mystic_amethyst");

    public static ResourceKey<ConfiguredFeature<?, ?>> STUBBY_KEY = registerKey("stubby");

    public static ResourceKey<ConfiguredFeature<?, ?>> SILVER_BIRCH_KEY = registerKey("silver_birch");
    public static ResourceKey<ConfiguredFeature<?, ?>> SILVER_BIRCH_FLOWER_KEY = registerKey("silver_birch_flower");
    public static ResourceKey<ConfiguredFeature<?, ?>> GOLDEN_LEAF_LITTER_KEY = registerKey("golden_leaf_litter");

    public static ResourceKey<ConfiguredFeature<?, ?>> DESOLATE_SPIKE_KEY = registerKey("desolate_spike");

    public static ResourceKey<ConfiguredFeature<?, ?>> PURPLE_MIXED_OAK_KEY = registerKey("purple_mixed_oak");
    public static ResourceKey<ConfiguredFeature<?, ?>> RED_MIXED_OAK_KEY = registerKey("red_mixed_oak");
    public static ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MIXED_OAK_KEY = registerKey("yellow_mixed_oak");
    public static ResourceKey<ConfiguredFeature<?, ?>> MIXED_OAK_KEY = registerKey("mixed_oak");

    public static ResourceKey<ConfiguredFeature<?, ?>> TRIMMED_TREE_KEY = registerKey("trimmed_tree");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORDERLY_FLOWER_KEY = registerKey("orderly_flower");
    public static ResourceKey<ConfiguredFeature<?, ?>> ORDERLY_COURTS_RUINS_KEY = registerKey("orderly_courts_ruins");

    public static ResourceKey<ConfiguredFeature<?, ?>> TREE_ON_TREE_TREE_KEY = registerKey("tree_on_tree_tree");

    public static ResourceKey<ConfiguredFeature<?, ?>> ANTHOCYANIN_KEY = registerKey("anthocyanin");
    public static ResourceKey<ConfiguredFeature<?, ?>> ANTHOCYANIN_FLOWER_KEY = registerKey("anthocyanin_flower");

    public static ResourceKey<ConfiguredFeature<?, ?>> FIELDS_OF_PAIN_FILL_KEY = registerKey("fields_of_pain_fill");

    public static ResourceKey<ConfiguredFeature<?, ?>> THICK_PUMPKIN_KEY = registerKey("thick_pumpkin");
    public static ResourceKey<ConfiguredFeature<?, ?>> THRUMLETONS_FLOWER_KEY = registerKey("thrumletons_flower");

    public static ResourceKey<ConfiguredFeature<?, ?>> SPEARY_KEY = registerKey("speary");
    public static ResourceKey<ConfiguredFeature<?, ?>> SPEARY_FLOWER_KEY = registerKey("speary_flower");

    public static ResourceKey<ConfiguredFeature<?, ?>> WISTERIA_KEY = registerKey("wisteria");
    public static ResourceKey<ConfiguredFeature<?, ?>> PASTEL_FLOWER_KEY = registerKey("pastel_flower");

    public static ResourceKey<ConfiguredFeature<?, ?>> MUSH_REEDS_KEY = registerKey("mush_reeds");
    public static ResourceKey<ConfiguredFeature<?, ?>> MUSH_REEDS_WATER_KEY = registerKey("mush_reeds_water");

    public static ResourceKey<ConfiguredFeature<?, ?>> RED_MINI_SHROOM_KEY = registerKey("red_mini_shroom");
    public static ResourceKey<ConfiguredFeature<?, ?>> BROWN_MINI_SHROOM_KEY = registerKey("brown_mini_shroom");

    public static ResourceKey<ConfiguredFeature<?, ?>> WITCHY_KEY = registerKey("witchy");

    public static ResourceKey<ConfiguredFeature<?, ?>> LUPINE_FILL_KEY = registerKey("lupine_fill");

    public static ResourceKey<ConfiguredFeature<?, ?>> ALPINE_SPRUCE_KEY = registerKey("alpine");
    public static ResourceKey<ConfiguredFeature<?, ?>> OCCASIONAL_BERRY_BUSH_KEY = registerKey("occasional_berry_bushes");
    public static ResourceKey<ConfiguredFeature<?, ?>> BOULDER_KEY = registerKey("boulder");

    public static ResourceKey<ConfiguredFeature<?, ?>> SPRING_FLOWER_ALLIUM_KEY = registerKey("spring_allium");
    public static ResourceKey<ConfiguredFeature<?, ?>> SPRING_FLOWER_PHLOX_KEY = registerKey("spring_phlox");

    public static ResourceKey<ConfiguredFeature<?, ?>> SATISTREE_KEY = registerKey("satistree");
    public static ResourceKey<ConfiguredFeature<?, ?>> GIGANTIC_SATISTREE_KEY = registerKey("gigantic_satistree");
    public static ResourceKey<ConfiguredFeature<?, ?>> ALIEN_FENCE_PLANT_KEY = registerKey("alien_fence_plant");
    public static ResourceKey<ConfiguredFeature<?, ?>> ALIEN_PHYLLOSTACHYS_KEY = registerKey("alien_phyllostachys");
    public static ResourceKey<ConfiguredFeature<?, ?>> ALIEN_PHYLLOSTACHYS_PATCH_KEY = registerKey("alien_phyllostachys_patch");
    public static ResourceKey<ConfiguredFeature<?, ?>> ROCK_KEY = registerKey("rock");
    public static ResourceKey<ConfiguredFeature<?, ?>> SATIS_PITCHER_PLANT_FLOWER_KEY = registerKey("satis_pitcher_plant_flower");
    public static ResourceKey<ConfiguredFeature<?, ?>> SATIS_FLOWER_KEY = registerKey("satis_flower");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatures = context.lookup(Registries.CONFIGURED_FEATURE);

        // mystic
        register(context, MYSTIC_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(ModBlocks.MYSTIC_LOG.get()),
                new MysticTrunkPlacer(9, 2, 0),

                BlockStateProvider.simple(ModBlocks.MYSTIC_LEAVES.get()),
                new MysticFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 7),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        register(
                context,
                MYSTIC_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        196,
                        12,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new DualNoiseProvider(
                                                new InclusiveRange<>(1, 3),
                                                new NormalNoise.NoiseParameters(-10, 1.0),
                                                1.0F,
                                                2345L,
                                                new NormalNoise.NoiseParameters(-3, 1.0),
                                                1.0F,
                                                List.of(
                                                        Blocks.TALL_GRASS.defaultBlockState(),
                                                        Blocks.ALLIUM.defaultBlockState(),
                                                        Blocks.AZURE_BLUET.defaultBlockState(),
                                                        Blocks.WHITE_TULIP.defaultBlockState(),
                                                        Blocks.CORNFLOWER.defaultBlockState(),
                                                        Blocks.OXEYE_DAISY.defaultBlockState(),
                                                        Blocks.SHORT_GRASS.defaultBlockState()
                                                )
                                        )
                                )
                        )
                )
        );

        register(
                context,
                MYSTIC_AMETHYST_KEY,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        60,
                        24,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(Blocks.SMALL_AMETHYST_BUD.defaultBlockState(), 5)
                                                        .add(Blocks.MEDIUM_AMETHYST_BUD.defaultBlockState(), 2)
                                                        .add(Blocks.LARGE_AMETHYST_BUD.defaultBlockState(), 1)
                                                        .build()
                                        )
                                )
                        )
                )
        );

        // stubby
        register(context, STUBBY_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.OAK_LOG),
                new StubbyTrunkPlacer(3, 1, 1),

                BlockStateProvider.simple(Blocks.ACACIA_LEAVES),
                new StubbyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),

                new TwoLayersFeatureSize(1, 0, 2)).build());

        // silver birch
        register(context, SILVER_BIRCH_KEY, Feature.TREE, new TreeConfiguration.TreeConfigurationBuilder(
                BlockStateProvider.simple(Blocks.BIRCH_LOG),
                new MysticTrunkPlacer(10, 2, 0),

                new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                        .add(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get().defaultBlockState(), 1)
                        .add(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get().defaultBlockState(), 12)
                        .add(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get().defaultBlockState(), 7)
                        .build()),

                new SilverBirchFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 7),

                new TwoLayersFeatureSize(1, 0, 2)).build());


        register(
                context,
                SILVER_BIRCH_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        240,
                        12,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new DualNoiseProvider(
                                                new InclusiveRange<>(1, 3),
                                                new NormalNoise.NoiseParameters(-10, 1.0),
                                                1.0F,
                                                2345L,
                                                new NormalNoise.NoiseParameters(-3, 1.0),
                                                1.0F,
                                                List.of(
                                                        Blocks.OXEYE_DAISY.defaultBlockState(),
                                                        Blocks.WHITE_TULIP.defaultBlockState(),
                                                        Blocks.AZURE_BLUET.defaultBlockState(),
                                                        Blocks.DANDELION.defaultBlockState()
                                                )
                                        )
                                )
                        )
                )
        );

        SimpleWeightedRandomList.Builder<BlockState> goldenLeafLitterBuilder = SimpleWeightedRandomList.builder();

        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                goldenLeafLitterBuilder.add(
                        ModBlocks.GOLDEN_LEAF_LITTER.get().defaultBlockState().setValue(GoldenLeafLitterBlock.AMOUNT, Integer.valueOf(i)).setValue(GoldenLeafLitterBlock.FACING, direction), 1
                );
            }
        }

        register(
                context,
                GOLDEN_LEAF_LITTER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(goldenLeafLitterBuilder)))
                )
        );

        // desolate
        register(context, DESOLATE_SPIKE_KEY,
                ModFeatures.DESOLATE_SPIKE_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);

        // mixed
        register(context, PURPLE_MIXED_OAK_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(context, RED_MIXED_OAK_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(ModBlocks.RED_MIXED_OAK_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(context, YELLOW_MIXED_OAK_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get()),
                        new BlobFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(
                context,
                MIXED_OAK_KEY,
                Feature.SIMPLE_RANDOM_SELECTOR,
                new SimpleRandomFeatureConfiguration(
                        HolderSet.direct(
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ModConfiguredFeatures.PURPLE_MIXED_OAK_KEY)),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ModConfiguredFeatures.RED_MIXED_OAK_KEY)),
                                PlacementUtils.inlinePlaced(configuredFeatures.getOrThrow(ModConfiguredFeatures.YELLOW_MIXED_OAK_KEY))
                        )
                )
        );

        // orderly
        register(
                context,
                ORDERLY_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        110, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH)))
                )
        );

        register(
                context,
                TRIMMED_TREE_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(6, 0, 0),
                        BlockStateProvider.simple(Blocks.OAK_LEAVES),
                        new TrimmedTreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        // orderly courts ruins
        register(context, ORDERLY_COURTS_RUINS_KEY,
                ModFeatures.ORDERLY_COURTS_RUINS_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);

        // tree on tree
        register(context, TREE_ON_TREE_TREE_KEY, Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new TreeOnTreeTreeTrunkPlacer(4, 2, 0),
                        BlockStateProvider.simple(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get()),
                        new TreeOnTreeTreeFoliagePlacer(ConstantInt.of(2), ConstantInt.of(0), 3),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        // anthocyanin
        register(
                context,
                ANTHOCYANIN_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.ANTHOCYANIN_LOG.get()),
                        new AnthocyaninTrunkPlacer(6, 0, 0),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                .add(ModBlocks.ANTHOCYANIN_LEAVES.get().defaultBlockState(), 4)
                                .add(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get().defaultBlockState(), 1)
                                .build()),
                        new AnthocyaninFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(
                context,
                ANTHOCYANIN_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        30,
                        16,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get().defaultBlockState(), 5)
                                                        .add(Blocks.BLUE_ORCHID.defaultBlockState(), 2)
                                                        .add(Blocks.CORNFLOWER.defaultBlockState(), 1)
                                                        .build()
                                        )
                                )
                        )
                )
        );

        // pain
        register(
                context,
                FIELDS_OF_PAIN_FILL_KEY,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        22, 12, 1, PlacementUtils.onlyWhenEmpty(ModFeatures.SPIKY_ARCHES_FILL_FEATURE.get(), new SimpleBlockConfiguration(
                        new WeightedStateProvider(
                                SimpleWeightedRandomList.<BlockState>builder()
                                        .add(ModBlocks.SPIKY_ARCHES.get().defaultBlockState().setValue(SpikyArchesBlock.VARIANT, 0), 4)
                                        .add(ModBlocks.SPIKY_ARCHES.get().defaultBlockState().setValue(SpikyArchesBlock.VARIANT, 1), 4)
                                        .add(ModBlocks.SPIKY_ARCHES.get().defaultBlockState().setValue(SpikyArchesBlock.VARIANT, 2), 4)
                                        .add(ModBlocks.SPIKY_ARCHES.get().defaultBlockState().setValue(SpikyArchesBlock.VARIANT, 3), 1)
                                        .build()
                        )
                ))
                )
        );

        register(context, THICK_PUMPKIN_KEY,
                ModFeatures.THICK_PUMPKIN_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);

        register(
                context,
                THRUMLETONS_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        42,
                        16,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        // sunflowers separately
                                                        .add(Blocks.OXEYE_DAISY.defaultBlockState(), 5)
                                                        .add(Blocks.AZURE_BLUET.defaultBlockState(), 3)
                                                        .build()
                                        )
                                )
                        )
                )
        );

        // speary
        register(
                context,
                SPEARY_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.OAK_LOG),
                        new StraightTrunkPlacer(3, 2, 0),
                        BlockStateProvider.simple(Blocks.AZALEA_LEAVES),
                        new SpearyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(
                context,
                SPEARY_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        34,
                        16,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        new WeightedStateProvider(
                                                SimpleWeightedRandomList.<BlockState>builder()
                                                        .add(Blocks.OXEYE_DAISY.defaultBlockState(), 1)
                                                        .add(Blocks.AZURE_BLUET.defaultBlockState(), 1)
                                                        .add(Blocks.POPPY.defaultBlockState(), 1)
                                                        .build()
                                        )
                                )
                        )
                )
        );

        // pastel
        register(
                context,
                WISTERIA_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.JUICY_WISTERIA_LOG.get()),
                        // + juiceless log inside
                        new WisteriaTrunkPlacer(
                                7,
                                1,
                                0,
                                new WeightedListInt(
                                        SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()
                                ),
                                UniformInt.of(2, 4),
                                UniformInt.of(-4, -3),
                                UniformInt.of(-1, 0)
                        ),
                        BlockStateProvider.simple(ModBlocks.WISTERIA_LEAVES.get()),
                        new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.5F, 0.5F, 0.4F, 0.33333334F),
                        new TwoLayersFeatureSize(1, 0, 2)).ignoreVines().build());

        SimpleWeightedRandomList.Builder<BlockState> wisteriaPhloxBuilder = SimpleWeightedRandomList.builder();

        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                wisteriaPhloxBuilder.add(
                        ModBlocks.PHLOX.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1
                );
            }
        }

        register(
                context,
                PASTEL_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(wisteriaPhloxBuilder)))
                )
        );

        // mush
        register(
                context,
                MUSH_REEDS_KEY,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        512, 16, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(
                        BlockStateProvider.simple(ModBlocks.REEDS.get())
                )))
        );

        register(
                context,
                MUSH_REEDS_WATER_KEY,
                ModFeatures.MUSH_REEDS_FEATURE.get(),
                NoneFeatureConfiguration.INSTANCE
        );

        // linked with below, change both
        register(
                context,
                RED_MINI_SHROOM_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.MUSHROOM_STEM),
                        new ModififedForkingTrunkPlacer(3, 1, 2),
                        BlockStateProvider.simple(Blocks.RED_MUSHROOM_BLOCK),
                        new BlobFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(0, 0, 0)
                ).build()
        );

        register(
                context,
                BROWN_MINI_SHROOM_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.MUSHROOM_STEM),
                        new ModififedForkingTrunkPlacer(3, 1, 2),
                        BlockStateProvider.simple(Blocks.BROWN_MUSHROOM_BLOCK),
                        new BlobFoliagePlacer(ConstantInt.of(1), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(0, 0, 0)
                ).build()
        );

        // witchy
        register(
                context,
                WITCHY_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.CHERRY_WOOD),
                        new WitchyTrunkPlacer(0, 0, 0),
                        new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder()
                                .add(Blocks.AZALEA_LEAVES.defaultBlockState(), 4)
                                .add(Blocks.FLOWERING_AZALEA_LEAVES.defaultBlockState(), 1)
                                .build()),
                        new WitchyFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        // lupine
        register(
                context,
                LUPINE_FILL_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        100,
                        12,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(ModBlocks.LUPINE.get())
                                )
                        )
                )
        );

        // alpine
        register(
                context,
                ALPINE_SPRUCE_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(Blocks.SPRUCE_LOG),
                        new AlpineSpruceTrunkPlacer(12, 1, 1),
                        BlockStateProvider.simple(Blocks.SPRUCE_LEAVES),
                        new AlpineSpruceFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(
                context,
                OCCASIONAL_BERRY_BUSH_KEY,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        1,
                        0,
                        0,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.SWEET_BERRY_BUSH.defaultBlockState().setValue(SweetBerryBushBlock.AGE, 3))
                                )
                        )
                )
        );

        register(context, BOULDER_KEY,
                ModFeatures.BOULDER_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);

        // spring
        register(
                context,
                SPRING_FLOWER_ALLIUM_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        85,
                        16,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.ALLIUM)
                                )
                        )
                )
        );

        SimpleWeightedRandomList.Builder<BlockState> springPhloxBuilder = SimpleWeightedRandomList.builder();

        for (int i = 1; i <= 4; i++) {
            for (Direction direction : Direction.Plane.HORIZONTAL) {
                springPhloxBuilder.add(
                        ModBlocks.PHLOX.get().defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1
                );
            }
        }

        register(
                context,
                SPRING_FLOWER_PHLOX_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        80, 12, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(wisteriaPhloxBuilder)))
                )
        );

        // satis
        register(
                context,
                SATISTREE_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.SATISTREE_LOG.get()),
                        new SatistreeTrunkPlacer(12, 1, 1),
                        BlockStateProvider.simple(ModBlocks.ALIEN_LEAVES.get()),
                        new SatistreeFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(
                context,
                GIGANTIC_SATISTREE_KEY,
                ModFeatures.GIGANTIC_SATISTREE_FEATURE.get(),
                NoneFeatureConfiguration.INSTANCE
        );

        register(
                context,
                ALIEN_FENCE_PLANT_KEY,
                Feature.TREE,
                new TreeConfiguration.TreeConfigurationBuilder(
                        BlockStateProvider.simple(ModBlocks.ALIEN_FENCE_PLANT.get()),
                        new AlienFencePlantTrunkPlacer(12, 1, 1),
                        BlockStateProvider.simple(ModBlocks.ALIEN_LEAVES.get()),
                        new AlienFencePlantFoliagePlacer(ConstantInt.of(0), ConstantInt.of(0), 0),
                        new TwoLayersFeatureSize(1, 0, 1)).build()
        );

        register(context,
                ALIEN_PHYLLOSTACHYS_KEY,
                ModFeatures.ALIEN_PHYLLOSTACHYS_FEATURE.get(),
                new ProbabilityFeatureConfiguration(0.0F)
        );

        register(context, ALIEN_PHYLLOSTACHYS_PATCH_KEY,
                Feature.RANDOM_PATCH,
                new RandomPatchConfiguration(
                        256,
                        5,
                        2,
                        PlacementUtils.inlinePlaced(context.lookup(Registries.CONFIGURED_FEATURE).getOrThrow(ALIEN_PHYLLOSTACHYS_KEY))
                )
        );

        register(context, ROCK_KEY,
                ModFeatures.ROCK_FEATURE.get(), NoneFeatureConfiguration.INSTANCE);

        register(
                context,
                SATIS_PITCHER_PLANT_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        24,
                        5,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.PITCHER_PLANT)
                                )
                        )
                )
        );

        register(
                context,
                SATIS_FLOWER_KEY,
                Feature.FLOWER,
                new RandomPatchConfiguration(
                        42,
                        12,
                        2,
                        PlacementUtils.onlyWhenEmpty(
                                Feature.SIMPLE_BLOCK,
                                new SimpleBlockConfiguration(
                                        BlockStateProvider.simple(Blocks.OXEYE_DAISY)
                                )
                        )
                )
        );
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
