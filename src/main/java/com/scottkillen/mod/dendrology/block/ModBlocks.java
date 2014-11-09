package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.item.LeavesItem;
import com.scottkillen.mod.dendrology.item.LogItem;
import com.scottkillen.mod.dendrology.item.PlanksItem;
import com.scottkillen.mod.dendrology.item.SaplingItem;
import com.scottkillen.mod.dendrology.content.TreeContent;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

@SuppressWarnings({ "UtilityClass", "PublicField", "WeakerAccess", "PublicStaticCollectionField" })
public final class ModBlocks
{
    public static final ImmutableList<ModLogBlock> LOG_BLOCKS = TreeContent.getLogBlocks();
    public static final ImmutableList<ModLeavesBlock> LEAVES_BLOCKS = TreeContent.getLeavesBlocks();
    public static final ImmutableList<ModPlanksBlock> PLANKS_BLOCKS = TreeContent.getPlanksBlocks();
    public static final ImmutableList<ModSaplingBlock> SAPLING_BLOCKS = TreeContent.getSaplingBlocks();
    private static final int DEFAULT_LEAVES_FIRE_ENCOURAGEMENT = 30;
    private static final int DEFAULT_LOG_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_PLANKS_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_LEAVES_FLAMMABILITY = 60;
    private static final int DEFAULT_LOG_FLAMMABILITY = 5;
    private static final int DEFAULT_PLANKS_FLAMMABILITY = 20;

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        initLogBlocks();
        initLeavesBlock();
        initSaplingBlocks();
        initPlanksBlocks();
    }

    private static void initPlanksBlocks()
    {
        int planksCount = 0;
        for (final ModPlanksBlock wood : PLANKS_BLOCKS)
        {
            registerPlanksBlock(wood, String.format("wood%d", planksCount), wood.getSubblockNames());
            planksCount++;
        }
    }

    private static void initSaplingBlocks()
    {
        int saplingCount = 0;
        for (final ModSaplingBlock sapling : SAPLING_BLOCKS)
        {
            registerSaplingBlock(sapling, String.format("sapling%d", saplingCount), sapling.getSubblockNames());
            saplingCount++;
        }
    }

    private static void initLeavesBlock()
    {
        int leavesCount = 0;
        for (final Block block : LEAVES_BLOCKS)
        {
            registerLeavesBlock(block, String.format("leaves%d", leavesCount));
            leavesCount++;
        }
    }

    private static void initLogBlocks()
    {
        int logCount = 0;
        for (final ModLogBlock block : LOG_BLOCKS)
        {
            registerLogBlock(block, String.format("logs%d", logCount), block.getSubblockNames());
            logCount++;
        }
    }

    private static void registerLeavesBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, LeavesItem.class, name);
        Blocks.fire.setFireInfo(block, DEFAULT_LEAVES_FIRE_ENCOURAGEMENT, DEFAULT_LEAVES_FLAMMABILITY);
    }

    private static void registerLogBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, LogItem.class, name, block,
                subblockNames.toArray(new String[subblockNames.size()]));
        Blocks.fire.setFireInfo(block, DEFAULT_LOG_FIRE_ENCOURAGEMENT, DEFAULT_LOG_FLAMMABILITY);
    }

    private static void registerSaplingBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, SaplingItem.class, name, block,
                subblockNames.toArray(new String[subblockNames.size()]));
    }

    private static void registerPlanksBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, PlanksItem.class, name, block,
                subblockNames.toArray(new String[subblockNames.size()]));
        Blocks.fire.setFireInfo(block, DEFAULT_PLANKS_FIRE_ENCOURAGEMENT, DEFAULT_PLANKS_FLAMMABILITY);
    }
}
