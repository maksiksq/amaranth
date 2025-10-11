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

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> DESOLATE_SPIKE =
            FEATURES.register("desolate_spike",
                    () -> new DesolateSpike(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<NoneFeatureConfiguration>> ORDERLY_COURTS_RUINS =
            FEATURES.register("orderly_courts_ruins",
                    () -> new OrderlyCourtsRuins(NoneFeatureConfiguration.CODEC));

    public static final DeferredHolder<Feature<?>, Feature<SimpleBlockConfiguration>> SPIKY_ARCHES_FILL =
            FEATURES.register("spiky_arches_fill",
                    () -> new SpikyArchesFill(SimpleBlockConfiguration.CODEC));
}