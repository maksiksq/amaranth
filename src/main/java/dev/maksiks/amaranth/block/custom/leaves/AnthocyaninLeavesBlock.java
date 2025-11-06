package dev.maksiks.amaranth.block.custom.leaves;

import com.mojang.serialization.MapCodec;
import dev.maksiks.amaranth.particle.ModParticles;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import static dev.maksiks.amaranth.ClientConfig.MINIMIZE_BIOME_AMBIENCE_PARTICLES;

public class AnthocyaninLeavesBlock extends FlammableLeavesBlock {
    public static final MapCodec<AnthocyaninLeavesBlock> CODEC = simpleCodec(AnthocyaninLeavesBlock::new);

    @Override
    public MapCodec<AnthocyaninLeavesBlock> codec() {
        return CODEC;
    }

    public AnthocyaninLeavesBlock(Properties p_273704_) {
        super(p_273704_);
    }

    @Override
    public void animateTick(BlockState state, Level level, BlockPos pos, RandomSource random) {
        super.animateTick(state, level, pos, random);

        if (MINIMIZE_BIOME_AMBIENCE_PARTICLES.getAsBoolean() && random.nextInt(3) != 0) {
            return;
        }

        if (level.isClientSide && random.nextInt(4) == 0) {
            BlockPos below = pos.below();
            if (!isFaceFull(level.getBlockState(below).getCollisionShape(level, below), Direction.UP)) {
                double radius = 2.0 + random.nextDouble() * 3.0;
                double angle = random.nextDouble() * Math.PI * 2.0;

                double dx = Math.cos(angle) * radius;
                double dz = Math.sin(angle) * radius;

                double y = pos.getY() - 2.0 - random.nextDouble() * 2.0;
                double x = pos.getX() + 0.5 + dx;
                double z = pos.getZ() + 0.5 + dz;

                double vx = dx * 0.004 + (random.nextDouble() - 0.5) * 0.002;
                double vy = -0.005 + (random.nextDouble() - 0.5) * 0.002;
                double vz = dz * 0.004 + (random.nextDouble() - 0.5) * 0.002;

                level.addParticle(ModParticles.ANTHOCYANIN_PARTICLES.get(), x, y, z, vx, vy, vz);
            }
        }
    }
}

