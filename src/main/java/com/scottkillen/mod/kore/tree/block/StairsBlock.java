package com.scottkillen.mod.kore.tree.block;

import com.google.common.base.Objects;
import com.scottkillen.mod.kore.common.OrganizesResources;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;

public class StairsBlock extends BlockStairs
{
    private final String resourcePrefix;

    public StairsBlock(Block modelBlock, int modelMeta, OrganizesResources resourceOrganizer)
    {
        super(modelBlock, modelMeta);
        setCreativeTab(resourceOrganizer.getCreativeTab());

        resourcePrefix = resourceOrganizer.getResourcePrefix();
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
        return "tile." + resourcePrefix + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("resourcePrefix", resourcePrefix).toString();
    }
}
