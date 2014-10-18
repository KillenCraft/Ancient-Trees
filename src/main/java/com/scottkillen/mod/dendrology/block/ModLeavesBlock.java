package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import java.util.List;

public class ModLeavesBlock extends BlockLeaves
{
    private static final int CAPACITY = 4;
    private final ImmutableList<String> subblockNames;
    private final ImmutableList<Colorizer> subblockColorizers;

    public enum Colorizer
    {
        BASIC,
        BIRCH
                {
                    @Override
                    int getColor()
                    {
                        return ColorizerFoliage.getFoliageColorBirch();
                    }
                },
        PINE
                {
                    @Override
                    int getColor()
                    {
                        return ColorizerFoliage.getFoliageColorPine();
                    }
                };

        int getColor()
        {
            return ColorizerFoliage.getFoliageColorBasic();
        }
    }

    public ModLeavesBlock(List<String> subblockNames, List<Colorizer> subblockColorizers)
    {
        Preconditions.checkArgument(!subblockNames.isEmpty());
        Preconditions.checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);
        Preconditions.checkArgument(!subblockColorizers.isEmpty());
        Preconditions.checkArgument(subblockColorizers.size() <= CAPACITY);
        this.subblockColorizers = ImmutableList.copyOf(subblockColorizers);
        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("leaves");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int metadata)
    {
        final Colorizer colorizer = subblockColorizers.get(metadata & 3);
        return colorizer.getColor();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        final int metadata = blockAccess.getBlockMetadata(x, y, z) & 3;
        final Colorizer colorizer = subblockColorizers.get(metadata & 3);
        switch(colorizer)
        {
            case BIRCH:
            case PINE:
                return colorizer.getColor();
            default:
                return super.colorMultiplier(blockAccess, x, y, z);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int metadata)
    {
        return field_150129_M[Minecraft.getMinecraft().gameSettings.fancyGraphics ? 0 : 1][metadata & 3];
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subblocks)
    {
        for (int i = 0; i < subblockNames.size(); i++)
            subblocks.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        field_150129_M[0] = new IIcon[subblockNames.size()];
        field_150129_M[1] = new IIcon[subblockNames.size()];

        for (int i = 0; i < subblockNames.size(); i++)
        {
            final String iconName = TheMod.RESOURCE_PREFIX + "leaves_" + subblockNames.get(i);
            field_150129_M[0][i] = iconRegister.registerIcon(iconName);
            field_150129_M[1][i] = iconRegister.registerIcon(iconName + "_opaque");
        }
    }

    @Override
    public String[] func_150125_e()
    {
        return subblockNames.toArray(new String[0]);
    }
}
