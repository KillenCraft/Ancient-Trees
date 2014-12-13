package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;

public interface ProvidesSapling extends ProvidesAbstractTree
{
    ModSaplingBlock getSaplingBlock();

    int getSaplingMeta();
}
