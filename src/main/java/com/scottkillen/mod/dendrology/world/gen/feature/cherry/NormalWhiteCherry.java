package com.scottkillen.mod.dendrology.world.gen.feature.cherry;

import com.scottkillen.mod.dendrology.world.gen.feature.cherry.NormalPinkCherry;

public class NormalWhiteCherry extends NormalPinkCherry
{
    public NormalWhiteCherry(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected int getLeavesMetadata() { return 1; }
}
