package dev.maksiks.amaranth.block.custom;

import com.mojang.serialization.MapCodec;
import dev.maksiks.amaranth.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.ParticleUtils;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

public class ModWisteriaLeavesBlock extends ModFlammableLeavesBlock {
    public static final MapCodec<net.minecraft.world.level.block.CherryLeavesBlock> CODEC = simpleCodec(net.minecraft.world.level.block.CherryLeavesBlock::new);

    @Override
    public MapCodec<net.minecraft.world.level.block.CherryLeavesBlock> codec() {
        return CODEC;
    }

    public ModWisteriaLeavesBlock(Properties p_273704_) {
        super(p_273704_);
    }

    @Override
    public void animateTick(BlockState p_272714_, Level level, BlockPos p_273218_, RandomSource p_273360_) {
        super.animateTick(p_272714_, level, p_273218_, p_273360_);
        // adding more falling petals if it's thundering
        // hopefully not to a laggy degree :)
        if (p_273360_.nextInt(level.isThundering() ? 6 : 10) == 0) {
            BlockPos blockpos = p_273218_.below();
            BlockState blockstate = level.getBlockState(blockpos);
            if (!isFaceFull(blockstate.getCollisionShape(level, blockpos), Direction.UP)) {
                ParticleUtils.spawnParticleBelow(level, p_273218_, p_273360_, ModParticles.WISTERIA_PARTICLES.get());
            }
        }
    }
}

