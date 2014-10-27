package com.scottkillen.mod.dendrology.world.gen.feature.cerasu;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractLargeVanillaOak;
import net.minecraft.block.Block;

public class LargePinkCerasuTree extends AbstractLargeVanillaOak
{
    public LargePinkCerasuTree(boolean isFromSapling) { super(isFromSapling); }

    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves2; }

    @Override
    protected int getLeavesMetadata() { return 0; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs0; }

    @Override
    protected int getUnmaskedLogMeta() { return 2; }
}
