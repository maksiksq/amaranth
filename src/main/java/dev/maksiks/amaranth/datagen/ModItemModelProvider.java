package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.Direction;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.packs.PackType;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.client.model.generators.ModelFile;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredItem;


public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Amaranth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // misc
        basicItem(ModItems.PALETTE_OVERLOAD_MUSIC_DISC.get());
        basicItem(ModItems.EMPTY_TEA_CUP.get());

        // mystic
        basicItem(ModItems.MAFIA_BLOB.get());
        basicItem(ModItems.BEANIE_BLOB.get());
        basicItem(ModItems.HEXFRUIT.get());

        buttonItem(ModBlocks.MYSTIC_BUTTON, ModBlocks.MYSTIC_PLANKS);
        fenceItem(ModBlocks.MYSTIC_FENCE, ModBlocks.MYSTIC_PLANKS);

        basicItem(ModBlocks.MYSTIC_DOOR.asItem());

        splatBlockItem(ModBlocks.MYSTIC_SAPLING);

        // stubby
        splatBlockItem(ModBlocks.STUBBY_SAPLING);

        // silver
        splatBlockItem(ModBlocks.GOLDEN_LEAF_LITTER);
        splatBlockItem(ModBlocks.SILVER_BIRCH_SAPLING);

        // mixed
        splatBlockItem(ModBlocks.PURPLE_MIXED_OAK_SAPLING);
        splatBlockItem(ModBlocks.RED_MIXED_OAK_SAPLING);
        splatBlockItem(ModBlocks.YELLOW_MIXED_OAK_SAPLING);

        // orderly
        splatBlockItem(ModBlocks.TRIMMED_TREE_SAPLING);

        withExistingParent(ModItems.SHROOM_BOI_SPAWN_EGG.getId().getPath(), mcLoc("item/template_spawn_egg"));

        // shroom
        basicItem(ModItems.MUSHROOM_TEA.get());

        // anthocyanin
        buttonItem(ModBlocks.ANTHOCYANIN_BUTTON, ModBlocks.ANTHOCYANIN_PLANKS);
        fenceItem(ModBlocks.ANTHOCYANIN_FENCE, ModBlocks.ANTHOCYANIN_PLANKS);

        basicItem(ModBlocks.ANTHOCYANIN_DOOR.asItem());
        basicItem(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.asItem());

        splatBlockItem(ModBlocks.ANTHOCYANIN_SAPLING);

        splatBlockItem(ModBlocks.MALACHITE_VIPERS_BUGLOSS);

        // pain
        splatBlockItem(ModBlocks.SPIKY_ARCHES);
        basicItem(ModItems.THORN.asItem());

        basicItem(ModItems.CROWN_OF_THORNS.asItem());
    }

    private void splatBlockItem(DeferredBlock<Block> item) {
        withExistingParent(item.getId().getPath(),
                ResourceLocation.parse("item/generated")).texture("layer0",
                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + item.getId().getPath()));
    }

    public void buttonItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/button_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }

    public void fenceItem(DeferredBlock<?> block, DeferredBlock<Block> baseBlock) {
        this.withExistingParent(block.getId().getPath(), mcLoc("block/fence_inventory"))
                .texture("texture",  ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID,
                        "block/" + baseBlock.getId().getPath()));
    }
}
