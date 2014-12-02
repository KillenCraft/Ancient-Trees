package com.scottkillen.mod.kore.tree.item;

import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class SaplingItem extends ItemMultiTexture
{
    public SaplingItem(Block block, ModSaplingBlock sapling, String[] names) { super(block, sapling, names); }
}
