package com.scottkillen.mod.koresample.tree;

import com.scottkillen.mod.koresample.tree.block.SaplingBlock;
import net.minecraft.world.gen.feature.WorldGenerator;

@SuppressWarnings("InterfaceNeverImplemented")
public interface DefinesSapling
{
    void assignSaplingBlock(SaplingBlock block);

    void assignSaplingSubBlockIndex(int index);

    SaplingBlock saplingBlock();

    int saplingSubBlockIndex();

    String speciesName();

    @Deprecated
    WorldGenerator treeGenerator();

    WorldGenerator saplingTreeGenerator();
}
