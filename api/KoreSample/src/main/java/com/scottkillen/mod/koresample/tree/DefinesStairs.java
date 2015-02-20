package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.common.block.StairsBlock;
import net.minecraft.block.Block;

public interface DefinesStairs
{
    void assignStairsBlock(StairsBlock block);

    StairsBlock stairsBlock();

    Block stairsModelBlock();

    int stairsModelSubBlockIndex();

    String stairsName();
}
