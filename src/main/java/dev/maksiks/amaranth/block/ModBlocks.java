package dev.maksiks.amaranth.block;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.custom.*;
import dev.maksiks.amaranth.item.ModItems;
import dev.maksiks.amaranth.worldgen.tree.ModTreeGrowers;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Amaranth.MOD_ID);

    public static final DeferredBlock<Block> MYSTIC_LOG = registerBlock("mystic_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> MYSTIC_WOOD = registerBlock("mystic_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_LOG = registerBlock("stripped_mystic_log",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_WOOD = registerBlock("stripped_mystic_wood",
            () -> new ModFlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> MYSTIC_PLANKS = registerBlock("mystic_planks",
            () -> new ModFlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> MYSTIC_LEAVES = registerBlock("mystic_leaves",
            () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> MYSTIC_SAPLING = registerBlock("mystic_sapling",
            () -> new SaplingBlock(ModTreeGrowers.MYSTIC_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

     private static final Supplier<BlockBehaviour.Properties> normalWoodProps = () -> BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
                .instrument(NoteBlockInstrument.BASS)
                .strength(2.0F, 3.0F)
                .sound(SoundType.WOOD)
                .ignitedByLava();

     // non-full block stuff
     public static final DeferredBlock<StairBlock> MYSTIC_STAIRS = registerBlock("mystic_stairs",
             () -> new StairBlock(ModBlocks.MYSTIC_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
     public static final DeferredBlock<SlabBlock> MYSTIC_SLAB = registerBlock("mystic_slab",
             () -> new SlabBlock(normalWoodProps.get()));

     public static final DeferredBlock<PressurePlateBlock> MYSTIC_PRESSURE_PLATE = registerBlock("mystic_pressure_plate",
             () -> new PressurePlateBlock(BlockSetType.SPRUCE, normalWoodProps.get()));
     public static final DeferredBlock<ButtonBlock> MYSTIC_BUTTON = registerBlock("mystic_button",
             () -> new ButtonBlock(BlockSetType.SPRUCE, 30, normalWoodProps.get().noCollission()));

    public static final DeferredBlock<FenceBlock> MYSTIC_FENCE = registerBlock("mystic_fence",
            () -> new FenceBlock(normalWoodProps.get()));
    public static final DeferredBlock<FenceGateBlock> MYSTIC_FENCE_GATE = registerBlock("mystic_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, normalWoodProps.get()));

    public static final DeferredBlock<DoorBlock> MYSTIC_DOOR = registerBlock("mystic_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<TrapDoorBlock> MYSTIC_TRAPDOOR = registerBlock("mystic_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE, normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never)));

    // sign
    // hanged sign
    // boat
    // chest boat

    // stubby
    public static final DeferredBlock<Block> STUBBY_SAPLING = registerBlock("stubby_sapling",
            () -> new SaplingBlock(ModTreeGrowers.STUBBY_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    // silver birch
    public static final DeferredBlock<Block> SILVERY_SILVER_BIRCH_LEAVES = registerBlock("silvery_silver_birch_leaves",
            () -> new ModSilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> LIGHT_SILVER_BIRCH_LEAVES = registerBlock("light_silver_birch_leaves",
            () -> new ModSilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> DARK_SILVER_BIRCH_LEAVES = registerBlock("dark_silver_birch_leaves",
            () -> new ModSilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> SILVER_BIRCH_SAPLING = registerBlock("silver_birch_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SILVER_BIRCH_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    public static final DeferredBlock<Block> GOLDEN_LEAF_LITTER = registerBlock("golden_leaf_litter",
            () -> new ModGoldenLeafLitterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));

    // mixed forest
    public static final DeferredBlock<Block> SORROW_ICE = registerBlock("sorrow_ice",
            () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)));
    public static final DeferredBlock<Block> REMNANT_SORROW_ICE = registerBlock("remnant_sorrow_ice",
            () -> new HalfTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));

    // desolate ice fields
    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_LEAVES = registerBlock("purple_mixed_oak_leaves",
            () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_LEAVES = registerBlock("yellow_mixed_oak_leaves",
            () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> RED_MIXED_OAK_LEAVES = registerBlock("red_mixed_oak_leaves",
            () -> new ModFlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_SAPLING = registerBlock("purple_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.PURPLE_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> RED_MIXED_OAK_SAPLING = registerBlock("red_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.RED_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_SAPLING = registerBlock("yellow_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.YELLOW_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));



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
