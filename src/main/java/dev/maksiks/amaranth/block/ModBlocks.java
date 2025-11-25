package dev.maksiks.amaranth.block;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.custom.*;
import dev.maksiks.amaranth.block.custom.leaves.*;
import dev.maksiks.amaranth.item.ModItems;
import dev.maksiks.amaranth.worldgen.tree.ModTreeGrowers;
import net.minecraft.util.ColorRGBA;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.properties.BlockSetType;
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument;
import net.minecraft.world.level.block.state.properties.WoodType;
import net.minecraft.world.level.material.MapColor;
import net.minecraft.world.level.material.PushReaction;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(Amaranth.MOD_ID);


    public static HashMap<DeferredBlock<Block>, DeferredBlock<FlowerPotBlock>> MOD_FLOWER_POTS = new HashMap<>();

    private static final Supplier<BlockBehaviour.Properties> normalWoodProps = () -> BlockBehaviour.Properties.of()
            .mapColor(MapColor.WOOD)
            .instrument(NoteBlockInstrument.BASS)
            .strength(2.0F, 3.0F)
            .sound(SoundType.WOOD)
            .ignitedByLava();

    private static DeferredBlock<FlowerPotBlock> registerFlowerPot(DeferredBlock<Block> plant) {
        Amaranth.LOGGER.info("PLANT ID: {}", plant.getId().getPath().replace("amaranth:", ""));
        Amaranth.LOGGER.info("PLANT 1ID: {}", "potted_" + plant.getId().getPath());
        DeferredBlock<FlowerPotBlock> pot = registerBlock("potted_" + plant.getId().getPath(),
                () -> new FlowerPotBlock(() -> (FlowerPotBlock) Blocks.FLOWER_POT, plant, BlockBehaviour.Properties.ofFullCopy(Blocks.POTTED_SPRUCE_SAPLING)));
        MOD_FLOWER_POTS.put(plant, pot);
        return pot;
    }

    // misc
    // mmm yes calcite 2
    public static final DeferredBlock<Block> MARBLE = registerBlock("marble",
            () -> new Block(BlockBehaviour.Properties.ofFullCopy(Blocks.CALCITE)));

    // mystic
    public static final DeferredBlock<Block> MYSTIC_LOG = registerBlock("mystic_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> MYSTIC_WOOD = registerBlock("mystic_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_LOG = registerBlock("stripped_mystic_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final DeferredBlock<Block> STRIPPED_MYSTIC_WOOD = registerBlock("stripped_mystic_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> MYSTIC_PLANKS = registerBlock("mystic_planks",
            () -> new FlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> MYSTIC_LEAVES = registerBlock("mystic_leaves",
            () -> new FlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> MYSTIC_SAPLING = registerBlock("mystic_sapling",
            () -> new SaplingBlock(ModTreeGrowers.MYSTIC_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_MYSTIC_SAPLING = registerFlowerPot(MYSTIC_SAPLING);

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
    public static final DeferredBlock<FlowerPotBlock> POTTED_STUBBY_SAPLING = registerFlowerPot(STUBBY_SAPLING);

    // silver birch
    public static final DeferredBlock<Block> SILVERY_SILVER_BIRCH_LEAVES = registerBlock("silvery_silver_birch_leaves",
            () -> new SilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> LIGHT_SILVER_BIRCH_LEAVES = registerBlock("light_silver_birch_leaves",
            () -> new SilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> DARK_SILVER_BIRCH_LEAVES = registerBlock("dark_silver_birch_leaves",
            () -> new SilverBirchLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> SILVER_BIRCH_SAPLING = registerBlock("silver_birch_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SILVER_BIRCH_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_SILVER_BIRCH_SAPLING = registerFlowerPot(SILVER_BIRCH_SAPLING);

    public static final DeferredBlock<Block> GOLDEN_LEAF_LITTER = registerBlock("golden_leaf_litter",
            () -> new GoldenLeafLitterBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));

    // desolate ice fields
    public static final DeferredBlock<Block> SORROW_ICE = registerBlock("sorrow_ice",
            () -> new IceBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ICE)));
    public static final DeferredBlock<Block> REMNANT_SORROW_ICE = registerBlock("remnant_sorrow_ice",
            () -> new HalfTransparentBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BLUE_ICE)));

    // mixed forest
    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_LEAVES = registerBlock("purple_mixed_oak_leaves",
            () -> new FlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_LEAVES = registerBlock("yellow_mixed_oak_leaves",
            () -> new FlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> RED_MIXED_OAK_LEAVES = registerBlock("red_mixed_oak_leaves",
            () -> new FlammableLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_LEAVES)));

    public static final DeferredBlock<Block> PURPLE_MIXED_OAK_SAPLING = registerBlock("purple_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.PURPLE_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_PURPLE_MIXED_OAK_SAPLING = registerFlowerPot(PURPLE_MIXED_OAK_SAPLING);

    public static final DeferredBlock<Block> RED_MIXED_OAK_SAPLING = registerBlock("red_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.RED_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_RED_MIXED_OAK_SAPLING = registerFlowerPot(RED_MIXED_OAK_SAPLING);

    public static final DeferredBlock<Block> YELLOW_MIXED_OAK_SAPLING = registerBlock("yellow_mixed_oak_sapling",
            () -> new SaplingBlock(ModTreeGrowers.YELLOW_MIXED_OAK_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_YELLOW_MIXED_OAK_SAPLING = registerFlowerPot(YELLOW_MIXED_OAK_SAPLING);

    // orderly courts
    public static final DeferredBlock<Block> TRIMMED_TREE_SAPLING = registerBlock("trimmed_tree_sapling",
            () -> new SaplingBlock(ModTreeGrowers.TRIMMED_TREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.OAK_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_TRIMMED_TREE_SAPLING = registerFlowerPot(TRIMMED_TREE_SAPLING);

    // anthocyanin
    public static final DeferredBlock<Block> ANTHOCYANIN_LOG = registerBlock("anthocyanin_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> ANTHOCYANIN_WOOD = registerBlock("anthocyanin_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_ANTHOCYANIN_LOG = registerBlock("stripped_anthocyanin_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final DeferredBlock<Block> STRIPPED_ANTHOCYANIN_WOOD = registerBlock("stripped_anthocyanin_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> ANTHOCYANIN_PLANKS = registerBlock("anthocyanin_planks",
            () -> new FlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> ANTHOCYANIN_LEAVES = registerBlock("anthocyanin_leaves",
            () -> new AnthocyaninLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));
    public static final DeferredBlock<Block> BLOOMING_ANTHOCYANIN_LEAVES = registerBlock("blooming_anthocyanin_leaves",
            () -> new AnthocyaninLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> ANTHOCYANIN_SAPLING = registerBlock("anthocyanin_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ANTHOCYANIN_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_ANTHOCYANIN_SAPLING = registerFlowerPot(ANTHOCYANIN_SAPLING);

    // non-full block stuff
    public static final DeferredBlock<StairBlock> ANTHOCYANIN_STAIRS = registerBlock("anthocyanin_stairs",
            () -> new StairBlock(ModBlocks.ANTHOCYANIN_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
    public static final DeferredBlock<SlabBlock> ANTHOCYANIN_SLAB = registerBlock("anthocyanin_slab",
            () -> new SlabBlock(normalWoodProps.get()));

    public static final DeferredBlock<PressurePlateBlock> ANTHOCYANIN_PRESSURE_PLATE = registerBlock("anthocyanin_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE, normalWoodProps.get()));
    public static final DeferredBlock<ButtonBlock> ANTHOCYANIN_BUTTON = registerBlock("anthocyanin_button",
            () -> new ButtonBlock(BlockSetType.SPRUCE, 30, normalWoodProps.get().noCollission()));

    public static final DeferredBlock<FenceBlock> ANTHOCYANIN_FENCE = registerBlock("anthocyanin_fence",
            () -> new FenceBlock(normalWoodProps.get()));
    public static final DeferredBlock<FenceGateBlock> ANTHOCYANIN_FENCE_GATE = registerBlock("anthocyanin_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, normalWoodProps.get()));

    public static final DeferredBlock<DoorBlock> ANTHOCYANIN_DOOR = registerBlock("anthocyanin_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<DoorBlock> ORNAMENTED_ANTHOCYANIN_DOOR = registerBlock("ornamented_anthocyanin_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<TrapDoorBlock> ANTHOCYANIN_TRAPDOOR = registerBlock("anthocyanin_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE, normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<TrapDoorBlock> ORNAMENTED_ANTHOCYANIN_TRAPDOOR = registerBlock("ornamented_anthocyanin_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE, normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never)));

    public static final DeferredBlock<Block> MALACHITE_VIPERS_BUGLOSS = registerBlock("malachite_vipers_bugloss",
            () -> new FlowerBlock(MobEffects.POISON, 0.35F, BlockBehaviour.Properties.ofFullCopy(Blocks.POPPY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_MALACHITE_VIPERS_BUGLOSS = registerFlowerPot(MALACHITE_VIPERS_BUGLOSS);

    // pain
    public static final DeferredBlock<Block> SPIKY_ARCHES = registerBlock("spiky_arches",
            () -> new SpikyArchesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SWEET_BERRY_BUSH)));

    // thrumletons
    public static final DeferredBlock<Block> THICK_PUMPKIN = registerBlock("thick_pumpkin",
            () -> new ThickPumpkinBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PUMPKIN)));

    // speary
    public static final DeferredBlock<Block> SPEARY_SAPLING = registerBlock("speary_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SPEARY_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_SPEARY_SAPLING = registerFlowerPot(SPEARY_SAPLING);

    // pastel
    public static final DeferredBlock<Block> JUICY_WISTERIA_LOG = registerBlock("juicy_wisteria_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> WISTERIA_LOG = registerBlock("wisteria_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> WISTERIA_WOOD = registerBlock("wisteria_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_WISTERIA_LOG = registerBlock("stripped_wisteria_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final DeferredBlock<Block> STRIPPED_WISTERIA_WOOD = registerBlock("stripped_wisteria_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> WISTERIA_PLANKS = registerBlock("wisteria_planks",
            () -> new FlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> WISTERIA_LEAVES = registerBlock("wisteria_leaves",
            () -> new WisteriaLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> WISTERIA_SAPLING = registerBlock("wisteria_sapling",
            () -> new SaplingBlock(ModTreeGrowers.WISTERIA_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_WISTERIA_SAPLING = registerFlowerPot(WISTERIA_SAPLING);

    // non-full block stuff
    public static final DeferredBlock<StairBlock> WISTERIA_STAIRS = registerBlock("wisteria_stairs",
            () -> new StairBlock(ModBlocks.WISTERIA_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
    public static final DeferredBlock<SlabBlock> WISTERIA_SLAB = registerBlock("wisteria_slab",
            () -> new SlabBlock(normalWoodProps.get()));

    public static final DeferredBlock<PressurePlateBlock> WISTERIA_PRESSURE_PLATE = registerBlock("wisteria_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE, normalWoodProps.get()));
    public static final DeferredBlock<ButtonBlock> WISTERIA_BUTTON = registerBlock("wisteria_button",
            () -> new ButtonBlock(BlockSetType.SPRUCE, 30, normalWoodProps.get().noCollission()));

    public static final DeferredBlock<FenceBlock> WISTERIA_FENCE = registerBlock("wisteria_fence",
            () -> new FenceBlock(normalWoodProps.get()));
    public static final DeferredBlock<FenceGateBlock> WISTERIA_FENCE_GATE = registerBlock("wisteria_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, normalWoodProps.get()));

    public static final DeferredBlock<DoorBlock> WISTERIA_DOOR = registerBlock("wisteria_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<TrapDoorBlock> WISTERIA_TRAPDOOR = registerBlock("wisteria_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE, normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never)));

    public static final DeferredBlock<Block> PHLOX = registerBlock("phlox",
            () -> new PinkPetalsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.PINK_PETALS)));

    // mush
    public static final DeferredBlock<Block> REEDS = registerBlock("reeds",
            () -> new ReedsBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.ROSE_BUSH)));

    public static final DeferredBlock<Block> RED_MINI_SHROOM_SPORELING = registerBlock("red_mini_shroom_sporeling",
            () -> new SaplingBlock(ModTreeGrowers.RED_MINI_SHROOM_GROWER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.MUD)
                            .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_RED_MINI_SHROOM_SPORELING = registerFlowerPot(RED_MINI_SHROOM_SPORELING);

    public static final DeferredBlock<Block> BROWN_MINI_SHROOM_SPORELING = registerBlock("brown_mini_shroom_sporeling",
            () -> new SaplingBlock(ModTreeGrowers.BROWN_MINI_SHROOM_GROWER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.MUD)
                            .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_BROWN_MINI_SHROOM_SPORELING = registerFlowerPot(BROWN_MINI_SHROOM_SPORELING);

    // witchy
    public static final DeferredBlock<Block> WITCHY_SAPLING = registerBlock("witchy_sapling",
            () -> new SaplingBlock(ModTreeGrowers.WITCHY_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_WITCHY_SAPLING = registerFlowerPot(WITCHY_SAPLING);

    // lupine
    public static final DeferredBlock<Block> LUPINE = registerBlock("lupine",
            () -> new FlowerBlockNonShifting(MobEffects.POISON, 0.35F, BlockBehaviour.Properties.of()
                    .mapColor(MapColor.PLANT)
                    .noCollission()
                    .instabreak()
                    .sound(SoundType.GRASS)
                    .offsetType(BlockBehaviour.OffsetType.NONE)
                    .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_LUPINE = registerFlowerPot(LUPINE);

    // alpine
    public static final DeferredBlock<Block> ALPINE_SPRUCE_SAPLING = registerBlock("alpine_spruce_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ALPINE_SPRUCE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_ALPINE_SPRUCE_SAPLING = registerFlowerPot(ALPINE_SPRUCE_SAPLING);

    // ashen
    public static final DeferredBlock<Block> VOLCANIC_ASH = registerBlock("volcanic_ash",
            () -> new ColoredFallingBlock(
                    new ColorRGBA(2170911),
                    BlockBehaviour.Properties.ofFullCopy(Blocks.SAND)));

    // satis
    public static final DeferredBlock<Block> SATISTREE_LOG = registerBlock("satistree_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LOG)));
    public static final DeferredBlock<Block> SATISTREE_WOOD = registerBlock("satistree_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_WOOD)));
    public static final DeferredBlock<Block> STRIPPED_SATISTREE_LOG = registerBlock("stripped_satistree_log",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_LOG)));
    public static final DeferredBlock<Block> STRIPPED_SATISTREE_WOOD = registerBlock("stripped_satistree_wood",
            () -> new FlammableRotatedPillarBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.STRIPPED_SPRUCE_WOOD)));

    public static final DeferredBlock<Block> SATISTREE_PLANKS = registerBlock("satistree_planks",
            () -> new FlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));

    public static final DeferredBlock<Block> ALIEN_LEAVES = registerBlock("alien_leaves",
            () -> new AlienLeavesBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_LEAVES)));

    public static final DeferredBlock<Block> SATISTREE_SAPLING = registerBlock("satistree_sapling",
            () -> new SaplingBlock(ModTreeGrowers.SATISTREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_SATISTREE_SAPLING = registerFlowerPot(SATISTREE_SAPLING);
    public static final DeferredBlock<Block> GIGANTIC_SATISTREE_SPROUTS = registerBlock("gigantic_satistree_sprouts",
            () -> new SaplingBlock(ModTreeGrowers.GIGANTIC_SATISTREE_GROWER, BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_SAPLING)));

    // non-full block stuff
    public static final DeferredBlock<StairBlock> SATISTREE_STAIRS = registerBlock("satistree_stairs",
            () -> new StairBlock(ModBlocks.SATISTREE_PLANKS.get().defaultBlockState(), normalWoodProps.get()));
    public static final DeferredBlock<SlabBlock> SATISTREE_SLAB = registerBlock("satistree_slab",
            () -> new SlabBlock(normalWoodProps.get()));

    public static final DeferredBlock<PressurePlateBlock> SATISTREE_PRESSURE_PLATE = registerBlock("satistree_pressure_plate",
            () -> new PressurePlateBlock(BlockSetType.SPRUCE, normalWoodProps.get()));
    public static final DeferredBlock<ButtonBlock> SATISTREE_BUTTON = registerBlock("satistree_button",
            () -> new ButtonBlock(BlockSetType.SPRUCE, 30, normalWoodProps.get().noCollission()));

    public static final DeferredBlock<FenceBlock> SATISTREE_FENCE = registerBlock("satistree_fence",
            () -> new FenceBlock(normalWoodProps.get()));
    public static final DeferredBlock<FenceGateBlock> SATISTREE_FENCE_GATE = registerBlock("satistree_fence_gate",
            () -> new FenceGateBlock(WoodType.SPRUCE, normalWoodProps.get()));

    public static final DeferredBlock<DoorBlock> SATISTREE_DOOR = registerBlock("satistree_door",
            () -> new DoorBlock(BlockSetType.SPRUCE, BlockBehaviour.Properties.of().strength(2F).noOcclusion().isValidSpawn(Blocks::never)));
    public static final DeferredBlock<TrapDoorBlock> SATISTREE_TRAPDOOR = registerBlock("satistree_trapdoor",
            () -> new TrapDoorBlock(BlockSetType.SPRUCE, normalWoodProps.get().noOcclusion().isValidSpawn(Blocks::never)));

    public static final DeferredBlock<Block> ALIEN_PHYLLOSTACHYS_SAPLING = registerBlock("alien_phyllostachys_sapling",
            () -> new AlienPhyllostachysSaplingBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO_SAPLING)));
    public static final DeferredBlock<Block> ALIEN_PHYLLOSTACHYS = registerBlock("alien_phyllostachys",
            () -> new AlienPhyllostachysStalkBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.BAMBOO)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_ALIEN_PHYLLOSTACHYS = registerFlowerPot(ALIEN_PHYLLOSTACHYS);
    public static final DeferredBlock<Block> ALIEN_FENCE_PLANKS = registerBlock("alien_fence_planks",
            () -> new FlammablePlanksBlock(BlockBehaviour.Properties.ofFullCopy(Blocks.SPRUCE_PLANKS)));
    // chewy on the inside!
    public static final DeferredBlock<FenceBlock> ALIEN_FENCE_PLANT = registerBlock("alien_fence_plant",
            () -> new FenceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.WOOD)
                    .instrument(NoteBlockInstrument.BANJO)
                    .strength(2.0F, 3.0F)
                    .sound(SoundType.WET_GRASS)
                    .ignitedByLava()));

    public static final DeferredBlock<Block> ALIEN_FENCE_PLANT_SAPLING = registerBlock("alien_fence_plant_sapling",
            () -> new SaplingBlock(ModTreeGrowers.ALIEN_FENCE_PLANT_GROWER,
                    BlockBehaviour.Properties.of()
                            .mapColor(MapColor.PLANT)
                            .noCollission()
                            .randomTicks()
                            .instabreak()
                            .sound(SoundType.WET_GRASS)
                            .pushReaction(PushReaction.DESTROY)));
    public static final DeferredBlock<FlowerPotBlock> POTTED_ALIEN_FENCE_PLANT_SAPLING = registerFlowerPot(ALIEN_FENCE_PLANT_SAPLING);

    public static Map<DeferredBlock<Block>, DeferredBlock<Block>> MOD_STRIPPABLES = Map.of(
            MYSTIC_LOG, STRIPPED_MYSTIC_LOG,
            MYSTIC_WOOD, STRIPPED_MYSTIC_WOOD,
            ANTHOCYANIN_LOG, STRIPPED_ANTHOCYANIN_LOG,
            ANTHOCYANIN_WOOD, STRIPPED_ANTHOCYANIN_WOOD,
            WISTERIA_LOG, STRIPPED_WISTERIA_LOG,
            WISTERIA_WOOD, STRIPPED_WISTERIA_WOOD,
            JUICY_WISTERIA_LOG, STRIPPED_WISTERIA_LOG,
            SATISTREE_LOG, STRIPPED_SATISTREE_LOG,
            SATISTREE_WOOD, STRIPPED_SATISTREE_WOOD
    );

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
