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
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class ModPlacedFeatures {
    public static final ResourceKey<PlacedFeature> MYSTIC_TREE_PLACED_KEY = registerKey("mystic_tree_placed");
    public static final ResourceKey<PlacedFeature> MYSTIC_FLOWER_PLACED_KEY = registerKey("mystic_flower_placed");
    public static final ResourceKey<PlacedFeature> MYSTIC_AMETHYST_PLACED_KEY = registerKey("mystic_amethyst_placed");

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
    }

    private static ResourceKey<PlacedFeature> registerKey(String name) {
        return ResourceKey.create(Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name));
    }

    private static void register(BootstrapContext<PlacedFeature> context, ResourceKey<PlacedFeature> key, Holder<ConfiguredFeature<?, ?>> configuration,
                                 List<PlacementModifier> modifiers) {
        context.register(key, new PlacedFeature(configuration, List.copyOf(modifiers)));
    }
}
