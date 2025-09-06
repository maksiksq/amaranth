package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.neoforged.neoforge.common.data.BlockTagsProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagProvider extends BlockTagsProvider {
    public ModBlockTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, Amaranth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // temp
        tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.MYSTIC_LOG.get());

        tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MYSTIC_LOG.get());


        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MYSTIC_LOG.get())
                .add(ModBlocks.MYSTIC_WOOD.get())
                .add(ModBlocks.STRIPPED_MYSTIC_LOG.get())
                .add(ModBlocks.STRIPPED_MYSTIC_WOOD.get());

        tag(BlockTags.FENCES).add(ModBlocks.MYSTIC_FENCE.get());
        tag(BlockTags.FENCE_GATES).add(ModBlocks.MYSTIC_FENCE_GATE.get());
    }
}
