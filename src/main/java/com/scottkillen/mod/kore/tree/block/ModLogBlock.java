package com.scottkillen.mod.kore.tree.block;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.kore.common.Named;
import com.scottkillen.mod.kore.common.OrganizesResources;
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
    private final String resourcePrefix;

    public ModLogBlock(List<? extends Named> names, OrganizesResources resourceOrganizer)
    {
        Preconditions.checkArgument(!names.isEmpty());
        Preconditions.checkArgument(names.size() <= CAPACITY);
        this.names = ImmutableList.copyOf(names);
        setCreativeTab(resourceOrganizer.getCreativeTab());
        setBlockName("log");

        resourcePrefix = resourceOrganizer.getResourcePrefix();
    }

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @SuppressWarnings("LocalVariableHidesMemberVariable")
    public ImmutableList<String> getSubBlockNames()
    {
        final List<String> names = Lists.newArrayList();
        for (final Named named : this.names)
            names.add(named.getName());
        return ImmutableList.copyOf(names);
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + resourcePrefix + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
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
            final String iconName = resourcePrefix + "log_" + names.get(i).getName();
            field_150167_a[i] = iconRegister.registerIcon(iconName);
            field_150166_b[i] = iconRegister.registerIcon(iconName + "_top");
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("names", names).add("resourcePrefix", resourcePrefix).toString();
    }
}
