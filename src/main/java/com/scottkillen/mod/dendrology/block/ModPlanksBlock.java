package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
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
    private final ImmutableList<String> subblockNames;
    private final List<IIcon> icons = Lists.newArrayList();

    public ModPlanksBlock(List<String> subblockNames)
    {
        Preconditions.checkArgument(!subblockNames.isEmpty());
        Preconditions.checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);
        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("wood");
    }

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @SuppressWarnings("ReturnOfCollectionOrArrayField")
    public ImmutableList<String> getSubblockNames()
    {
        return subblockNames;
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + TheMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
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
        for (int i = 0; i < subblockNames.size(); i++)
            //noinspection ObjectAllocationInLoop
            subblocks.add(new ItemStack(item, 1, i));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        icons.clear();

        for (int i = 0; i < subblockNames.size(); i++)
        {
            //noinspection StringConcatenationMissingWhitespace
            final String iconName = TheMod.RESOURCE_PREFIX + "planks_" + subblockNames.get(i);
            icons.add(i, iconRegister.registerIcon(iconName));
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("subblockNames", subblockNames).add("icons", icons).toString();
    }
}
