package com.scottkillen.mod.dendrology.content.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.kore.tree.DefinesTree;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.CraftingManager;

public enum Recipes
{
    ;

    public static void init()
    {
        initLogRecipes();
        initPlankRecipes();
        initSaplingRecipes();
    }

    private static void initSaplingRecipes()
    {
        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 9),
                new ItemStack(OverworldTreeSpecies.CERASU.getSaplingBlock(), 1,
                        OverworldTreeSpecies.CERASU.getSaplingMeta()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 10),
                new ItemStack(OverworldTreeSpecies.EWCALY.getSaplingBlock(), 1,
                        OverworldTreeSpecies.EWCALY.getSaplingMeta()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 5),
                new ItemStack(OverworldTreeSpecies.PORFFOR.getSaplingBlock(), 1,
                        OverworldTreeSpecies.PORFFOR.getSaplingMeta()));

        CraftingManager.getInstance().addShapelessRecipe(new ItemStack(Items.dye, 1, 14),
                new ItemStack(OverworldTreeSpecies.ACEMUS.getSaplingBlock(), 1,
                        OverworldTreeSpecies.ACEMUS.getSaplingMeta()));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initLogRecipes()
    {
        for (final DefinesTree tree : ModBlocks.getContent())
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
        for (final DefinesTree tree : ModBlocks.getContent())
            CraftingManager.getInstance().addRecipe(new ItemStack(tree.getStairsBlock(), 4), "#  ", "## ", "###", '#',
                    new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta()));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    private static void initPlankSlabRecipes()
    {
        for (final DefinesTree tree : ModBlocks.getContent())
            CraftingManager.getInstance()
                    .addRecipe(new ItemStack(tree.getSingleSlabBlock(), 6, tree.getSlabMeta()), "###", '#',
                            new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta()));
    }
}
