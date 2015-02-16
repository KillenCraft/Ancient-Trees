package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.tree.DefinesWood;
import com.scottkillen.mod.koresample.tree.block.WoodBlock;

public final class ModWoodBlock extends WoodBlock
{
    public ModWoodBlock(Iterable<? extends DefinesWood> subBlocks)
    {
        super(ImmutableList.copyOf(subBlocks));
        setCreativeTab(TheMod.INSTANCE.creativeTab());

        setHardness(2.0f);
        setResistance(5.0f);
        setStepSound(soundTypeWood);
    }

    @Override
    protected String resourcePrefix() { return TheMod.getResourcePrefix(); }
}
