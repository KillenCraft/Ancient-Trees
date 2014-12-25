package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.tree.DefinesLeaves;
import com.scottkillen.mod.kore.tree.block.LeavesBlock;

public final class ModLeavesBlock extends LeavesBlock
{
    public ModLeavesBlock(Iterable<? extends DefinesLeaves> subBlocks)
    {
        super(ImmutableList.copyOf(subBlocks));
        setCreativeTab(TheMod.CREATIVE_TAB);
    }

    @Override
    protected String resourcePrefix() { return TheMod.getResourcePrefix(); }
}
