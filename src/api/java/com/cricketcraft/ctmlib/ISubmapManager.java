package com.cricketcraft.ctmlib;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraftforge.common.util.ForgeDirection;

public interface ISubmapManager
{
    IIcon getIcon(int paramInt1, int paramInt2);

    IIcon getIcon(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, int paramInt4);

    @SideOnly(Side.CLIENT)
    void registerIcons(String paramString, Block paramBlock, IIconRegister paramIIconRegister);

    @SideOnly(Side.CLIENT)
    RenderBlocks createRenderContext(RenderBlocks paramRenderBlocks, Block paramBlock, IBlockAccess paramIBlockAccess);

    @SideOnly(Side.CLIENT)
    void preRenderSide(RenderBlocks paramRenderBlocks, IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection);

    @SideOnly(Side.CLIENT)
    void postRenderSide(RenderBlocks paramRenderBlocks, IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, ForgeDirection paramForgeDirection);
}
