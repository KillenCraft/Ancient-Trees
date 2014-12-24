package com.scottkillen.mod.kore.tree.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.kore.common.Named;
import com.scottkillen.mod.kore.common.OrganizesResources;
import com.scottkillen.mod.kore.common.ProvidesPotionEffect;
import com.scottkillen.mod.kore.tree.ProvidesSapling;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.BlockSapling;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public class SaplingBlock extends BlockSapling
{
    public static final int CAPACITY = 8;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<ProvidesSapling> trees;
    private final List<IIcon> subblockIcons;
    private final String resourcePrefix;

    public SaplingBlock(List<? extends ProvidesSapling> trees, OrganizesResources resourceOrganizer)
    {
        checkArgument(!trees.isEmpty());
        checkArgument(trees.size() <= CAPACITY);
        this.trees = ImmutableList.copyOf(trees);

        subblockIcons = Lists.newArrayListWithCapacity(trees.size());

        setCreativeTab(resourceOrganizer.getCreativeTab());
        setBlockName("sapling");
        setHardness(0.0F);
        setStepSound(soundTypeGrass);

        resourcePrefix = resourceOrganizer.getResourcePrefix();
    }

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    private static int mask(int metadata) { return metadata & METADATA_MASK; }

    public ImmutableList<String> getSubBlockNames()
    {
        final List<String> names = Lists.newArrayListWithCapacity(trees.size());
        for (final Named named : trees)
            names.add(named.getName());
        return ImmutableList.copyOf(names);
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int metadata) { return subblockIcons.get(mask(metadata)); }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand)
    {
        if (!TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;

        final int metadata = mask(world.getBlockMetadata(x, y, z));
        final WorldGenerator treeGen = trees.get(metadata).getTreeGen();
        world.setBlock(x, y, z, Blocks.air, 0, 4);
        if (!treeGen.generate(world, rand, x, y, z)) world.setBlock(x, y, z, this, metadata, 4);
    }

    @Override
    public int damageDropped(int metadata)
    {
        return mask(metadata);
    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subBlocks)
    {
        for (int i = 0; i < trees.size(); i++)
            //noinspection ObjectAllocationInLoop
            subBlocks.add(new ItemStack(item, 1, i));
    }

    @Override
    public void registerBlockIcons(IIconRegister iconRegister)
    {
        subblockIcons.clear();

        for (int i = 0; i < trees.size(); i++)
        {
            //noinspection StringConcatenationMissingWhitespace
            final String iconName = resourcePrefix + "sapling_" + trees.get(i).getName();
            subblockIcons.add(i, iconRegister.registerIcon(iconName));
        }
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + resourcePrefix + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @SuppressWarnings("ReturnOfNull")
    public String getPotionEffect(ItemStack itemStack)
    {
        final int itemDamage = itemStack.getItemDamage();
        if (itemDamage < 0 || itemDamage >= trees.size()) return null;

        final ProvidesSapling sapling = trees.get(itemDamage);
        return sapling instanceof ProvidesPotionEffect ? ((ProvidesPotionEffect) sapling).getPotionEffect() : null;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("trees", trees).add("subblockIcons", subblockIcons)
                .add("resourcePrefix", resourcePrefix).toString();
    }
}
