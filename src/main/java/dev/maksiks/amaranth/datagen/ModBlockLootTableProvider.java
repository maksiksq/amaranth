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
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.BLOCKS.getEntries().stream().map(Holder::value)::iterator;
    }
}
