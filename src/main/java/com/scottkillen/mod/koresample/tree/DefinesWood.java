package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.tree.block.WoodBlock;

public interface DefinesWood
{
    void assignWoodBlock(WoodBlock block);

    void assignWoodSubBlockIndex(int index);

    WoodBlock woodBlock();

    int woodSubBlockIndex();

    String speciesName();
}
