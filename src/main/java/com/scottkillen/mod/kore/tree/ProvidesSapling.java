package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;

public interface ProvidesSapling
{
    ModSaplingBlock getSaplingBlock();

    int getSaplingMeta();
}
