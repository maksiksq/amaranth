package dev.maksiks.amaranth.worldgen.tree.trunk_placer;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.tree.trunk_placer.custom_placer.*;
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
    public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<WisteriaTrunkPlacer>> WISTERIA_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("wisteria_trunk_placer", () -> new TrunkPlacerType<>(WisteriaTrunkPlacer.CODEC));
   public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<ModififedForkingTrunkPlacer>> MODIFIED_FORKING_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("modified_forking_trunk_placer", () -> new TrunkPlacerType<>(ModififedForkingTrunkPlacer.CODEC));
   public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<WitchyTrunkPlacer>> WITCHY_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("witchy_trunk_placer", () -> new TrunkPlacerType<>(WitchyTrunkPlacer.CODEC));
   public static final DeferredHolder<TrunkPlacerType<?>, TrunkPlacerType<AlpineSpruceTrunkPlacer>> ALPINE_SPRUCE_TRUNK_PLACER =
            TRUNK_PLACER_TYPES.register("alpine_spruce_placer", () -> new TrunkPlacerType<>(AlpineSpruceTrunkPlacer.CODEC));
}
