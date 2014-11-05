package com.scottkillen.mod.dendrology.world.gen.feature.cerasu;

import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;

import static com.scottkillen.mod.dendrology.reference.Tree.CERASU;

public class LargeCerasuTree extends AbstractLargeVanillaOak
{

    public LargeCerasuTree()
    {
        super(CERASU);
    }

    @Override
    protected int getUnmaskedLogMeta() { return CERASU.getLogMeta(); }
}
