package com.scottkillen.mod.koresample.tree.block;

import com.scottkillen.mod.koresample.tree.DefinesSlab;
import java.util.Collection;

// Use com.scottkillen.mod.koresample.common.block.SlabBlock instead
// This is left to honor the api contract
@Deprecated
public abstract class SlabBlock extends com.scottkillen.mod.koresample.common.block.SlabBlock
{
    protected SlabBlock(boolean isDouble, Collection<? extends DefinesSlab> subBlocks)
    {
        super(isDouble, subBlocks);
    }
}
