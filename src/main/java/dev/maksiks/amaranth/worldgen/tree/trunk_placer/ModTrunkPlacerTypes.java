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
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<StubbyTrunkPlacer>> STUBBY_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("stubby_trunk_placer", () -> new TrunkPlacerType<>(StubbyTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<TreeOnTreeTreeTrunkPlacer>> TREE_ON_TREE_TREE_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("tree_on_tree_tree_trunk_placer", () -> new TrunkPlacerType<>(TreeOnTreeTreeTrunkPlacer.CODEC));
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<AnthocyaninTrunkPlacer>> ANTHOCYANIN_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("anthocyanin_trunk_placer", () -> new TrunkPlacerType<>(AnthocyaninTrunkPlacer.CODEC));
}
