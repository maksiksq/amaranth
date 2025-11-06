package dev.maksiks.amaranth.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;
import net.neoforged.neoforge.common.util.TriState;

import javax.annotation.Nullable;

public class ReedsBlock extends DoublePlantBlock implements SimpleWaterloggedBlock {
    public static final BooleanProperty WATERLOGGED = BlockStateProperties.WATERLOGGED;

    public ReedsBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(
                this.stateDefinition.any()
                        .setValue(HALF, DoubleBlockHalf.LOWER)
                        .setValue(WATERLOGGED, false)
        );
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        super.createBlockStateDefinition(builder);
        builder.add(WATERLOGGED);
    }

    @Override
    public FluidState getFluidState(BlockState state) {
        return state.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(state);
    }

    @Override
    public BlockState updateShape(BlockState state, Direction direction, BlockState neighborState,
                                  LevelAccessor level, BlockPos currentPos, BlockPos neighborPos) {
        if (state.getValue(WATERLOGGED)) {
            level.scheduleTick(currentPos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }
        return super.updateShape(state, direction, neighborState, level, currentPos, neighborPos);
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        FluidState fluid = context.getLevel().getFluidState(context.getClickedPos());
        boolean isWater = fluid.getType() == Fluids.WATER;
        BlockState state = super.getStateForPlacement(context);
        return state != null ? state.setValue(WATERLOGGED, isWater) : null;
    }

    @Override
    protected boolean canSurvive(BlockState state, LevelReader level, BlockPos pos) {
        DoubleBlockHalf half = state.getValue(HALF);
        BlockPos belowPos = pos.below();
        BlockState below = level.getBlockState(belowPos);

        if (half == DoubleBlockHalf.UPPER) {
            return below.is(this) && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }

        // checking if the block is valid
        TriState soilDecision = below.canSustainPlant(level, pos.below(), Direction.UP, state);
        if (!soilDecision.isDefault()) return soilDecision.isTrue();
        if (!isValidReedSoil(below)) return false;

        // in water
        BlockPos abovePos = pos.above();
        FluidState fluidHere = level.getFluidState(pos);
        FluidState fluidAbove = level.getFluidState(abovePos);

        boolean waterHere = fluidHere.is(Fluids.WATER);
        boolean waterAbove = fluidAbove.is(Fluids.WATER);

        if (waterHere && !waterAbove) {
            return true;
        }

        // out of water
        if (!waterHere) {
            for (Direction dir : Direction.Plane.HORIZONTAL) {
                FluidState nearby = level.getFluidState(belowPos.relative(dir));
                if (nearby.is(Fluids.WATER)) {
                    return true;
                }
            }
        }

        return false;
    }

    public static boolean isValidReedSoil(BlockState state) {
        return
                state.is(BlockTags.DIRT) ||
                state.is(BlockTags.SAND) ||
                state.is(Blocks.GRAVEL) ||
                state.is(Blocks.CLAY) ||
                state.is(Blocks.MUD);
    }
}
