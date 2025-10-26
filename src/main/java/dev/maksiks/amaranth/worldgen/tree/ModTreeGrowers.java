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

    public static final TreeGrower PURPLE_MIXED_OAK_GROWER = new TreeGrower(Amaranth.MOD_ID + ":purple_mixed_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.PURPLE_MIXED_OAK_KEY), Optional.empty());
    public static final TreeGrower RED_MIXED_OAK_GROWER = new TreeGrower(Amaranth.MOD_ID + ":red_mixed_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.RED_MIXED_OAK_KEY), Optional.empty());
    public static final TreeGrower YELLOW_MIXED_OAK_GROWER = new TreeGrower(Amaranth.MOD_ID + ":yellow_mixed_oak",
            Optional.empty(), Optional.of(ModConfiguredFeatures.YELLOW_MIXED_OAK_KEY), Optional.empty());

    public static final TreeGrower TRIMMED_TREE_GROWER = new TreeGrower(Amaranth.MOD_ID + ":trimmed_tree",
            Optional.empty(), Optional.of(ModConfiguredFeatures.TRIMMED_TREE_KEY), Optional.empty());

    public static final TreeGrower ANTHOCYANIN_GROWER = new TreeGrower(Amaranth.MOD_ID + ":anthocyanin",
            Optional.empty(), Optional.of(ModConfiguredFeatures.ANTHOCYANIN_KEY), Optional.empty());

    public static final TreeGrower SPEARY_GROWER = new TreeGrower(Amaranth.MOD_ID + ":speary",
            Optional.empty(), Optional.of(ModConfiguredFeatures.SPEARY_KEY), Optional.empty());

    public static final TreeGrower WISTERIA_GROWER = new TreeGrower(Amaranth.MOD_ID + ":wisteria",
            Optional.empty(), Optional.of(ModConfiguredFeatures.WISTERIA_KEY), Optional.empty());
}