package com.scottkillen.mod.dendrology.compat.storagedrawers;

import com.google.common.base.Objects;
import com.jaquadro.minecraft.storagedrawers.api.IStorageDrawersApi;
import com.jaquadro.minecraft.storagedrawers.api.StorageDrawersApi;
import com.jaquadro.minecraft.storagedrawers.api.config.IAddonConfig;
import com.jaquadro.minecraft.storagedrawers.api.config.IBlockConfig;
import com.jaquadro.minecraft.storagedrawers.api.config.IIntegrationConfig;
import com.jaquadro.minecraft.storagedrawers.api.config.IUserConfig;
import com.jaquadro.minecraft.storagedrawers.api.pack.BlockConfiguration;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackBlockFactory;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackDataResolver;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;

public final class SDRefinedRelocationBlocks
{
    private Block fullDrawers1 = null;
    private Block fullDrawers2 = null;
    private Block fullDrawers4 = null;
    private Block halfDrawers2 = null;
    private Block halfDrawers4 = null;

    private static IStorageDrawersApi getAPI()
    {
        return StorageDrawersApi.instance();
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    void setup(SDBlocks blocks)
    {
        final IStorageDrawersApi api = getAPI();
        if (api == null) return;

        final IUserConfig userConfig = api.userConfig();
        final IIntegrationConfig config = userConfig.integrationConfig();
        if (!Loader.isModLoaded("RefinedRelocation") || !config.isRefinedRelocationEnabled()) return;

        final IPackBlockFactory factory = api.packFactory();
        createBlocks(blocks, factory);

        blocks.bindSortingBlocks(factory, fullDrawers1, fullDrawers2, fullDrawers4, halfDrawers2, halfDrawers4);

        registerBlocks(factory, userConfig);

        configureNEI(factory, userConfig);
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void configureNEI(IPackBlockFactory factory, IUserConfig userConfig)
    {
        final IAddonConfig config = userConfig.addonConfig();
        if (!config.showAddonItemsNEI())
        {
            factory.hideBlock(getQualifiedName(fullDrawers1));
            factory.hideBlock(getQualifiedName(fullDrawers2));
            factory.hideBlock(getQualifiedName(fullDrawers4));
            factory.hideBlock(getQualifiedName(halfDrawers2));
            factory.hideBlock(getQualifiedName(halfDrawers4));
        }
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void registerBlocks(IPackBlockFactory factory, IUserConfig userConfig)
    {
        final IBlockConfig config = userConfig.blockConfig();

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.SortingFull1)))
            factory.registerBlock(fullDrawers1, "fullDrawersSort1");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.SortingFull2)))
            factory.registerBlock(fullDrawers2, "fullDrawersSort2");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.SortingFull4)))
            factory.registerBlock(fullDrawers4, "fullDrawersSort4");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.SortingHalf2)))
            factory.registerBlock(halfDrawers2, "halfDrawersSort2");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.SortingHalf4)))
            factory.registerBlock(halfDrawers4, "halfDrawersSort4");
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void createBlocks(SDBlocks blocks, IPackBlockFactory factory)
    {
        final IPackDataResolver resolver = blocks.getResolver();

        fullDrawers1 = factory.createBlock(BlockConfiguration.SortingFull1, resolver);
        fullDrawers2 = factory.createBlock(BlockConfiguration.SortingFull2, resolver);
        fullDrawers4 = factory.createBlock(BlockConfiguration.SortingFull4, resolver);
        halfDrawers2 = factory.createBlock(BlockConfiguration.SortingHalf2, resolver);
        halfDrawers4 = factory.createBlock(BlockConfiguration.SortingHalf4, resolver);
    }

    public static String getQualifiedName(Block block)
    {
        return GameData.getBlockRegistry().getNameForObject(block);
    }


    @Method(modid = StorageDrawersMod.MOD_ID)
    public void writeRecipes(SDBlocks blocks)
    {
        final IStorageDrawersApi api = getAPI();
        if (api == null) return;

        final IUserConfig userConfig = api.userConfig();
        final IIntegrationConfig integrationConfig = userConfig.integrationConfig();
        if (!Loader.isModLoaded("RefinedRelocation") || !integrationConfig.isRefinedRelocationEnabled()) return;

        final IBlockConfig config = api.userConfig().blockConfig();
        final IPackDataResolver resolver = blocks.getResolver();
        for (int drawerMetadata = 0; drawerMetadata < 16; drawerMetadata++)
            if (resolver.isValidMetaValue(drawerMetadata)) writeRecipesSet(blocks, drawerMetadata, config);
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void writeRecipesSet(SDBlocks blocks, int drawerMetadata, IBlockConfig config)
    {
        final String full1Name = config.getBlockConfigName(BlockConfiguration.SortingFull1);
        if (config.isBlockEnabled(full1Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers1, config.getBlockRecipeOutput(full1Name), drawerMetadata), "x x",
                        " y ", "x x", 'x', Items.gold_nugget, 'y',
                        new ItemStack(blocks.fullDrawers1, 1, drawerMetadata));

        final String full2Name = config.getBlockConfigName(BlockConfiguration.SortingFull2);
        if (config.isBlockEnabled(full2Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers2, config.getBlockRecipeOutput(full2Name), drawerMetadata), "x x",
                        " y ", "x x", 'x', Items.gold_nugget, 'y',
                        new ItemStack(blocks.fullDrawers2, 1, drawerMetadata));

        final String full4Name = config.getBlockConfigName(BlockConfiguration.SortingFull4);
        if (config.isBlockEnabled(full4Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers4, config.getBlockRecipeOutput(full4Name), drawerMetadata), "x x",
                        " y ", "x x", 'x', Items.gold_nugget, 'y',
                        new ItemStack(blocks.fullDrawers4, 1, drawerMetadata));

        final String half2Name = config.getBlockConfigName(BlockConfiguration.SortingHalf2);
        if (config.isBlockEnabled(half2Name)) GameRegistry
                .addRecipe(new ItemStack(halfDrawers2, config.getBlockRecipeOutput(half2Name), drawerMetadata), "x x",
                        " y ", "x x", 'x', Items.gold_nugget, 'y',
                        new ItemStack(blocks.halfDrawers2, 1, drawerMetadata));

        final String half4Name = config.getBlockConfigName(BlockConfiguration.SortingHalf4);
        if (config.isBlockEnabled(half4Name)) GameRegistry
                .addRecipe(new ItemStack(halfDrawers4, config.getBlockRecipeOutput(half4Name), drawerMetadata), "x x",
                        " y ", "x x", 'x', Items.gold_nugget, 'y',
                        new ItemStack(blocks.halfDrawers4, 1, drawerMetadata));
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("fullDrawers1", fullDrawers1).add("fullDrawers2", fullDrawers2)
                .add("fullDrawers4", fullDrawers4).add("halfDrawers2", halfDrawers2).add("halfDrawers4", halfDrawers4)
                .toString();
    }
}
