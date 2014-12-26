package com.scottkillen.mod.kore.tree;

import com.scottkillen.mod.kore.tree.block.SaplingBlock;
import net.minecraft.world.gen.feature.WorldGenerator;

public interface DefinesSapling
{
    void assignSaplingBlock(SaplingBlock block);

    void assignSaplingSubBlockIndex(int index);

    SaplingBlock saplingBlock();

    int saplingSubBlockIndex();

    String speciesName();

    WorldGenerator treeGenerator();
}
