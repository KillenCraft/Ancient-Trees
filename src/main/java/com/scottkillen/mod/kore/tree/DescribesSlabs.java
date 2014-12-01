package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.ModPlanksBlock;
import com.scottkillen.mod.kore.tree.block.ModWoodSlabBlock;
import com.scottkillen.mod.kore.common.Named;

public interface DescribesSlabs extends Named
{
    ModPlanksBlock getPlanksBlock();

    ModWoodSlabBlock getSingleSlabBlock();

    int getSlabMeta();
}
