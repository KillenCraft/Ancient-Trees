package com.scottkillen.mod.dendrology.compat.minechem;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import minechem.api.RecipeAPI;
import net.minecraft.item.ItemStack;

public final class MinechemMod extends Integrator
{
    private static final String MOD_ID = "minechem";
    private static final String MOD_NAME = "Minechem";

    private static void reportProgress(String species, String recipeType)
    {
        TheMod.logger().info("Added Minechem decomposer recipe for %s %s.", species, recipeType);
    }

    @Method(modid = MOD_ID)
    private static void addDecomposerRecipes()
    {
        TheMod.logger().info("Adding Minechem decomposer recipes.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            final String speciesName = tree.speciesName();

            //noinspection ObjectAllocationInLoop
            final ItemStack planks = new ItemStack(tree.woodBlock(), 1, tree.woodSubBlockIndex());
            if (RecipeAPI.addDecompositionRecipe(planks, "2 cellulose")) reportProgress(speciesName, "planks");

            //noinspection ObjectAllocationInLoop
            final ItemStack slab = new ItemStack(tree.singleSlabBlock(), 1, tree.slabSubBlockIndex());
            if (RecipeAPI.addDecompositionRecipe(slab, "2 cellulose")) reportProgress(speciesName, "slab");

            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            if (RecipeAPI.addDecompositionRecipe(sapling, "cellulose")) reportProgress(speciesName, "sapling");

            //noinspection ObjectAllocationInLoop
            final ItemStack log = new ItemStack(tree.logBlock(), 1, tree.logSubBlockIndex());
            if (RecipeAPI.addDecompositionRecipe(log, "8 cellulose")) reportProgress(speciesName, "log");

            //noinspection ObjectAllocationInLoop
            final ItemStack leaves = new ItemStack(tree.leavesBlock(), 1, tree.leavesSubBlockIndex());
            if (RecipeAPI.addDecompositionRecipe(leaves, "4 cellulose")) reportProgress(speciesName, "leaves");
        }
    }

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID)  && Settings.INSTANCE.integrateMinechem() && modState == ModState.POSTINITIALIZED)
            addDecomposerRecipes();
    }

    @Override
    protected String modID() { return MOD_ID; }

    @Override
    protected String modName() { return MOD_NAME; }
}
