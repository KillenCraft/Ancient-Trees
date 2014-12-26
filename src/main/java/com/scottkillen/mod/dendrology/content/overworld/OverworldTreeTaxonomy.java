package com.scottkillen.mod.dendrology.content.overworld;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.kore.tree.DefinesLeaves;
import com.scottkillen.mod.kore.tree.DefinesLog;
import com.scottkillen.mod.kore.tree.DefinesSapling;
import com.scottkillen.mod.kore.tree.DefinesSlab;
import com.scottkillen.mod.kore.tree.DefinesStairs;
import com.scottkillen.mod.kore.tree.DefinesWood;
import com.scottkillen.mod.kore.tree.TreeTaxonomy;

public final class OverworldTreeTaxonomy implements TreeTaxonomy
{
    @Override
    public Iterable<? extends DefinesLeaves> leavesDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }

    @Override
    public Iterable<? extends DefinesLog> logDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }

    @Override
    public Iterable<? extends DefinesSapling> saplingDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }

    @Override
    public Iterable<? extends DefinesSlab> slabDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }

    @Override
    public Iterable<? extends DefinesStairs> stairsDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }

    @Override
    public Iterable<? extends DefinesWood> woodDefinitions()
    {
        return ImmutableList.copyOf(OverworldTreeSpecies.values());
    }
}
