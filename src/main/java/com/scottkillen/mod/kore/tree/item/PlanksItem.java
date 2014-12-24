package com.scottkillen.mod.kore.tree.item;

import com.scottkillen.mod.kore.tree.block.WoodBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class PlanksItem extends ItemMultiTexture
{
    public PlanksItem(Block block, WoodBlock log, String[] names) { super(block, log, names); }
}
