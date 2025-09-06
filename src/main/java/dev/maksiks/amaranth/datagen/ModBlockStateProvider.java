package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.data.PackOutput;
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
        blockWithItem(ModBlocks.MYSTIC_SAPLING_BLOCK);
        blockWithItem(ModBlocks.MYSTIC_LEAVES_BLOCK);
        blockWithItem(ModBlocks.MYSTIC_LOG_BLOCK);
//        blockWithItem(ModBlocks.MYSTIC_WOOD_BLOCK);
        blockWithItem(ModBlocks.STRIPPED_MYSTIC_LOG_BLOCK);
//        blockWithItem(ModBlocks.STRIPPED_MYSTIC_WOOD_BLOCK);
        blockWithItem(ModBlocks.MYSTIC_PLANKS_BLOCK);

        // TODO: Add a helper for this with some string manipulation
        stairsBlock(ModBlocks.MYSTIC_STAIRS.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));
        slabBlock(ModBlocks.MYSTIC_SLAB.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));

        buttonBlock(ModBlocks.MYSTIC_BUTTON.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));
        pressurePlateBlock(ModBlocks.MYSTIC_PRESSURE_PLATE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));

        fenceBlock(ModBlocks.MYSTIC_FENCE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));
        fenceGateBlock(ModBlocks.MYSTIC_FENCE_GATE.get(), blockTexture(ModBlocks.MYSTIC_PLANKS_BLOCK.get()));

        doorBlockWithRenderType(ModBlocks.MYSTIC_DOOR.get(), modLoc("block/mystic_door_bottom"), modLoc("block/mystic_door_top"), "cutout");
        trapdoorBlockWithRenderType(ModBlocks.MYSTIC_TRAPDOOR.get(), modLoc("block/mystic_trapdoor"), true, "cutout");

        blockItem(ModBlocks.MYSTIC_STAIRS);
        blockItem(ModBlocks.MYSTIC_SLAB);
        blockItem(ModBlocks.MYSTIC_PRESSURE_PLATE);
        blockItem(ModBlocks.MYSTIC_FENCE_GATE);
        blockItem(ModBlocks.MYSTIC_TRAPDOOR, "_bottom");
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
