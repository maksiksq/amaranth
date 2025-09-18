package dev.maksiks.amaranth.worldgen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.block.custom.ModGoldenLeafLitterBlock;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.MysticFoliagePlacer;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.SilverBirchFoliagePlacer;
import dev.maksiks.amaranth.worldgen.tree.foliage_placer.StubbyFoliagePlacer;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.MysticTrunkPlacer;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.StubbyTrunkPlacer;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.InclusiveRange;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.BlobFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.DualNoiseProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.StraightTrunkPlacer;
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

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context) {
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
                        ModBlocks.GOLDEN_LEAF_LITTER.get().defaultBlockState().setValue(ModGoldenLeafLitterBlock.AMOUNT, Integer.valueOf(i)).setValue(ModGoldenLeafLitterBlock.FACING, direction), 1
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
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> registerKey(String name) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> key, F feature, FC configuration) {
        context.register(key, new ConfiguredFeature<>(feature, configuration));
    }
}
