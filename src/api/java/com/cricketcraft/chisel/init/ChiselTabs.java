package com.cricketcraft.chisel.init;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;

public class ChiselTabs
{
    private static class CustomCreativeTab extends CreativeTabs
    {
        public CustomCreativeTab(String lable)
        {
            super(lable);
        }

        @Override
        public Item getTabIconItem()
        {
            return null;
        }
    }

    public static final CustomCreativeTab tabWoodChiselBlocks = new CustomCreativeTab("tabWoodChiselBlocks");
}
