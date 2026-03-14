package net.beyondlands.tbl.datagen;


import net.beyondlands.tbl.TBL;
import net.beyondlands.tbl.block.ModBlocks;
import net.beyondlands.tbl.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> LITHIUM_SMELTABLES = List.of(ModItems.RAW_LITHIUM.get(),
                ModBlocks.LITHIUM_ORE.get(), ModBlocks.LITHIUM_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.LITHIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', ModItems.LITHIUM.get())
                .unlockedBy(getHasName(ModItems.LITHIUM.get()), has(ModItems.LITHIUM.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LITHIUM.get(), 9)
                .requires(ModBlocks.LITHIUM_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.LITHIUM_BLOCK.get()), has(ModBlocks.LITHIUM_BLOCK.get())).save(pRecipeOutput);

        /*ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.ALEXANDRITE.get(), 32)
                .requires(ModBlocks.MAGIC_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.ALEXANDRITE_BLOCK.get()), has(ModBlocks.ALEXANDRITE_BLOCK.get()))
                .save(pRecipeOutput, TBL.MOD_ID + ":alexandrite_from_magic_block");*/

        oreSmelting(pRecipeOutput, LITHIUM_SMELTABLES, RecipeCategory.MISC, ModItems.LITHIUM.get(), 0.25f, 200, "lithium");
        oreBlasting(pRecipeOutput, LITHIUM_SMELTABLES, RecipeCategory.MISC, ModItems.LITHIUM.get(), 0.25f, 100, "lithium");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.FORCE_FIELD_BLOCK.get())
                .pattern(" # ")
                .pattern("tTt")
                .pattern("ttt")
                .define('#', ModBlocks.LITHIUM_BLOCK.get())
                .define('T', ModItems.LITHIUM_BATTERY.get())
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.LITHIUM.get()), has(ModItems.LITHIUM.get()))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get())
                .requires(ModBlocks.FORCE_FIELD_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.FORCE_FIELD_BLOCK.get()), has(ModBlocks.FORCE_FIELD_BLOCK.get()))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.FORCE_FIELD_BLOCK.get())
                .requires(ModBlocks.FORCE_FIELD_BLOCK_ATTRACT.get())
                .unlockedBy(getHasName(ModBlocks.FORCE_FIELD_BLOCK.get()), has(ModBlocks.FORCE_FIELD_BLOCK.get()))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput, TBL.MOD_ID + ":force_field_block_2" );


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LITHIUM_BATTERY.get())
                .pattern("t")
                .pattern("T")
                .pattern("t")
                .define('T', ModItems.LITHIUM.get())
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.LITHIUM.get()), has(ModItems.LITHIUM.get()))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.LITHIUM_BATTERY_BOOSTED.get())
                .pattern("t")
                .pattern("T")
                .pattern("t")
                .define('T', ModBlocks.LITHIUM_BLOCK.get())
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.LITHIUM.get()), has(ModItems.LITHIUM.get()))
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LITHIUM_BATTERY_STACK.get())
                .requires(ModItems.LITHIUM_BATTERY.get(), 9)
                .unlockedBy(getHasName(ModItems.LITHIUM_BATTERY.get()), has(ModItems.LITHIUM_BATTERY.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.LITHIUM_BATTERY_STACK_BOOSTED.get())
                .requires(ModItems.LITHIUM_BATTERY_BOOSTED.get(), 9)
                .unlockedBy(getHasName(ModItems.LITHIUM_BATTERY.get()), has(ModItems.LITHIUM_BATTERY.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.STEEL_INGOT.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SWORD.get())
                .pattern("t")
                .pattern("t")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_KNIFE.get())
                .pattern("t")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_PICKAXE.get())
                .pattern("ttt")
                .pattern(" T ")
                .pattern(" T ")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_SHOVEL.get())
                .pattern("t")
                .pattern("T")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HOE.get())
                .pattern("tt")
                .pattern(" T")
                .pattern(" T")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_AXE.get())
                .pattern("tt")
                .pattern("tT")
                .pattern(" T")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HAMMER.get())
                .pattern("ttt")
                .pattern("ttt")
                .pattern(" T ")
                .define('T', Items.STICK)
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_HELMET.get())
                .pattern("ttt")
                .pattern("t t")
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_CHESTPLATE.get())
                .pattern("t t")
                .pattern("ttt")
                .pattern("ttt")
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_LEGGINGS.get())
                .pattern("ttt")
                .pattern("t t")
                .pattern("t t")
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.STEEL_BOOTS.get())
                .pattern("t t")
                .pattern("t t")
                .define(('t'), ModItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(ModItems.STEEL_INGOT.get()), has(ModItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.ASH_WOOD.get(), 3)
                .pattern("tt")
                .pattern("tt")
                .define(('t'), ModBlocks.ASH_LOG.get())
                .unlockedBy(getHasName(ModBlocks.ASH_LOG.get()), has(ModBlocks.ASH_LOG.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModBlocks.ASH_PLANKS.get(), 4)
                .requires(ModBlocks.ASH_LOG.get())
                .unlockedBy(getHasName(ModBlocks.ASH_LOG.get()), has(ModBlocks.ASH_LOG.get()))
                .save(pRecipeOutput);
    }



    protected static void oreSmelting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTIme, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.SMELTING_RECIPE, SmeltingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTIme, pGroup, "_from_smelting");
    }

    protected static void oreBlasting(RecipeOutput recipeOutput, List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult,
                                      float pExperience, int pCookingTime, String pGroup) {
        oreCooking(recipeOutput, RecipeSerializer.BLASTING_RECIPE, BlastingRecipe::new, pIngredients, pCategory, pResult,
                pExperience, pCookingTime, pGroup, "_from_blasting");
    }

    protected static <T extends AbstractCookingRecipe> void oreCooking(RecipeOutput recipeOutput, RecipeSerializer<T> pCookingSerializer, AbstractCookingRecipe.Factory<T> factory,
                                                                       List<ItemLike> pIngredients, RecipeCategory pCategory, ItemLike pResult, float pExperience, int pCookingTime, String pGroup, String pRecipeName) {
        for(ItemLike itemlike : pIngredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(itemlike), pCategory, pResult, pExperience, pCookingTime, pCookingSerializer, factory).group(pGroup).unlockedBy(getHasName(itemlike), has(itemlike))
                    .save(recipeOutput, TBL.MOD_ID + ":" + getItemName(pResult) + pRecipeName + "_" + getItemName(itemlike));
        }
    }
}