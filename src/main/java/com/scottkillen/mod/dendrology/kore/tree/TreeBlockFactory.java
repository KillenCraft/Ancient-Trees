package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.common.util.slab.SingleDoubleSlab;
import com.scottkillen.mod.dendrology.kore.tree.block.LeavesBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.LogBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.SaplingBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.StairsBlock;
import com.scottkillen.mod.dendrology.kore.tree.block.WoodBlock;

public interface TreeBlockFactory
{
    LeavesBlock createLeavesBlock(Iterable<DefinesLeaves> subBlocks);

    LogBlock createLogBlock(Iterable<DefinesLog> subBlocks);

    SaplingBlock createSaplingBlock(Iterable<DefinesSapling> subBlocks);

    SingleDoubleSlab createSlabBlocks(Iterable<DefinesSlab> subBlocks);

    StairsBlock createStairsBlock(DefinesStairs definition);

    WoodBlock createWoodBlock(Iterable<DefinesWood> subBlocks);
}
