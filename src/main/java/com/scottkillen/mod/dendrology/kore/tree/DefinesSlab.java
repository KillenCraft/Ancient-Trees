package com.scottkillen.mod.dendrology.kore.tree;

import com.scottkillen.mod.dendrology.kore.tree.block.SlabBlock;
import net.minecraft.block.Block;

public interface DefinesSlab
{

    void assignDoubleSlabBlock(SlabBlock doubleSlabBlock);

    void assignSingleSlabBlock(SlabBlock block);

    void assignSlabSubBlockIndex(int index);

    SlabBlock doubleSlabBlock();

    SlabBlock singleSlabBlock();

    int slabSubBlockIndex();

    Block slabModelBlock();

    int slabModelSubBlockIndex();

    String slabName();
}
