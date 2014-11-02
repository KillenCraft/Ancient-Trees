package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Maps;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.world.AcemusColorizer;
import com.scottkillen.mod.dendrology.world.CerasuColorizer;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.List;
import java.util.Map;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public class ModLeavesBlock extends BlockLeaves
{
    private static final int CAPACITY = 4;
    private static final int METADATA_MASK = CAPACITY - 1;
    private static final Map<Leaves, ImmutablePair<Item, Integer>> saplings = Maps.newHashMap();
    private final ImmutableList<String> subblockNames;
    private final ImmutableList<Colorizer> subblockColorizers;

    public ModLeavesBlock(List<String> subblockNames, List<Colorizer> subblockColorizers)
    {
        checkArgument(!subblockNames.isEmpty());
        checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);

        checkArgument(!subblockColorizers.isEmpty());
        checkArgument(subblockColorizers.size() <= CAPACITY);
        this.subblockColorizers = ImmutableList.copyOf(subblockColorizers);

        checkArgument(subblockNames.size() == subblockColorizers.size());

        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("leaves");
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 3;

    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    private static boolean isFancyGraphics() {return Minecraft.getMinecraft().gameSettings.fancyGraphics;}

    public static void addSapling(ModLeavesBlock leaves, int leavesMetadata, Block sapling, int saplingMetadata)
    {
        saplings.put(new Leaves(leaves, leavesMetadata), ImmutablePair.of(Item.getItemFromBlock(sapling), saplingMetadata));
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int metadata)
    {
        final Colorizer colorizer = subblockColorizers.get(mask(metadata));

        switch (colorizer)
        {
            case NONE:
                return 0xffffff;
            case ACEMUS:
                return AcemusColorizer.getAcemusInventoryColor();
            case CERASU:
                return CerasuColorizer.getCerasuInventoryColor();
            default:
                return Blocks.leaves.getRenderColor(0);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        final int metadata = mask(blockAccess.getBlockMetadata(x, y, z));
        final Colorizer colorizer = subblockColorizers.get(mask(metadata));

        switch (colorizer)
        {
            case NONE:
                return 0xffffff;
            case ACEMUS:
                return AcemusColorizer.getAcemusColor(x, z);
            case CERASU:
                return CerasuColorizer.getCerasuColor(x, y, z);
            default:
                return Blocks.leaves.colorMultiplier(blockAccess, x, y, z);
        }
    }

    @Override
    public Item getItemDropped(int metadata, Random unused, int unused2)
    {
        return saplings.get(new Leaves(this, mask(metadata))).left;
    }

    @Override
    public int damageDropped(int metadata)
    {
        return saplings.get(new Leaves(this, mask(metadata))).right;
    }

    @Override
    public boolean isOpaqueCube()
    {
        return !isFancyGraphics();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int metadata)
    {
        return field_150129_M[isFancyGraphics() ? 0 : 1][mask(metadata)];
    }

    @Override
    public String[] func_150125_e()
    {
        return subblockNames.toArray(new String[0]);
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + TheMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subblocks)
    {
        for (int i = 0; i < subblockNames.size(); i++)
            //noinspection unchecked,ObjectAllocationInLoop
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
            //noinspection StringConcatenationMissingWhitespace
            final String iconName = TheMod.RESOURCE_PREFIX + "leaves_" + subblockNames.get(i).replace('.', '_');
            field_150129_M[0][i] = iconRegister.registerIcon(iconName);
            field_150129_M[1][i] = iconRegister.registerIcon(iconName + "_opaque");
        }
    }

    @SuppressWarnings("OverlyComplexBooleanExpression")
    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        final Block block = blockAccess.getBlock(x, y, z);
        return !(!isFancyGraphics() && block.equals(this)) && (side == 0 && minY > 0.0D || side == 1 && maxY < 1.0D || side == 2 && minZ > 0.0D || side == 3 && maxZ < 1.0D || side == 4 && minX > 0.0D || side == 5 && maxX < 1.0D || !blockAccess.getBlock(x, y, z).isOpaqueCube());
    }

    public enum Colorizer
    {
        ACEMUS,
        BASIC,
        CERASU,
        NONE
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("subblockNames", subblockNames).add("subblockColorizers", subblockColorizers).toString();
    }

    private static final class Leaves
    {
        private final Block leaves;
        private final int metadata;

        private Leaves(Block leaves, int metadata)
        {
            this.leaves = leaves;
            this.metadata = metadata;
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(leaves, metadata);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Leaves that = (Leaves) o;
            return metadata == that.metadata && leaves.equals(that.leaves);
        }

        @Override
        public String toString()
        {
            return Objects.toStringHelper(this).add("leaves", leaves).add("metadata", metadata).toString();
        }
    }


}
