package com.scottkillen.mod.dendrology.world.gen.feature.cherry;

import com.scottkillen.mod.dendrology.world.gen.feature.cherry.LargePinkCherry;

public class LargeWhiteCherry extends LargePinkCherry
{
    public LargeWhiteCherry(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected int getLeavesMetadata() { return 1; }

}
