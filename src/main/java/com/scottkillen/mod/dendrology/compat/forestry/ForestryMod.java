package com.scottkillen.mod.dendrology.compat.forestry;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.kore.common.util.log.Logger;
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

public enum ForestryMod
{
    ;

    private static final String MOD_ID = "Forestry";
    private static final Logger logger = Logger.forMod(TheMod.MOD_ID);

    private static final int FORESTER = 2;
    private static final int BUILDER = 5;

    public static void integrate(ModState modState)
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
        } else logger.info("Forestry mod not present. %s state integration skipped.", modState);
    }

    private static void init()
    {
        addFarmables();
    }

    private static void postInit()
    {
        addBackpackItems();
        addSaplingRecipes();
    }

    @Method(modid = MOD_ID)
    private static void addBackpackItems()
    {
        logger.info("Extending Forestry's backpacks.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.getSaplingBlock(), 1, tree.getSaplingMeta());
            BackpackManager.backpackItems[FORESTER].add(sapling);

            //noinspection ObjectAllocationInLoop
            final ItemStack log = new ItemStack(tree.getLogBlock(), 1, tree.getLogMeta());
            BackpackManager.backpackItems[FORESTER].add(log);

            //noinspection ObjectAllocationInLoop
            final ItemStack leaves = new ItemStack(tree.getLeavesBlock(), 1, tree.getLeavesMeta());
            BackpackManager.backpackItems[FORESTER].add(leaves);

            //noinspection ObjectAllocationInLoop
            final ItemStack stairs = new ItemStack(tree.getStairsBlock());
            BackpackManager.backpackItems[BUILDER].add(stairs);
        }
    }

    @Method(modid = MOD_ID)
    private static void addFarmables()
    {
        logger.info("Adding farmable saplings to Forestry's farms.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.getSaplingBlock(), 1, tree.getSaplingMeta());
            FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", String.format("farmArboreal@%s.%s",
                    GameData.getBlockRegistry().getNameForObject(Block.getBlockFromItem(sapling.getItem())),
                    sapling.getItemDamage()));
        }
    }

    @Method(modid = MOD_ID)
    private static void addSaplingRecipes()
    {
        logger.info("Adding sapling recipes to Forestry's fermenter.");

        final int fermentationValue = ForestryAPI.activeMode.getIntegerSetting("fermenter.yield.sapling");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.getSaplingBlock(), 1, tree.getSaplingMeta());
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.0f, getFluidStack("biomass"), getFluidStack("water"));
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.5f, getFluidStack("biomass"), getFluidStack("juice"));
            RecipeManagers.fermenterManager
                    .addRecipe(sapling, fermentationValue, 1.5f, getFluidStack("biomass"), getFluidStack("honey"));
        }
    }

    private static FluidStack getFluidStack(String amount)
    {
        return FluidRegistry.getFluidStack(amount, 1000);
    }

}
