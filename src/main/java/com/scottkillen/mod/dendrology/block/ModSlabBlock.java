package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.common.block.SlabBlock;
import com.scottkillen.mod.koresample.tree.DefinesSlab;

public final class ModSlabBlock extends SlabBlock
{
    public ModSlabBlock(boolean isDouble, Iterable<? extends DefinesSlab> subBlocks)
    {
        super(isDouble, ImmutableList.copyOf(subBlocks));
        setCreativeTab(TheMod.INSTANCE.creativeTab());
        setHardness(2.0F);
        setResistance(5.0F);
        setStepSound(soundTypeWood);
    }

    @Override
    protected String resourcePrefix() { return TheMod.getResourcePrefix(); }
}
