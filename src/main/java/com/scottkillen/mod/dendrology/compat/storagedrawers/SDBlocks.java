package com.scottkillen.mod.dendrology.compat.storagedrawers;

import com.google.common.collect.Lists;
import com.jaquadro.minecraft.storagedrawers.StorageDrawers;
import com.jaquadro.minecraft.storagedrawers.api.StorageDrawersApi;
import com.jaquadro.minecraft.storagedrawers.api.pack.BlockConfiguration;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackBlockFactory;
import com.jaquadro.minecraft.storagedrawers.api.pack.IPackDataResolver;
import com.jaquadro.minecraft.storagedrawers.api.pack.StandardDataResolver;
import com.jaquadro.minecraft.storagedrawers.config.ConfigManager;
import com.jaquadro.minecraft.storagedrawers.core.ModItems;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.common.block.SlabBlock;
import com.scottkillen.mod.koresample.tree.block.WoodBlock;
import cpw.mods.fml.common.Optional.Method;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;
import java.util.List;

public final class SDBlocks
{
    private Block fullDrawers1;
    private Block fullDrawers2;
    private Block fullDrawers4;
    private Block halfDrawers2;
    private Block halfDrawers4;
    private Block trim;

    private static List<String> speciesNames()
    {
        final List<String> names = Lists.newArrayList();
        for (OverworldTreeSpecies species : OverworldTreeSpecies.values())
            names.add(species.speciesName());
        return names;
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    void init()
    {
        final IPackBlockFactory factory = StorageDrawersApi.instance().packFactory();
        IPackDataResolver resolver = new StandardDataResolver(TheMod.MOD_ID, speciesNames().toArray(new String[] {}),
                TheMod.INSTANCE.creativeTab());

        ConfigManager config = (ConfigManager) StorageDrawers.config;

        fullDrawers1 = factory.createBlock(BlockConfiguration.BasicFull1, resolver);
        fullDrawers2 = factory.createBlock(BlockConfiguration.BasicFull2, resolver);
        fullDrawers4 = factory.createBlock(BlockConfiguration.BasicFull4, resolver);
        halfDrawers2 = factory.createBlock(BlockConfiguration.BasicHalf2, resolver);
        halfDrawers4 = factory.createBlock(BlockConfiguration.BasicHalf4, resolver);
        trim = factory.createBlock(BlockConfiguration.Trim, resolver);

        if (config.isBlockEnabled("fulldrawers1")) factory.registerBlock(fullDrawers1, "fullDrawers1");

        if (config.isBlockEnabled("fulldrawers2")) factory.registerBlock(fullDrawers2, "fullDrawers2");

        if (config.isBlockEnabled("fulldrawers4")) factory.registerBlock(fullDrawers4, "fullDrawers4");

        if (config.isBlockEnabled("halfdrawers2")) factory.registerBlock(halfDrawers2, "halfDrawers2");

        if (config.isBlockEnabled("halfdrawers4")) factory.registerBlock(halfDrawers4, "halfDrawers4");

        if (config.isBlockEnabled("trim")) factory.registerBlock(trim, "trim");
    }

    @Method(modid = StorageDrawersMod.MOD_ID)
    public void writeRecipes()
    {
        ConfigManager config = (ConfigManager) StorageDrawers.config;

        int i = 0;
        for (OverworldTreeSpecies species : OverworldTreeSpecies.values())
        {
            final WoodBlock planks = species.woodBlock();
            final int woodSubBlockIndex = species.woodSubBlockIndex();

            if (config.isBlockEnabled("fulldrawers1"))
            {
                GameRegistry
                        .addRecipe(new ItemStack(fullDrawers1, config.getBlockRecipeOutput("fulldrawers1"), i), "xxx",
                                " y ", "xxx", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);
            }

            if (config.isBlockEnabled("fulldrawers2")) GameRegistry
                    .addRecipe(new ItemStack(fullDrawers2, config.getBlockRecipeOutput("fulldrawers2"), i), "xyx",
                            "xxx", "xyx", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);
            if (config.isBlockEnabled("fulldrawers4")) GameRegistry
                    .addRecipe(new ItemStack(fullDrawers4, config.getBlockRecipeOutput("fulldrawers4"), i), "yxy",
                            "xxx", "yxy", 'x', new ItemStack(planks, 1, woodSubBlockIndex), 'y', Blocks.chest);
            if (config.isBlockEnabled("trim"))
            {
                GameRegistry.addRecipe(new ItemStack(trim, config.getBlockRecipeOutput("trim"), i), "xyx", "yyy", "xyx",
                        'x', Items.stick, 'y', new ItemStack(planks, 1, woodSubBlockIndex));
            }

            final SlabBlock recipeSlab = species.singleSlabBlock();
            final int slabSubBlockIndex = species.slabSubBlockIndex();

            if (config.isBlockEnabled("halfdrawers2")) GameRegistry
                    .addRecipe(new ItemStack(halfDrawers2, config.getBlockRecipeOutput("halfdrawers2"), i), "xyx",
                            "xxx", "xyx", 'x', new ItemStack(recipeSlab, 1, slabSubBlockIndex), 'y', Blocks.chest);
            if (config.isBlockEnabled("halfdrawers4")) GameRegistry
                    .addRecipe(new ItemStack(halfDrawers4, config.getBlockRecipeOutput("halfdrawers4"), i), "yxy",
                            "xxx", "yxy", 'x', new ItemStack(recipeSlab, 1, slabSubBlockIndex), 'y', Blocks.chest);
        }

        if (config.isBlockEnabled("fulldrawers1")) GameRegistry
                .addRecipe(new ItemStack(ModItems.upgradeTemplate, 2), "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers1, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("fulldrawers2")) GameRegistry
                .addRecipe(new ItemStack(ModItems.upgradeTemplate, 2), "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers2, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("halfdrawers2")) GameRegistry
                .addRecipe(new ItemStack(ModItems.upgradeTemplate, 2), "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(halfDrawers2, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("fulldrawers4")) GameRegistry
                .addRecipe(new ItemStack(ModItems.upgradeTemplate, 2), "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(fullDrawers4, 1, OreDictionary.WILDCARD_VALUE));
        if (config.isBlockEnabled("halfdrawers4")) GameRegistry
                .addRecipe(new ItemStack(ModItems.upgradeTemplate, 2), "xxx", "xyx", "xxx", 'x', Items.stick, 'y',
                        new ItemStack(halfDrawers4, 1, OreDictionary.WILDCARD_VALUE));

    }
}
