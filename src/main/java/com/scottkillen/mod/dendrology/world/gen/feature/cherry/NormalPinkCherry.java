package com.scottkillen.mod.dendrology.world.gen.feature.cherry;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractVanillaOak;
import net.minecraft.block.Block;

public class NormalPinkCherry extends AbstractVanillaOak
{
    public NormalPinkCherry(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves2; }

    @Override
    protected int getLeavesMetadata() { return 0; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs0; }

    @Override
    protected int getLogMetadata() { return 2; }
}
