package dev.maksiks.amaranth.worldgen.levelgen.noise;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise;
import net.minecraft.world.phys.Vec3;

public class ModNoises {
    public static final ResourceKey<NormalNoise.NoiseParameters> SILVER_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath("amaranth", "silver")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> STRIPE_ATTEMPT_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath("amaranth", "stripe_attempt")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> VEINY_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath("amaranth", "veiny")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> PATCHY_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath("amaranth", "patchy")
            );


}
