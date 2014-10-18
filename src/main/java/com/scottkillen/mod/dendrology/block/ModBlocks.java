package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.item.LogItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

public class ModBlocks
{
    static final int DEFAULT_FIRE_ENCOURAGEMENT = 5;
    static final int DEFAULT_FLAMMABILITY = 5;

    private static final ImmutableList<String> LOG0_NAMES = ImmutableList.of("cedar", "ironwood", "eucalyptus", "beech");
    private static final ImmutableList<String> LOG1_NAMES = ImmutableList.of("maple", "palm", "walnut", "ginkgo");
    private static final ImmutableList<String> LOG2_NAMES = ImmutableList.of("poplar", "cherry", "willow");

    public static Block logs0 = new ModLogBlock(LOG0_NAMES);
    public static Block logs1 = new ModLogBlock(LOG1_NAMES);
    public static Block logs2 = new ModLogBlock(LOG2_NAMES);

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        registerLogBlock(logs0, "logs0", LOG0_NAMES);
        registerLogBlock(logs1, "logs1", LOG1_NAMES);
        registerLogBlock(logs2, "logs2", LOG2_NAMES);
    }

    private static void registerLogBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, LogItem.class, name, block, subblockNames.toArray(new String[0]));
        Blocks.fire.setFireInfo(block, DEFAULT_FIRE_ENCOURAGEMENT, DEFAULT_FLAMMABILITY);
    }
}
