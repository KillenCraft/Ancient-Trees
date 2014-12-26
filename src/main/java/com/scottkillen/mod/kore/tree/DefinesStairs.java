package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.StairsBlock;
import net.minecraft.block.Block;

public interface DefinesStairs
{
    void assignStairsBlock(StairsBlock block);

    StairsBlock stairsBlock();

    Block stairsModelBlock();

    int stairsModelSubBlockIndex();

    String stairsName();
}
