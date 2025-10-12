package dev.maksiks.amaranth.worldgen.features;

import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModFeatures {
    public static final DeferredRegister<Feature<?>> FEATURES =
            DeferredRegister.create(BuiltInRegistries.FEATURE, "amaranth");

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESOLATE_SPIKE_FEATURE =
            FEATURES.register("desolate_spike",
                    () -> new DesolateSpikeFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ORDERLY_COURTS_RUINS_FEATURE =
            FEATURES.register("orderly_courts_ruins",
                    () -> new OrderlyCourtsRuinsFeature(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> SPIKY_ARCHES_FILL_FEATURE =
            FEATURES.register("spiky_arches_fill",
                    () -> new SpikyArchesFillFeature(SimpleBlockConfiguration.CODEC));


    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> THICK_PUMPKIN_FEATURE =
            FEATURES.register("thick_pumpkin",
                    () -> new ThickPumpkinFeature(NoneFeatureConfiguration.CODEC));
}