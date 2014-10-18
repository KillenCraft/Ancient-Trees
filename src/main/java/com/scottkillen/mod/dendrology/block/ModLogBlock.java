package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import java.util.List;

public class ModLogBlock extends BlockLog
{
    private static final int CAPACITY = 4;
    private final ImmutableList<String> subblockNames;

    public ModLogBlock(List<String> subblockNames)
    {
        Preconditions.checkArgument(!subblockNames.isEmpty());
        Preconditions.checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);
        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("log");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subblocks)
    {
        for (int i = 0; i < subblockNames.size(); i ++)
            //noinspection unchecked
            subblocks.add(new ItemStack(item, 1, i));
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

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        field_150167_a = new IIcon[subblockNames.size()];
        field_150166_b = new IIcon[subblockNames.size()];

        for (int i = 0; i < subblockNames.size(); i++)
        {
            final String iconName = TheMod.RESOURCE_PREFIX + "log_" + subblockNames.get(i);
            field_150167_a[i] = iconRegister.registerIcon(iconName);
            field_150166_b[i] = iconRegister.registerIcon(iconName + "_top");
        }
    }
}
