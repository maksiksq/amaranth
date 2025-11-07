package dev.maksiks.amaranth.worldgen.tree.foliage_placer;

import dev.maksiks.amaranth.Amaranth;

import dev.maksiks.amaranth.worldgen.tree.foliage_placer.custom.*;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.core.registries.BuiltInRegistries.FOLIAGE_PLACER_TYPE;

public class ModFoliagePlacerTypes{
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(FOLIAGE_PLACER_TYPE, Amaranth.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<MysticFoliagePlacer>> MYSTIC_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("foliage_trunk_placer", () -> new FoliagePlacerType<>(MysticFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<StubbyFoliagePlacer>> STUBBY_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("stubby_trunk_placer", () -> new FoliagePlacerType<>(StubbyFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SilverBirchFoliagePlacer>> SILVER_BIRCH_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("silver_birch_foliage_placer", () -> new FoliagePlacerType<>(SilverBirchFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<TrimmedTreeFoliagePlacer>> TRIMMED_TREE_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("trimmed_tree_foliage_placer", () -> new FoliagePlacerType<>(TrimmedTreeFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<TreeOnTreeTreeFoliagePlacer>> TREE_ON_TREE_TREE_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("tree_on_tree_tree_foliage_placer", () -> new FoliagePlacerType<>(TreeOnTreeTreeFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<AnthocyaninFoliagePlacer>> ANTHOCYANIN_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("anthocyanin_foliage_placer", () -> new FoliagePlacerType<>(AnthocyaninFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<SpearyFoliagePlacer>> SPEARY_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("speary_foliage_placer", () -> new FoliagePlacerType<>(SpearyFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<WitchyFoliagePlacer>> WITCHY_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("witchy_foliage_placer", () -> new FoliagePlacerType<>(WitchyFoliagePlacer.CODEC));

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<AlpineSpruceFoliagePlacer>> ALPINE_SPRUCE_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("alpine_spruce_foliage_placer", () -> new FoliagePlacerType<>(AlpineSpruceFoliagePlacer.getCODEC()));
}
