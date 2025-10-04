package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.entries.LootPoolSingletonContainer;
import net.minecraft.world.level.storage.loot.predicates.BonusLevelTableCondition;
import net.minecraft.world.level.storage.loot.predicates.LootItemCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.neoforged.fml.common.Mod;

import java.util.Set;

public class ModBlockLootTableProvider extends BlockLootSubProvider {
    protected ModBlockLootTableProvider(HolderLookup.Provider registries) {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags(), registries);
    }

    private LootItemCondition.Builder hasShearsOrSilkTouch() {
        return HAS_SHEARS.or(this.hasSilkTouch());
    }

    private LootItemCondition.Builder doesNotHaveShearsOrSilkTouch() {
        return this.hasShearsOrSilkTouch().invert();
    }

    protected LootTable.Builder createFruitLeavesDrops(Block leavesBlock, Block saplingBlock, Item item, float... chances) {
        HolderLookup.RegistryLookup<Enchantment> registrylookup = this.registries.lookupOrThrow(Registries.ENCHANTMENT);
        return this.createLeavesDrops(leavesBlock, saplingBlock, chances)
                .withPool(
                        LootPool.lootPool()
                                .setRolls(ConstantValue.exactly(1.0F))
                                .when(this.doesNotHaveShearsOrSilkTouch())
                                .add(
                                        ((LootPoolSingletonContainer.Builder) this.applyExplosionCondition(leavesBlock, LootItem.lootTableItem(item)))
                                                .when(
                                                        BonusLevelTableCondition.bonusLevelFlatChance(
                                                                registrylookup.getOrThrow(Enchantments.FORTUNE), 0.005F, 0.0055555557F, 0.00625F, 0.008333334F, 0.025F
                                                        )
                                                )
                                )
                );
    }

    @Override
    protected void generate() {
        // mystic
        this.dropSelf(ModBlocks.MYSTIC_LOG.get());
        this.dropSelf(ModBlocks.MYSTIC_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_MYSTIC_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_MYSTIC_WOOD.get());
        this.dropSelf(ModBlocks.MYSTIC_PLANKS.get());
        this.dropSelf(ModBlocks.MYSTIC_SAPLING.get());

        this.add(ModBlocks.MYSTIC_LEAVES.get(),
                block -> createFruitLeavesDrops(ModBlocks.MYSTIC_LEAVES.get(), ModBlocks.MYSTIC_SAPLING.get(), ModItems.HEXFRUIT.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.MYSTIC_STAIRS.get());
        this.add(ModBlocks.MYSTIC_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.MYSTIC_SLAB.get()));

        this.dropSelf(ModBlocks.MYSTIC_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.MYSTIC_BUTTON.get());

        this.dropSelf(ModBlocks.MYSTIC_FENCE.get());
        this.dropSelf(ModBlocks.MYSTIC_FENCE_GATE.get());
        this.dropSelf(ModBlocks.MYSTIC_TRAPDOOR.get());

        this.add(ModBlocks.MYSTIC_DOOR.get(),
                block -> createDoorTable(ModBlocks.MYSTIC_DOOR.get()));

        // stubby
        this.dropSelf(ModBlocks.STUBBY_SAPLING.get());

        // silver birch
        this.add(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.SILVERY_SILVER_BIRCH_LEAVES.get(), ModBlocks.SILVER_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.LIGHT_SILVER_BIRCH_LEAVES.get(), ModBlocks.SILVER_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.DARK_SILVER_BIRCH_LEAVES.get(), ModBlocks.SILVER_BIRCH_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));


        this.dropSelf(ModBlocks.SILVER_BIRCH_SAPLING.get());

        this.add(ModBlocks.GOLDEN_LEAF_LITTER.get(),
                block -> createPetalsDrops(ModBlocks.GOLDEN_LEAF_LITTER.get()));

        // desolate
        this.add(ModBlocks.SORROW_ICE.get(),
                block -> createSilkTouchOnlyTable(ModBlocks.SORROW_ICE.get()));
        this.add(ModBlocks.REMNANT_SORROW_ICE.get(),
                block -> createSilkTouchOnlyTable(ModBlocks.REMNANT_SORROW_ICE.get()));

        // mixed
        this.add(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.PURPLE_MIXED_OAK_LEAVES.get(), ModBlocks.PURPLE_MIXED_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.RED_MIXED_OAK_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.RED_MIXED_OAK_LEAVES.get(), ModBlocks.RED_MIXED_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.add(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.YELLOW_MIXED_OAK_LEAVES.get(), ModBlocks.YELLOW_MIXED_OAK_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.PURPLE_MIXED_OAK_SAPLING.get());
        this.dropSelf(ModBlocks.RED_MIXED_OAK_SAPLING.get());
        this.dropSelf(ModBlocks.YELLOW_MIXED_OAK_SAPLING.get());

        // orderly
        this.dropSelf(ModBlocks.TRIMMED_TREE_SAPLING.get());

        // anthocyanin
        this.dropSelf(ModBlocks.ANTHOCYANIN_LOG.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_WOOD.get());
        this.dropSelf(ModBlocks.STRIPPED_ANTHOCYANIN_LOG.get());
        this.dropSelf(ModBlocks.STRIPPED_ANTHOCYANIN_WOOD.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_PLANKS.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_SAPLING.get());

        this.add(ModBlocks.ANTHOCYANIN_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.ANTHOCYANIN_LEAVES.get(), ModBlocks.ANTHOCYANIN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));
        this.add(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get(),
                block -> createLeavesDrops(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get(), ModBlocks.ANTHOCYANIN_SAPLING.get(), NORMAL_LEAVES_SAPLING_CHANCES));

        this.dropSelf(ModBlocks.ANTHOCYANIN_STAIRS.get());
        this.add(ModBlocks.ANTHOCYANIN_SLAB.get(),
                block -> createSlabItemTable(ModBlocks.ANTHOCYANIN_SLAB.get()));

        this.dropSelf(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_BUTTON.get());

        this.dropSelf(ModBlocks.ANTHOCYANIN_FENCE.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_FENCE_GATE.get());
        this.dropSelf(ModBlocks.ANTHOCYANIN_TRAPDOOR.get());
        this.dropSelf(ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get());

        this.add(ModBlocks.ANTHOCYANIN_DOOR.get(),
                block -> createDoorTable(ModBlocks.ANTHOCYANIN_DOOR.get()));
        this.add(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get(),
                block -> createDoorTable(ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get()));

        this.dropSelf(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get());
        this.add(ModBlocks.POTTED_MALACHITE_VIPERS_BUGLOSS.get(),
                block -> createPotFlowerItemTable(ModBlocks.MALACHITE_VIPERS_BUGLOSS.get()));
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
