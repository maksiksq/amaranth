package dev.maksiks.amaranth.block;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Amaranth.MOD_ID);

    private static boolean never(BlockState state, BlockGetter blockGetter, BlockPos pos) {
        return false;
    }

    private static Block leaves(SoundType soundType) {
        return new LeavesBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(MapColor.PLANT)
                        .strength(0.2F)
                        .randomTicks()
                        .sound(soundType)
                        .noOcclusion()
                        .isValidSpawn(Blocks::ocelotOrParrot)
                        .isSuffocating(ModBlocks::never)
                        .isViewBlocking(ModBlocks::never)
                        .ignitedByLava()
                        .pushReaction(PushReaction.DESTROY)
                        .isRedstoneConductor(ModBlocks::never)
        );
    }

    private static Block log(MapColor topMapColor, MapColor sideMapColor) {
        return new RotatedPillarBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(p_258972_ -> p_258972_.getValue(RotatedPillarBlock.AXIS) == Direction.Axis.Y ? topMapColor : sideMapColor)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(2.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava()
        );
    }

    private static Block wood(MapColor mapColor) {
        return new RotatedPillarBlock(
                BlockBehaviour.Properties.of()
                        .mapColor(mapColor)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(2.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava()
        );
    }

    private static Block planks(MapColor mapColor) {
        return new Block(
                BlockBehaviour.Properties.of()
                        .mapColor(mapColor)
                        .instrument(NoteBlockInstrument.BASS)
                        .strength(2.0F, 3.0F)
                        .sound(SoundType.WOOD)
                        .ignitedByLava()
        );
    }

    // the way vanilla does this is a bit ??? question raising
    private static Block stairs(Block baseBlock) {
        return new StairBlock(baseBlock.defaultBlockState(), BlockBehaviour.Properties.ofLegacyCopy(baseBlock));
    }

    private static Block woodSlab(MapColor mapColor) {
        return new SlabBlock(
                        BlockBehaviour.Properties.of()
                                .mapColor(MapColor.WOOD)
                                .instrument(NoteBlockInstrument.BASS)
                                .strength(2.0F, 3.0F)
                                .sound(SoundType.WOOD)
                                .ignitedByLava()
                );
    }



    public static final DeferredBlock<Block> MYSTIC_LEAVES_BLOCK = registerBlock("mystic_leaves_block", () -> leaves(SoundType.AZALEA_LEAVES));
    public static final DeferredBlock<Block> MYSTIC_LOG_BLOCK = registerBlock("mystic_log_block", () -> log(MapColor.TERRACOTTA_MAGENTA, MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> MYSTIC_WOOD_BLOCK = registerBlock("mystic_wood_block", () -> wood(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_LOG_BLOCK = registerBlock("stripped_mystic_log_block", () -> log(MapColor.TERRACOTTA_MAGENTA, MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_WOOD_BLOCK = registerBlock("stripped_mystic_wood_block", () -> wood(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> MYSTIC_PLANKS_BLOCK = registerBlock("mystic_planks_block", () -> planks(MapColor.COLOR_PURPLE));
    public static final DeferredBlock<Block> MYSTIC_STAIRS_BLOCK = registerBlock("mystic_stairs_block", () -> stairs(MYSTIC_PLANKS_BLOCK.get()));
    public static final DeferredBlock<Block> MYSTIC_SLAB_BLOCK = registerBlock("mystic_slab_block", () -> woodSlab(MapColor.COLOR_PURPLE));
    // fence
    // fence gate
    // door
    // trapdoor
    // pressure plate
    // button
    // sapling
    // sign
    // hanged sign
    // boat
    // chest boat


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
