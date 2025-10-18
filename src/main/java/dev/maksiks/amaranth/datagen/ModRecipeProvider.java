package dev.maksiks.amaranth.datagen;

import dev.maksiks.amaranth.Amaranth;
import dev.maksiks.amaranth.block.ModBlocks;
import dev.maksiks.amaranth.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ClipContext;
import net.minecraft.world.level.block.Blocks;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider {
    protected ModRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
        super(provider, recipeOutput);
    }

    public static class Runner extends RecipeProvider.Runner {
        public Runner(PackOutput output, CompletableFuture<HolderLookup.Provider> provider) {
            super(output, provider);
        }

        @Override
        protected RecipeProvider createRecipeProvider(HolderLookup.Provider provider, RecipeOutput recipeOutput) {
            return new ModRecipeProvider(provider, recipeOutput);
        }

        @Override
        public String getName() {
            return "Cool Recipes";
        }
    }

    @Override
    protected void buildRecipes() {
        // misc
        // temp
        shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(this.output);

        shapeless(RecipeCategory.MISC, ModItems.MAFIA_BLOB.get(), 1)
                .requires(ModBlocks.MYSTIC_LEAVES.get())
                .unlockedBy("has_mystic_leaves", has(ModBlocks.MYSTIC_LEAVES.get()))
                .save(this.output, "amaranth:mafia_blob_from_leaves");

        shaped(RecipeCategory.MISC, ModItems.BEANIE_BLOB.get())
                .pattern("HHH")
                .pattern("HHH")
                .pattern("HHH")
                .define('H', ModItems.HEXFRUIT.get())
                .unlockedBy("has_hexfruit", has(ModItems.HEXFRUIT.get()))
                .save(this.output);

        // not temp

        shaped(RecipeCategory.MISC, ModItems.EMPTY_TEA_CUP.get(), 1)
                .pattern("M M")
                .pattern(" M ")
                .pattern("   ")
                .define('M', ModBlocks.MARBLE.get().asItem())
                .unlockedBy("has_marble", has(ModBlocks.MARBLE.get()))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(getItemName(ModItems.EMPTY_TEA_CUP) + "_from_marble")));

        // poland
        shaped(RecipeCategory.MISC, ModItems.EMPTY_TEA_CUP.get(), 1)
                .pattern("D D")
                .pattern(" D ")
                .pattern("   ")
                .define('D', Blocks.POLISHED_DIORITE.asItem())
                .unlockedBy("has_diorite", has(Blocks.DIORITE))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(getItemName(ModItems.EMPTY_TEA_CUP) + "_from_polished_diorite")));

        shapeless(RecipeCategory.MISC, ModItems.MUSHROOM_TEA.get(), 1)
                .requires(ModItems.EMPTY_TEA_CUP.get())
                .requires(Items.RED_MUSHROOM_BLOCK)
                .requires(Items.BROWN_MUSHROOM_BLOCK)
                .unlockedBy("has_tea_cup", has(ModItems.EMPTY_TEA_CUP.get()))
                .save(this.output);

        shapeless(RecipeCategory.DECORATIONS, ModBlocks.SPIKY_ARCHES.asItem(), 1)
                .requires(ModItems.THORN.get())
                .requires(ModItems.THORN.get())
                .requires(ModItems.THORN.get())
                .requires(Items.VINE)
                .unlockedBy("has_thorn", has(ModItems.THORN.get()))
                .save(this.output);

        shaped(RecipeCategory.COMBAT, ModItems.CROWN_OF_THORNS, 1)
                .pattern("TTT")
                .pattern("T T")
                .pattern("   ")
                .define('T', ModItems.THORN.get())
                .unlockedBy("has_thorn", has(ModItems.THORN.get()))
                .save(this.output);

        shapeless(RecipeCategory.DECORATIONS, ModBlocks.THICK_PUMPKIN.asItem(), 1)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .requires(Blocks.PUMPKIN)
                .unlockedBy("has_pumpkin", has(Blocks.PUMPKIN.asItem()))
                .save(this.output);

        shapeless(RecipeCategory.FOOD, Items.PUMPKIN_SEEDS, 12)
                .requires(ModBlocks.THICK_PUMPKIN.asItem())
                .unlockedBy("has_thick_pumpkin", has(ModBlocks.THICK_PUMPKIN.get()))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(getItemName(Items.PUMPKIN_SEEDS) + "_from_thick_pumpkin")));

        // who in the world will ever need this
        shapeless(RecipeCategory.FOOD, Items.PUMPKIN_PIE, 5)
                .requires(ModBlocks.THICK_PUMPKIN.asItem())
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.SUGAR)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .requires(Items.EGG)
                .unlockedBy("has_thick_pumpkin", has(ModBlocks.THICK_PUMPKIN.get()))
                .save(this.output, ResourceKey.create(Registries.RECIPE, ResourceLocation.parse(getItemName(Items.PUMPKIN_PIE) + "_from_thick_pumpkin")));

        // yoink a custom method for smelting later so it's inside neo's folder because it's hardcoded to be in mc

        stairBuilder(ModBlocks.MYSTIC_STAIRS.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get()))
                .save(this.output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.MYSTIC_SLAB.get(), ModBlocks.MYSTIC_PLANKS.get());

        buttonBuilder(ModBlocks.MYSTIC_BUTTON.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(this.output);
        pressurePlate(ModBlocks.MYSTIC_PRESSURE_PLATE.get(), ModBlocks.MYSTIC_PLANKS.get());

        fenceBuilder(ModBlocks.MYSTIC_FENCE.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(this.output);
        fenceGateBuilder(ModBlocks.MYSTIC_FENCE_GATE.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(this.output);

        doorBuilder(ModBlocks.MYSTIC_DOOR.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(this.output);
        trapdoorBuilder(ModBlocks.MYSTIC_TRAPDOOR.get(), Ingredient.of(ModBlocks.MYSTIC_PLANKS.get())).group("mystic")
                .unlockedBy("has_mystic_planks", has(ModBlocks.MYSTIC_PLANKS.get())).save(this.output);

        // anthocyanin
        stairBuilder(ModBlocks.ANTHOCYANIN_STAIRS.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get()))
                .save(this.output);
        slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.ANTHOCYANIN_SLAB.get(), ModBlocks.ANTHOCYANIN_PLANKS.get());

        buttonBuilder(ModBlocks.ANTHOCYANIN_BUTTON.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(this.output);
        pressurePlate(ModBlocks.ANTHOCYANIN_PRESSURE_PLATE.get(), ModBlocks.ANTHOCYANIN_PLANKS.get());

        fenceBuilder(ModBlocks.ANTHOCYANIN_FENCE.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(this.output);
        fenceGateBuilder(ModBlocks.ANTHOCYANIN_FENCE_GATE.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(this.output);

        doorBuilder(ModBlocks.ANTHOCYANIN_DOOR.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(this.output);
        trapdoorBuilder(ModBlocks.ANTHOCYANIN_TRAPDOOR.get(), Ingredient.of(ModBlocks.ANTHOCYANIN_PLANKS.get())).group("anthocyanin")
                .unlockedBy("has_anthocyanin_planks", has(ModBlocks.ANTHOCYANIN_PLANKS.get())).save(this.output);

        shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_ANTHOCYANIN_TRAPDOOR.get(), 1)
                .requires(ModBlocks.ANTHOCYANIN_TRAPDOOR.get())
                .requires(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get())
                .unlockedBy("has_anthocyanin_trapdoor", has(ModBlocks.ANTHOCYANIN_TRAPDOOR.get()))
                .unlockedBy("has_blooming_anthocyanin_leaves", has(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get()))
                .save(this.output);

        shapeless(RecipeCategory.DECORATIONS, ModBlocks.ORNAMENTED_ANTHOCYANIN_DOOR.get(), 1)
                .requires(ModBlocks.ANTHOCYANIN_DOOR.get())
                .requires(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get())
                .unlockedBy("has_anthocyanin_door", has(ModBlocks.ANTHOCYANIN_DOOR.get()))
                .unlockedBy("has_blooming_anthocyanin_leaves", has(ModBlocks.BLOOMING_ANTHOCYANIN_LEAVES.get()))
                .save(this.output);
    }
}
