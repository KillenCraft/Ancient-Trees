package com.scottkillen.mod.dendrology.world.gen.feature.cerasu;

public class NormalWhiteCerasuTree extends NormalPinkCerasuTree
{
    public NormalWhiteCerasuTree(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected int getLeavesMetadata() { return 1; }
}
