package com.scottkillen.mod.dendrology.compat.forestry;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.registry.GameRegistry;
import forestry.api.core.ForestryAPI;
import forestry.api.recipes.RecipeManagers;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

public final class ForestryMod extends Integrator
{
    private static final String MOD_ID = "Forestry";
    private static final String MOD_NAME = MOD_ID;

    private static void addBackpackItem(String backpack, ItemStack stack)
    {
        if (stack == null) return;

        addBackpackItem(backpack, stack.getItem(), stack.getItemDamage());
    }

    private static void addBackpackItem(String backpack, Item item, int damage)
    {
        sendBackpackMessage(String.format("%s@%s:%d", backpack, GameRegistry.findUniqueIdentifierFor(item), damage));
    }

    private static void addBackpackItems()
    {
        TheMod.logger().info("Extending Forestry's backpacks.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            addBackpackItem("forester", sapling);

            //noinspection ObjectAllocationInLoop
            final ItemStack log = new ItemStack(tree.logBlock(), 1, tree.logSubBlockIndex());
            addBackpackItem("forester", log);

            //noinspection ObjectAllocationInLoop
            final ItemStack leaves = new ItemStack(tree.leavesBlock(), 1, tree.leavesSubBlockIndex());
            addBackpackItem("forester", leaves);

            //noinspection ObjectAllocationInLoop
            final ItemStack stairs = new ItemStack(tree.stairsBlock());
            addBackpackItem("builder", stairs);
        }
    }

    private static void addFarmables()
    {
        TheMod.logger().info("Adding farmable saplings to Forestry's farms.");
        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            //noinspection ObjectAllocationInLoop
            final ItemStack sapling = new ItemStack(tree.saplingBlock(), 1, tree.saplingSubBlockIndex());
            addFarmableSapling(sapling);
        }
    }

    private static void addFarmableSapling(ItemStack stack)
    {
        if (stack == null) return;

        addFarmableSapling(stack.getItem(), stack.getItemDamage());
    }

    private static void addFarmableSapling(Item item, int damage)
    {
        sendFarmableMessage(String.format("farmArboreal@%s:%d", GameRegistry.findUniqueIdentifierFor(item), damage));
    }

    @Method(modid = MOD_ID)
    private static void addSaplingRecipes()
    {
        TheMod.logger().info("Adding sapling recipes to Forestry's fermenter.");

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

    private static void sendBackpackMessage(String message)
    {
        FMLInterModComms.sendMessage("Forestry", "add-backpack-items", message);
    }

    private static void sendFarmableMessage(String message)
    {
        FMLInterModComms.sendMessage("Forestry", "add-farmable-sapling", message);
    }

    private static FluidStack fluidStack(String fluidName) { return FluidRegistry.getFluidStack(fluidName, 1000); }

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
        if (Loader.isModLoaded(MOD_ID) && Settings.INSTANCE.integrateForestry())
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
    protected String modID() { return MOD_ID; }

    @Override
    protected String modName() { return MOD_NAME; }
}
