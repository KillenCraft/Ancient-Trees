package com.scottkillen.mod.koresample.common.util.multiblock;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import java.util.List;

public interface SubBlockManager
{
	@SideOnly(Side.CLIENT)
	IIcon getIcon(int side, int meta);

	@SideOnly(Side.CLIENT)
	void registerIcons(IIconRegister register);

	@SideOnly(Side.CLIENT)
	void getSubBlocks(Item item, CreativeTabs tabs, List list);
}
