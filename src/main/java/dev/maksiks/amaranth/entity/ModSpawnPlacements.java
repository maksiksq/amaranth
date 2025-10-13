package dev.maksiks.amaranth.entity;

import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.level.BlockAndTintGetter;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;

public class ModSpawnPlacements {
    protected static boolean isBrightEnoughToSpawn(BlockAndTintGetter level, BlockPos pos) {
        return level.getRawBrightness(pos, 0) > 8;
    }

    // unused anyway
//    public static boolean canSpawnOnGroundOrMycelium(
//            EntityType<? extends Mob> type,
//            LevelAccessor level,
//            MobSpawnType reason,
//            BlockPos pos,
//            RandomSource random
//    ) {
//        boolean flag = MobSpawnType.ignoresLightRequirements(reason) || isBrightEnoughToSpawn(level, pos);
//        return level.getBlockState(pos.below()).is(BlockTags.ANIMALS_SPAWNABLE_ON) || level.getBlockState(pos.below()).is(Blocks.MYCELIUM) && flag;
//    }
}