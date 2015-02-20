package com.cricketcraft.chisel.api.carving;

import net.minecraft.block.Block;

public interface ICarvingRegistry {
    void addVariation(String groupName, Block block, int metadata, int order);

    void registerOre(String groupName, String oreName);

    void setVariationSound(String name, String s);
}