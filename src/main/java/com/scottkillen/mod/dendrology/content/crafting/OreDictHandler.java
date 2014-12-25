package com.scottkillen.mod.dendrology.content.crafting;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

@SuppressWarnings("MethodMayBeStatic")
public final class OreDictHandler
{
    @SuppressWarnings("MethodWithMultipleLoops")
    public void registerBlocksWithOreDictinary()
    {
        for (final Block block : ModBlocks.logBlocks())
            registerWildcardOre(block, "logWood");

        for (final Block block : ModBlocks.woodBlocks())
            registerWildcardOre(block, "plankWood");

        for (final Block block : ModBlocks.singleSlabBlocks())
            registerWildcardOre(block, "slabWood");

        for (final Block block : ModBlocks.stairsBlocks())
            OreDictionary.registerOre("stairWood", block);

        for (final Block block : ModBlocks.saplingBlocks())
            registerWildcardOre(block, "treeSapling");

        for (final Block block : ModBlocks.leavesBlocks())
            registerWildcardOre(block, "treeLeaves");
    }

    private void registerWildcardOre(Block block, String name)
    {
        OreDictionary.registerOre(name, new ItemStack(block, 1, WILDCARD_VALUE));
    }
}
