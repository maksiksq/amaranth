package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.RotatedPillarBlock;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModBlockStateProvider extends BlockStateProvider {
    public ModBlockStateProvider(PackOutput output, ExistingFileHelper exFileHelper) {
        super(output, Amaranth.MOD_ID, exFileHelper);
    }

    @Override
    protected void registerStatesAndModels() {
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
        saplingBlock(ModBlocks.MYSTIC_SAPLING);
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
        saplingBlock(ModBlocks.STUBBY_SAPLING);

        // silver
        leavesBlock(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES);
        leavesBlock(ModBlocks.DARK_SILVER_BIRCH_LEAVES);

        // leaf litter is made manually
        saplingBlock(ModBlocks.SILVER_BIRCH_SAPLING);

        // desolate
        iceBlock(ModBlocks.SORROW_ICE);
        blockItem(ModBlocks.SORROW_ICE);
        blockWithItem(ModBlocks.REMNANT_SORROW_ICE);

        // mixed
        leavesBlock(ModBlocks.PURPLE_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.RED_MIXED_OAK_LEAVES);
        leavesBlock(ModBlocks.YELLOW_MIXED_OAK_LEAVES);

        saplingBlock(ModBlocks.PURPLE_MIXED_OAK_SAPLING);
        saplingBlock(ModBlocks.RED_MIXED_OAK_SAPLING);
        saplingBlock(ModBlocks.YELLOW_MIXED_OAK_SAPLING);

        // orderly
        saplingBlock(ModBlocks.TRIMMED_TREE_SAPLING);

        // anthocyanin
        logBlock(((RotatedPillarBlock) ModBlocks.ANTHOCYANIN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.ANTHOCYANIN_WOOD.get()), blockTexture(ModBlocks.ANTHOCYANIN_LOG.get()), blockTexture(ModBlocks.MYSTIC_LOG.get()));

        logBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()));
        axisBlock(((RotatedPillarBlock) ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get()), blockTexture(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()), blockTexture(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()));

        blockItem(ModBlocks.ANTHOCYANIN_LOG);
        blockItem(ModBlocks.ANTHOCYANIN_WOOD);
        blockItem(ModBlocks.STRIPPED_ANTHOCYANIN_LOG);
        blockItem(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD);

        blockWithItem(ModBlocks.ANTHOCYANIN_PLANKS);
        saplingBlock(ModBlocks.ANTHOCYANIN_SAPLING);
        leavesBlock(ModBlocks.ANTHOCYANIN_LEAVES);

        // TODO: Add a helper for this with some string manipulation
        stairsBlock(ModBlocks.ANTHOCYANIN_STAIRS.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        slabBlock(ModBlocks.ANTHOCYANIN_SLAB.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()), blockTexture(ModBlocks.MYSTIC_PLANKS.get()));

        buttonBlock(ModBlocks.ANTHOCYANIN_BUTTON.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        pressurePlateBlock(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));

        fenceBlock(ModBlocks.ANTHOCYANIN_FENCE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));
        fenceGateBlock(ModBlocks.ANTHOCYANIN_FENCE_GATE.get(), blockTexture(ModBlocks.ANTHOCYANIN_PLANKS.get()));

        doorBlockWithRenderType(ModBlocks.ANTHOCYANIN_DOOR.get(), modLoc("block/anthocyanin_door_bottom"), modLoc("block/mystic_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.ANTHOCYANIN_TRAPDOOR.get(), modLoc("block/anthocyanin_trapdoor"), true, "cutout");

        blockItem(ModBlocks.ANTHOCYANIN_STAIRS);
        blockItem(ModBlocks.ANTHOCYANIN_SLAB);
        blockItem(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE);
        blockItem(ModBlocks.ANTHOCYANIN_FENCE_GATE);
        blockItem(ModBlocks.ANTHOCYANIN_TRAPDOOR, "_bottom");

    }

    private void saplingBlock(DeferredBlock<Block> blockRegistryObject) {
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
