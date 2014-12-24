package com.scottkillen.mod.kore.tree.loader;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.kore.common.OrganizesResources;
import com.scottkillen.mod.kore.tree.DefinesTree;
import com.scottkillen.mod.kore.tree.block.LeavesBlock;
import com.scottkillen.mod.kore.tree.block.LogBlock;
import com.scottkillen.mod.kore.tree.block.WoodBlock;
import com.scottkillen.mod.kore.tree.block.SaplingBlock;
import com.scottkillen.mod.kore.tree.block.StairsBlock;
import com.scottkillen.mod.kore.tree.block.SlabBlock;
import com.scottkillen.mod.kore.tree.util.SingleSlabRegistry;
import java.util.List;

public class TreeSpeciesLoader
{
    private final ImmutableList<DefinesTree> species;

    private List<LogBlock> logBlocks;
    private List<LeavesBlock> leavesBlocks;
    private List<WoodBlock> planksBlocks;
    private List<SaplingBlock> saplingBlocks;
    private List<SlabBlock> singleSlabBlocks;
    private List<SlabBlock> doubleSlabBlocks;
    private List<StairsBlock> stairsBlocks;

    private final OrganizesResources resourceOrganizer;

    public TreeSpeciesLoader(List<? extends DefinesTree> species, OrganizesResources resourceOrganizer)
    {
        this.species = ImmutableList.copyOf(species);
        this.resourceOrganizer = resourceOrganizer;

        logBlocks = Lists.newArrayListWithCapacity(species.size() / LogBlock.CAPACITY + 1);
        leavesBlocks = Lists.newArrayListWithCapacity(species.size() / LeavesBlock.CAPACITY + 1);
        planksBlocks = Lists.newArrayListWithCapacity(species.size() / WoodBlock.CAPACITY + 1);
        saplingBlocks = Lists.newArrayListWithCapacity(species.size() / SaplingBlock.CAPACITY + 1);
        singleSlabBlocks = Lists.newArrayListWithCapacity(species.size() / SlabBlock.CAPACITY + 1);
        doubleSlabBlocks = Lists.newArrayListWithCapacity(species.size() / SlabBlock.CAPACITY + 1);
        stairsBlocks = Lists.newArrayListWithCapacity(species.size());
    }

    public void load()
    {
        loadLogBlocks();
        logBlocks = ImmutableList.copyOf(logBlocks);

        loadLeavesBlocks();
        leavesBlocks = ImmutableList.copyOf(leavesBlocks);

        loadPlanksBlocks();
        planksBlocks = ImmutableList.copyOf(planksBlocks);

        loadSaplingBlocks();
        saplingBlocks = ImmutableList.copyOf(saplingBlocks);

        loadSlabBlocks();
        singleSlabBlocks = ImmutableList.copyOf(singleSlabBlocks);
        doubleSlabBlocks = ImmutableList.copyOf(doubleSlabBlocks);

        loadStairsBlocks();
        stairsBlocks = ImmutableList.copyOf(stairsBlocks);
    }

    private void loadLeavesBlocks()
    {
        final List<DefinesTree> trees = Lists.newArrayListWithCapacity(LeavesBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setLeavesMeta(trees.size());

            trees.add(aSpecies);
            if (trees.size() == LeavesBlock.CAPACITY)
            {
                createLeavesBlock(trees);

                trees.clear();
            }
        }
        if (!trees.isEmpty())
        {
            createLeavesBlock(trees);
        }
    }

    private void loadLogBlocks()
    {
        final List<DefinesTree> pendingUpdates = Lists.newArrayListWithCapacity(LogBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setLogMeta(pendingUpdates.size());

            pendingUpdates.add(aSpecies);
            if (pendingUpdates.size() == LogBlock.CAPACITY)
            {
                createLogBlock(pendingUpdates);

                pendingUpdates.clear();
            }
        }
        if (!pendingUpdates.isEmpty())
        {
            createLogBlock(pendingUpdates);
        }
    }

    private void loadPlanksBlocks()
    {
        final List<DefinesTree> pendingUpdates = Lists.newArrayListWithCapacity(WoodBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setPlanksMeta(pendingUpdates.size());

            pendingUpdates.add(aSpecies);
            if (pendingUpdates.size() == WoodBlock.CAPACITY)
            {
                createPlanksBlock(pendingUpdates);

                pendingUpdates.clear();
            }
        }
        if (!pendingUpdates.isEmpty())
        {
            createPlanksBlock(pendingUpdates);
        }
    }

