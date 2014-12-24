package com.scottkillen.mod.kore.tree.item;

import com.scottkillen.mod.kore.tree.block.LogBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class LogItem extends ItemMultiTexture
{
    public LogItem(Block block, LogBlock log, String[] names) { super(block, log, names); }
}
