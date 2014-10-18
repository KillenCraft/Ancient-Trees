package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.item.LeavesItem;
import com.scottkillen.mod.dendrology.item.LogItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemLeaves;

import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.*;

public class ModBlocks
{
    static final int DEFAULT_LEAVES_FIRE_ENCOURAGEMENT = 30;
    static final int DEFAULT_LOG_FIRE_ENCOURAGEMENT = 5;
    static final int DEFAULT_LEAVES_FLAMMABILITY = 60;
    static final int DEFAULT_LOG_FLAMMABILITY = 5;

    private static final ImmutableList<String> LOG0_NAMES = ImmutableList.of("beech", "cedar", "cherry", "cypress");
    private static final ImmutableList<String> LOG1_NAMES = ImmutableList.of("eucalyptus", "ginkgo", "ironwood", "maple");
    private static final ImmutableList<String> LOG2_NAMES = ImmutableList.of("palm", "poplar", "walnut", "willow");
    private static final ImmutableList<String> LEAVES0_NAMES = ImmutableList.of("beech", "cedar", "cypress", "eucalyptus");
    private static final ImmutableList<String> LEAVES1_NAMES = ImmutableList.of("ironwood", "ginkgo", "palm", "poplar");
    private static final ImmutableList<String> LEAVES2_NAMES = ImmutableList.of("cherry.pink", "cherry.white", "maple.red", "maple.yellow");
    private static final ImmutableList<String> LEAVES3_NAMES = ImmutableList.of("ginkgo.yellow", "walnut", "willow");
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES0_COLORS = ImmutableList.of(BASIC, PINE, PINE, BIRCH);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES1_COLORS = ImmutableList.of(BASIC, BASIC, PINE, BASIC);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES2_COLORS = ImmutableList.of(NONE, NONE, NONE, NONE);
    private static final ImmutableList<ModLeavesBlock.Colorizer> LEAVES3_COLORS = ImmutableList.of(NONE, BASIC, BIRCH);

    public static Block logs0 = new ModLogBlock(LOG0_NAMES);
    public static Block logs1 = new ModLogBlock(LOG1_NAMES);
    public static Block logs2 = new ModLogBlock(LOG2_NAMES);
    public static Block leaves0 = new ModLeavesBlock(LEAVES0_NAMES, LEAVES0_COLORS);
    public static Block leaves1 = new ModLeavesBlock(LEAVES1_NAMES, LEAVES1_COLORS);
    public static Block leaves2 = new ModLeavesBlock(LEAVES2_NAMES, LEAVES2_COLORS);
    public static Block leaves3 = new ModLeavesBlock(LEAVES3_NAMES, LEAVES3_COLORS);

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        registerLogBlock(logs0, "logs0", LOG0_NAMES);
        registerLogBlock(logs1, "logs1", LOG1_NAMES);
        registerLogBlock(logs2, "logs2", LOG2_NAMES);
        registerLeavesBlock(leaves0, "leaves0");
        registerLeavesBlock(leaves1, "leaves1");
        registerLeavesBlock(leaves2, "leaves2");
        registerLeavesBlock(leaves3, "leaves3");
    }

    private static void registerLeavesBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, LeavesItem.class, name);
        Blocks.fire.setFireInfo(block, DEFAULT_LEAVES_FIRE_ENCOURAGEMENT, DEFAULT_LEAVES_FLAMMABILITY);
    }

    private static void registerLogBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, LogItem.class, name, block, subblockNames.toArray(new String[0]));
        Blocks.fire.setFireInfo(block, DEFAULT_LOG_FIRE_ENCOURAGEMENT, DEFAULT_LOG_FLAMMABILITY);
    }
}
