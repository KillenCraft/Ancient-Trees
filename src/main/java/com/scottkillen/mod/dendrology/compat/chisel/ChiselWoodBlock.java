package com.scottkillen.mod.dendrology.compat.chisel;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.common.util.multiblock.SubBlockManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.util.IIcon;
import net.minecraft.util.StatCollector;
import java.util.List;

public final class ChiselWoodBlock extends Block
{
    private static final ImmutableList<String> VARIATIONS = ImmutableList
            .of("clean", "short", "vertical", "uneven", "parquet", "fancy", "blinds", "panel", "double", "crate",
                    "cratefancy", "scaffold", "large", "chaotic", "verticalchaotic");

    private final SubBlockManager subBlocks;
    private final String speciesName;

    public ChiselWoodBlock(String speciesName)
    {
        super(Material.wood);
        subBlocks = new ChiselWoodSubBlockManager(speciesName);
        setHardness(2.0f);
        setResistance(5.0f);
        setStepSound(soundTypeWood);
        setHarvestLevel("axe", 0);
        this.speciesName = speciesName;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int metadata)
    {
        return subBlocks.getIcon(side, metadata);
    }

    @Override
    public int damageDropped(int i)
    {
        return i;
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register)
    {
        subBlocks.registerIcons(register);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void getSubBlocks(Item item, CreativeTabs tabs, List list)
    {
        subBlocks.getSubBlocks(item, tabs, list);
    }

    public String getVariationName(int i)
    {
        return StatCollector.translateToLocal(
                String.format("chisel.%s:planks.%s.%s.name", TheMod.MOD_ID, speciesName, VARIATIONS.get(i)));
    }
}
