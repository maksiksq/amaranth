package dev.maksiks.amaranth.worldgen.tree;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.worldgen.ModConfiguredFeatures;
import net.minecraft.world.level.block.grower.TreeGrower;

import java.util.Optional;

public class ModTreeGrowers {
    public static final TreeGrower MYSTIC_GROWER = new TreeGrower(Amaranth.MOD_ID + ":mystic",
            Optional.empty(), Optional.of(ModConfiguredFeatures.MYSTIC_KEY), Optional.empty());
    public static final TreeGrower STUBBY_GROWER = new TreeGrower(Amaranth.MOD_ID + ":stubby",
            Optional.empty(), Optional.of(ModConfiguredFeatures.STUBBY_KEY), Optional.empty());
    public static final TreeGrower SILVER_BIRCH_GROWER = new TreeGrower(Amaranth.MOD_ID + ":silver_birch",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SILVER_BIRCH_KEY), Optional.empty());
}