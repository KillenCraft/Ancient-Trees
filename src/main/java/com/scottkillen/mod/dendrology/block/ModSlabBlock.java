package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.tree.DefinesSlab;
import com.scottkillen.mod.kore.tree.block.SlabBlock;

public final class ModSlabBlock extends SlabBlock
{
    public ModSlabBlock(boolean isDouble, Iterable<? extends DefinesSlab> subBlocks)
    {
        super(isDouble, ImmutableList.copyOf(subBlocks));
        setCreativeTab(TheMod.CREATIVE_TAB);
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
    }

    @Override
    protected String resourcePrefix() { return TheMod.getResourcePrefix(); }
}
