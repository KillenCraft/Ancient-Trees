package com.scottkillen.mod.dendrology.kore.tree.block;

import com.scottkillen.mod.dendrology.kore.tree.DefinesStairs;
import net.minecraft.block.BlockStairs;

public abstract class StairsBlock extends BlockStairs
{
    protected StairsBlock(DefinesStairs model)
    {
        super(model.stairsModelBlock(), model.stairsModelSubBlockIndex());
    }


    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @Override
    public final String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + resourcePrefix() + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    protected abstract String resourcePrefix();
}
