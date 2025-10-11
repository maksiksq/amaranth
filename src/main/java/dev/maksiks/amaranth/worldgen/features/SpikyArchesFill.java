package dev.maksiks.amaranth.worldgen.features;

import com.mojang.serialization.Codec;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.SimpleBlockFeature;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;

public class SpikyArchesFill extends SimpleBlockFeature {
    public SpikyArchesFill(Codec<SimpleBlockConfiguration> p_66808_) {
        super(p_66808_);
    }

    @Override
    public boolean place(FeaturePlaceContext<SimpleBlockConfiguration> p_160341_) {
        SimpleBlockConfiguration simpleblockconfiguration = p_160341_.config();
        WorldGenLevel worldgenlevel = p_160341_.level();
        BlockPos blockpos = p_160341_.origin();
        BlockState blockstate = simpleblockconfiguration.toPlace().getState(p_160341_.random(), blockpos);
        if (blockstate.canSurvive(worldgenlevel, blockpos)) {
            if (blockstate.getBlock() instanceof DoublePlantBlock) {
                if (!worldgenlevel.isEmptyBlock(blockpos.above())) {
                    return false;
                }

                DoublePlantBlock.placeAt(worldgenlevel, blockstate, blockpos, 2);
            } else {
                if (!worldgenlevel.getBlockState(blockpos.below()).isFaceSturdy(worldgenlevel, blockpos.below(), Direction.UP)
                                && !worldgenlevel.getBlockState(blockpos.below()).is(ModBlocks.SPIKY_ARCHES.get())) {
                    return false;
                }
                worldgenlevel.setBlock(blockpos, blockstate, 2);
            }

            return true;
        } else {
            return false;
        }
    }
}
