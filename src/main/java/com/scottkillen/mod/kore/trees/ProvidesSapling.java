package com.scottkillen.mod.kore.trees;

import com.scottkillen.mod.dendrology.block.ModSaplingBlock;

public interface ProvidesSapling
{
    ModSaplingBlock getSaplingBlock();

    int getSaplingMeta();
}
