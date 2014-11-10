package com.scottkillen.mod.dendrology.crafting;

import com.scottkillen.mod.dendrology.content.TreeContent;
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
        for (final TreeContent tree : TreeContent.values())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getPlanksBlock(), 4, tree.getPlanksMeta()), "#", '#',
                            new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta()));
    }

    private static void initPlankRecipes()
    {
        initPlankStairsRecipes();
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initPlankStairsRecipes()
    {
        for (final TreeContent tree : TreeContent.values())
            CraftingManager.getInstance().addRecipe(new ItemStack(tree.getStairsBlock(), 4), "#  ", "## ", "###", '#',
                    new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta()));
    }
}
