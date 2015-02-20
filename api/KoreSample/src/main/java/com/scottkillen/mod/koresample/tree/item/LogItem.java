package com.scottkillen.mod.koresample.tree.item;

import com.scottkillen.mod.koresample.tree.block.LogBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public abstract class LogItem extends ItemMultiTexture
{
    // This provides a reminder that you must extend this class and change the constructor to accept your extension of
    // LogBlock in the second parameter

    protected LogItem(Block block, LogBlock log, String[] names) { super(block, log, names); }
}
