package com.scottkillen.mod.dendrology.compat.minechem;

import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.util.log.Logger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Method;
import minechem.api.RecipeAPI;
import net.minecraft.item.ItemStack;

public enum MinechemMod
{
    ;
    private static final String MINECHEM = "minechem";

    public static void integrate()
    {
        if (Loader.isModLoaded(MINECHEM))
        {
            addDecomposerRecipes();
        } else Logger.info("Minechem mod not present. Integration skipped.");
    }

    @Method(modid = MINECHEM)
    private static void addDecomposerRecipes()
    {
        Logger.info("Adding Minechem decomposer recipes.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack planks = new ItemStack(tree.getPlanksBlock(), 1, tree.getPlanksMeta());
            if (RecipeAPI.addDecompositionRecipe(planks, "2 cellulose"))
                Logger.info("Added Minechem decomposer recipe for %s planks.", tree.getName());

            //noinspection ObjectAllocationInLoop
            final ItemStack slab = new ItemStack(tree.getSingleSlabBlock(), 1, tree.getSlabMeta());
            if (RecipeAPI.addDecompositionRecipe(slab, "2 cellulose"))
                Logger.info("Added Minechem decomposer recipe for %s slab.", tree.getName());

            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.getSaplingBlock(), 1, tree.getSaplingMeta());
            if (RecipeAPI.addDecompositionRecipe(sapling, "cellulose"))
                Logger.info("Added Minechem decomposer recipe for %s sapling.", tree.getName());

            //noinspection ObjectAllocationInLoop
            final ItemStack log = new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta());
            if (RecipeAPI.addDecompositionRecipe(log, "8 cellulose"))
                Logger.info("Added Minechem decomposer recipe for %s wood.", tree.getName());

            //noinspection ObjectAllocationInLoop
            final ItemStack leaves = new ItemStack(tree.getLeavesBlock(), 1, tree.getLeavesMeta());
            if (RecipeAPI.addDecompositionRecipe(leaves, "4 cellulose"))
                Logger.info("Added Minechem decomposer recipe for %s wood.", tree.getName());
        }
    }
}
