package com.scottkillen.mod.kore.tree.item;

import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class LogItem extends ItemMultiTexture
{
    public LogItem(Block block, ModLogBlock log, String[] names) { super(block, log, names); }
}
