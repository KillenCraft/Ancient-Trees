package com.scottkillen.mod.dendrology.world.gen.feature.porffor;

import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaTree;

import static com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies.PORFFOR;

public class LargePorfforTree extends AbstractLargeVanillaTree
{
    @Override
    protected int getUnmaskedLogMeta() { return PORFFOR.logSubBlockIndex(); }
}
