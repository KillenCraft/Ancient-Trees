package com.scottkillen.mod.dendrology.content.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public enum OreDict
{
    ;

    @SuppressWarnings("MethodWithMultipleLoops")
    public static void registerOres()
    {
        for (final Block block : ModBlocks.LOG_BLOCKS)
            registerWildcardOre(block, "logWood");

        for (final Block block : ModBlocks.PLANKS_BLOCKS)
            registerWildcardOre(block, "plankWood");

        for (final Block block : ModBlocks.SINGLE_SLAB_BLOCKS)
            registerWildcardOre(block, "slabWood");

        for (final Block block : ModBlocks.STAIRS_BLOCKS)
            OreDictionary.registerOre("stairWood", block);

        for (final Block block : ModBlocks.SAPLING_BLOCKS)
            registerWildcardOre(block, "treeSapling");

        for (final Block block : ModBlocks.LEAVES_BLOCKS)
            registerWildcardOre(block, "treeLeaves");
    }

    private static void registerWildcardOre(Block block, String name)
    {
        OreDictionary.registerOre(name, new ItemStack(block, 1, WILDCARD_VALUE));
    }
}
