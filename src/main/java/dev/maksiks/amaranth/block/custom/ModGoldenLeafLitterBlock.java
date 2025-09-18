package dev.maksiks.amaranth.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;

public class ModGoldenLeafLitterBlock extends PinkPetalsBlock {
    public ModGoldenLeafLitterBlock(Properties p_273335_) {
        super(p_273335_);
    }

    @Override
    protected boolean mayPlaceOn(BlockState state, BlockGetter level, BlockPos pos) {
        return state.is(BlockTags.DIRT) || state.is(BlockTags.TERRACOTTA) || state.getBlock() instanceof net.minecraft.world.level.block.FarmBlock;
    }

}
