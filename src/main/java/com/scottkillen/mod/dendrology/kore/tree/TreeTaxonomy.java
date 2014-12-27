package com.scottkillen.mod.dendrology.kore.tree;

public interface TreeTaxonomy
{
    Iterable<? extends DefinesLeaves> leavesDefinitions();

    Iterable<? extends DefinesLog> logDefinitions();

    Iterable<? extends DefinesSapling> saplingDefinitions();

    Iterable<? extends DefinesSlab> slabDefinitions();

    Iterable<? extends DefinesStairs> stairsDefinitions();

    Iterable<? extends DefinesWood> woodDefinitions();
}
