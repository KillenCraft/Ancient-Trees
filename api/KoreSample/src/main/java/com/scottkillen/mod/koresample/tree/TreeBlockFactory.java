package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.common.util.slab.SingleDoubleSlab;
import com.scottkillen.mod.koresample.tree.block.LeavesBlock;
import com.scottkillen.mod.koresample.tree.block.LogBlock;
import com.scottkillen.mod.koresample.tree.block.SaplingBlock;
import com.scottkillen.mod.koresample.common.block.StairsBlock;
import com.scottkillen.mod.koresample.tree.block.WoodBlock;

public interface TreeBlockFactory
{
    LeavesBlock createLeavesBlock(Iterable<DefinesLeaves> subBlocks);

    LogBlock createLogBlock(Iterable<DefinesLog> subBlocks);

    SaplingBlock createSaplingBlock(Iterable<DefinesSapling> subBlocks);

    SingleDoubleSlab createSlabBlocks(Iterable<DefinesSlab> subBlocks);

    StairsBlock createStairsBlock(DefinesStairs definition);

    WoodBlock createWoodBlock(Iterable<DefinesWood> subBlocks);
}
