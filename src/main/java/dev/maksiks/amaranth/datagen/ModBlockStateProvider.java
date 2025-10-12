package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
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
        leavesBlock(ModBlocks.MYSTIC_LEAVES);

        // TODO: Add a helper for this with some string manipulation
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

        // silver
        leavesBlock(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.DARK_SILVER_BIRCH_LEAVES);

        // leaf litter is made manually
        twoPlanesCutoutBlock(ModBlocks.SILVER_BIRCH_SAPLING);

        // desolate
        iceBlock(ModBlocks.SORROW_ICE);
        blockItem(ModBlocks.SORROW_ICE);
        blockWithItem(ModBlocks.REMNANT_SORROW_ICE);

        // mixed
        leavesBlock(ModBlocks.PURPLE_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.RED_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.YELLOW_MIXED_OAK_LEAVES);

        twoPlanesCutoutBlock(ModBlocks.PURPLE_MIXED_OAK_SAPLING);
        twoPlanesCutoutBlock(ModBlocks.RED_MIXED_OAK_SAPLING);
        twoPlanesCutoutBlock(ModBlocks.YELLOW_MIXED_OAK_SAPLING);

        // orderly
        twoPlanesCutoutBlock(ModBlocks.TRIMMED_TREE_SAPLING);

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
        leavesBlock(ModBlocks.ANTHOCYANIN_LEAVES);
        leavesBlock(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES);

        // TODO: Add a helper for this
        stairsBlock(ModBlocks.ANTHOCYANIN_STAIRS.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        slabBlock(ModBlocks.ANTHOCYANIN_SLAB.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));

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
        // pot is made manually

        // pain
        randomVariantTwoPlaneCutout(ModBlocks.SPIKY_ARCHES, 4);

        // thrumletons
        // TODO: more blockstates
        thickPumpkinBlock(ModBlocks.THICK_PUMPKIN);
        thickPumpkinBlockItem(ModBlocks.THICK_PUMPKIN);
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
            int variant = state.getValue(dev.maksiks.amaranth.block.custom.ModSpikyArchesBlock.VARIANT);
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
