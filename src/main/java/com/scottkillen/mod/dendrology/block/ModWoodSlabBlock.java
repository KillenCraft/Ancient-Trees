package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.TreeContent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public class ModWoodSlabBlock extends BlockSlab
{
    public static final int CAPACITY = 8;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<TreeContent> trees;
    private final ImmutableList<String> subblockNames;

    public ModWoodSlabBlock(boolean isDouble, List<String> subblockNames, List<TreeContent> trees)
    {
        super(isDouble, Material.wood);

        checkArgument(!subblockNames.isEmpty());
        checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);

        checkArgument(!trees.isEmpty());
        checkArgument(trees.size() <= CAPACITY);
        this.trees = ImmutableList.copyOf(trees);

        checkArgument(subblockNames.size() == trees.size());

        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("slab");
    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}

    @SuppressWarnings("SuspiciousMethodCalls")
    private static boolean isSingleSlab(Item item)
    {
        return ModBlocks.SINGLE_SLAB_BLOCKS.contains(Block.getBlockFromItem(item));
    }

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int side, int metadata)
    {
        final TreeContent tree = trees.get(mask(metadata));
        return tree.getPlanksBlock().getIcon(side, mask(metadata));
    }

    @Override
    public Item getItemDropped(int metadata, Random unused, int unused2)
    {
        final TreeContent tree = trees.get(mask(metadata));
        return Item.getItemFromBlock(tree.getSingleSlabBlock());
    }

    @Override
    protected ItemStack createStackedBlock(int metadata)
    {
        final TreeContent tree = trees.get(mask(metadata));
        return new ItemStack(Item.getItemFromBlock(tree.getSingleSlabBlock()), 2, tree.getSlabMetadata());
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
        if (isSingleSlab(item))
        {
            for (int i = 0; i < subblockNames.size(); ++i)
            {
                //noinspection ObjectAllocationInLoop
                subblocks.add(new ItemStack(item, 1, i));
            }
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void registerBlockIcons(IIconRegister unused) {}

    @Override
    public String func_150002_b(int metadata)
    {
        int metadata1 = metadata;
        if (metadata1 < 0 || metadata1 >= subblockNames.size())
        {
            metadata1 = 0;
        }

        return getUnlocalizedName() + '.' + subblockNames.get(metadata1);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("trees", trees).add("subblockNames", subblockNames).toString();
    }
}
