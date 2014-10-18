package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.block.ModLogBlock;
import com.scottkillen.mod.dendrology.block.ModSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class SaplingItem extends ItemMultiTexture
{
    public SaplingItem(Block block, ModSaplingBlock sapling, String[] names) { super(block, sapling, names); }
}
