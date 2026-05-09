package net.musicalement.tbl.datagen;


import net.musicalement.tbl.TBL;
import net.musicalement.tbl.block.TBlBlocks;
import net.musicalement.tbl.item.TBlItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Blocks;
import net.neoforged.neoforge.common.conditions.IConditionBuilder;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class TBlRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public TBlRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        List<ItemLike> LITHIUM_SMELTABLES = List.of(TBlItems.RAW_LITHIUM.get(),
                TBlBlocks.LITHIUM_ORE.get(), TBlBlocks.LITHIUM_DEEPSLATE_ORE.get());

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlBlocks.LITHIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', TBlItems.LITHIUM.get())
                .unlockedBy(getHasName(TBlItems.LITHIUM.get()), has(TBlItems.LITHIUM.get())).save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlBlocks.RAW_LITHIUM_BLOCK.get())
                .pattern("AAA")
                .pattern("AAA")
                .pattern("AAA")
                .define('A', TBlItems.RAW_LITHIUM.get())
                .unlockedBy(getHasName(TBlItems.RAW_LITHIUM.get()), has(TBlItems.RAW_LITHIUM.get())).save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlItems.LITHIUM.get(), 9)
                .requires(TBlBlocks.LITHIUM_BLOCK.get())
                .unlockedBy(getHasName(TBlBlocks.LITHIUM_BLOCK.get()), has(TBlBlocks.LITHIUM_BLOCK.get())).save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlItems.RAW_LITHIUM.get(), 9)
                .requires(TBlBlocks.RAW_LITHIUM_BLOCK.get())
                .unlockedBy(getHasName(TBlBlocks.RAW_LITHIUM_BLOCK.get()), has(TBlBlocks.RAW_LITHIUM_BLOCK.get())).save(pRecipeOutput);

        

        oreSmelting(pRecipeOutput, LITHIUM_SMELTABLES, RecipeCategory.MISC, TBlItems.LITHIUM.get(), 0.25f, 200, "lithium");
        oreBlasting(pRecipeOutput, LITHIUM_SMELTABLES, RecipeCategory.MISC, TBlItems.LITHIUM.get(), 0.25f, 100, "lithium");


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlBlocks.FORCE_FIELD_BLOCK.get())
                .pattern(" # ")
                .pattern("tTt")
                .pattern("ttt")
                .define('#', TBlBlocks.LITHIUM_BLOCK.get())
                .define('T', TBlItems.LITHIUM_BATTERY.get())
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.LITHIUM.get()), has(TBlItems.LITHIUM.get()))
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get())
                .requires(TBlBlocks.FORCE_FIELD_BLOCK.get())
                .unlockedBy(getHasName(TBlBlocks.FORCE_FIELD_BLOCK.get()), has(TBlBlocks.FORCE_FIELD_BLOCK.get()))
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);


        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlBlocks.FORCE_FIELD_BLOCK.get())
                .requires(TBlBlocks.FORCE_FIELD_BLOCK_ATTRACT.get())
                .unlockedBy(getHasName(TBlBlocks.FORCE_FIELD_BLOCK.get()), has(TBlBlocks.FORCE_FIELD_BLOCK.get()))
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput, TBL.MOD_ID + ":force_field_block_2" );


        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.LITHIUM_BATTERY.get())
                .pattern("t")
                .pattern("T")
                .pattern("t")
                .define('T', TBlItems.LITHIUM.get())
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.LITHIUM.get()), has(TBlItems.LITHIUM.get()))
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.LITHIUM_BATTERY_BOOSTED.get())
                .pattern("t")
                .pattern("T")
                .pattern("t")
                .define('T', TBlBlocks.LITHIUM_BLOCK.get())
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.LITHIUM.get()), has(TBlItems.LITHIUM.get()))
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlItems.LITHIUM_BATTERY_STACK.get())
                .requires(TBlItems.LITHIUM_BATTERY.get(), 9)
                .unlockedBy(getHasName(TBlItems.LITHIUM_BATTERY.get()), has(TBlItems.LITHIUM_BATTERY.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlItems.LITHIUM_BATTERY_STACK_BOOSTED.get())
                .requires(TBlItems.LITHIUM_BATTERY_BOOSTED.get(), 9)
                .unlockedBy(getHasName(TBlItems.LITHIUM_BATTERY.get()), has(TBlItems.LITHIUM_BATTERY.get()))
                .save(pRecipeOutput);

        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlItems.STEEL_INGOT.get())
                .requires(Items.IRON_INGOT)
                .requires(Items.COAL)
                .unlockedBy(getHasName(Items.COAL), has(Items.COAL))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_SWORD.get())
                .pattern("t")
                .pattern("t")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_KNIFE.get())
                .pattern("t")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_PICKAXE.get())
                .pattern("ttt")
                .pattern(" T ")
                .pattern(" T ")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_SHOVEL.get())
                .pattern("t")
                .pattern("T")
                .pattern("T")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_HOE.get())
                .pattern("tt")
                .pattern(" T")
                .pattern(" T")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_AXE.get())
                .pattern("tt")
                .pattern("tT")
                .pattern(" T")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_HAMMER.get())
                .pattern("ttt")
                .pattern("ttt")
                .pattern(" T ")
                .define('T', Items.STICK)
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_HELMET.get())
                .pattern("ttt")
                .pattern("t t")
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_CHESTPLATE.get())
                .pattern("t t")
                .pattern("ttt")
                .pattern("ttt")
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_LEGGINGS.get())
                .pattern("ttt")
                .pattern("t t")
                .pattern("t t")
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlItems.STEEL_BOOTS.get())
                .pattern("t t")
                .pattern("t t")
                .define(('t'), TBlItems.STEEL_INGOT.get())
                .unlockedBy(getHasName(TBlItems.STEEL_INGOT.get()), has(TBlItems.STEEL_INGOT.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlBlocks.ASH_WOOD.get(), 3)
                .pattern("tt")
                .pattern("tt")
                .define(('t'), TBlBlocks.ASH_LOG.get())
                .unlockedBy(getHasName(TBlBlocks.ASH_LOG.get()), has(TBlBlocks.ASH_LOG.get()))
                .save(pRecipeOutput);
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, TBlBlocks.ASH_PLANKS.get(), 4)
                .requires(TBlBlocks.ASH_LOG.get())
                .unlockedBy(getHasName(TBlBlocks.ASH_LOG.get()), has(TBlBlocks.ASH_LOG.get()))
                .save(pRecipeOutput);

        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, TBlBlocks.BOOSTING_TABLE.get(), 1)
                .pattern("dsd")
                .pattern("sts")
                .pattern("dsd")
                .define(('t'), Blocks.ENCHANTING_TABLE)
                .define(('d'), Items.DIAMOND)
                .define(('s'), TBlItems.ENCHANTITE_SHARD.get())
                .unlockedBy(getHasName(TBlItems.ENCHANTITE_SHARD.get()), has(TBlItems.ENCHANTITE_SHARD.get()))
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