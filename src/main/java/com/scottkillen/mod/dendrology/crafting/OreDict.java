package com.scottkillen.mod.dendrology.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public enum OreDict
{
    ;

    public static void registerOres()
    {
        for (final Block block : ModBlocks.logs)
            OreDictionary.registerOre("logWood", new ItemStack(block, 1, WILDCARD_VALUE));

        for (final Block block : ModBlocks.saplings)
            OreDictionary.registerOre("treeSapling", new ItemStack(block, 1, WILDCARD_VALUE));

        for (final Block block : ModBlocks.leaves)
            OreDictionary.registerOre("treeLeaves", new ItemStack(block, 1, WILDCARD_VALUE));
    }
}
