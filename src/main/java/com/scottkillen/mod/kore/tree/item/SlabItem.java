package com.scottkillen.mod.kore.tree.item;

import com.scottkillen.mod.kore.tree.block.SlabBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;

public class SlabItem extends ItemSlab
{
    public SlabItem(Block block, SlabBlock singleSlab, SlabBlock doubleSlab, Boolean isDouble)
    {
        super(block, singleSlab, doubleSlab, isDouble);
    }
}
