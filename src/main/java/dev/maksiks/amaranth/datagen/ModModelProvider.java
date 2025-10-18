package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.block.custom.ModSpikyArchesBlock;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.blockstates.MultiVariantGenerator;
import net.minecraft.client.data.models.blockstates.PropertyDispatch;
import net.minecraft.client.data.models.blockstates.Variant;
import net.minecraft.client.data.models.blockstates.VariantProperties;
import net.minecraft.client.data.models.model.*;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.stream.Stream;

import static net.minecraft.client.data.models.model.ModelTemplates.createItem;


public class ModModelProvider extends ModelProvider {

    public ModModelProvider(PackOutput output) {
        super(output, Amaranth.MOD_ID);
    }

    @Override
    protected void registerModels(BlockModelGenerators blockModels, ItemModelGenerators itemModels) {
        // misc
        itemModels.generateFlatItem(ModItems.PALETTE_OVERLOAD_MUSIC_DISC.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.EMPTY_TEA_CUP.get(), ModelTemplates.FLAT_ITEM);

        // mystic
        itemModels.generateFlatItem(ModItems.MAFIA_BLOB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.BEANIE_BLOB.get(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.HEXFRUIT.get(), ModelTemplates.FLAT_ITEM);

        saplingItem(ModBlocks.MYSTIC_SAPLING, itemModels);

        // stubby
        saplingItem(ModBlocks.STUBBY_SAPLING, itemModels);

        // silver
        itemModels.generateFlatItem(ModBlocks.GOLDEN_LEAF_LITTER.asItem(), ModelTemplates.FLAT_ITEM);
        saplingItem(ModBlocks.SILVER_BIRCH_SAPLING, itemModels);

        // mixed
        saplingItem(ModBlocks.PURPLE_MIXED_OAK_SAPLING, itemModels);
        saplingItem(ModBlocks.RED_MIXED_OAK_SAPLING, itemModels);
        saplingItem(ModBlocks.YELLOW_MIXED_OAK_SAPLING, itemModels);

        // orderly
        saplingItem(ModBlocks.TRIMMED_TREE_SAPLING, itemModels);

        itemModels.generateFlatItem(ModItems.SHROOM_BOI_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        // shroom
        itemModels.generateFlatItem(ModItems.MUSHROOM_TEA.get(), ModelTemplates.FLAT_ITEM);

        // anthocyanin
        saplingItem(ModBlocks.ANTHOCYANIN_SAPLING, itemModels);

        itemModels.generateFlatItem(ModBlocks.MALACHITE_VIPERS_BUGLOSS.asItem(), ModelTemplates.FLAT_ITEM);

        // pain
        itemModels.generateFlatItem(ModBlocks.SPIKY_ARCHES.asItem(), ModelTemplates.FLAT_ITEM);
        itemModels.generateFlatItem(ModItems.THORN.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.CROWN_OF_THORNS.get(), ModelTemplates.FLAT_ITEM);

        //
        // BLOCKS
        //

        // misc
        blockModels.createTrivialCube(ModBlocks.MARBLE.get());

        // mystic
        blockModels.woodProvider(ModBlocks.MYSTIC_LOG.get()).logWithHorizontal(ModBlocks.MYSTIC_LOG.get()).wood(ModBlocks.MYSTIC_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_MYSTIC_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_MYSTIC_LOG.get()).wood(ModBlocks.STRIPPED_MYSTIC_WOOD.get());


        blockModels.createTintedLeaves(ModBlocks.MYSTIC_LEAVES.get(), TexturedModel.LEAVES, -0xFFFFFF00);
        blockModels.createCrossBlock(ModBlocks.MYSTIC_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // yoo this datagen update is cool as hell
        blockModels.family(ModBlocks.MYSTIC_PLANKS.get())
                .fence(ModBlocks.MYSTIC_FENCE.get())
                .fenceGate(ModBlocks.MYSTIC_FENCE_GATE.get())
                .stairs(ModBlocks.MYSTIC_STAIRS.get())
                .slab(ModBlocks.MYSTIC_SLAB.get())
                .button(ModBlocks.MYSTIC_BUTTON.get())
                .pressurePlate(ModBlocks.MYSTIC_PRESSURE_PLATE.get())
                .door(ModBlocks.MYSTIC_DOOR.get())
                .trapdoor(ModBlocks.MYSTIC_TRAPDOOR.get());

        // stubby
        blockModels.createCrossBlock(ModBlocks.STUBBY_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // silver
        blockModels.createTintedLeaves(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get(), TexturedModel.LEAVES, -0xFFFFFF00);
        blockModels.createTintedLeaves(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get(), TexturedModel.LEAVES, -0xFFFFFF00);
        blockModels.createTintedLeaves(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get(), TexturedModel.LEAVES, -0xFFFFFF00);

        // leaf litter is made manually
        blockModels.createCrossBlock(ModBlocks.SILVER_BIRCH_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // desolate
        // ahhhh it was so hard to find how to do this in this version im dying
        blockModels.createTrivialBlock(
                ModBlocks.SORROW_ICE.get(),
                TexturedModel.CUBE.updateTemplate(template ->
                        template.extend()
                                .renderType("minecraft:translucent")
                                .build()
                )
        );

        blockModels.createTrivialCube(ModBlocks.REMNANT_SORROW_ICE.get());

        // mixed
        blockModels.createTintedLeaves(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createTintedLeaves(ModBlocks.RED_MIXED_OAK_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createTintedLeaves(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get(), TexturedModel.LEAVES, -12012264);

        blockModels.createCrossBlock(ModBlocks.PURPLE_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.RED_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);
        blockModels.createCrossBlock(ModBlocks.YELLOW_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // orderly
        blockModels.createCrossBlock(ModBlocks.TRIMMED_TREE_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        // anthocyanin
        blockModels.woodProvider(ModBlocks.ANTHOCYANIN_LOG.get()).logWithHorizontal(ModBlocks.ANTHOCYANIN_LOG.get()).wood(ModBlocks.ANTHOCYANIN_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()).wood(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get());


        blockModels.createTintedLeaves(ModBlocks.ANTHOCYANIN_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createTintedLeaves(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get(), TexturedModel.LEAVES, -12012264);
        blockModels.createCrossBlock(ModBlocks.ANTHOCYANIN_SAPLING.get(), BlockModelGenerators.PlantType.TINTED);

        blockModels.family(ModBlocks.ANTHOCYANIN_PLANKS.get())
                .fence(ModBlocks.ANTHOCYANIN_FENCE.get())
                .fenceGate(ModBlocks.ANTHOCYANIN_FENCE_GATE.get())
                .stairs(ModBlocks.ANTHOCYANIN_STAIRS.get())
                .slab(ModBlocks.ANTHOCYANIN_SLAB.get())
                .button(ModBlocks.ANTHOCYANIN_BUTTON.get())
                .pressurePlate(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get())
                .door(ModBlocks.ANTHOCYANIN_DOOR.get())
                .door(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get())
                .trapdoor(ModBlocks.ANTHOCYANIN_TRAPDOOR.get());

        // for whatever reason the trapdoor is void unlike everything else
        blockModels.createTrapdoor(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get());

        blockModels.createCrossBlock(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get(), BlockModelGenerators.PlantType.TINTED);
        // pot is made manually

        // pain
        randomVariantTwoPlaneCutout(ModBlocks.SPIKY_ARCHES, blockModels, 4);

        // thrumletons
        // TODO: more blockstates
        thickPumpkinBlock(ModBlocks.THICK_PUMPKIN, blockModels);
        thickPumpkinBlockItem(ModBlocks.THICK_PUMPKIN, blockModels);
    }

    private void saplingItem(DeferredBlock<Block> block, ItemModelGenerators itemModels) {
        itemModels.itemModelOutput.accept(
                block.asItem(),
                ItemModelUtils.plainModel(ModelLocationUtils.getModelLocation(block.get()))
        );
    }

    private void randomVariantTwoPlaneCutout(DeferredBlock<Block> block,
                                             BlockModelGenerators blockModels, int variantCount) {
        Block blk = block.get();
        String baseName = BuiltInRegistries.BLOCK.getKey(blk).getPath();

        for (int i = 0; i < variantCount; i++) {
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, "block/" + baseName + "_" + i);

            ModelTemplates.CROSS.create(
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, baseName + "_" + i),
                    TextureMapping.cross(texture),
                    blockModels.modelOutput
            );
        }

        var dispatch = PropertyDispatch.property(ModSpikyArchesBlock.VARIANT);
        for (int i = 0; i < variantCount; i++) {
            ResourceLocation model = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, baseName + "_" + i);

            dispatch = dispatch.select(i, Variant.variant().with(VariantProperties.MODEL, model));
        }

        var generator = MultiVariantGenerator.multiVariant(blk).with(dispatch);

        blockModels.blockStateOutput.accept(generator);
    }

    private void thickPumpkinBlock(DeferredBlock<Block> block,
                                   BlockModelGenerators blockModels) {
        Block blk = block.get();
        String baseName = BuiltInRegistries.BLOCK.getKey(blk).getPath();

        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + baseName + "_side");
        ResourceLocation end = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + baseName + "_end");
        ResourceLocation inner = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_inner");

        for (Direction dir : Direction.values()) {
            ResourceLocation faceTex = (dir == Direction.UP || dir == Direction.DOWN) ? end : side;
            String dirName = dir.getName();

            ModelTemplates.CUBE_ALL.create(
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, baseName + "_" + dirName),
                    TextureMapping.cube(faceTex),
                    blockModels.modelOutput
            );

            ModelTemplates.CUBE_ALL.create(
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, baseName + "_" + dirName + "_inner"),
                    TextureMapping.cube(inner),
                    blockModels.modelOutput
            );
        }

        var generator = MultiVariantGenerator.multiVariant(blk);

        for (Direction dir : Direction.values()) {
            BooleanProperty prop = switch (dir) {
                case NORTH -> BlockStateProperties.NORTH;
                case SOUTH -> BlockStateProperties.SOUTH;
                case EAST  -> BlockStateProperties.EAST;
                case WEST  -> BlockStateProperties.WEST;
                case UP    -> BlockStateProperties.UP;
                case DOWN  -> BlockStateProperties.DOWN;
            };

            String dirName = dir.getName();

            ResourceLocation outerModel = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, baseName + "_" + dirName);
            ResourceLocation innerModel = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, baseName + "_" + dirName + "_inner");

            generator.with(PropertyDispatch.property(prop)
                    .select(true, Variant.variant().with(VariantProperties.MODEL, outerModel))
                    .select(false, Variant.variant().with(VariantProperties.MODEL, innerModel)));
        }

        blockModels.blockStateOutput.accept(generator);
    }

    private void thickPumpkinBlockItem(DeferredBlock<Block> block,
                                       BlockModelGenerators blockModels) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_side");
        ResourceLocation end = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_end");
        ResourceLocation inner = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_inner");

        ModelTemplates.CUBE.create(
                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, name),
                new TextureMapping()
                        .put(TextureSlot.NORTH, inner)
                        .put(TextureSlot.SOUTH, side)
                        .put(TextureSlot.EAST, side)
                        .put(TextureSlot.WEST, inner)
                        .put(TextureSlot.UP, end)
                        .put(TextureSlot.DOWN, end)
                        .put(TextureSlot.PARTICLE, side),
                blockModels.modelOutput
        );
    }



    @Override
    protected Stream<? extends Holder<Block>> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().filter(x ->
                !x.is(ModBlocks.GOLDEN_LEAF_LITTER) &&
                        !x.is(ModBlocks.POTTED_MALACHITE_VIPERS_BUGLOSS));
    }

    @Override
    protected Stream<? extends Holder<Item>> getKnownItems() {
        // filter out custom item models if i have any
        return ModItems.ITEMS.getEntries().stream();
    }
}
