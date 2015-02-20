package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.tree.block.LeavesBlock;
import com.scottkillen.mod.koresample.tree.block.LogBlock;
import com.scottkillen.mod.koresample.tree.block.SaplingBlock;
import net.minecraft.world.gen.feature.WorldGenerator;

@SuppressWarnings("InterfaceNeverImplemented")
public interface DefinesTree
{
    LeavesBlock leavesBlock();

    int leavesSubBlockIndex();

    LogBlock logBlock();

    int logSubBlockIndex();

    SaplingBlock saplingBlock();

    int saplingSubBlockIndex();

    WorldGenerator saplingTreeGenerator();

    WorldGenerator worldTreeGenerator();
}
