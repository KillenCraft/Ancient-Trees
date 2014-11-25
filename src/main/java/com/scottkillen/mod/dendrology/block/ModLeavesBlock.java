package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.world.AcemusColorizer;
import com.scottkillen.mod.dendrology.world.CerasuColorizer;
import com.scottkillen.mod.dendrology.world.KulistColorizer;
import com.scottkillen.mod.kore.trees.DescribesLeaves;
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
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public class ModLeavesBlock extends BlockLeaves
{
    public static final int CAPACITY = 4;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<String> subblockNames;
    private final ImmutableList<DescribesLeaves> descriptors;

    public ModLeavesBlock(List<String> subblockNames, List<? extends DescribesLeaves> descriptors)
    {
        checkArgument(!subblockNames.isEmpty());
        checkArgument(subblockNames.size() <= CAPACITY);
        this.subblockNames = ImmutableList.copyOf(subblockNames);

        checkArgument(!descriptors.isEmpty());
        checkArgument(descriptors.size() <= CAPACITY);
        this.descriptors = ImmutableList.copyOf(descriptors);

        checkArgument(subblockNames.size() == descriptors.size());

        setCreativeTab(TheMod.CREATIVE_TAB);
        setBlockName("leaves");
    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    private static boolean isFancyGraphics() {return Minecraft.getMinecraft().gameSettings.fancyGraphics;}

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int metadata)
    {
        final Colorizer colorizer = descriptors.get(mask(metadata)).getColorizer();

        switch (colorizer)
        {
            case NO_COLOR:
                return 0xffffff;
            case ACEMUS_COLOR:
                return AcemusColorizer.getInventoryColor();
            case CERASU_COLOR:
                return CerasuColorizer.getInventoryColor();
            case KULIST_COLOR:
                return KulistColorizer.getInventoryColor();
            default:
                return Blocks.leaves.getRenderColor(0);
        }
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        final int metadata = mask(blockAccess.getBlockMetadata(x, y, z));
        final Colorizer colorizer = descriptors.get(mask(metadata)).getColorizer();

        switch (colorizer)
        {
            case NO_COLOR:
                return 0xffffff;
            case ACEMUS_COLOR:
                return AcemusColorizer.getColor(x, z);
            case CERASU_COLOR:
                return CerasuColorizer.getColor(x, y, z);
            case KULIST_COLOR:
                return KulistColorizer.getColor(x, y, z);
            default:
                return Blocks.leaves.colorMultiplier(blockAccess, x, y, z);
        }
    }

    @Override
    public Item getItemDropped(int metadata, Random unused, int unused2)
    {
        return Item.getItemFromBlock(descriptors.get(mask(metadata)).getSaplingBlock());
    }

    @Override
    public int damageDropped(int metadata)
    {
        return descriptors.get(mask(metadata)).getSaplingMeta();
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
        return subblockNames.toArray(new String[subblockNames.size()]);
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + TheMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @Override
    public int getDamageValue(World world, int x, int y, int z)
    {
        return world.getBlockMetadata(x, y, z) & 3;

    }

    @SuppressWarnings("unchecked")
    @SideOnly(Side.CLIENT)
    @Override
    public void getSubBlocks(Item item, CreativeTabs unused, List subBlocks)
    {
        for (int i = 0; i < subblockNames.size(); i++)
            //noinspection ObjectAllocationInLoop
            subBlocks.add(new ItemStack(item, 1, i));
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
        return !(!isFancyGraphics() && block.equals(this)) &&
                (side == 0 && minY > 0.0D || side == 1 && maxY < 1.0D || side == 2 && minZ > 0.0D ||
                        side == 3 && maxZ < 1.0D || side == 4 && minX > 0.0D || side == 5 && maxX < 1.0D ||
                        !blockAccess.getBlock(x, y, z).isOpaqueCube());
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("subblockNames", subblockNames).add("descriptors", descriptors)
                .toString();
    }

    public enum Colorizer
    {
        ACEMUS_COLOR,
        BASIC_COLOR,
        CERASU_COLOR,
        KULIST_COLOR,
        NO_COLOR
    }
}
