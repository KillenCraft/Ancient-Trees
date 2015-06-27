package com.scottkillen.mod.dendrology.compat.storagedrawers;

import com.google.common.collect.Lists;
import com.jaquadro.minecraft.storagedrawers.api.IStorageDrawersApi;
import com.jaquadro.minecraft.storagedrawers.api.StorageDrawersApi;
import com.jaquadro.minecraft.storagedrawers.api.config.IAddonConfig;
import com.jaquadro.minecraft.storagedrawers.api.config.IBlockConfig;
import com.jaquadro.minecraft.storagedrawers.api.config.IUserConfig;
import com.jaquadro.minecraft.storagedrawers.api.pack.BlockConfiguration;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackBlockFactory;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackDataResolver;
import com.jaquadro.minecraft.storagedrawers.api.pack.StandardDataResolver;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.common.block.SlabBlock;
import com.scottkillen.mod.koresample.tree.block.WoodBlock;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.List;

public final class SDBlocks
{
    Block fullDrawers1 = null;
    Block fullDrawers2 = null;
    Block fullDrawers4 = null;
    Block halfDrawers2 = null;
    Block halfDrawers4 = null;
    private Block trim = null;

    private Object packResolver = null;

    public final CreativeTabs creativeTab = new CreativeTabs("storageDrawersAncientTrees")
    {
        @Override
        @SideOnly(Side.CLIENT)
        public Item getTabIconItem()
        {
            final IStorageDrawersApi api = getAPI();
            if (api == null) return Item.getItemFromBlock(Blocks.chest);

            final IBlockConfig config = api.userConfig().blockConfig();

            if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull2)))
                return Item.getItemFromBlock(fullDrawers2);
            if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull4)))
                return Item.getItemFromBlock(fullDrawers4);
            if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull1)))
                return Item.getItemFromBlock(fullDrawers1);
            if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicHalf2)))
                return Item.getItemFromBlock(halfDrawers2);
            if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicHalf4)))
                return Item.getItemFromBlock(halfDrawers4);

            return Item.getItemFromBlock(Blocks.chest);
        }

        @Override
        @SideOnly(Side.CLIENT)
        public int func_151243_f() { return 9; }
    };

    private static List<String> speciesNames()
    {
        final List<String> names = Lists.newArrayList();
        for (final OverworldTreeSpecies species : OverworldTreeSpecies.values())
            names.add(species.speciesName());
        return names;
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    void setup()
    {
        final IStorageDrawersApi api = getAPI();
        if (api == null) return;

        final IPackBlockFactory factory = api.packFactory();
        createBlocks(factory);

        final IUserConfig userConfig = api.userConfig();
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
            factory.hideBlock(getQualifiedName(trim));
        }
    }

    private void registerBlocks(IPackBlockFactory factory, IUserConfig userConfig)
    {
        final IBlockConfig config = userConfig.blockConfig();

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull1)))
            factory.registerBlock(fullDrawers1, "fullDrawers1");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull2)))
            factory.registerBlock(fullDrawers2, "fullDrawers2");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicFull4)))
            factory.registerBlock(fullDrawers4, "fullDrawers4");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicHalf2)))
            factory.registerBlock(halfDrawers2, "halfDrawers2");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.BasicHalf4)))
            factory.registerBlock(halfDrawers4, "halfDrawers4");

        if (config.isBlockEnabled(config.getBlockConfigName(BlockConfiguration.Trim)))
            factory.registerBlock(trim, "trim");
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void createBlocks(IPackBlockFactory factory)
    {
        final IPackDataResolver resolver = getResolver();

        fullDrawers1 = factory.createBlock(BlockConfiguration.BasicFull1, resolver).setCreativeTab(creativeTab);
        fullDrawers2 = factory.createBlock(BlockConfiguration.BasicFull2, resolver).setCreativeTab(creativeTab);
        fullDrawers4 = factory.createBlock(BlockConfiguration.BasicFull4, resolver).setCreativeTab(creativeTab);
        halfDrawers2 = factory.createBlock(BlockConfiguration.BasicHalf2, resolver).setCreativeTab(creativeTab);
        halfDrawers4 = factory.createBlock(BlockConfiguration.BasicHalf4, resolver).setCreativeTab(creativeTab);
        trim = factory.createBlock(BlockConfiguration.Trim, resolver).setCreativeTab(creativeTab);
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private static IStorageDrawersApi getAPI()
    {
        return StorageDrawersApi.instance();
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    IPackDataResolver getResolver()
    {
        if (packResolver == null) packResolver =
                new StandardDataResolver(TheMod.MOD_ID, speciesNames().toArray(new String[] {}),
                        TheMod.INSTANCE.creativeTab());
        return (IPackDataResolver) packResolver;
    }

    public static String getQualifiedName(Block block)
    {
        return GameData.getBlockRegistry().getNameForObject(block);
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    public void writeRecipes()
    {
        final IStorageDrawersApi api = getAPI();
        if (api == null) return;

        final IBlockConfig config = api.userConfig().blockConfig();

        int drawerMetadata = 0;
        for (final OverworldTreeSpecies species : OverworldTreeSpecies.values())
        {
            writeRecipesforSpecies(species, drawerMetadata, config);
            drawerMetadata++;
        }

        final ItemStack upgradeTemplate = new ItemStack(GameRegistry.findItem("StorageDrawers", "upgradeTemplate"), 2);

        if (config.isBlockEnabled("fulldrawers1")) GameRegistry
                .addRecipe(upgradeTemplate, "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers1, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("fulldrawers2")) GameRegistry
                .addRecipe(upgradeTemplate, "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers2, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("halfdrawers2")) GameRegistry
                .addRecipe(upgradeTemplate, "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(halfDrawers2, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("fulldrawers4")) GameRegistry
                .addRecipe(upgradeTemplate, "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers4, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("halfdrawers4")) GameRegistry
                .addRecipe(upgradeTemplate, "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(halfDrawers4, 1, OreDictionary.WILDCARD_VALUE));

    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    private void writeRecipesforSpecies(OverworldTreeSpecies species, int drawerMetadata, IBlockConfig config)
    {
        final WoodBlock planks = species.woodBlock();
        final int woodSubBlockIndex = species.woodSubBlockIndex();

        final String full1Name = config.getBlockConfigName(BlockConfiguration.BasicFull1);
        if (config.isBlockEnabled(full1Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers1, config.getBlockRecipeOutput(full1Name), drawerMetadata), "xxx",
                        " y ", "xxx", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);

        final String full2Name = config.getBlockConfigName(BlockConfiguration.BasicFull2);
        if (config.isBlockEnabled(full2Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers2, config.getBlockRecipeOutput(full2Name), drawerMetadata), "xyx",
                        "xxx", "xyx", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);

        final String full4Name = config.getBlockConfigName(BlockConfiguration.BasicFull4);
        if (config.isBlockEnabled(full4Name)) GameRegistry
                .addRecipe(new ItemStack(fullDrawers4, config.getBlockRecipeOutput(full4Name), drawerMetadata), "yxy",
                        "xxx", "yxy", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);

        final String trimName = config.getBlockConfigName(BlockConfiguration.Trim);
        if (config.isBlockEnabled(trimName)) GameRegistry
                .addRecipe(new ItemStack(trim, config.getBlockRecipeOutput(trimName), drawerMetadata), "xyx", "yyy",
                        "xyx", 'x', Items.stick, 'y', new ItemStack(planks, 1, woodSubBlockIndex));

        final SlabBlock recipeSlab = species.singleSlabBlock();
        final int slabSubBlockIndex = species.slabSubBlockIndex();

        final String half2Name = config.getBlockConfigName(BlockConfiguration.BasicHalf2);
        if (config.isBlockEnabled(half2Name)) GameRegistry
                .addRecipe(new ItemStack(halfDrawers2, config.getBlockRecipeOutput(half2Name), drawerMetadata), "xyx",
                        "xxx", "xyx", 'x', new ItemStack(recipeSlab, 1, slabSubBlockIndex), 'y', Blocks.chest);

        final String half4Name = config.getBlockConfigName(BlockConfiguration.BasicHalf4);
        if (config.isBlockEnabled(half4Name)) GameRegistry
                .addRecipe(new ItemStack(halfDrawers4, config.getBlockRecipeOutput(half4Name), drawerMetadata), "yxy",
                        "xxx", "yxy", 'x', new ItemStack(recipeSlab, 1, slabSubBlockIndex), 'y', Blocks.chest);
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    public void bindSortingBlocks(IPackBlockFactory factory, Block fullDrawers1, Block fullDrawers2, Block fullDrawers4,
                                  Block halfDrawers2, Block halfDrawers4)
    {
        factory.bindSortingBlock(this.fullDrawers1, fullDrawers1);
        factory.bindSortingBlock(this.fullDrawers2, fullDrawers2);
        factory.bindSortingBlock(this.fullDrawers4, fullDrawers4);
        factory.bindSortingBlock(this.halfDrawers2, halfDrawers2);
        factory.bindSortingBlock(this.halfDrawers4, halfDrawers4);
    }
}
