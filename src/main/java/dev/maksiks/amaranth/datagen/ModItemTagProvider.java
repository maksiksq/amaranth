package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.tags.BlockTags;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import org.jetbrains.annotations.Nullable;

import java.util.concurrent.CompletableFuture;

public class ModItemTagProvider extends ItemTagsProvider {
    public ModItemTagProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider, CompletableFuture<TagLookup<Block>> blockTags, @Nullable ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, Amaranth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        this.tag(ItemTags.LOGS_THAT_BURN)
                .add(ModBlocks.MYSTIC_LOG.get().asItem())
                .add(ModBlocks.MYSTIC_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_MYSTIC_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_MYSTIC_WOOD.get().asItem())
                .add(ModBlocks.ANTHOCYANIN_LOG.get().asItem())
                .add(ModBlocks.ANTHOCYANIN_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get().asItem())
                .add(ModBlocks.WISTERIA_LOG.get().asItem())
                .add(ModBlocks.WISTERIA_WOOD.get().asItem())
                .add(ModBlocks.STRIPPED_WISTERIA_LOG.get().asItem())
                .add(ModBlocks.STRIPPED_WISTERIA_WOOD.get().asItem());

        this.tag(ItemTags.PLANKS)
                .add(ModBlocks.MYSTIC_PLANKS.get().asItem())
                .add(ModBlocks.ANTHOCYANIN_PLANKS.get().asItem())
                .add(ModBlocks.WISTERIA_PLANKS.get().asItem());

        // uh
        this.tag(ItemTags.GOAT_FOOD)
                .add(ModItems.MUSHROOM_TEA.get());

        this.tag(ItemTags.ARMOR_ENCHANTABLE)
                .add(ModItems.CROWN_OF_THORNS.get());
        this.tag(ItemTags.HEAD_ARMOR)
                .add(ModItems.CROWN_OF_THORNS.get());
        this.tag(ItemTags.HEAD_ARMOR_ENCHANTABLE)
                .add(ModItems.CROWN_OF_THORNS.get());

    }
}
