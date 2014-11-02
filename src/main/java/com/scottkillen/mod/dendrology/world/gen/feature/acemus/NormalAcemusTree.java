package com.scottkillen.mod.dendrology.world.gen.feature.acemus;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.AbstractVanillaOak;
import net.minecraft.block.Block;

public class NormalAcemusTree extends AbstractVanillaOak
{
    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves2; }

    @Override
    protected int getLeavesMetadata() { return 2; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs1; }

    @Override
    protected int getLogMetadata() { return 3; }
}
