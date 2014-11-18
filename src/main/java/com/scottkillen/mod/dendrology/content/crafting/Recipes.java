package com.scottkillen.mod.dendrology.content.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.content.ISpecies;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public enum Recipes
{
    ;

    public static void init()
    {
        initLogRecipes();
        initPlankRecipes();
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initLogRecipes()
    {
        for (final ISpecies tree : ModBlocks.getContent())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getPlanksBlock(), 4, tree.getPlanksMeta()), "#", '#',
                            new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta()));
    }

    private static void initPlankRecipes()
    {
        initPlankStairsRecipes();
        initPlankSlabRecipes();
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initPlankStairsRecipes()
    {
        for (final ISpecies tree : ModBlocks.getContent())
            CraftingManager.getInstance().addRecipe(new ItemStack(tree.getStairsBlock(), 4), "#  ", "## ", "###", '#',
                    new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta()));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initPlankSlabRecipes()
    {
        for (final ISpecies tree : ModBlocks.getContent())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getSingleSlabBlock(), 6, tree.getSlabMeta()), "###", '#',
                            new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta()));
    }
}
