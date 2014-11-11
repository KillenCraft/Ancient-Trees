package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.block.ModLogBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class LogItem extends ItemMultiTexture
{
    public LogItem(Block block, ModLogBlock log, String[] names) { super(block, log, names); }
}
