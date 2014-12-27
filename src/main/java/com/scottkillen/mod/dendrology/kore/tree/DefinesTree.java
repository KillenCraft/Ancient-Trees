package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.tree.block.LeavesBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.LogBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.SaplingBlock;

public interface DefinesTree
{
    LeavesBlock leavesBlock();

    int leavesSubBlockIndex();

    LogBlock logBlock();

    int logSubBlockIndex();

    SaplingBlock saplingBlock();

    int saplingSubBlockIndex();
}
