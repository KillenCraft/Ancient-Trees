package com.scottkillen.mod.dendrology.compat.forestry;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.common.util.log.Logger;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameData;
import forestry.api.core.ForestryAPI;
import forestry.api.recipes.RecipeManagers;
import forestry.api.storage.BackpackManager;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public final class ForestryMod extends Integrator
{
    private static final String MOD_ID = "Forestry";
    private static final String MOD_NAME = MOD_ID;

    private static final int FORESTER = 2;
    private static final int BUILDER = 5;

    @Method(modid = MOD_ID)
    private static void addBackpackItems()
    {
        Logger.forMod(TheMod.INSTANCE.modID()).info("Extending Forestry's backpacks.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            BackpackManager.backpackItems[FORESTER].add(sapling);

            //noinspection ObjectAllocationInLoop
            final ItemStack log = new ItemStack(tree.logBlock(), 1, tree.logSubBlockIndex());
            BackpackManager.backpackItems[FORESTER].add(log);

            //noinspection ObjectAllocationInLoop
            final ItemStack leaves = new ItemStack(tree.leavesBlock(), 1, tree.leavesSubBlockIndex());
            BackpackManager.backpackItems[FORESTER].add(leaves);

            //noinspection ObjectAllocationInLoop
            final ItemStack stairs = new ItemStack(tree.stairsBlock());
            BackpackManager.backpackItems[BUILDER].add(stairs);
        }
    }

    @Method(modid = MOD_ID)
    private static void addFarmables()
    {
        Logger.forMod(TheMod.INSTANCE.modID()).info("Adding farmable saplings to Forestry's farms.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", String.format("farmArboreal@%s.%s",
                    GameData.getBlockRegistry().getNameForObject(Block.getBlockFromItem(sapling.getItem())),
                    sapling.getItemDamage()));
        }
    }

    @Method(modid = MOD_ID)
    private static void addSaplingRecipes()
    {
        Logger.forMod(TheMod.INSTANCE.modID()).info("Adding sapling recipes to Forestry's fermenter.");

        final int fermentationValue = ForestryAPI.activeMode.getIntegerSetting("fermenter.yield.sapling");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.0f, fluidStack("biomass"), fluidStack("water"));
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.5f, fluidStack("biomass"), fluidStack("juice"));
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.5f, fluidStack("biomass"), fluidStack("honey"));
        }
    }

    @Method(modid = MOD_ID)
    private static FluidStack fluidStack(String fluidName) { return FluidRegistry.getFluidStack(fluidName, 1000); }

    @Method(modid = MOD_ID)
    private static void init() { addFarmables(); }

    @Method(modid = MOD_ID)
    private static void postInit()
    {
        addBackpackItems();
        addSaplingRecipes();
    }

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID))
        {
            switch (modState)
            {
                case INITIALIZED:
                    init();
                    break;
                case POSTINITIALIZED:
                    postInit();
                    break;
                default:
            }
        }
    }

    @Override
    protected String modID()
    {
        return MOD_ID;
    }

    @Override
    protected String modName()
    {
        return MOD_NAME;
    }
}
