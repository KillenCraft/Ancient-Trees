package com.scottkillen.mod.dendrology.block;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.registry.TreeRegistry;
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
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.IBlockAccess;
import java.util.List;
import java.util.Random;

import static com.google.common.base.Preconditions.*;

public class ModLeavesBlock extends BlockLeaves
{
    private static final int CAPACITY = 4;
    private static final int METADATA_MASK = CAPACITY - 1;
    private final ImmutableList<String> subblockNames;
    private final ImmutableList<Colorizer> subblockColorizers;

    @SuppressWarnings({
            "InnerClassTooDeeplyNested",
            "DeserializableClassInSecureContext",
            "SerializableClassInSecureContext"
    })
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
                },
        NONE
                {
                    @Override
                    int getColor()
                    {
                        return 0xffffff;
                    }
                };

        int getColor()
        {
            return ColorizerFoliage.getFoliageColorBasic();
        }
    }

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
    public Item getItemDropped(int metadata, Random unused, int unused2)
    {
        return TreeRegistry.getSapling(this, mask(metadata)).left;
    }

    private static int mask(int metadata) {return metadata & METADATA_MASK;}

    @Override
    public int damageDropped(int metadata)
    {
        return TreeRegistry.getSapling(this, mask(metadata)).right;
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int getRenderColor(int metadata)
    {
        final Colorizer colorizer = subblockColorizers.get(mask(metadata));
        return colorizer.getColor();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public int colorMultiplier(IBlockAccess blockAccess, int x, int y, int z)
    {
        final int metadata = mask(blockAccess.getBlockMetadata(x, y, z));
        final Colorizer colorizer = subblockColorizers.get(mask(metadata));
        return colorizer.getColor();
    }

    @SideOnly(Side.CLIENT)
    @Override
    public IIcon getIcon(int unused, int metadata)
    {
        return field_150129_M[isFancyGraphics() ? 0 : 1][mask(metadata)];
    }

    @Override
    public boolean isOpaqueCube()
    {
        return !isFancyGraphics();
    }

    @SuppressWarnings("WeakerAccess")
    protected static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        //noinspection StringConcatenationMissingWhitespace
        return "tile." + TheMod.RESOURCE_PREFIX + getUnwrappedUnlocalizedName(super.getUnlocalizedName());
    }

    @SuppressWarnings("OverlyComplexBooleanExpression")
    @Override
    public boolean shouldSideBeRendered(IBlockAccess blockAccess, int x, int y, int z, int side)
    {
        final Block block = blockAccess.getBlock(x, y, z);
        return !(!isFancyGraphics() && block.equals(this)) && (side == 0 && minY > 0.0D || side == 1 && maxY < 1.0D || side == 2 && minZ > 0.0D || side == 3 && maxZ < 1.0D || side == 4 && minX > 0.0D || side == 5 && maxX < 1.0D || !blockAccess.getBlock(x, y, z).isOpaqueCube());
    }

    private static boolean isFancyGraphics() {return Minecraft.getMinecraft().gameSettings.fancyGraphics;}

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

    @Override
    public String[] func_150125_e()
    {
        return subblockNames.toArray(new String[0]);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("subblockNames", subblockNames).add("subblockColorizers", subblockColorizers).toString();
    }
}
