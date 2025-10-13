package dev.maksiks.amaranth.block;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.custom.*;
import dev.maksiks.amaranth.item.ModItems;
import dev.maksiks.amaranth.worldgen.tree.ModTreeGrowers;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffects;
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

    private static final Supplier<BlockBehaviour.Properties> normalWoodProps = () -> BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    // misc
    // mmm yes calcite 2
    public static final DeferredBlock<Block> MARBLE = registerBlock("marble",
            Block::new, BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE));

    // mystic
    public static final DeferredBlock<Block> MYSTIC_LOG = registerBlock("mystic_log",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
    public static final DeferredBlock<Block> MYSTIC_WOOD = registerBlock("mystic_wood",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_LOG = registerBlock("stripped_mystic_log",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_WOOD = registerBlock("stripped_mystic_wood",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));

    public static final DeferredBlock<Block> MYSTIC_PLANKS = registerBlock("mystic_planks",
            ModFlammablePlanksBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));

    public static final DeferredBlock<Block> MYSTIC_LEAVES = registerBlock("mystic_leaves",
            ModFlammableLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));

    public static final DeferredBlock<Block> MYSTIC_SAPLING = registerBlock("mystic_sapling",
            () -> new SaplingBlock(ModTreeGrowers.MYSTIC_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    // non-full block stuff
    public static final DeferredBlock<StairBlock> MYSTIC_STAIRS = registerBlock("mystic_stairs",
            () -> new StairBlock(ModBlocks.MYSTIC_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
    public static final DeferredBlock<SlabBlock> MYSTIC_SLAB = registerBlock("mystic_slab",
            SlabBlock::new, normalWoodProps.get());

    public static final DeferredBlock<PressurePlateBlock> MYSTIC_PRESSURE_PLATE = registerBlock("mystic_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.SPRUCE, props), normalWoodProps.get());
    public static final DeferredBlock<ButtonBlock> MYSTIC_BUTTON = registerBlock("mystic_button",
            props -> new ButtonBlock(BlockSetType.SPRUCE, 30, props.noCollission()), normalWoodProps.get());

    public static final DeferredBlock<FenceBlock> MYSTIC_FENCE = registerBlock("mystic_fence",
            FenceBlock::new, normalWoodProps.get());
    public static final DeferredBlock<FenceGateBlock> MYSTIC_FENCE_GATE = registerBlock("mystic_fence_gate",
            props -> new FenceGateBlock(WoodType.SPRUCE, props), normalWoodProps.get());

    public static final DeferredBlock<DoorBlock> MYSTIC_DOOR = registerBlock("mystic_door",
            props -> new DoorBlock(BlockSetType.SPRUCE, props), BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never));
    public static final DeferredBlock<TrapDoorBlock> MYSTIC_TRAPDOOR = registerBlock("mystic_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.SPRUCE, props), normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never));

    // sign
    // hanged sign
    // boat
    // chest boat

    // stubby
    public static final DeferredBlock<Block> STUBBY_SAPLING = registerBlock("stubby_sapling",
            () -> new SaplingBlock(ModTreeGrowers.STUBBY_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    // silver birch
    public static final DeferredBlock<Block> SILVERY_SILVER_BIRCH_LEAVES = registerBlock("silvery_silver_birch_leaves",
            ModSilverBirchLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));

    public static final DeferredBlock<Block> LIGHT_SILVER_BIRCH_LEAVES = registerBlock("light_silver_birch_leaves",
            ModSilverBirchLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));

    public static final DeferredBlock<Block> DARK_SILVER_BIRCH_LEAVES = registerBlock("dark_silver_birch_leaves",
            ModSilverBirchLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));

    public static final DeferredBlock<Block> SILVER_BIRCH_SAPLING = registerBlock("silver_birch_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SILVER_BIRCH_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    public static final DeferredBlock<Block> GOLDEN_LEAF_LITTER = registerBlock("golden_leaf_litter",
            ModGoldenLeafLitterBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS));

    // desolate ice fields
    public static final DeferredBlock<Block> SORROW_ICE = registerBlock("sorrow_ice",
            IceBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.ICE));
    public static final DeferredBlock<Block> REMNANT_SORROW_ICE = registerBlock("remnant_sorrow_ice",
            HalfTransparentBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE));

    // mixed forest
    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_LEAVES = registerBlock("purple_mixed_oak_leaves",
            ModFlammableLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_LEAVES = registerBlock("yellow_mixed_oak_leaves",
            ModFlammableLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredBlock<Block> RED_MIXED_OAK_LEAVES = registerBlock("red_mixed_oak_leaves",
            ModFlammableLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES));

    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_SAPLING = registerBlock("purple_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.PURPLE_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> RED_MIXED_OAK_SAPLING = registerBlock("red_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.RED_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_SAPLING = registerBlock("yellow_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.YELLOW_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    // orderly courts
    public static final DeferredBlock<Block> TRIMMED_TREE_SAPLING = registerBlock("trimmed_tree_sapling",
            () -> new SaplingBlock(ModTreeGrowers.TRIMMED_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));

    // anthocyanin
    public static final DeferredBlock<Block> ANTHOCYANIN_LOG = registerBlock("anthocyanin_log",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG));
    public static final DeferredBlock<Block> ANTHOCYANIN_WOOD = registerBlock("anthocyanin_wood",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD));
    public static final DeferredBlock<Block> STRIPPED_ANTHOCYANIN_LOG = registerBlock("stripped_anthocyanin_log",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG));
    public static final DeferredBlock<Block> STRIPPED_ANTHOCYANIN_WOOD = registerBlock("stripped_anthocyanin_wood",
            ModFlammableRotatedPillarBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD));

    public static final DeferredBlock<Block> ANTHOCYANIN_PLANKS = registerBlock("anthocyanin_planks",
            ModFlammablePlanksBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS));

    public static final DeferredBlock<Block> ANTHOCYANIN_LEAVES = registerBlock("anthocyanin_leaves",
            ModAnthocyaninLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));
    public static final DeferredBlock<Block> BLOOMING_ANTHOCYANIN_LEAVES = registerBlock("blooming_anthocyanin_leaves",
            ModAnthocyaninLeavesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES));

    public static final DeferredBlock<Block> ANTHOCYANIN_SAPLING = registerBlock("anthocyanin_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ANTHOCYANIN_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    // non-full block stuff
    public static final DeferredBlock<StairBlock> ANTHOCYANIN_STAIRS = registerBlock("anthocyanin_stairs",
            () -> new StairBlock(ModBlocks.ANTHOCYANIN_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
    public static final DeferredBlock<SlabBlock> ANTHOCYANIN_SLAB = registerBlock("anthocyanin_slab",
            SlabBlock::new, normalWoodProps.get());

    public static final DeferredBlock<PressurePlateBlock> ANTHOCYANIN_PRESSURE_PLATE = registerBlock("anthocyanin_pressure_plate",
            props -> new PressurePlateBlock(BlockSetType.SPRUCE, props), normalWoodProps.get());
    public static final DeferredBlock<ButtonBlock> ANTHOCYANIN_BUTTON = registerBlock("anthocyanin_button",
            props -> new ButtonBlock(BlockSetType.SPRUCE, 30, props.noCollission()), normalWoodProps.get());

    public static final DeferredBlock<FenceBlock> ANTHOCYANIN_FENCE = registerBlock("anthocyanin_fence",
            FenceBlock::new, normalWoodProps.get());
    public static final DeferredBlock<FenceGateBlock> ANTHOCYANIN_FENCE_GATE = registerBlock("anthocyanin_fence_gate",
            props -> new FenceGateBlock(WoodType.SPRUCE, props), normalWoodProps.get());

    public static final DeferredBlock<DoorBlock> ANTHOCYANIN_DOOR = registerBlock("anthocyanin_door",
            props -> new DoorBlock(BlockSetType.SPRUCE, props), BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never));
    public static final DeferredBlock<DoorBlock> ORNAMENTED_ANTHOCYANIN_DOOR = registerBlock("ornamented_anthocyanin_door",
            props -> new DoorBlock(BlockSetType.SPRUCE, props), BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never));
    public static final DeferredBlock<TrapDoorBlock> ANTHOCYANIN_TRAPDOOR = registerBlock("anthocyanin_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.SPRUCE, props), normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never));
    public static final DeferredBlock<TrapDoorBlock> ORNAMENTED_ANTHOCYANIN_TRAPDOOR = registerBlock("ornamented_anthocyanin_trapdoor",
            props -> new TrapDoorBlock(BlockSetType.SPRUCE, props), normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never));

    public static final DeferredBlock<Block> MALACHITE_VIPERS_BUGLOSS = registerBlock("malachite_vipers_bugloss",
            () -> new FlowerBlock(MobEffects.POISON, 0.35F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));

    public static final DeferredBlock<FlowerPotBlock> POTTED_MALACHITE_VIPERS_BUGLOSS = registerBlock("potted_malachite_vipers_bugloss",
            () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, MALACHITE_VIPERS_BUGLOSS, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_POPPY)));

    // pain
    public static final DeferredBlock<Block> SPIKY_ARCHES = registerBlock("spiky_arches",
            ModSpikyArchesBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH));

    // thrumletons
    public static final DeferredBlock<Block> THICK_PUMPKIN = registerBlock("thick_pumpkin",
            ModThickPumpkinBlock::new, BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN));

    @FunctionalInterface
    private interface BlockFactory<T extends Block> {
        T create(BlockBehaviour.Properties properties);
    }

    private static <T extends Block> DeferredBlock<T> registerBlock(String name,
                                                                    BlockFactory<T> factory,
                                                                    BlockBehaviour.Properties properties) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, () ->
                factory.create(properties.setId(
                        ResourceKey.create(Registries.BLOCK,
                                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name))
                ))
        );
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    // without seId
    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return toReturn;
    }

    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        ModItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties().setId(ResourceKey.create(Registries.ITEM, ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name)))));
    }

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}