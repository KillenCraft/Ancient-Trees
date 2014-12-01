package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.kore.tree.block.ModPlanksBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;

public class PlanksItem extends ItemMultiTexture
{
    public PlanksItem(Block block, ModPlanksBlock log, String[] names) { super(block, log, names); }
}
