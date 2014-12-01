package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.dendrology.block.ModSaplingBlock;

public interface ProvidesSapling
{
    ModSaplingBlock getSaplingBlock();

    int getSaplingMeta();
}
