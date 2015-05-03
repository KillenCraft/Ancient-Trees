package com.scottkillen.mod.dendrology.compat.chisel;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.koresample.common.util.multiblock.SubBlockManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import java.util.List;

public class ChiselWoodSubBlockManager implements SubBlockManager
{
    private final String speciesName;
    private final List<IIcon> icons = Lists.newArrayListWithCapacity(16);
    private static final ImmutableList<String> TEXTURES = ImmutableList
            .of("clean", "short", "vertical", "vertical-uneven", "parquet", "fancy", "blinds", "panel-nails",
                    "double-side", "crate", "crate-fancy", "crateex", "large", "chaotic-hor", "chaotic", "double-top");

    public ChiselWoodSubBlockManager(String speciesName)
    {
        this.speciesName = speciesName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta)
    {
        return (meta != 9 || (side != 0 && side != 1) ? icons.get(meta) : icons.get(15));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        icons.clear();

        for (final String texture : TEXTURES)
        {
            icons.add(iconRegister.registerIcon(String.format("chisel:planks-%s/%s", speciesName, texture)));
        }
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs unused, List subBlocks)
    {
        final int numSubBlocks = TEXTURES.size() - 1;
        for (int i = 0; i < numSubBlocks; i++)
            subBlocks.add(new ItemStack(item, 1, i));
    }
}
