package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.block.custom.SpikyArchesBlock;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.FlowerPotBlock;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ConfiguredModel;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Amaranth.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
        // misc
        blockWithItem(ModBlocks.MARBLE);

        // mystic
        logBlock(((RotatedPillarBlock) ModBlocks.MYSTIC_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.MYSTIC_WOOD.get()), blockTexture(ModBlocks.MYSTIC_LOG.get()), blockTexture(ModBlocks.MYSTIC_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MYSTIC_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_MYSTIC_WOOD.get()), blockTexture(ModBlocks.STRIPPED_MYSTIC_LOG.get()), blockTexture(ModBlocks.STRIPPED_MYSTIC_LOG.get()));

        blockItem(ModBlocks.MYSTIC_LOG);
        blockItem(ModBlocks.MYSTIC_WOOD);
        blockItem(ModBlocks.STRIPPED_MYSTIC_LOG);
        blockItem(ModBlocks.STRIPPED_MYSTIC_WOOD);

        blockWithItem(ModBlocks.MYSTIC_PLANKS);
        twoPlanesCutoutBlock(ModBlocks.MYSTIC_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_MYSTIC_SAPLING, ModBlocks.MYSTIC_SAPLING);
        leavesBlock(ModBlocks.MYSTIC_LEAVES);

        stairsBlock(ModBlocks.MYSTIC_STAIRS.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));
        slabBlock(ModBlocks.MYSTIC_SLAB.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));

        buttonBlock(ModBlocks.MYSTIC_BUTTON.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));
        pressurePlateBlock(ModBlocks.MYSTIC_PRESSURE_PLATE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));

        fenceBlock(ModBlocks.MYSTIC_FENCE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));
        fenceGateBlock(ModBlocks.MYSTIC_FENCE_GATE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.MYSTIC_DOOR.get(), modLoc("block/mystic_door_bottom"), modLoc("block/mystic_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.MYSTIC_TRAPDOOR.get(), modLoc("block/mystic_trapdoor"), true, "cutout");

        blockItem(ModBlocks.MYSTIC_STAIRS);
        blockItem(ModBlocks.MYSTIC_SLAB);
        blockItem(ModBlocks.MYSTIC_PRESSURE_PLATE);
        blockItem(ModBlocks.MYSTIC_FENCE_GATE);
        blockItem(ModBlocks.MYSTIC_TRAPDOOR, "_bottom");

        // stubby
        twoPlanesCutoutBlock(ModBlocks.STUBBY_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_STUBBY_SAPLING, ModBlocks.STUBBY_SAPLING);

        // silver
        leavesBlock(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.DARK_SILVER_BIRCH_LEAVES);

        // leaf litter is made manually
        twoPlanesCutoutBlock(ModBlocks.SILVER_BIRCH_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_SILVER_BIRCH_SAPLING, ModBlocks.SILVER_BIRCH_SAPLING);

        // desolate
        iceBlock(ModBlocks.SORROW_ICE);
        blockItem(ModBlocks.SORROW_ICE);
        blockWithItem(ModBlocks.REMNANT_SORROW_ICE);

        // mixed
        leavesBlock(ModBlocks.PURPLE_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.RED_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.YELLOW_MIXED_OAK_LEAVES);

        twoPlanesCutoutBlock(ModBlocks.PURPLE_MIXED_OAK_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_PURPLE_MIXED_OAK_SAPLING, ModBlocks.PURPLE_MIXED_OAK_SAPLING);
        twoPlanesCutoutBlock(ModBlocks.RED_MIXED_OAK_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_RED_MIXED_OAK_SAPLING, ModBlocks.RED_MIXED_OAK_SAPLING);
        twoPlanesCutoutBlock(ModBlocks.YELLOW_MIXED_OAK_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_YELLOW_MIXED_OAK_SAPLING, ModBlocks.YELLOW_MIXED_OAK_SAPLING);

        // orderly
        twoPlanesCutoutBlock(ModBlocks.TRIMMED_TREE_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_TRIMMED_TREE_SAPLING, ModBlocks.TRIMMED_TREE_SAPLING);

        // anthocyanin
        logBlock(((RotatedPillarBlock) ModBlocks.ANTHOCYANIN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.ANTHOCYANIN_WOOD.get()), blockTexture(ModBlocks.ANTHOCYANIN_LOG.get()), blockTexture(ModBlocks.ANTHOCYANIN_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get()), blockTexture(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()), blockTexture(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()));

        blockItem(ModBlocks.ANTHOCYANIN_LOG);
        blockItem(ModBlocks.ANTHOCYANIN_WOOD);
        blockItem(ModBlocks.STRIPPED_ANTHOCYANIN_LOG);
        blockItem(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD);

        blockWithItem(ModBlocks.ANTHOCYANIN_PLANKS);
        twoPlanesCutoutBlock(ModBlocks.ANTHOCYANIN_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_ANTHOCYANIN_SAPLING, ModBlocks.ANTHOCYANIN_SAPLING);
        leavesBlock(ModBlocks.ANTHOCYANIN_LEAVES);
        leavesBlock(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES);

        stairsBlock(ModBlocks.ANTHOCYANIN_STAIRS.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        slabBlock(ModBlocks.ANTHOCYANIN_SLAB.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));

        buttonBlock(ModBlocks.ANTHOCYANIN_BUTTON.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        pressurePlateBlock(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));

        fenceBlock(ModBlocks.ANTHOCYANIN_FENCE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        fenceGateBlock(ModBlocks.ANTHOCYANIN_FENCE_GATE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.ANTHOCYANIN_DOOR.get(), modLoc("block/anthocyanin_door_bottom"), modLoc("block/anthocyanin_door_top"), "cutout");
        doorBlockWithRenderType(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get(), modLoc("block/ornamented_anthocyanin_door_bottom"), modLoc("block/ornamented_anthocyanin_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ANTHOCYANIN_TRAPDOOR.get(), modLoc("block/anthocyanin_trapdoor"), true, "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get(), modLoc("block/ornamented_anthocyanin_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ANTHOCYANIN_STAIRS);
        blockItem(ModBlocks.ANTHOCYANIN_SLAB);
        blockItem(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE);
        blockItem(ModBlocks.ANTHOCYANIN_FENCE_GATE);
        blockItem(ModBlocks.ANTHOCYANIN_TRAPDOOR, "_bottom");
        blockItem(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR, "_bottom");

        twoPlanesCutoutBlock(ModBlocks.MALACHITE_VIPERS_BUGLOSS);
        pottedPlantBlock(ModBlocks.POTTED_MALACHITE_VIPERS_BUGLOSS, ModBlocks.MALACHITE_VIPERS_BUGLOSS);

        // pain
        randomVariantTwoPlaneCutout(ModBlocks.SPIKY_ARCHES, 4);

        // thrumletons
        // TODO: more blockstates
        thickPumpkinBlock(ModBlocks.THICK_PUMPKIN);
        thickPumpkinBlockItem(ModBlocks.THICK_PUMPKIN);

        // speary
        twoPlanesCutoutBlock(ModBlocks.SPEARY_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_SPEARY_SAPLING, ModBlocks.SPEARY_SAPLING);

        // pastel
        axisBlock((RotatedPillarBlock) ModBlocks.JUICY_WISTERIA_LOG.get(), modLoc("block/wisteria_log"), modLoc("block/juicy_wisteria_log_top"));
        logBlock(((RotatedPillarBlock) ModBlocks.WISTERIA_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.WISTERIA_WOOD.get()), blockTexture(ModBlocks.WISTERIA_LOG.get()), blockTexture(ModBlocks.WISTERIA_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_WISTERIA_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_WISTERIA_WOOD.get()), blockTexture(ModBlocks.STRIPPED_WISTERIA_LOG.get()), blockTexture(ModBlocks.STRIPPED_WISTERIA_LOG.get()));

        blockItem(ModBlocks.JUICY_WISTERIA_LOG);
        blockItem(ModBlocks.WISTERIA_LOG);
        blockItem(ModBlocks.WISTERIA_WOOD);
        blockItem(ModBlocks.STRIPPED_WISTERIA_LOG);
        blockItem(ModBlocks.STRIPPED_WISTERIA_WOOD);

        blockWithItem(ModBlocks.WISTERIA_PLANKS);
        twoPlanesCutoutBlock(ModBlocks.WISTERIA_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_WISTERIA_SAPLING, ModBlocks.WISTERIA_SAPLING);
        leavesBlock(ModBlocks.WISTERIA_LEAVES);

        stairsBlock(ModBlocks.WISTERIA_STAIRS.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));
        slabBlock(ModBlocks.WISTERIA_SLAB.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));

        buttonBlock(ModBlocks.WISTERIA_BUTTON.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));
        pressurePlateBlock(ModBlocks.WISTERIA_PRESSURE_PLATE.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));

        fenceBlock(ModBlocks.WISTERIA_FENCE.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));
        fenceGateBlock(ModBlocks.WISTERIA_FENCE_GATE.get(), blockTexture(ModBlocks.WISTERIA_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.WISTERIA_DOOR.get(), modLoc("block/wisteria_door_bottom"), modLoc("block/wisteria_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.WISTERIA_TRAPDOOR.get(), modLoc("block/wisteria_trapdoor"), true, "cutout");

        blockItem(ModBlocks.WISTERIA_STAIRS);
        blockItem(ModBlocks.WISTERIA_SLAB);
        blockItem(ModBlocks.WISTERIA_PRESSURE_PLATE);
        blockItem(ModBlocks.WISTERIA_FENCE_GATE);
        blockItem(ModBlocks.WISTERIA_TRAPDOOR, "_bottom");

        // mush
        doubleFourPlaneCropBlock(ModBlocks.REEDS);

        twoPlanesCutoutBlock(ModBlocks.RED_MINI_SHROOM_SPORELING);
        pottedPlantBlock(ModBlocks.POTTED_RED_MINI_SHROOM_SPORELING, ModBlocks.RED_MINI_SHROOM_SPORELING);
        twoPlanesCutoutBlock(ModBlocks.BROWN_MINI_SHROOM_SPORELING);
        pottedPlantBlock(ModBlocks.POTTED_BROWN_MINI_SHROOM_SPORELING, ModBlocks.BROWN_MINI_SHROOM_SPORELING);

        // witchy
        twoPlanesCutoutBlock(ModBlocks.WITCHY_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_WITCHY_SAPLING, ModBlocks.WITCHY_SAPLING);

        // lupine
        twoPlanesCutoutMippedBlock(ModBlocks.LUPINE);
        pottedPlantBlock(ModBlocks.POTTED_LUPINE, ModBlocks.LUPINE);

        // alpine
        twoPlanesCutoutBlock(ModBlocks.ALPINE_SPRUCE_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_ALPINE_SPRUCE_SAPLING, ModBlocks.ALPINE_SPRUCE_SAPLING);

        // ashen
        blockWithItem(ModBlocks.VOLCANIC_ASH);

        // satis
        logBlock(((RotatedPillarBlock) ModBlocks.SATISTREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.SATISTREE_WOOD.get()), blockTexture(ModBlocks.SATISTREE_LOG.get()), blockTexture(ModBlocks.SATISTREE_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SATISTREE_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_SATISTREE_WOOD.get()), blockTexture(ModBlocks.STRIPPED_SATISTREE_LOG.get()), blockTexture(ModBlocks.STRIPPED_SATISTREE_LOG.get()));

        blockItem(ModBlocks.SATISTREE_LOG);
        blockItem(ModBlocks.SATISTREE_WOOD);
        blockItem(ModBlocks.STRIPPED_SATISTREE_LOG);
        blockItem(ModBlocks.STRIPPED_SATISTREE_WOOD);

        blockWithItem(ModBlocks.SATISTREE_PLANKS);
        twoPlanesCutoutBlock(ModBlocks.SATISTREE_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_SATISTREE_SAPLING, ModBlocks.SATISTREE_SAPLING);
        twoPlanesCutoutBlock(ModBlocks.GIGANTIC_SATISTREE_SPROUTS);
        leavesBlock(ModBlocks.ALIEN_LEAVES);

        stairsBlock(ModBlocks.SATISTREE_STAIRS.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));
        slabBlock(ModBlocks.SATISTREE_SLAB.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));

        buttonBlock(ModBlocks.SATISTREE_BUTTON.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));
        pressurePlateBlock(ModBlocks.SATISTREE_PRESSURE_PLATE.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));

        fenceBlock(ModBlocks.SATISTREE_FENCE.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));
        fenceGateBlock(ModBlocks.SATISTREE_FENCE_GATE.get(), blockTexture(ModBlocks.SATISTREE_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.SATISTREE_DOOR.get(), modLoc("block/satistree_door_bottom"), modLoc("block/satistree_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.SATISTREE_TRAPDOOR.get(), modLoc("block/satistree_trapdoor"), true, "cutout");

        blockItem(ModBlocks.SATISTREE_STAIRS);
        blockItem(ModBlocks.SATISTREE_SLAB);
        blockItem(ModBlocks.SATISTREE_PRESSURE_PLATE);
        blockItem(ModBlocks.SATISTREE_FENCE_GATE);
        blockItem(ModBlocks.SATISTREE_TRAPDOOR, "_bottom");

        twoPlanesCutoutBlock(ModBlocks.ALIEN_PHYLLOSTACHYS_SAPLING);
        // aline bamboo is made manually

        blockWithItem(ModBlocks.ALIEN_FENCE_PLANKS);
        fenceBlock(ModBlocks.ALIEN_FENCE_PLANT.get(), blockTexture(ModBlocks.ALIEN_FENCE_PLANKS.get()));
        twoPlanesCutoutBlock(ModBlocks.ALIEN_FENCE_PLANT_SAPLING);
        pottedPlantBlock(ModBlocks.POTTED_ALIEN_FENCE_PLANT_SAPLING, ModBlocks.ALIEN_FENCE_PLANT_SAPLING);
    }

    private void pottedPlantBlock(DeferredBlock<FlowerPotBlock> pottedBlock, DeferredBlock<Block> plantBlock) {
        String pottedName = BuiltInRegistries.BLOCK.getKey(pottedBlock.get()).getPath();
        ResourceLocation plantTexture = blockTexture(plantBlock.get());

        ModelFile pottedModel = models()
                .withExistingParent(pottedName, mcLoc("block/flower_pot_cross"))
                .texture("plant", plantTexture)
                .renderType("cutout");

        simpleBlock(pottedBlock.get(), pottedModel);
    }

    private void thickPumpkinBlock(DeferredBlock<Block> blockRegistryObject) {
        Block block = blockRegistryObject.get();
        String baseName = BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath();

        ResourceLocation side = modLoc("block/" + baseName + "_side");
        ResourceLocation end = modLoc("block/" + baseName + "_end");
        ResourceLocation inner = modLoc("block/thick_pumpkin_inner");

        var builder = getMultipartBuilder(block);

        for (Direction dir : Direction.values()) {
            ResourceLocation faceTex = (dir == Direction.UP || dir == Direction.DOWN) ? end : side;

            String dirName = dir.getName();

            var outerModel = models().withExistingParent(baseName + "_" + dirName, mcLoc("block/block"))
                    .element()
                    .from(0, 0, 0).to(16, 16, 16)
                    .face(dir).texture("#texture").end()
                    .end()
                    .texture("texture", faceTex)
                    .texture("particle", faceTex);

            var innerModel = models().withExistingParent(baseName + "_" + dirName + "_inner", mcLoc("block/block"))
                    .element()
                    .from(0, 0, 0).to(16, 16, 16)
                    .face(dir).texture("#texture").end()
                    .end()
                    .texture("texture", inner)
                    .texture("particle", inner);

            BooleanProperty prop = switch (dir) {
                case NORTH -> BlockStateProperties.NORTH;
                case SOUTH -> BlockStateProperties.SOUTH;
                case EAST  -> BlockStateProperties.EAST;
                case WEST  -> BlockStateProperties.WEST;
                case UP    -> BlockStateProperties.UP;
                case DOWN  -> BlockStateProperties.DOWN;
            };

            builder
                    .part().modelFile(outerModel)
                    .addModel()
                    .condition(prop, Boolean.TRUE)
                    .end()
                    .part().modelFile(innerModel)
                    .addModel()
                    .condition(prop, Boolean.FALSE)
                    .end();
        }
    }

    private void thickPumpkinBlockItem(DeferredBlock<Block> block) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();
        ResourceLocation side = modLoc("block/thick_pumpkin_side");
        ResourceLocation end = modLoc("block/thick_pumpkin_end");
        ResourceLocation inner = modLoc("block/thick_pumpkin_inner");

        models().withExistingParent(name, mcLoc("block/cube"))
                .texture("north", inner)
                .texture("south", side)
                .texture("east", side)
                .texture("west", inner)
                .texture("up", end)
                .texture("down", end);

        simpleBlockItem(block.get(), models().getExistingFile(modLoc(name)));
    }

    private void randomVariantTwoPlaneCutout(DeferredBlock<Block> blockRegistryObject, int variantCount) {
        String baseName = BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath();

        getVariantBuilder(blockRegistryObject.get()).forAllStates(state -> {
            int variant = state.getValue(SpikyArchesBlock.VARIANT);
            return ConfiguredModel.builder()
                    .modelFile(models()
                            .cross(baseName + "_" + variant, modLoc("block/" + baseName + "_" + variant))
                            .renderType("cutout"))
                    .build();
        });

        for (int i = 0; i < variantCount; i++) {
            models().cross(baseName + "_" + i, modLoc("block/" + baseName + "_" + i)).renderType("cutout");
        }
    }

    private void twoPlanesCutoutBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout"));
    }

    private void twoPlanesCutoutMippedBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlock(blockRegistryObject.get(),
                models().cross(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("cutout_mipped"));
    }

    private void doubleFourPlaneCropBlock(DeferredBlock<Block> block) {
        Block b = block.get();
        String name = BuiltInRegistries.BLOCK.getKey(b).getPath();

        models().withExistingParent(name + "_bottom", mcLoc("block/crop"))
                .texture("crop", modLoc("block/" + name + "_bottom"))
                .renderType("cutout_mipped");

        models().withExistingParent(name + "_top", mcLoc("block/crop"))
                .texture("crop", modLoc("block/" + name + "_top"))
                .renderType("cutout_mipped");

        getVariantBuilder(b).forAllStates(state -> {
            DoubleBlockHalf half = state.getValue(BlockStateProperties.DOUBLE_BLOCK_HALF);
            String suffix = (half == DoubleBlockHalf.UPPER) ? "_top" : "_bottom";
            return ConfiguredModel.builder().modelFile(models().getExistingFile(modLoc(name + suffix))).build();
        });
    }

    private void leavesBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().singleTexture(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), ResourceLocation.parse("minecraft:block/leaves"),
                        "all", blockTexture(blockRegistryObject.get())).renderType("cutout_mipped"));
    }

    private void iceBlock(DeferredBlock<Block> blockRegistryObject) {
        simpleBlockWithItem(blockRegistryObject.get(),
                models().cubeAll(BuiltInRegistries.BLOCK.getKey(blockRegistryObject.get()).getPath(), blockTexture(blockRegistryObject.get())).renderType("translucent"));
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("amaranth:block/" + deferredBlock.getId().getPath()));
    }

    private void blockItem(DeferredBlock<?> deferredBlock, String appendix) {
        simpleBlockItem(deferredBlock.get(), new ModelFile.UncheckedModelFile("amaranth:block/" + deferredBlock.getId().getPath() + appendix));
    }
}
