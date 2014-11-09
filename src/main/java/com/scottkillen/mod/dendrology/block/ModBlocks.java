package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.item.LeavesItem;
import com.scottkillen.mod.dendrology.item.LogItem;
import com.scottkillen.mod.dendrology.item.PlanksItem;
import com.scottkillen.mod.dendrology.item.SaplingItem;
import com.scottkillen.mod.dendrology.reference.Tree;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;

import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.ACEMUS_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.BASIC_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.CERASU_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.KULIST_COLOR;
import static com.scottkillen.mod.dendrology.block.ModLeavesBlock.Colorizer.NONE;

@SuppressWarnings({ "UtilityClass", "PublicField", "WeakerAccess" })
public final class ModBlocks
{
    private static final int DEFAULT_LEAVES_FIRE_ENCOURAGEMENT = 30;
    private static final int DEFAULT_LOG_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_PLANKS_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_LEAVES_FLAMMABILITY = 60;
    private static final int DEFAULT_LOG_FLAMMABILITY = 5;
    private static final int DEFAULT_PLANKS_FLAMMABILITY = 20;

    public static ImmutableList<ModLogBlock> logs = Tree.getLogBlocks();

    public static ImmutableList<ModLeavesBlock> leaves = Tree.getLeavesBlocks();

    public static ImmutableList<ModSaplingBlock> saplings = ImmutableList.of(
            ModSaplingBlock.of(0),
            ModSaplingBlock.of(1)
    );

    public static ImmutableList<ModPlanksBlock> planks = ImmutableList.of(ModPlanksBlock.of(0));

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        int logCount = 0;
        for (final ModLogBlock block : logs)
        {
            registerLogBlock(block, String.format("logs%d", logCount), block.getSubblockNames());
            logCount++;
        }

        int leavesCount = 0;
        for (final Block block : leaves)
        {
            registerLeavesBlock(block, String.format("leaves%d", leavesCount));
            leavesCount++;
        }

        int saplingCount = 0;
        for (final ModSaplingBlock sapling : saplings)
        {
            registerSaplingBlock(sapling, String.format("sapling%d", saplingCount), sapling.getSubblockNames());
            saplingCount++;
        }

        int planksCount = 0;
        for (final ModPlanksBlock wood : planks)
        {
            registerPlanksBlock(wood, String.format("wood%d", planksCount), wood.getSubblockNames());
            planksCount++;
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
