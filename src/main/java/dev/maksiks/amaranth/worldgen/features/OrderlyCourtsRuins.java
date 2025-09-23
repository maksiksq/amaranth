package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class OrderlyCourtsRuins extends Feature<NoneFeatureConfiguration> {

    public OrderlyCourtsRuins(Codec<NoneFeatureConfiguration> codec) {
        super(codec);
    }

    // so i was originally going to make these like 50 separate structures but
    // then i realized that's probably an awful idea
    // making it a placed feature is pain tho because all of these are kind of
    // different so making them in code is tedious as hell
    // so i just threw the nbt files into chatgpt and told it to make the structures
    // i dont like vibe coding stuff much but this is a brilliant usage of it imo
    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> ctx) {
        return false;
    }
}