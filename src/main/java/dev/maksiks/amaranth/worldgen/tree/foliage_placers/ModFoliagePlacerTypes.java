package dev.maksiks.amaranth.worldgen.tree.foliage_placers;

import dev.maksiks.amaranth.Amaranth;

import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;
import net.minecraft.world.level.levelgen.feature.trunkplacers.*;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

import static net.minecraft.core.registries.BuiltInRegistries.FOLIAGE_PLACER_TYPE;

public class ModFoliagePlacerTypes{
    public static final DeferredRegister<FoliagePlacerType<?>> FOLIAGE_PLACER_TYPES =
            DeferredRegister.create(FOLIAGE_PLACER_TYPE, Amaranth.MOD_ID);

    public static final DeferredHolder<FoliagePlacerType<?>, FoliagePlacerType<MysticFoliagePlacer>> MYSTIC_FOLIAGE_PLACER =
            FOLIAGE_PLACER_TYPES.register("foliage_trunk_placer", () -> new FoliagePlacerType<>(MysticFoliagePlacer.CODEC));
}
