package com.scottkillen.mod.dendrology.world.gen.feature.cerasu;

public class LargeWhiteCerasuTree extends LargePinkCerasuTree
{
    public LargeWhiteCerasuTree(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected int getLeavesMetadata() { return 1; }

}
