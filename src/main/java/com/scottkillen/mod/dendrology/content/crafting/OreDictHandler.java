package com.scottkillen.mod.dendrology.content.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public enum OreDictHandler
{
    ;

    @SuppressWarnings("MethodWithMultipleLoops")
    public static void init()
    {
        for (final Block block : ModBlocks.getLogBlocks())
            registerWildcardOre(block, "logWood");

        for (final Block block : ModBlocks.getPlanksBlocks())
            registerWildcardOre(block, "plankWood");

        for (final Block block : ModBlocks.getSingleSlabBlocks())
            registerWildcardOre(block, "slabWood");

        for (final Block block : ModBlocks.getStairsBlocks())
            OreDictionary.registerOre("stairWood", block);

        for (final Block block : ModBlocks.getSaplingBlocks())
            registerWildcardOre(block, "treeSapling");

        for (final Block block : ModBlocks.getLeavesBlocks())
            registerWildcardOre(block, "treeLeaves");
    }

    private static void registerWildcardOre(Block block, String name)
    {
        OreDictionary.registerOre(name, new ItemStack(block, 1, WILDCARD_VALUE));
    }
}
