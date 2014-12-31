package com.scottkillen.mod.koresample.tree.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.scottkillen.mod.koresample.tree.DefinesLeaves;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public abstract class LeavesBlock extends BlockLeaves
{
    public static final int CAPACITY = 4;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<DefinesLeaves> subBlocks;

    protected LeavesBlock(Collection<? extends DefinesLeaves> subBlocks)
    {
        checkArgument(!subBlocks.isEmpty());
        checkArgument(subBlocks.size() <= CAPACITY);
        this.subBlocks = ImmutableList.copyOf(subBlocks);
        setBlockName("leaves");
    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @SideOnly(Side.CLIENT)
    private static boolean isFancyGraphics() {return Minecraft.getMinecraft().gameSettings.fancyGraphics;}

    protected final List<DefinesLeaves> subBlocks() { return Collections.unmodifiableList(subBlocks); }

    @SideOnly(Side.CLIENT)
    @Override
    public final int getRenderColor(int metadata) { return subBlocks.get(mask(metadata)).getLeavesInventoryColor(); }

    @SideOnly(Side.CLIENT)
    @Override
    public final int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        final int metadata = mask(blockAccess.getBlockMetadata(x, y, z));
        return subBlocks.get(metadata).getLeavesColor(blockAccess, x, y, z);
    }

    @Override
    public final Item getItemDropped(int metadata, Random unused, int unused2)
    {
        return Item.getItemFromBlock(subBlocks.get(mask(metadata)).saplingDefinition().saplingBlock());
    }

    @Override
    public final int damageDropped(int metadata)
    {
        return subBlocks.get(mask(metadata)).saplingDefinition().saplingSubBlockIndex();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public final boolean isOpaqueCube() { return !isFancyGraphics(); }

    @SideOnly(Side.CLIENT)
    @Override
    public final IIcon getIcon(int unused, int metadata)
    {
        return field_150129_M[isFancyGraphics() ? 0 : 1][mask(metadata)];
    }

    @Override
    public final String[] func_150125_e()
    {
        final List<String> names = Lists.newArrayList();
        for (final DefinesLeaves subBlock : subBlocks)
            names.add(subBlock.speciesName());
        return names.toArray(new String[names.size()]);
    }

    @Override
    public final String getUnlocalizedName()
    {
        return String.format("tile.%s%s", resourcePrefix(), getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public final int getDamageValue(World world, int x, int y, int z) { return world.getBlockMetadata(x, y, z) & 3; }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public final void getSubBlocks(Item item, CreativeTabs unused, List subBlocks)
    {
        for (int i = 0; i < this.subBlocks.size(); i++)
            //noinspection ObjectAllocationInLoop
            subBlocks.add(new ItemStack(item, 1, i));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public final void registerBlockIcons(IIconRegister iconRegister)
    {
        field_150129_M[0] = new IIcon[subBlocks.size()];
        field_150129_M[1] = new IIcon[subBlocks.size()];

        for (int i = 0; i < subBlocks.size(); i++)
        {
            final String iconName =
                    String.format("%sleaves_%s", resourcePrefix(), subBlocks.get(i).speciesName().replace('.', '_'));
            field_150129_M[0][i] = iconRegister.registerIcon(iconName);
            field_150129_M[1][i] = iconRegister.registerIcon(iconName + "_opaque");
        }
    }

    protected abstract String resourcePrefix();

    @SuppressWarnings("OverlyComplexBooleanExpression")
    @SideOnly(Side.CLIENT)
    @Override
    public final boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        final Block block = blockAccess.getBlock(x, y, z);
        return !(!isFancyGraphics() && block.equals(this)) &&
                (side == 0 && minY > 0.0D || side == 1 && maxY < 1.0D || side == 2 && minZ > 0.0D ||
                        side == 3 && maxZ < 1.0D || side == 4 && minX > 0.0D || side == 5 && maxX < 1.0D ||
                        !blockAccess.getBlock(x, y, z).isOpaqueCube());
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("subBlocks", subBlocks).toString();
    }
}
