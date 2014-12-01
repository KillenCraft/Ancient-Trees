package com.scottkillen.mod.kore.trees;

import com.scottkillen.mod.dendrology.block.ModPlanksBlock;
import com.scottkillen.mod.dendrology.block.ModWoodSlabBlock;
import com.scottkillen.mod.kore.common.Named;

public interface DescribesSlabs extends Named
{
    ModPlanksBlock getPlanksBlock();

    ModWoodSlabBlock getSingleSlabBlock();

    int getSlabMeta();
}
