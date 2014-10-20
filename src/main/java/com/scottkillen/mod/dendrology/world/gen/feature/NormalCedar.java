package com.scottkillen.mod.dendrology.world.gen.feature;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;

class NormalCedar extends ModWorldGenAbstractTree
{
    @Override
    protected boolean canBeReplacedByLog(World world, int x, int y, int z)
    {
        return super.canBeReplacedByLog(world, x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }

    @Override
    protected boolean isReplaceable(World world, int x, int y, int z)
    {
        return super.isReplaceable(world, x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }

    NormalCedar(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    @SuppressWarnings({ "MethodWithMultipleLoops", "OverlyComplexMethod" })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final int height = rand.nextInt(10) + 9;

        if (!goodGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = 0; level <= height; level++)
        {
            placeLog(world, x, y + level, z);

            if (level == height) leafTop(world, x, y + level, z);

            if (level > 5 && level < height)
            {
                if (level == height - 1)
                {
                    leafGen(world, 2, x, y + level, z);
                }

                if (level == height - 4 || level == height - 7)
                {
                    for (int next = 1; next < 3; next++)
                    {
                        placeLog(world, x + next, y + level - 2, z);
                        placeLog(world, x - next, y + level - 2, z);
                        placeLog(world, x, y + level - 2, z + next);
                        placeLog(world, x, y + level - 2, z - next);
                    }
                    leafGen(world, level == height - 4 ? 3 : 4, x, y + level, z);
                }

                if (level == height - 10 || level == height - 13)
                {
                    leafGen(world, level == height - 10 ? 3 : 2, x, y + level, z);
                }
            }
        }

        return true;
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    void leafTop(World world, int x, int y, int z)
    {
        for (int dX = -2; dX <= 2; dX++)
            for (int dZ = -2; dZ <= 2; dZ++)
            {
                if (Math.abs(dX) + Math.abs(dZ) < 3)
                    placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) + Math.abs(dZ) < 2)
                    placeLeaves(world, x + dX, y + 1, z + dZ);

                if (Math.abs(dX) == 0 && Math.abs(dZ) == 0)
                    placeLeaves(world, x + dX, y + 2, z + dZ);
            }
    }

    void leafGen(World world, int size, int x, int y, int z)
    {
        final int radius;
        final int limiter1;
        final int limiter2;
        final int limiter3;

        switch (size)
        {
            case 3:
                radius = 4;
                limiter1 = 3;
                limiter2 = 5;
                limiter3 = 7;
                break;
            case 4:
                radius = 5;
                limiter1 = 5;
                limiter2 = 7;
                limiter3 = 8;
                break;
            case 5:
                radius = 6;
                limiter1 = 7;
                limiter2 = 8;
                limiter3 = 9;
                break;
            default:
                radius = 3;
                limiter1 = 2;
                limiter2 = 3;
                limiter3 = 5;
        }
        doLeafGen(world, x, y, z, radius, limiter1, limiter2, limiter3);
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    private void doLeafGen(World world, int x, int y, int z, int radius, int limiter1, int limiter2, int limiter3)
    {
        for (int dX = -radius; dX <= radius; dX++)
            for (int dZ = -radius; dZ <= radius; dZ++)
            {
                if (Math.abs(dX) + Math.abs(dZ) < limiter1)
                    placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) + Math.abs(dZ) < limiter2)
                    placeLeaves(world, x + dX, y - 1, z + dZ);

                if (Math.abs(dX) + Math.abs(dZ) < limiter3)
                    placeLeaves(world, x + dX, y - 2, z + dZ);
            }
    }

    @Override
    protected Block getLeavesBlock() {return ModBlocks.leaves0;}

    @Override
    protected int getLeavesMetadata() {return 1;}

    @Override
    protected Block getLogBlock() {return ModBlocks.logs0;}

    @Override
    protected int getLogMetadata() {return 1;}
}
