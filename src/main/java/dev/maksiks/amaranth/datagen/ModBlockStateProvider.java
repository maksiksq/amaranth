package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.data.PackOutput;
import net.neoforged.neoforge.client.model.generators.BlockStateProvider;
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
    }

    private void blockWithItem(DeferredBlock<?> deferredBlock) {
        simpleBlockWithItem(deferredBlock.get(), cubeAll(deferredBlock.get()));
    }
}
