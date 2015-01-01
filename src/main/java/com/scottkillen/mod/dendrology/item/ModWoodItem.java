package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.block.ModWoodBlock;
import com.scottkillen.mod.koresample.tree.item.WoodItem;
import net.minecraft.block.Block;

public final class ModWoodItem extends WoodItem
{
    public ModWoodItem(Block block, ModWoodBlock log, String[] names)
    {
        super(block, log, names);
    }
}