    private void loadSaplingBlocks()
    {
        final List<DefinesTree> trees = Lists.newArrayList();
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setSaplingMeta(trees.size());

            trees.add(aSpecies);
            if (trees.size() == SaplingBlock.CAPACITY)
            {
                createSaplingBlock(trees);

                trees.clear();
            }
        }
        if (!trees.isEmpty())
        {
            createSaplingBlock(trees);
        }
    }

    private void loadSlabBlocks()
    {
        final List<DefinesTree> trees = Lists.newArrayList();
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setSlabMeta(trees.size());

            trees.add(aSpecies);
            if (trees.size() == SlabBlock.CAPACITY)
            {
                createSlabBlocks(trees);

                trees.clear();
            }
        }
        if (!trees.isEmpty())
        {
            createSlabBlocks(trees);
        }
    }

    private void loadStairsBlocks()
    {
        for (final DefinesTree aSpecies : species)
        {
            //noinspection ObjectAllocationInLoop
            final StairsBlock block =
                    new StairsBlock(aSpecies.getPlanksBlock(), aSpecies.getPlanksMeta(), resourceOrganizer);
            block.setBlockName(String.format("stairs.%s", aSpecies.getName()));
            stairsBlocks.add(block);
            aSpecies.setStairsBlock(block);
        }
    }

    private void createLeavesBlock(List<DefinesTree> pendingUpdates)
    {
        final LeavesBlock block = new LeavesBlock(pendingUpdates, resourceOrganizer);
        leavesBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setLeavesBlock(block);
    }

    private void createLogBlock(List<DefinesTree> pendingUpdates)
    {
        final LogBlock block = new LogBlock(pendingUpdates, resourceOrganizer);
        logBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setLogBlock(block);
    }

    private void createPlanksBlock(List<DefinesTree> pendingUpdates)
    {
        final WoodBlock block = new WoodBlock(pendingUpdates, resourceOrganizer);
        planksBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setPlanksBlock(block);
    }

    private void createSaplingBlock(List<DefinesTree> pendingUpdates)
    {
        final SaplingBlock block = new SaplingBlock(pendingUpdates, resourceOrganizer);
        saplingBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setSaplingBlock(block);
    }

    private void createSlabBlocks(List<DefinesTree> pendingUpdates)
    {
        final SlabBlock singleSlabBlock = new SlabBlock(false, pendingUpdates, resourceOrganizer);
        final SlabBlock doubleSlabBlock = new SlabBlock(true, pendingUpdates, resourceOrganizer);

        SingleSlabRegistry.add(singleSlabBlock);

        singleSlabBlocks.add(singleSlabBlock);
        doubleSlabBlocks.add(doubleSlabBlock);

        for (final DefinesTree update : pendingUpdates)
        {
            update.setSlabBlock(singleSlabBlock, false);
            update.setSlabBlock(doubleSlabBlock, true);
        }
    }

    public ImmutableList<LogBlock> getLogBlocks()
    {
        return ImmutableList.copyOf(logBlocks);
    }

    public ImmutableList<LeavesBlock> getLeavesBlocks()
    {
        return ImmutableList.copyOf(leavesBlocks);
    }

    public ImmutableList<WoodBlock> getPlanksBlocks()
    {
        return ImmutableList.copyOf(planksBlocks);
    }

    public ImmutableList<SaplingBlock> getSaplingBlocks()
    {
        return ImmutableList.copyOf(saplingBlocks);
    }

    public ImmutableList<SlabBlock> getSingleSlabBlocks()
    {
        return ImmutableList.copyOf(singleSlabBlocks);
    }

    public ImmutableList<SlabBlock> getDoubleSlabBlocks()
    {
        return ImmutableList.copyOf(doubleSlabBlocks);
    }

    public ImmutableList<StairsBlock> getStairsBlocks()
    {
        return ImmutableList.copyOf(stairsBlocks);
    }

    public ImmutableList<DefinesTree> getSpecies()
    {
        return ImmutableList.copyOf(species);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("species", species).add("logBlocks", logBlocks)
                .add("leavesBlocks", leavesBlocks).add("planksBlocks", planksBlocks).add("saplingBlocks", saplingBlocks)
                .add("singleSlabBlocks", singleSlabBlocks).add("doubleSlabBlocks", doubleSlabBlocks)
                .add("stairsBlocks", stairsBlocks).add("resourceOrganizer", resourceOrganizer).toString();
    }
}
