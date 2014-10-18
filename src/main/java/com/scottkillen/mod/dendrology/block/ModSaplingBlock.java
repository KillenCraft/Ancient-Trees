package com.scottkillen.mod.dendrology.block;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.registry.TreeRegistry;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.checkArgument;

public class ModSaplingBlock extends BlockSapling
{
    private static final int CAPACITY = 8;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<String> subblockNames;
    private List<IIcon> subblockIcons = Lists.newArrayList();

    public ModSaplingBlock(List<String> subblockNames)
    {
        checkArgument(!subblockNames.isEmpty());
        checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);

        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("sapling");
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int metadata)
    {
        return subblockIcons.get(mask(metadata));
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand)
    {
        TreeRegistry.growTree(world, x, y, z, rand);
    }

    @Override
    public int damageDropped(int metadata)
    {
        return mask(metadata);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subblocks)
    {
        for (int i = 0; i < subblockNames.size(); i++)
            subblocks.add(new ItemStack(item, 1, i));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        subblockIcons.clear();

        for (int i = 0; i < subblockNames.size(); i++)
        {
            final String iconName = TheMod.RESOURCE_PREFIX + "sapling_" + subblockNames.get(i);
            subblockIcons.add(i, iconRegister.registerIcon(iconName));
        }
    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}
}
