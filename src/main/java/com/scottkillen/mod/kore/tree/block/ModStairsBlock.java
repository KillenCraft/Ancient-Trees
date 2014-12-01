package com.scottkillen.mod.kore.tree.block;

import com.scottkillen.mod.dendrology.TheMod;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class ModStairsBlock extends BlockStairs
{
    public ModStairsBlock(Block modelBlock, int modelMeta)
    {
        super(modelBlock, modelMeta);
        setCreativeTab(TheMod.CREATIVE_TAB);
    }


    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + TheMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }
}
