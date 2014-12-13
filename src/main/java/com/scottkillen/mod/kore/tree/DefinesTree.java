package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.ModLeavesBlock;
import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import com.scottkillen.mod.kore.tree.block.ModPlanksBlock;
import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;
import com.scottkillen.mod.kore.tree.block.ModStairsBlock;
import com.scottkillen.mod.kore.tree.block.ModWoodSlabBlock;

public interface DefinesTree extends DescribesLeaves, DescribesSlabs
{
    ModLeavesBlock getLeavesBlock();

    void setLeavesBlock(ModLeavesBlock block);

    int getLeavesMeta();

    void setLeavesMeta(int meta);

    ModLogBlock getLogBlock();

    void setLogBlock(ModLogBlock logBlock);

    int getLogMeta();

    void setLogMeta(int meta);

    void setPlanksBlock(ModPlanksBlock block);

    int getPlanksMeta();

    void setPlanksMeta(int meta);

    void setSaplingBlock(ModSaplingBlock block);

    void setSaplingMeta(int meta);

    void setSlabBlock(ModWoodSlabBlock block, boolean isDouble);

    void setSlabMeta(int meta);

    ModStairsBlock getStairsBlock();

    void setStairsBlock(ModStairsBlock block);
}
