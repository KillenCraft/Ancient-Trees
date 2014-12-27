package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.tree.block.LeavesBlock;

public interface DefinesLeaves extends ProvidesColor
{
    void assignLeavesBlock(LeavesBlock block);

    void assignLeavesSubBlockIndex(int index);

    LeavesBlock leavesBlock();

    int leavesSubBlockIndex();

    DefinesSapling saplingDefinition();

    String speciesName();
}
