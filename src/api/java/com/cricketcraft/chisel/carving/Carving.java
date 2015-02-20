package com.cricketcraft.chisel.carving;

import com.cricketcraft.chisel.api.carving.ICarvingRegistry;
import net.minecraft.block.Block;

public class Carving implements ICarvingRegistry
{
    public static final ICarvingRegistry chisel = new Carving();

    @Override
    public void addVariation(String groupName, Block block, int metadata, int order) { }

    @Override
    public void registerOre(String groupName, String oreName) { }

    @Override
    public void setVariationSound(String name, String s) { }
}
