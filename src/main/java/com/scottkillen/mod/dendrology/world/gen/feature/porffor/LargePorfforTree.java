package com.scottkillen.mod.dendrology.world.gen.feature.porffor;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;
import net.minecraft.block.Block;

public class LargePorfforTree extends AbstractLargeVanillaOak
{
    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves3; }

    @Override
    protected int getLeavesMetadata() { return 3; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs3; }

    @Override
    protected int getUnmaskedLogMeta() { return 0; }
}
