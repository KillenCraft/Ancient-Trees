package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.WoodBlock;
import com.scottkillen.mod.kore.tree.block.SlabBlock;
import com.scottkillen.mod.kore.common.Named;

public interface DescribesSlabs extends Named
{
    WoodBlock getPlanksBlock();

    SlabBlock getSingleSlabBlock();

    int getSlabMeta();
}
