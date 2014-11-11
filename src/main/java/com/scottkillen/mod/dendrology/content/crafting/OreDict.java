package com.scottkillen.mod.dendrology.content.crafting;

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
        for (final Block block : ModBlocks.LEAVES_BLOCKS)
            registerWildcardOre(block, "treeLeaves");

        for (final Block block : ModBlocks.LOG_BLOCKS)
            registerWildcardOre(block, "logWood");

        for (final Block block : ModBlocks.PLANKS_BLOCKS)
            registerWildcardOre(block, "plankWood");

        for (final Block block : ModBlocks.SAPLING_BLOCKS)
            registerWildcardOre(block, "treeSapling");
    }

    private static void registerWildcardOre(Block block, String name)
    {
        OreDictionary.registerOre(name, new ItemStack(block, 1, WILDCARD_VALUE));
    }
}
