package dev.maksiks.amaranth.block.custom;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.tags.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.BambooStalkBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.BambooLeaves;
import net.minecraft.world.level.material.FluidState;

import javax.annotation.Nullable;

public class AlienPhyllostachysStalkBlock extends BambooStalkBlock {
    private static final int MAX_HEIGHT = 5;

    public AlienPhyllostachysStalkBlock(Properties properties) {
        super(properties);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluidstate = context.getLevel().getFluidState(context.getClickedPos());
        if (!fluidstate.isEmpty()) {
            return null;
        } else {
            BlockState blockstate = context.getLevel().getBlockState(context.getClickedPos().below());
            net.neoforged.neoforge.common.util.TriState soilDecision = blockstate.canSustainPlant(context.getLevel(), context.getClickedPos().below(), net.minecraft.core.Direction.UP, this.defaultBlockState());
            if (soilDecision.isDefault() ? blockstate.is(ModTags.Blocks.ALIEN_PHYLLOSTACHYS_PLANTABLE_ON) : soilDecision.isTrue()) {
                if (blockstate.is(ModBlocks.ALIEN_PHYLLOSTACHYS_SAPLING.get())) {
                    return this.defaultBlockState().setValue(AGE, Integer.valueOf(0));
                } else if (blockstate.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())) {
                    int i = blockstate.getValue(AGE) > 0 ? 1 : 0;
                    return this.defaultBlockState().setValue(AGE, Integer.valueOf(i));
                } else {
                    BlockState blockstate1 = context.getLevel().getBlockState(context.getClickedPos().above());
                    return blockstate1.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())
                            ? this.defaultBlockState().setValue(AGE, blockstate1.getValue(AGE))
                            : ModBlocks.ALIEN_PHYLLOSTACHYS_SAPLING.get().defaultBlockState();
                }
            } else {
                return null;
            }
        }
    }

    @Override
    protected BlockState updateShape(
            BlockState state, Direction direction, BlockState neighborState, LevelAccessor level, BlockPos pos, BlockPos neighborPos
    ) {
        if (!state.canSurvive(level, pos)) {
            level.scheduleTick(pos, this, 1);
        }

        if (direction == Direction.UP && neighborState.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get()) && neighborState.getValue(AGE) > state.getValue(AGE)) {
            level.setBlock(pos, state.cycle(AGE), 2);
        }

        return super.updateShape(state, direction, neighborState, level, pos, neighborPos);
    }

    @Override
    protected void growBamboo(BlockState state, Level level, BlockPos pos, RandomSource random, int age) {
        BlockState blockstate = level.getBlockState(pos.below());
        BlockPos blockpos = pos.below(2);
        BlockState blockstate1 = level.getBlockState(blockpos);
        BambooLeaves bambooleaves = BambooLeaves.NONE;
        if (age >= 1) {
            if (!blockstate.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get()) || blockstate.getValue(LEAVES) == BambooLeaves.NONE) {
                bambooleaves = BambooLeaves.SMALL;
            } else if (blockstate.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get()) && blockstate.getValue(LEAVES) != BambooLeaves.NONE) {
                bambooleaves = BambooLeaves.LARGE;
                if (blockstate1.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())) {
                    level.setBlock(pos.below(), blockstate.setValue(LEAVES, BambooLeaves.SMALL), 3);
                    level.setBlock(blockpos, blockstate1.setValue(LEAVES, BambooLeaves.NONE), 3);
                }
            }
        }

        int i = state.getValue(AGE) != 1 && !blockstate1.is(ModBlocks.ALIEN_PHYLLOSTACHYS.get()) ? 0 : 1;
        int j = (age < MAX_HEIGHT-2 || !(random.nextFloat() < 0.25F)) && age != MAX_HEIGHT-1 ? 0 : 1;
        level.setBlock(
                pos.above(), this.defaultBlockState().setValue(AGE, Integer.valueOf(i)).setValue(LEAVES, bambooleaves).setValue(STAGE, Integer.valueOf(j)), 3
        );
    }

    @Override
    protected int getHeightAboveUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < MAX_HEIGHT && level.getBlockState(pos.above(i + 1)).is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())) {
            i++;
        }

        return i;
    }

    @Override
    protected int getHeightBelowUpToMax(BlockGetter level, BlockPos pos) {
        int i = 0;

        while (i < MAX_HEIGHT && level.getBlockState(pos.below(i + 1)).is(ModBlocks.ALIEN_PHYLLOSTACHYS.get())) {
            i++;
        }

        return i;
    }

    @Override
    public boolean isFlammable(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return true;
    }

    @Override
    public int getFlammability(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }

    @Override
    public int getFireSpreadSpeed(BlockState state, BlockGetter level, BlockPos pos, Direction direction) {
        return 60;
    }


    @Override
    protected void randomTick(BlockState state, ServerLevel level, BlockPos pos, RandomSource random) {
        if (state.getValue(STAGE) == 0) {
            if (level.isEmptyBlock(pos.above()) && level.getRawBrightness(pos.above(), 0) >= 9) {
                int i = this.getHeightBelowUpToMax(level, pos) + 1;
                if (i < MAX_HEIGHT && net.neoforged.neoforge.common.CommonHooks.canCropGrow(level, pos, state, random.nextInt(8) == 0)) {
                    this.growBamboo(state, level, pos, random, i);
                    net.neoforged.neoforge.common.CommonHooks.fireCropGrowPost(level, pos, state);
                }
            }
        }
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        net.neoforged.neoforge.common.util.TriState soilDecision = level.getBlockState(pos.below()).canSustainPlant(level, pos.below(), Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        return level.getBlockState(pos.below()).is(ModTags.Blocks.ALIEN_PHYLLOSTACHYS_PLANTABLE_ON);
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        int i = this.getHeightAboveUpToMax(level, pos);
        int j = this.getHeightBelowUpToMax(level, pos);
        return i + j + 1 < MAX_HEIGHT && level.getBlockState(pos.above(i)).getValue(STAGE) != 1;
    }

}
