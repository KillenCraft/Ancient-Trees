package com.scottkillen.mod.dendrology.world.gen.feature.acemus;

import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;

import static com.scottkillen.mod.dendrology.reference.Tree.ACEMUS;

public class LargeAcemusTree extends AbstractLargeVanillaOak
{
    public LargeAcemusTree() { super(ACEMUS); }

    @Override
    protected int getUnmaskedLogMeta() { return ACEMUS.getLogMeta(); }
}
