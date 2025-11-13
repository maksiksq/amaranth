package dev.maksiks.amaranth.worldgen.noise;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.synth.NormalNoise;

public class ModNoises {
    public static final ResourceKey<NormalNoise.NoiseParameters> SILVER_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "silver")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> STRIPE_ATTEMPT_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "stripe_attempt")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> VEINY_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "veiny")
            );
    
    public static final ResourceKey<NormalNoise.NoiseParameters> CRACKED_VEINY_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "cracked_veiny")
            );

    public static final ResourceKey<NormalNoise.NoiseParameters> PATCHY_NOISE =
            ResourceKey.create(
                    Registries.NOISE,
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "patchy")
            );


}
