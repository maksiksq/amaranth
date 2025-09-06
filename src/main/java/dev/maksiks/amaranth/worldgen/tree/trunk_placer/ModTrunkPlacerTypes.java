package dev.maksiks.amaranth.worldgen.tree.trunk_placer;

import dev.maksiks.amaranth.Amaranth;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModTrunkPlacerTypes{
    public static final DeferredRegister<TrunkPlacerType<?>> TRUNK_PLACER_TYPES =
            DeferredRegister.create(BuiltInRegistries.TRUNK_PLACER_TYPE, Amaranth.MOD_ID);

    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<MysticTrunkPlacer>> MYSTIC_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("mystic_trunk_placer", () -> new TrunkPlacerType<>(MysticTrunkPlacer.CODEC));
}
