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
        // misc
        this.tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.MARBLE.get());

        this.tag(BlockTags.NEEDS_STONE_TOOL)
                .add(ModBlocks.MARBLE.get());

        // TODO: Tidy and add all the missing ones even if useless
        // logs
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MYSTIC_LOG.get())
                .add(ModBlocks.MYSTIC_WOOD.get())
                .add(ModBlocks.STRIPPED_MYSTIC_LOG.get())
                .add(ModBlocks.STRIPPED_MYSTIC_WOOD.get())
                .add(ModBlocks.ANTHOCYANIN_LOG.get())
                .add(ModBlocks.ANTHOCYANIN_WOOD.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get())
                .add(ModBlocks.WISTERIA_LOG.get())
                .add(ModBlocks.WISTERIA_WOOD.get())
                .add(ModBlocks.STRIPPED_WISTERIA_LOG.get())
                .add(ModBlocks.STRIPPED_WISTERIA_WOOD.get());

        // saplings and leaves
        this.tag(BlockTags.LEAVES).add(ModBlocks.MYSTIC_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.MYSTIC_SAPLING.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.ANTHOCYANIN_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.WISTERIA_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.ANTHOCYANIN_SAPLING.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.WISTERIA_SAPLING.get());

        this.tag(BlockTags.FENCES).add(ModBlocks.MYSTIC_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.MYSTIC_FENCE_GATE.get());

        this.tag(BlockTags.DOORS).add(ModBlocks.MYSTIC_DOOR.get());
        this.tag(BlockTags.DOORS).add(ModBlocks.ANTHOCYANIN_DOOR.get());
        this.tag(BlockTags.DOORS).add(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get());
        this.tag(BlockTags.DOORS).add(ModBlocks.WISTERIA_DOOR.get());

        this.tag(BlockTags.TRAPDOORS).add(ModBlocks.MYSTIC_TRAPDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ModBlocks.ANTHOCYANIN_TRAPDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get());
        this.tag(BlockTags.TRAPDOORS).add(ModBlocks.WISTERIA_TRAPDOOR.get());

        this.tag(BlockTags.FENCES).add(ModBlocks.ANTHOCYANIN_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.ANTHOCYANIN_FENCE_GATE.get());
        this.tag(BlockTags.FENCES).add(ModBlocks.WISTERIA_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.WISTERIA_FENCE_GATE.get());

        // stubby
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.STUBBY_SAPLING.get());

        // silver
        this.tag(BlockTags.LEAVES).add(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.SILVER_BIRCH_SAPLING.get());

        // desolate
        this.tag(BlockTags.ICE).add(ModBlocks.SORROW_ICE.get());
        this.tag(BlockTags.ICE).add(ModBlocks.REMNANT_SORROW_ICE.get());

        // mixed
        this.tag(BlockTags.LEAVES).add(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.RED_MIXED_OAK_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get());

        this.tag(BlockTags.SAPLINGS).add(ModBlocks.PURPLE_MIXED_OAK_SAPLING.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.RED_MIXED_OAK_SAPLING.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.YELLOW_MIXED_OAK_SAPLING.get());

        // orderly
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.TRIMMED_TREE_SAPLING.get());

        this.tag(BlockTags.FLOWERS).add(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get());
        this.tag(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_MALACHITE_VIPERS_BUGLOSS.get());

        // thrumletons
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.THICK_PUMPKIN.get());
    }
}
