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
    }

    private static void initLogRecipes()
    {
        for (TreeContent tree : TreeContent.values())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getPlanksBlock(), 4, tree.getPlanksMeta()), "#", '#',
                            new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta()));
    }
}
