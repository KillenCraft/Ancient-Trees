package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.LeavesBlock;
import com.scottkillen.mod.kore.tree.block.LogBlock;
import com.scottkillen.mod.kore.tree.block.WoodBlock;
import com.scottkillen.mod.kore.tree.block.SaplingBlock;
import com.scottkillen.mod.kore.tree.block.StairsBlock;
import com.scottkillen.mod.kore.tree.block.SlabBlock;

public interface DefinesTree extends DescribesLeaves, DescribesSlabs
{
    LeavesBlock getLeavesBlock();

    void setLeavesBlock(LeavesBlock block);

    int getLeavesMeta();

    void setLeavesMeta(int meta);

    LogBlock getLogBlock();

    void setLogBlock(LogBlock logBlock);

    int getLogMeta();

    void setLogMeta(int meta);

    void setPlanksBlock(WoodBlock block);

    int getPlanksMeta();

    void setPlanksMeta(int meta);

    void setSaplingBlock(SaplingBlock block);

    void setSaplingMeta(int meta);

    void setSlabBlock(SlabBlock block, boolean isDouble);

    void setSlabMeta(int meta);

    StairsBlock getStairsBlock();

    void setStairsBlock(StairsBlock block);
}
