package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.tree.block.WoodBlock;

public interface DefinesWood
{
    void assignWoodBlock(WoodBlock block);

    void assignWoodSubBlockIndex(int index);

    WoodBlock woodBlock();

    int woodSubBlockIndex();

    String speciesName();
}
