package dev.maksiks.amaranth.block.custom;

import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.level.pathfinder.PathType;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

public class SpikyArchesBlock extends Block implements BonemealableBlock {
    public static final IntegerProperty VARIANT = IntegerProperty.create("variant", 0, 3);

    public SpikyArchesBlock(Properties properties) {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(VARIANT, 0));
    }

    ///
    /// So mobs dont walk in.
    /// it's not as good as sweet berry bushes and idk how sweet berry bushes do this
    /// did test it with /tick sprint 1d, and mobs didn't really die just get hurt until despawn
    /// so no accidental mob/lag farms at least
    ///
    @Override
    public PathType getBlockPathType(BlockState state, BlockGetter level, BlockPos pos, @Nullable Mob entity) {
        return PathType.DAMAGE_OTHER;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(VARIANT);
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context) {
        BlockPos pos = context.getClickedPos();
        // maybe mc has a better way to do this i just couldn't find it so oh
        int variant = Math.floorMod(pos.hashCode(), 4);
        return this.defaultBlockState().setValue(VARIANT, variant);
    }

    @Override
    protected void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
        if (entity instanceof LivingEntity && entity.getType() != EntityType.FOX && entity.getType() != EntityType.BEE) {
            entity.makeStuckInBlock(state, new Vec3(0.8F, 0.75, 0.8F));
                    // would do thorns for the funny death message but the sfx is annoying
                    entity.hurt(level.damageSources().sweetBerryBush(), 1.0F);
        }
    }

    @Override
    public boolean isValidBonemealTarget(LevelReader level, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public boolean isBonemealSuccess(Level level, RandomSource random, BlockPos pos, BlockState state) {
        return true;
    }

    @Override
    public void performBonemeal(ServerLevel level, RandomSource random, BlockPos pos, BlockState state) {
        popResource(level, pos, new ItemStack(ModItems.THORN.asItem(), random.nextInt(2) + 1));
    }
}
