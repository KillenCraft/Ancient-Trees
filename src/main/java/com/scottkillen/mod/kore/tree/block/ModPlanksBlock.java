package com.scottkillen.mod.kore.tree.block;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.kore.common.Named;
import com.scottkillen.mod.kore.common.OrganizesResources;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockWood;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import java.util.List;

public class ModPlanksBlock extends BlockWood
{
    public static final int CAPACITY = 16;
    private final ImmutableList<Named> names;
    private final List<IIcon> icons = Lists.newArrayList();
    private final String resourcePrefix;

    public ModPlanksBlock(List<? extends Named> names, OrganizesResources resourceOrganizer)
    {
        Preconditions.checkArgument(!names.isEmpty());
        Preconditions.checkArgument(names.size() <= CAPACITY);
        this.names = ImmutableList.copyOf(names);
        setCreativeTab(resourceOrganizer.getCreativeTab());
        setBlockName("wood");

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

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int meta)
    {
        final int meta1 = meta < 0 || meta >= icons.size() ? 0 : meta;
        return icons.get(meta1);
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
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons.clear();

        for (int i = 0; i < names.size(); i++)
        {
            //noinspection StringConcatenationMissingWhitespace
            final String iconName = resourcePrefix + "planks_" + names.get(i).getName();
            icons.add(i, iconRegister.registerIcon(iconName));
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("names", names).add("icons", icons)
                .add("resourcePrefix", resourcePrefix).toString();
    }
}
