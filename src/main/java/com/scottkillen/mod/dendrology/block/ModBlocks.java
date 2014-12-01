package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.content.ITreeSpecies;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.content.loader.SpeciesLoader;
import com.scottkillen.mod.dendrology.item.LeavesItem;
import com.scottkillen.mod.dendrology.item.LogItem;
import com.scottkillen.mod.dendrology.item.PlanksItem;
import com.scottkillen.mod.dendrology.item.SaplingItem;
import com.scottkillen.mod.dendrology.item.SlabItem;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import java.util.Arrays;

@SuppressWarnings({ "UtilityClass", "WeakerAccess" })
public final class ModBlocks
{
    private static final int DEFAULT_LEAVES_FIRE_ENCOURAGEMENT = 30;
    private static final int DEFAULT_LOG_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_PLANKS_FIRE_ENCOURAGEMENT = 5;
    private static final int DEFAULT_STAIRS_FIRE_ENCOURAGEMENT = DEFAULT_PLANKS_FIRE_ENCOURAGEMENT;
    private static final int DEFAULT_LEAVES_FLAMMABILITY = 60;
    private static final int DEFAULT_LOG_FLAMMABILITY = 5;
    private static final int DEFAULT_PLANKS_FLAMMABILITY = 20;
    private static final int DEFAULT_STAIRS_FLAMMABILITY = DEFAULT_PLANKS_FLAMMABILITY;

    private static final SpeciesLoader overworldContent = new SpeciesLoader(Arrays.asList(OverworldTreeSpecies.values()));

    private ModBlocks()
    {
        throw new AssertionError();
    }

    public static void init()
    {
        overworldContent.load();
        initLogBlocks();
        initLeavesBlock();
        initSaplingBlocks();
        initPlanksBlocks();
        initStairsBlocks();
        initSingleSlabBlocks();
        initDoubleSlabBlocks();
    }

    private static void initStairsBlocks()
    {
        final ImmutableList<ModStairsBlock> stairsBlocks = overworldContent.getStairsBlocks();

        int stairsCount = 0;
        for (final ModStairsBlock stairs : stairsBlocks)
        {
            registerStairsBlock(stairs, String.format("stairs%d", stairsCount));
            stairsCount++;
        }
    }

    private static void initPlanksBlocks()
    {
        final ImmutableList<ModPlanksBlock> planksBlocks = overworldContent.getPlanksBlocks();

        int planksCount = 0;
        for (final ModPlanksBlock wood : planksBlocks)
        {
            registerPlanksBlock(wood, String.format("wood%d", planksCount), wood.getSubBlockNames());
            planksCount++;
        }
    }

    private static void initSaplingBlocks()
    {
        final ImmutableList<ModSaplingBlock> saplingBlocks = overworldContent.getSaplingBlocks();

        int saplingCount = 0;
        for (final ModSaplingBlock sapling : saplingBlocks)
        {
            registerSaplingBlock(sapling, String.format("sapling%d", saplingCount), sapling.getSubBlockNames());
            saplingCount++;
        }
    }

    private static void initSingleSlabBlocks()
    {
        int slabCount = 0;
        final ImmutableList<ModWoodSlabBlock> singleSlabBlocks = overworldContent.getSingleSlabBlocks();
        final ImmutableList<ModWoodSlabBlock> doubleSlabBlocks = overworldContent.getDoubleSlabBlocks();
        for (final ModWoodSlabBlock slab : singleSlabBlocks)
        {
            registerSlabBlock(slab, String.format("sslab%d", slabCount), slab, doubleSlabBlocks.get(slabCount), false);
            slabCount++;
        }
    }

    private static void initDoubleSlabBlocks()
    {
        int slabCount = 0;
        final ImmutableList<ModWoodSlabBlock> doubleSlabBlocks = overworldContent.getDoubleSlabBlocks();
        final ImmutableList<ModWoodSlabBlock> singleSlabBlocks = overworldContent.getSingleSlabBlocks();
        for (final ModWoodSlabBlock slab : doubleSlabBlocks)
        {
            registerSlabBlock(slab, String.format("dslab%d", slabCount), singleSlabBlocks.get(slabCount), slab, true);
            slabCount++;
        }
    }

    private static void initLeavesBlock()
    {
        final ImmutableList<ModLeavesBlock> leavesBlocks = overworldContent.getLeavesBlocks();

        int leavesCount = 0;
        for (final Block block : leavesBlocks)
        {
            registerLeavesBlock(block, String.format("leaves%d", leavesCount));
            leavesCount++;
        }
    }

    private static void initLogBlocks()
    {
        final ImmutableList<ModLogBlock> logBlocks = overworldContent.getLogBlocks();

        int logCount = 0;
        for (final ModLogBlock block : logBlocks)
        {
            registerLogBlock(block, String.format("logs%d", logCount), block.getSubBlockNames());
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

    private static void registerSlabBlock(Block block, String name, ModWoodSlabBlock singleSlab,
                                          ModWoodSlabBlock doubleSlab, boolean isDouble)
    {
        GameRegistry.registerBlock(block, SlabItem.class, name, singleSlab, doubleSlab, isDouble);
    }

    private static void registerPlanksBlock(Block block, String name, ImmutableList<String> subblockNames)
    {
        GameRegistry.registerBlock(block, PlanksItem.class, name, block,
                subblockNames.toArray(new String[subblockNames.size()]));
        Blocks.fire.setFireInfo(block, DEFAULT_PLANKS_FIRE_ENCOURAGEMENT, DEFAULT_PLANKS_FLAMMABILITY);
    }

    private static void registerStairsBlock(Block block, String name)
    {
        GameRegistry.registerBlock(block, name);
        Blocks.fire.setFireInfo(block, DEFAULT_STAIRS_FIRE_ENCOURAGEMENT, DEFAULT_STAIRS_FLAMMABILITY);
    }

    public static boolean isSingleSlabBlock(Block block)
    {
        return overworldContent.isSingleSlabBlock(block);
    }

    public static Iterable<? extends Block> getLeavesBlocks()
    {
        return overworldContent.getLeavesBlocks();
    }

    public static Iterable<? extends Block> getLogBlocks()
    {
        return overworldContent.getLogBlocks();
    }

    public static Iterable<? extends Block> getPlanksBlocks()
    {
        return overworldContent.getPlanksBlocks();
    }

    public static Iterable<? extends Block> getSaplingBlocks()
    {
        return overworldContent.getSaplingBlocks();
    }

    public static Iterable<? extends Block> getSingleSlabBlocks()
    {
        return overworldContent.getSingleSlabBlocks();
    }

    public static Iterable<? extends Block> getStairsBlocks()
    {
        return overworldContent.getStairsBlocks();
    }

    public static Iterable<? extends ITreeSpecies> getContent()
    {
        return overworldContent.getSpecies();
    }
}
