package com.scottkillen.mod.dendrology.crafting;

import com.scottkillen.mod.dendrology.reference.Tree;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public enum Recipes
{
    ;

    public static void init()
    {
        initLogRecipes();
    }

    private static void initLogRecipes()
    {
        for (Tree tree : Tree.values())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getPlanksBlock(), 4, tree.getPlanksMeta()), "#", '#',
                            new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta()));
    }
}
