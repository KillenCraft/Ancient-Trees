package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.kore.tree.DefinesLog;
import com.scottkillen.mod.dendrology.kore.tree.block.LogBlock;

public final class ModLogBlock extends LogBlock
{
    public ModLogBlock(Iterable<? extends DefinesLog> subBlocks)
    {
        super(ImmutableList.copyOf(subBlocks));
        setCreativeTab(TheMod.CREATIVE_TAB);
    }

    @Override
    protected String resourcePrefix() { return TheMod.getResourcePrefix(); }
}
