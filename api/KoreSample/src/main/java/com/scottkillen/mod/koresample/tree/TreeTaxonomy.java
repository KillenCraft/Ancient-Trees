package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.tree.DefinesLeaves;

public interface TreeTaxonomy
{
    Iterable<? extends DefinesLeaves> leavesDefinitions();

    Iterable<? extends DefinesLog> logDefinitions();

    Iterable<? extends DefinesSapling> saplingDefinitions();

    Iterable<? extends DefinesSlab> slabDefinitions();

    Iterable<? extends DefinesStairs> stairsDefinitions();

    Iterable<? extends DefinesWood> woodDefinitions();
}
