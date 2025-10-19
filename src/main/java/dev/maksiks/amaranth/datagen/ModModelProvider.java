package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.block.custom.ModSpikyArchesBlock;
import dev.maksiks.amaranth.block.custom.ModThickPumpkinBlock;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.ModelProvider;
import net.minecraft.client.data.models.MultiVariant;
import net.minecraft.client.data.models.blockstates.*;
import net.minecraft.client.data.models.model.*;
import net.minecraft.client.renderer.block.model.Variant;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.data.PackOutput;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.neoforged.neoforge.client.model.generators.template.ExtendedModelTemplateBuilder;
import net.neoforged.neoforge.registries.DeferredBlock;

import java.util.stream.Stream;

import static net.minecraft.client.data.models.BlockModelGenerators.createSimpleBlock;
import static net.minecraft.client.data.models.BlockModelGenerators.plainVariant;
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

        plainBlockItem(ModBlocks.MYSTIC_SAPLING, itemModels);

        // stubby
        plainBlockItem(ModBlocks.STUBBY_SAPLING, itemModels);

        // silver
        plainBlockItem(ModBlocks.GOLDEN_LEAF_LITTER, itemModels);
        plainBlockItem(ModBlocks.SILVER_BIRCH_SAPLING, itemModels);

        // mixed
        plainBlockItem(ModBlocks.PURPLE_MIXED_OAK_SAPLING, itemModels);
        plainBlockItem(ModBlocks.RED_MIXED_OAK_SAPLING, itemModels);
        plainBlockItem(ModBlocks.YELLOW_MIXED_OAK_SAPLING, itemModels);

        // orderly
        plainBlockItem(ModBlocks.TRIMMED_TREE_SAPLING, itemModels);

        itemModels.generateFlatItem(ModItems.SHROOM_BOI_SPAWN_EGG.get(), ModelTemplates.FLAT_ITEM);

        // shroom
        itemModels.generateFlatItem(ModItems.MUSHROOM_TEA.get(), ModelTemplates.FLAT_ITEM);

        // anthocyanin
        plainBlockItem(ModBlocks.ANTHOCYANIN_SAPLING, itemModels);
        plainBlockItem(ModBlocks.MALACHITE_VIPERS_BUGLOSS, itemModels);

        // pain
        plainBlockItem(ModBlocks.SPIKY_ARCHES, itemModels);
        itemModels.generateFlatItem(ModItems.THORN.get(), ModelTemplates.FLAT_ITEM);

        itemModels.generateFlatItem(ModItems.CROWN_OF_THORNS.asItem(), ModelTemplates.FLAT_ITEM);

        //
        // BLOCKS
        //

        // misc
        blockModels.createTrivialCube(ModBlocks.MARBLE.get());

        // mystic
        blockModels.woodProvider(ModBlocks.MYSTIC_LOG.get()).logWithHorizontal(ModBlocks.MYSTIC_LOG.get()).wood(ModBlocks.MYSTIC_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_MYSTIC_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_MYSTIC_LOG.get()).wood(ModBlocks.STRIPPED_MYSTIC_WOOD.get());

        createUntintedLeaves(ModBlocks.MYSTIC_LEAVES.get(), blockModels);
        createCrossBlockWithRenderType(ModBlocks.MYSTIC_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

        // yoo this datagen update is cool as hell
        blockModels.family(ModBlocks.MYSTIC_PLANKS.get())
                .fence(ModBlocks.MYSTIC_FENCE.get())
                .fenceGate(ModBlocks.MYSTIC_FENCE_GATE.get())
                .stairs(ModBlocks.MYSTIC_STAIRS.get())
                .slab(ModBlocks.MYSTIC_SLAB.get())
                .button(ModBlocks.MYSTIC_BUTTON.get())
                .pressurePlate(ModBlocks.MYSTIC_PRESSURE_PLATE.get());

        createDoorWithRenderType(ModBlocks.MYSTIC_DOOR.get(), blockModels, "cutout");
        createTrapdoorWithRenderType(ModBlocks.MYSTIC_TRAPDOOR.get(), blockModels, "cutout");

        // stubby
        createCrossBlockWithRenderType(ModBlocks.STUBBY_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

        // silver
        createUntintedLeaves(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get(), blockModels);
        createUntintedLeaves(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get(), blockModels);
        createUntintedLeaves(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get(), blockModels);

        // leaf litter is made manually
        createCrossBlockWithRenderType(ModBlocks.SILVER_BIRCH_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

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
        createUntintedLeaves(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get(), blockModels);
        createUntintedLeaves(ModBlocks.RED_MIXED_OAK_LEAVES.get(), blockModels);
        createUntintedLeaves(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get(), blockModels);

        createCrossBlockWithRenderType(ModBlocks.PURPLE_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");
        createCrossBlockWithRenderType(ModBlocks.RED_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");
        createCrossBlockWithRenderType(ModBlocks.YELLOW_MIXED_OAK_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

        // orderly
        createCrossBlockWithRenderType(ModBlocks.TRIMMED_TREE_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

        // anthocyanin
        blockModels.woodProvider(ModBlocks.ANTHOCYANIN_LOG.get()).logWithHorizontal(ModBlocks.ANTHOCYANIN_LOG.get()).wood(ModBlocks.ANTHOCYANIN_WOOD.get());
        blockModels.woodProvider(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()).logWithHorizontal(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get()).wood(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get());

        createUntintedLeaves(ModBlocks.ANTHOCYANIN_LEAVES.get(), blockModels);
        createUntintedLeaves(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get(), blockModels);

        createCrossBlockWithRenderType(ModBlocks.ANTHOCYANIN_SAPLING.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");

        blockModels.family(ModBlocks.ANTHOCYANIN_PLANKS.get())
                .fence(ModBlocks.ANTHOCYANIN_FENCE.get())
                .fenceGate(ModBlocks.ANTHOCYANIN_FENCE_GATE.get())
                .stairs(ModBlocks.ANTHOCYANIN_STAIRS.get())
                .slab(ModBlocks.ANTHOCYANIN_SLAB.get())
                .button(ModBlocks.ANTHOCYANIN_BUTTON.get())
                .pressurePlate(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get());

        createDoorWithRenderType(ModBlocks.ANTHOCYANIN_DOOR.get(), blockModels, "cutout");
        createDoorWithRenderType(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get(), blockModels, "cutout");
        createTrapdoorWithRenderType(ModBlocks.ANTHOCYANIN_TRAPDOOR.get(), blockModels, "cutout");
        createTrapdoorWithRenderType(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get(), blockModels, "cutout");

        createCrossBlockWithRenderType(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get(), BlockModelGenerators.PlantType.TINTED, blockModels, "cutout");
        // pot is made manually

        // pain
        randomVariantTwoPlaneCutout(ModBlocks.SPIKY_ARCHES, blockModels, 4, "cutout_mipped");

        // thrumletons
        thickPumpkinBlock(ModBlocks.THICK_PUMPKIN, blockModels);
        thickPumpkinBlockItem(ModBlocks.THICK_PUMPKIN, blockModels);
    }


    private void createUntintedLeaves(Block leaves, BlockModelGenerators blockModels) {
        blockModels.createTrivialBlock(
                leaves,
                TexturedModel.LEAVES.updateTemplate(template ->
                        template.extend()
                                .renderType("minecraft:cutout_mipped")
                                .build()
                )
        );
    }

    private void createDoorWithRenderType(Block door, BlockModelGenerators blockModels, String renderType) {
        TextureMapping textureMapping = TextureMapping.door(door);

        ModelTemplate bottomLeftTemplate = ModelTemplates.DOOR_BOTTOM_LEFT.extend().renderType(renderType).build();
        ModelTemplate bottomLeftOpenTemplate = ModelTemplates.DOOR_BOTTOM_LEFT_OPEN.extend().renderType(renderType).build();
        ModelTemplate bottomRightTemplate = ModelTemplates.DOOR_BOTTOM_RIGHT.extend().renderType(renderType).build();
        ModelTemplate bottomRightOpenTemplate = ModelTemplates.DOOR_BOTTOM_RIGHT_OPEN.extend().renderType(renderType).build();
        ModelTemplate topLeftTemplate = ModelTemplates.DOOR_TOP_LEFT.extend().renderType(renderType).build();
        ModelTemplate topLeftOpenTemplate = ModelTemplates.DOOR_TOP_LEFT_OPEN.extend().renderType(renderType).build();
        ModelTemplate topRightTemplate = ModelTemplates.DOOR_TOP_RIGHT.extend().renderType(renderType).build();
        ModelTemplate topRightOpenTemplate = ModelTemplates.DOOR_TOP_RIGHT_OPEN.extend().renderType(renderType).build();

        MultiVariant bottomLeft = plainVariant(bottomLeftTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant bottomLeftOpen = plainVariant(bottomLeftOpenTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant bottomRight = plainVariant(bottomRightTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant bottomRightOpen = plainVariant(bottomRightOpenTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant topLeft = plainVariant(topLeftTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant topLeftOpen = plainVariant(topLeftOpenTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant topRight = plainVariant(topRightTemplate.create(door, textureMapping, blockModels.modelOutput));
        MultiVariant topRightOpen = plainVariant(topRightOpenTemplate.create(door, textureMapping, blockModels.modelOutput));

        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createDoor(
                        door,
                        bottomLeft, bottomLeftOpen,
                        bottomRight, bottomRightOpen,
                        topLeft, topLeftOpen,
                        topRight, topRightOpen
                )
        );

        String doorName = BuiltInRegistries.BLOCK.getKey(door).getPath();
        ResourceLocation doorItemTexture = ResourceLocation.fromNamespaceAndPath(
                Amaranth.MOD_ID,
                "item/" + doorName
        );

        ResourceLocation doorItemModel = ModelTemplates.FLAT_ITEM.create(
                ModelLocationUtils.getModelLocation(door.asItem()),
                TextureMapping.layer0(doorItemTexture),
                blockModels.modelOutput
        );

        blockModels.itemModelOutput.accept(
                door.asItem(),
                ItemModelUtils.plainModel(doorItemModel)
        );
    }

    private void createTrapdoorWithRenderType(Block trapdoor, BlockModelGenerators blockModels, String renderType) {
        TextureMapping textureMapping = TextureMapping.defaultTexture(trapdoor);

        ModelTemplate bottomTemplate = ModelTemplates.ORIENTABLE_TRAPDOOR_BOTTOM.extend().renderType(renderType).build();
        ModelTemplate topTemplate = ModelTemplates.ORIENTABLE_TRAPDOOR_TOP.extend().renderType(renderType).build();
        ModelTemplate openTemplate = ModelTemplates.ORIENTABLE_TRAPDOOR_OPEN.extend().renderType(renderType).build();

        MultiVariant bottom = plainVariant(bottomTemplate.create(trapdoor, textureMapping, blockModels.modelOutput));
        MultiVariant top = plainVariant(topTemplate.create(trapdoor, textureMapping, blockModels.modelOutput));
        MultiVariant open = plainVariant(openTemplate.create(trapdoor, textureMapping, blockModels.modelOutput));

        blockModels.blockStateOutput.accept(
                BlockModelGenerators.createTrapdoor(trapdoor, top, bottom, open)
        );

        ResourceLocation trapdoorItemModel = bottomTemplate.create(
                ModelLocationUtils.getModelLocation(trapdoor.asItem()),
                textureMapping,
                blockModels.modelOutput
        );

        blockModels.itemModelOutput.accept(
                trapdoor.asItem(),
                ItemModelUtils.plainModel(trapdoorItemModel)
        );
    }

    private void plainBlockItem(DeferredBlock<Block> block, ItemModelGenerators itemModels) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        ResourceLocation modelLocation = ModelTemplates.FLAT_ITEM.create(
                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "item/" + name),
                TextureMapping.layer0(ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + name)),
                itemModels.modelOutput
        );

        itemModels.itemModelOutput.accept(
                block.asItem(),
                ItemModelUtils.plainModel(modelLocation)
        );
    }

    private void createCrossBlockWithRenderType(Block block, BlockModelGenerators.PlantType plantType,
                                                BlockModelGenerators blockModels, String renderType) {
        TextureMapping textureMapping = plantType.getTextureMapping(block);

        ModelTemplate extendedTemplate = plantType.getCross()
                .extend()
                .renderType(renderType)
                .build();

        MultiVariant multiVariant = plainVariant(
                extendedTemplate.create(block, textureMapping, blockModels.modelOutput)
        );

        blockModels.blockStateOutput.accept(createSimpleBlock(block, multiVariant));
    }

    // these two are garbage, neo has a way better way to do this now
    private void randomVariantTwoPlaneCutout(DeferredBlock<Block> block,
                                             BlockModelGenerators blockModels, int variantCount, String renderType) {
        Block blk = block.get();
        String baseName = BuiltInRegistries.BLOCK.getKey(blk).getPath();

        for (int i = 0; i < variantCount; i++) {
            ResourceLocation texture = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, "block/" + baseName + "_" + i);

            ModelTemplates.CROSS.extend().renderType(renderType).build().create(
                    ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + baseName + "_" + i),
                    TextureMapping.cross(texture),
                    blockModels.modelOutput
            );
        }

        var propertyDispatch = PropertyDispatch.initial(ModSpikyArchesBlock.VARIANT);

        for (int i = 0; i < variantCount; i++) {
            ResourceLocation modelLocation = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, "block/" + baseName + "_" + i);

            propertyDispatch = propertyDispatch.select(i, BlockModelGenerators.variant(new Variant(modelLocation)));
        }

        blockModels.blockStateOutput.accept(
                MultiVariantGenerator.dispatch(blk).with(propertyDispatch)
        );
    }

    private void thickPumpkinBlock(DeferredBlock<Block> block,
                                   BlockModelGenerators blockModels) {
        Block blk = block.get();
        String baseName = BuiltInRegistries.BLOCK.getKey(blk).getPath();

        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + baseName + "_side");
        ResourceLocation end = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/" + baseName + "_end");
        ResourceLocation inner = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_inner");

        TextureSlot texture = TextureSlot.create("texture");

        MultiPartGenerator multiPartGenerator = MultiPartGenerator.multiPart(blk);

        for (Direction dir : Direction.values()) {
            ResourceLocation faceTex = (dir == Direction.UP || dir == Direction.DOWN) ? end : side;
            String dirName = dir.getName();

            ResourceLocation outerModelLoc = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, "block/" + baseName + "_" + dirName);

            ExtendedModelTemplateBuilder.builder()
                    .parent(ResourceLocation.withDefaultNamespace("block/block"))
                    .requiredTextureSlot(texture)
                    .requiredTextureSlot(TextureSlot.PARTICLE)
                    .element(builder -> {
                        builder.from(0, 0, 0).to(16, 16, 16);
                        builder.face(dir, face -> face.texture(texture).cullface(dir));
                    })
                    .build()
                    .create(
                            outerModelLoc,
                            new TextureMapping()
                                    .put(texture, faceTex)
                                    .put(TextureSlot.PARTICLE, side),
                            blockModels.modelOutput
                    );

            ResourceLocation innerModelLoc = ResourceLocation.fromNamespaceAndPath(
                    Amaranth.MOD_ID, "block/" + baseName + "_" + dirName + "_inner");

            ExtendedModelTemplateBuilder.builder()
                    .parent(ResourceLocation.withDefaultNamespace("block/block"))
                    .requiredTextureSlot(texture)
                    .requiredTextureSlot(TextureSlot.PARTICLE)
                    .element(builder -> {
                        builder.from(0, 0, 0).to(16, 16, 16);
                        builder.face(dir, face -> face.texture(texture).cullface(dir));
                    })
                    .build()
                    .create(
                            innerModelLoc,
                            new TextureMapping()
                                    .put(texture, inner)
                                    .put(TextureSlot.PARTICLE, inner),
                            blockModels.modelOutput
                    );

            BooleanProperty prop = ModThickPumpkinBlock.PROPERTY_BY_DIRECTION.get(dir);

            multiPartGenerator = multiPartGenerator.with(
                    BlockModelGenerators.condition().term(prop, true),
                    BlockModelGenerators.variant(new Variant(outerModelLoc))
            );

            multiPartGenerator = multiPartGenerator.with(
                    BlockModelGenerators.condition().term(prop, false),
                    BlockModelGenerators.variant(new Variant(innerModelLoc))
            );
        }

        blockModels.blockStateOutput.accept(multiPartGenerator);
    }

    private void thickPumpkinBlockItem(DeferredBlock<Block> block,
                                       BlockModelGenerators blockModels) {
        String name = BuiltInRegistries.BLOCK.getKey(block.get()).getPath();

        ResourceLocation side = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_side");
        ResourceLocation end = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_end");
        ResourceLocation inner = ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "block/thick_pumpkin_inner");

        ModelTemplates.CUBE.create(
                ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "item/" + name),
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

        blockModels.itemModelOutput.accept(
                block.asItem(),
                ItemModelUtils.plainModel(
                        ResourceLocation.fromNamespaceAndPath(Amaranth.MOD_ID, "item/" + name)
                )
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