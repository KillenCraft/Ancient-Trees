package com.jaquadro.minecraft.gardentrees.world.gen;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import java.util.Random;

public abstract class WorldGenOrnamentalTree extends WorldGenAbstractTree
{
    public WorldGenOrnamentalTree(boolean blockNotify, Block wood, int metaWood, Block leaves, int metaLeaves)
    {
        super(true);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z) { return true; }

    @Override
    protected boolean isReplaceable(World world, int x, int y, int z) { return true; }
}
