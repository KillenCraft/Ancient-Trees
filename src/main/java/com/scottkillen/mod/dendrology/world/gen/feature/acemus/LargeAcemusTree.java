package com.scottkillen.mod.dendrology.world.gen.feature.acemus;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;
import net.minecraft.block.Block;

public class LargeAcemusTree extends AbstractLargeVanillaOak
{
    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves2; }

    @Override
    protected int getLeavesMetadata() { return 2; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs1; }

    @Override
    protected int getUnmaskedLogMeta() { return 3; }
}
