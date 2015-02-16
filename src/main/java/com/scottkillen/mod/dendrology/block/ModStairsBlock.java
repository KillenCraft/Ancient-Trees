package com.scottkillen.mod.dendrology.block;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.common.block.StairsBlock;
import com.scottkillen.mod.koresample.tree.DefinesStairs;

public final class ModStairsBlock extends StairsBlock
{
    public ModStairsBlock(DefinesStairs definition)
    {
        super(definition);
        setCreativeTab(TheMod.INSTANCE.creativeTab());
    }

    @Override
    protected String resourcePrefix()
    {
        return TheMod.getResourcePrefix();
    }
}
