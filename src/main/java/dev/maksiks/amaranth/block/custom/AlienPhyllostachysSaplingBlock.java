package dev.maksiks.amaranth.block.custom;

import com.mojang.serialization.MapCodec;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooSaplingBlock;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;

public class AlienPhyllostachysSaplingBlock extends BambooSaplingBlock {
    public static final MapCodec<AlienPhyllostachysSaplingBlock> CODEC =
            simpleCodec(AlienPhyllostachysSaplingBlock::new);

    @Override
    @SuppressWarnings("unchecked")
    public MapCodec<BambooSaplingBlock> codec() {
        return (MapCodec<BambooSaplingBlock>) (Object) CODEC;
    }

    public AlienPhyllostachysSaplingBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected BlockState updateShape(BlockState state, Direction facing, BlockState facingState, LevelAccessor level, BlockPos currentPos, BlockPos facingPos) {
        if (!state.canSurvive(level, currentPos)) {
            return Blocks.AIR.defaultBlockState();
        } else {
            if (facing == Direction.UP && facingState.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())) {
                level.setBlock(currentPos, ModBlocks.ALIEN_PHYLLOSTACHYS.get().defaultBlockState(), 2);
            }

            return super.updateShape(state, facing, facingState, level, currentPos, facingPos);
        }
    }

    @Override
    public ItemStack getCloneItemStack(LevelReader level, BlockPos pos, BlockState state) {
        return new ItemStack(ModBlocks.ALIEN_PHYLLOSTACHYS.asItem());
    }

    @Override
    protected void growBamboo(Level level, BlockPos state) {
        level.setBlock(state.above(), ModBlocks.ALIEN_PHYLLOSTACHYS.get().defaultBlockState().setValue(BambooStalkBlock.LEAVES, BambooLeaves.SMALL), 3);
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        net.neoforged.neoforge.common.util.TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), net.minecraft.core.Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return level.getBlockState(pos.below()).is(ModTags.Blocks.ALIEN_PHYLLOSTACHYS_PLANTABLE_ON);
    }

}
