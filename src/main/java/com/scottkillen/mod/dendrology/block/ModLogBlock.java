package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.common.Named;
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
    public static final int CAPACITY = 4;
    private final ImmutableList<Named> names;

    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public ImmutableList<String> getSubBlockNames()
    {
        final List<String> names = Lists.newArrayList();
        for (final Named named : this.names)
            names.add(named.getName());
        return ImmutableList.copyOf(names);
    }

    public ModLogBlock(List<? extends Named> names)
    {
        Preconditions.checkArgument(!names.isEmpty());
        Preconditions.checkArgument(names.size() <= CAPACITY);
        this.names = ImmutableList.copyOf(names);
        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("log");
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

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subblocks)
    {
        for (int i = 0; i < names.size(); i++)
            //noinspection ObjectAllocationInLoop
            subblocks.add(new ItemStack(item, 1, i));
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        field_150167_a = new IIcon[names.size()];
        field_150166_b = new IIcon[names.size()];

        for (int i = 0; i < names.size(); i++)
        {
            //noinspection StringConcatenationMissingWhitespace
            final String iconName = TheMod.RESOURCE_PREFIX + "log_" + names.get(i).getName();
            field_150167_a[i] = iconRegister.registerIcon(iconName);
            field_150166_b[i] = iconRegister.registerIcon(iconName + "_top");
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("names", names).toString();
    }
}
