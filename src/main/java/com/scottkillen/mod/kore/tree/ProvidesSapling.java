package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.SaplingBlock;

public interface ProvidesSapling extends ProvidesAbstractTree
{
    SaplingBlock getSaplingBlock();

    int getSaplingMeta();
}
