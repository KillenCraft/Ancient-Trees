package com.scottkillen.mod.koresample.tree.item;

import com.scottkillen.mod.koresample.tree.block.WoodBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public abstract class WoodItem extends ItemMultiTexture
{
    // This provides a reminder that you must extend this class and change the constructor to accept your extension of
    // WoodBlock in the second parameter

    protected WoodItem(Block block, WoodBlock log, String[] names) { super(block, log, names); }
}
