package com.scottkillen.mod.kore.tree.loader;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.tree.DefinesTree;
import com.scottkillen.mod.kore.tree.block.ModLeavesBlock;
import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import com.scottkillen.mod.kore.tree.block.ModPlanksBlock;
import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;
import com.scottkillen.mod.kore.tree.block.ModStairsBlock;
import com.scottkillen.mod.kore.tree.block.ModWoodSlabBlock;
import com.scottkillen.mod.kore.tree.util.SingleSlabRegistry;
import java.util.List;

public class TreeSpeciesLoader
{
    private final ImmutableList<DefinesTree> species;

    private List<ModLogBlock> logBlocks;
    private List<ModLeavesBlock> leavesBlocks;
    private List<ModPlanksBlock> planksBlocks;
    private List<ModSaplingBlock> saplingBlocks;
    private List<ModWoodSlabBlock> singleSlabBlocks;
    private List<ModWoodSlabBlock> doubleSlabBlocks;
    private List<ModStairsBlock> stairsBlocks;

    public TreeSpeciesLoader(List<? extends DefinesTree> species)
    {
        this.species = ImmutableList.copyOf(species);
        logBlocks = Lists.newArrayListWithCapacity(species.size() / ModLogBlock.CAPACITY + 1);
        leavesBlocks = Lists.newArrayListWithCapacity(species.size() / ModLeavesBlock.CAPACITY + 1);
        planksBlocks = Lists.newArrayListWithCapacity(species.size() / ModPlanksBlock.CAPACITY + 1);
        saplingBlocks = Lists.newArrayListWithCapacity(species.size() / ModSaplingBlock.CAPACITY + 1);
        singleSlabBlocks = Lists.newArrayListWithCapacity(species.size() / ModWoodSlabBlock.CAPACITY + 1);
        doubleSlabBlocks = Lists.newArrayListWithCapacity(species.size() / ModWoodSlabBlock.CAPACITY + 1);
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
        final List<DefinesTree> trees = Lists.newArrayListWithCapacity(ModLeavesBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setLeavesMeta(trees.size());

            trees.add(aSpecies);
            if (trees.size() == ModLeavesBlock.CAPACITY)
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
        final List<DefinesTree> pendingUpdates = Lists.newArrayListWithCapacity(ModLogBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setLogMeta(pendingUpdates.size());

            pendingUpdates.add(aSpecies);
            if (pendingUpdates.size() == ModLogBlock.CAPACITY)
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
        final List<DefinesTree> pendingUpdates = Lists.newArrayListWithCapacity(ModPlanksBlock.CAPACITY);
        for (final DefinesTree aSpecies : species)
        {
            aSpecies.setPlanksMeta(pendingUpdates.size());

            pendingUpdates.add(aSpecies);
            if (pendingUpdates.size() == ModPlanksBlock.CAPACITY)
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
            if (trees.size() == ModSaplingBlock.CAPACITY)
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
            if (trees.size() == ModWoodSlabBlock.CAPACITY)
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
            final ModStairsBlock block =
                    new ModStairsBlock(aSpecies.getPlanksBlock(), aSpecies.getPlanksMeta(), TheMod.INSTANCE);
            block.setBlockName(String.format("stairs.%s", aSpecies.getName()));
            stairsBlocks.add(block);
            aSpecies.setStairsBlock(block);
        }
    }

    private void createLeavesBlock(List<DefinesTree> pendingUpdates)
    {
        final ModLeavesBlock block = new ModLeavesBlock(pendingUpdates, TheMod.INSTANCE);
        leavesBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setLeavesBlock(block);
    }

    private void createLogBlock(List<DefinesTree> pendingUpdates)
    {
        final ModLogBlock block = new ModLogBlock(pendingUpdates, TheMod.INSTANCE);
        logBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setLogBlock(block);
    }

    private void createPlanksBlock(List<DefinesTree> pendingUpdates)
    {
        final ModPlanksBlock block = new ModPlanksBlock(pendingUpdates, TheMod.INSTANCE);
        planksBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setPlanksBlock(block);
    }

    private void createSaplingBlock(List<DefinesTree> pendingUpdates)
    {
        final ModSaplingBlock block = new ModSaplingBlock(pendingUpdates, TheMod.INSTANCE);
        saplingBlocks.add(block);

        for (final DefinesTree update : pendingUpdates)
            update.setSaplingBlock(block);
    }

    private void createSlabBlocks(List<DefinesTree> pendingUpdates)
    {
        final ModWoodSlabBlock singleSlabBlock = new ModWoodSlabBlock(false, pendingUpdates, TheMod.INSTANCE);
        final ModWoodSlabBlock doubleSlabBlock = new ModWoodSlabBlock(true, pendingUpdates, TheMod.INSTANCE);

        SingleSlabRegistry.add(singleSlabBlock);

        singleSlabBlocks.add(singleSlabBlock);
        doubleSlabBlocks.add(doubleSlabBlock);

        for (final DefinesTree update : pendingUpdates)
        {
            update.setSlabBlock(singleSlabBlock, false);
            update.setSlabBlock(doubleSlabBlock, true);
        }
    }

    public ImmutableList<ModLogBlock> getLogBlocks()
    {
        return ImmutableList.copyOf(logBlocks);
    }

    public ImmutableList<ModLeavesBlock> getLeavesBlocks()
    {
        return ImmutableList.copyOf(leavesBlocks);
    }

    public ImmutableList<ModPlanksBlock> getPlanksBlocks()
    {
        return ImmutableList.copyOf(planksBlocks);
    }

    public ImmutableList<ModSaplingBlock> getSaplingBlocks()
    {
        return ImmutableList.copyOf(saplingBlocks);
    }

    public ImmutableList<ModWoodSlabBlock> getSingleSlabBlocks()
    {
        return ImmutableList.copyOf(singleSlabBlocks);
    }

    public ImmutableList<ModWoodSlabBlock> getDoubleSlabBlocks()
    {
        return ImmutableList.copyOf(doubleSlabBlocks);
    }

    public ImmutableList<ModStairsBlock> getStairsBlocks()
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
                .add("stairsBlocks", stairsBlocks).toString();
    }
}
