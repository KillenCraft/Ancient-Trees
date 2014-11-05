package com.scottkillen.mod.dendrology.world.gen.feature.porffor;

import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;

import static com.scottkillen.mod.dendrology.reference.Tree.PORFFOR;

public class LargePorfforTree extends AbstractLargeVanillaOak
{
    public LargePorfforTree() { super(PORFFOR); }

    @Override
    protected int getUnmaskedLogMeta() { return PORFFOR.getLogMeta(); }
}
