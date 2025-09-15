package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Block;
import net.neoforged.neoforge.client.model.generators.ItemModelProvider;
import net.neoforged.neoforge.common.data.ExistingFileHelper;
import net.neoforged.neoforge.registries.DeferredBlock;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, Amaranth.MOD_ID, existingFileHelper);
    }

    @Override
    protected void registerModels() {
        // mystic
        basicItem(ModItems.MAFIA_BLOB.get());
        basicItem(ModItems.BEANIE_BLOB.get());
        basicItem(ModItems.HEXFRUIT.get());

        buttonItem(ModBlocks.MYSTIC_BUTTON, ModBlocks.MYSTIC_PLANKS);
        fenceItem(ModBlocks.MYSTIC_FENCE, ModBlocks.MYSTIC_PLANKS);

        basicItem(ModBlocks.MYSTIC_DOOR.asItem());

        saplingItem(ModBlocks.MYSTIC_SAPLING);

        // stubby
        saplingItem(ModBlocks.STUBBY_SAPLING);

        // silver
        saplingItem(ModBlocks.SILVER_BIRCH_SAPLING);
    }

    private void saplingItem(DeferredBlock<Block> item) {
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
