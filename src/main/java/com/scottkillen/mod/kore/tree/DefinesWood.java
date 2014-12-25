package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.WoodBlock;

public interface DefinesWood
{
    void assignWoodBlock(WoodBlock block);

    void assignWoodSubBlockIndex(int index);

    WoodBlock woodBlock();

    int woodSubBlockIndex();

    String speciesName();
}
