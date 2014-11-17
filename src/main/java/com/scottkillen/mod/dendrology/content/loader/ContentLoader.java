package com.scottkillen.mod.dendrology.content.loader;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import com.scottkillen.mod.dendrology.block.ModStairsBlock;
import com.scottkillen.mod.dendrology.block.ModWoodSlabBlock;
import com.scottkillen.mod.dendrology.content.IContent;
import net.minecraft.block.Block;
import java.util.List;

public class ContentLoader
{
    private final ImmutableList<IContent> content;

    private List<ModLogBlock> logBlocks;
    private List<ModLeavesBlock> leavesBlocks;
    private List<ModPlanksBlock> planksBlocks;
    private List<ModSaplingBlock> saplingBlocks;
    private List<ModWoodSlabBlock> singleSlabBlocks;
    private List<ModWoodSlabBlock> doubleSlabBlocks;
    private List<ModStairsBlock> stairsBlocks;

    public ContentLoader(List<? extends IContent> content)
    {
        this.content = ImmutableList.copyOf(content);
        logBlocks = Lists.newArrayListWithCapacity(content.size() / ModLogBlock.CAPACITY + 1);
        leavesBlocks = Lists.newArrayListWithCapacity(content.size() / ModLeavesBlock.CAPACITY + 1);
        planksBlocks = Lists.newArrayListWithCapacity(content.size() / ModPlanksBlock.CAPACITY + 1);
        saplingBlocks = Lists.newArrayListWithCapacity(content.size() / ModSaplingBlock.CAPACITY + 1);
        singleSlabBlocks = Lists.newArrayListWithCapacity(content.size() / ModWoodSlabBlock.CAPACITY + 1);
        doubleSlabBlocks = Lists.newArrayListWithCapacity(content.size() / ModWoodSlabBlock.CAPACITY + 1);
        stairsBlocks = Lists.newArrayListWithCapacity(content.size());
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
        final List<String> names = Lists.newArrayListWithCapacity(ModLeavesBlock.CAPACITY);
        final List<IContent> trees = Lists.newArrayListWithCapacity(ModLeavesBlock.CAPACITY);
        for (final IContent species : content)
        {
            species.setLeavesMeta(names.size());

            names.add(species.getName());
            trees.add(species);
            if (names.size() == ModLeavesBlock.CAPACITY)
            {
                createLeavesBlock(names, trees);

                names.clear();
                trees.clear();
            }
        }
        if (!names.isEmpty())
        {
            createLeavesBlock(names, trees);
        }
    }

    private void loadLogBlocks()
    {
        final List<String> names = Lists.newArrayListWithCapacity(ModLogBlock.CAPACITY);
        final List<IContent> pendingUpdates = Lists.newArrayListWithCapacity(ModLogBlock.CAPACITY);
        for (final IContent species : content)
        {
            species.setLogMeta(names.size());

            names.add(species.getName());
            pendingUpdates.add(species);
            if (names.size() == ModLogBlock.CAPACITY)
            {
                createLogBlock(names, pendingUpdates);

                names.clear();
                pendingUpdates.clear();
            }
        }
        if (!names.isEmpty())
        {
            createLogBlock(names, pendingUpdates);
        }
    }

    private void loadPlanksBlocks()
    {
        final List<String> names = Lists.newArrayList();
        final List<IContent> pendingUpdates = Lists.newArrayListWithCapacity(ModPlanksBlock.CAPACITY);
        for (final IContent species : content)
        {
            species.setPlanksMeta(names.size());

            names.add(species.getName());
            pendingUpdates.add(species);
            if (names.size() == ModPlanksBlock.CAPACITY)
            {
                createPlanksBlock(names, pendingUpdates);

                names.clear();
                pendingUpdates.clear();
            }
        }
        if (!names.isEmpty())
        {
            createPlanksBlock(names, pendingUpdates);
        }
    }

    private void loadSaplingBlocks()
    {
        final List<String> names = Lists.newArrayList();
        final List<IContent> trees = Lists.newArrayList();
        for (final IContent species : content)
        {
            species.setSaplingMeta(names.size());

            names.add(species.getName());
            trees.add(species);
            if (names.size() == ModSaplingBlock.CAPACITY)
            {
                createSaplingBlock(names, trees);

                names.clear();
                trees.clear();
            }
        }
        if (!names.isEmpty())
        {
            createSaplingBlock(names, trees);
        }
    }

    private void loadSlabBlocks()
    {
        final List<String> names = Lists.newArrayList();
        final List<IContent> trees = Lists.newArrayList();
        for (final IContent species : content)
        {
            species.setSlabMeta(names.size());

            names.add(species.toString());
            trees.add(species);
            if (names.size() == ModWoodSlabBlock.CAPACITY)
            {
                createSlabBlocks(names, trees);

                names.clear();
                trees.clear();
            }
        }
        if (!names.isEmpty())
        {
            createSlabBlocks(names, trees);
        }
    }

    private void loadStairsBlocks()
    {
        for (final IContent species : content)
        {
            //noinspection ObjectAllocationInLoop
            final ModStairsBlock block = new ModStairsBlock(species.getPlanksBlock(), species.getPlanksMeta());
            block.setBlockName(String.format("stairs.%s", species.getName()));
            stairsBlocks.add(block);
            species.setStairsBlock(block);
        }
    }

    private void createLeavesBlock(List<String> names, List<IContent> pendingUpdates)
    {
        final ModLeavesBlock block = new ModLeavesBlock(names, pendingUpdates);
        leavesBlocks.add(block);

        for (final IContent update : pendingUpdates)
            update.setLeavesBlock(block);
    }

    private void createLogBlock(List<String> names, List<IContent> pendingUpdates)
    {
        final ModLogBlock block = new ModLogBlock(names);
        logBlocks.add(block);

        for (final IContent update : pendingUpdates)
            update.setLogBlock(block);
    }

    private void createPlanksBlock(List<String> names, List<IContent> pendingUpdates)
    {
        final ModPlanksBlock block = new ModPlanksBlock(names);
        planksBlocks.add(block);

        for (final IContent update : pendingUpdates)
            update.setPlanksBlock(block);
    }

    private void createSaplingBlock(List<String> names, List<IContent> pendingUpdates)
    {
        final ModSaplingBlock block = new ModSaplingBlock(names, pendingUpdates);
        saplingBlocks.add(block);

        for (final IContent update : pendingUpdates)
            update.setSaplingBlock(block);
    }

    private void createSlabBlocks(List<String> names, List<IContent> pendingUpdates)
    {
        final ModWoodSlabBlock singleSlabBlock = new ModWoodSlabBlock(false, names, pendingUpdates);
        final ModWoodSlabBlock doubleSlabBlock = new ModWoodSlabBlock(true, names, pendingUpdates);

        singleSlabBlocks.add(singleSlabBlock);
        doubleSlabBlocks.add(doubleSlabBlock);

        for (final IContent update : pendingUpdates)
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

    @SuppressWarnings("SuspiciousMethodCalls")
    public boolean isSingleSlabBlock(Block block)
    {
        return singleSlabBlocks.contains(block);
    }

    public ImmutableList<IContent> getContent()
    {
        return ImmutableList.copyOf(content);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("content", content).add("logBlocks", logBlocks)
                .add("leavesBlocks", leavesBlocks).add("planksBlocks", planksBlocks).add("saplingBlocks", saplingBlocks)
                .add("singleSlabBlocks", singleSlabBlocks).add("doubleSlabBlocks", doubleSlabBlocks)
                .add("stairsBlocks", stairsBlocks).toString();
    }
}
