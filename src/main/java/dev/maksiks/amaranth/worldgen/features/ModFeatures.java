package dev.maksiks.amaranth.worldgen.features;

import dev.maksiks.amaranth.worldgen.ModConfiguredFeatures;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;
import net.neoforged.neoforge.registries.NeoForgeRegistries;

import java.util.function.Supplier;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, "amaranth");

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESOLATE_SPIKE =
            FEATURES.register("desolate_spike",
                    () -> new DesolateSpike(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ORDERLY_COURTS_RUINS =
            FEATURES.register("orderly_courts_ruins",
                    () -> new OrderlyCourtsRuins(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> SIMPLE_BLOCK_BUT_NOT_AIR =
            FEATURES.register("simple_block_but_not_air",
                    () -> new SimpleBlockButNotAir(SimpleBlockConfiguration.CODEC));
}