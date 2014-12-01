package com.scottkillen.mod.dendrology.world.gen.feature.cerasu;

import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaTree;

import static com.scottkillen.mod.dendrology.content.OverworldTreeSpecies.CERASU;

public class LargeCerasuTree extends AbstractLargeVanillaTree
{
    @Override
    protected int getUnmaskedLogMeta() { return CERASU.getLogMeta(); }
}
