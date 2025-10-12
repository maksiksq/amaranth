package dev.maksiks.amaranth.block.custom;

import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;

public class ModThickPumpkinBlock extends Block {
    public ModThickPumpkinBlock(Properties properties) {
        super(properties);
        this.stateDefinition.any()
                .setValue(BlockStateProperties.NORTH, false)
                .setValue(BlockStateProperties.SOUTH, false)
                .setValue(BlockStateProperties.EAST, false)
                .setValue(BlockStateProperties.WEST, false)
                .setValue(BlockStateProperties.UP, false)
                .setValue(BlockStateProperties.DOWN, false);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(
                BlockStateProperties.NORTH,
                BlockStateProperties.SOUTH,
                BlockStateProperties.EAST,
                BlockStateProperties.WEST,
                BlockStateProperties.UP,
                BlockStateProperties.DOWN
        );
    }
}
