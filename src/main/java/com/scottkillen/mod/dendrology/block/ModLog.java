package com.scottkillen.mod.dendrology.block;

import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

public class ModLog extends BlockLog
{
    private IIcon sideIcon;
    private IIcon topIcon;

    public ModLog()
    {
        setCreativeTab(TheMod.CREATIVE_TAB);
    }

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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        sideIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()));
        topIcon = iconRegister.registerIcon(getUnwrappedUnlocalizedName(getUnlocalizedName()) + "_top");
    }

    @Override
    protected IIcon getSideIcon(int unused)
    {
        return sideIcon;
    }

    @Override
    protected IIcon getTopIcon(int unused)
    {
        return topIcon;
    }
}
