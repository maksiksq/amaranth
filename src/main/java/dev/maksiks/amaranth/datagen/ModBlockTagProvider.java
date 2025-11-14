package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.block.Blocks;
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

        // mystic
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.MYSTIC_LOG.get())
                .add(ModBlocks.MYSTIC_WOOD.get())
                .add(ModBlocks.STRIPPED_MYSTIC_LOG.get())
                .add(ModBlocks.STRIPPED_MYSTIC_WOOD.get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.MYSTIC_LOG.get())
                .add(ModBlocks.MYSTIC_WOOD.get())
                .add(ModBlocks.STRIPPED_MYSTIC_LOG.get())
                .add(ModBlocks.STRIPPED_MYSTIC_WOOD.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.MYSTIC_PLANKS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.MYSTIC_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.MYSTIC_STAIRS.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.MYSTIC_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.MYSTIC_PRESSURE_PLATE.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.MYSTIC_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.MYSTIC_SAPLING.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.MYSTIC_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.MYSTIC_FENCE_GATE.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.MYSTIC_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.MYSTIC_TRAPDOOR.get());

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

        // anthocyanin
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.ANTHOCYANIN_LOG.get())
                .add(ModBlocks.ANTHOCYANIN_WOOD.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.ANTHOCYANIN_LOG.get())
                .add(ModBlocks.ANTHOCYANIN_WOOD.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get())
                .add(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.ANTHOCYANIN_PLANKS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.ANTHOCYANIN_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.ANTHOCYANIN_STAIRS.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.ANTHOCYANIN_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.ANTHOCYANIN_LEAVES.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.ANTHOCYANIN_SAPLING.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.ANTHOCYANIN_DOOR.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.ANTHOCYANIN_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.ANTHOCYANIN_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.ANTHOCYANIN_FENCE_GATE.get());

        this.tag(BlockTags.SMALL_FLOWERS).add(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get());
        this.tag(BlockTags.FLOWER_POTS).add(ModBlocks.POTTED_MALACHITE_VIPERS_BUGLOSS.get());

        // thrumletons
        this.tag(BlockTags.MINEABLE_WITH_AXE).add(ModBlocks.THICK_PUMPKIN.get());

        // pastel
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.WISTERIA_LOG.get())
                .add(ModBlocks.WISTERIA_WOOD.get())
                .add(ModBlocks.STRIPPED_WISTERIA_LOG.get())
                .add(ModBlocks.STRIPPED_WISTERIA_WOOD.get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.JUICY_WISTERIA_LOG.get())
                .add(ModBlocks.WISTERIA_LOG.get())
                .add(ModBlocks.WISTERIA_WOOD.get())
                .add(ModBlocks.STRIPPED_WISTERIA_LOG.get())
                .add(ModBlocks.STRIPPED_WISTERIA_WOOD.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.WISTERIA_PLANKS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.WISTERIA_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.WISTERIA_STAIRS.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.WISTERIA_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.WISTERIA_PRESSURE_PLATE.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.WISTERIA_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.WISTERIA_SAPLING.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.WISTERIA_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.WISTERIA_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.WISTERIA_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.WISTERIA_FENCE_GATE.get());

        // mush
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.RED_MINI_SHROOM_SPORELING.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.BROWN_MINI_SHROOM_SPORELING.get());

        // witchy
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.WITCHY_SAPLING.get());

        // lupine
        this.tag(BlockTags.SMALL_FLOWERS).add(ModBlocks.LUPINE.get());

        // alpine
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.ALPINE_SPRUCE_SAPLING.get());

        // ashen
        this.tag(BlockTags.MINEABLE_WITH_SHOVEL)
                .add(ModBlocks.VOLCANIC_ASH.get());

        // satis
        this.tag(BlockTags.LOGS_THAT_BURN)
                .add(ModBlocks.SATISTREE_LOG.get())
                .add(ModBlocks.SATISTREE_WOOD.get())
                .add(ModBlocks.STRIPPED_SATISTREE_LOG.get())
                .add(ModBlocks.STRIPPED_SATISTREE_WOOD.get());
        this.tag(BlockTags.OVERWORLD_NATURAL_LOGS)
                .add(ModBlocks.SATISTREE_LOG.get())
                .add(ModBlocks.SATISTREE_WOOD.get())
                .add(ModBlocks.STRIPPED_SATISTREE_LOG.get())
                .add(ModBlocks.STRIPPED_SATISTREE_WOOD.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.SATISTREE_PLANKS.get());
        this.tag(BlockTags.WOODEN_SLABS).add(ModBlocks.SATISTREE_SLAB.get());
        this.tag(BlockTags.WOODEN_STAIRS).add(ModBlocks.SATISTREE_STAIRS.get());
        this.tag(BlockTags.WOODEN_BUTTONS).add(ModBlocks.SATISTREE_BUTTON.get());
        this.tag(BlockTags.WOODEN_PRESSURE_PLATES).add(ModBlocks.SATISTREE_PRESSURE_PLATE.get());
        this.tag(BlockTags.LEAVES).add(ModBlocks.SATISTREE_LEAVES.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.SATISTREE_SAPLING.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.GIGANTIC_SATISTREE_SPROUTS.get());
        this.tag(BlockTags.WOODEN_DOORS).add(ModBlocks.SATISTREE_DOOR.get());
        this.tag(BlockTags.WOODEN_TRAPDOORS).add(ModBlocks.SATISTREE_TRAPDOOR.get());
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.SATISTREE_FENCE.get());
        this.tag(BlockTags.FENCE_GATES).add(ModBlocks.SATISTREE_FENCE_GATE.get());
        this.tag(ModTags.Blocks.ALIEN_PHYLLOSTACHYS_PLANTABLE_ON)
                .addTag(BlockTags.SAND)
                .addTag(BlockTags.DIRT)
                .add(ModBlocks.ALIEN_PHYLLOSTACHYS.get(), ModBlocks.ALIEN_PHYLLOSTACHYS_SAPLING.get(), Blocks.GRAVEL, Blocks.SUSPICIOUS_GRAVEL);
        this.tag(BlockTags.MINEABLE_WITH_AXE)
                .add(ModBlocks.ALIEN_PHYLLOSTACHYS.get());
        this.tag(BlockTags.PLANKS).add(ModBlocks.ALIEN_FENCE_PLANKS.get());
        // sorta wooden? idk, still counts
        this.tag(BlockTags.WOODEN_FENCES).add(ModBlocks.ALIEN_FENCE_PLANT.get());
        this.tag(BlockTags.SAPLINGS).add(ModBlocks.ALIEN_FENCE_PLANT_SAPLING.get());
    }
}