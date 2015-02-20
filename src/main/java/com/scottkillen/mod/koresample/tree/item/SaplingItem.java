package com.scottkillen.mod.koresample.tree.item;

import com.scottkillen.mod.koresample.tree.block.SaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public abstract class SaplingItem extends ItemMultiTexture
{
    // This provides a reminder that you must extend this class and change the constructor to accept your extension of
    // SaplingBlock in the second parameter

    protected SaplingItem(Block block, SaplingBlock sapling, String[] names) { super(block, sapling, names); }
}
