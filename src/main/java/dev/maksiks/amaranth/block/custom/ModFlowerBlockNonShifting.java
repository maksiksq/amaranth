package dev.maksiks.amaranth.block.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.FlowerBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class ModFlowerBlockNonShifting extends FlowerBlock {
    public ModFlowerBlockNonShifting(Holder<MobEffect> effect, float seconds, Properties properties) {
        super(effect, seconds, properties);
    }

    ///
    /// Not shifting it unlike usual flowers
    /// I still want the soup effect lol
    /// edit: apparently it shifts with the offset in properties?
    /// then what did this move?
    /// confused
    ///
    @Override
    protected VoxelShape getShape(BlockState state, BlockGetter level, BlockPos pos, CollisionContext context) {
        return SHAPE;
    }
}
