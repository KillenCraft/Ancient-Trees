package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.tree.block.LogBlock;
import net.minecraft.block.Block;

public interface DefinesLog
{
    void assignLogBlock(LogBlock block);

    void assignLogSubBlockIndex(int index);

    Block logBlock();

    int logSubBlockIndex();

    String speciesName();

    Block woodBlock();

    int woodSubBlockIndex();
}
