package com.scottkillen.mod.dendrology.world.gen.feature;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;

public class Cypress extends AbstractTree
{
    public Cypress(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    private static boolean inRangeInclusive(int value, int min, int max)
    {
        return min <= value && value <= max;
    }

    @Override
    protected boolean canBeReplacedByLog(World world, int x, int y, int z)
    {
        return super.canBeReplacedByLog(world, x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }

    @Override
    protected Block getLeavesBlock() {return ModBlocks.leaves0;}

    @Override
    protected int getLeavesMetadata() {return 2;}

    @Override
    protected Block getLogBlock() {return ModBlocks.logs0;}

    @Override
    protected int getLogMetadata() {return 3;}

    @Override
    protected boolean isReplaceable(World world, int x, int y, int z)
    {
        return super.isReplaceable(world, x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }

    @SuppressWarnings({
            "MethodWithMultipleLoops",
            "OverlyComplexMethod",
            "OverlyComplexBooleanExpression",
            "MethodWithMoreThanThreeNegations",
            "OverlyLongMethod",
            "OverlyNestedMethod",
            "ConstantConditions"
    })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final int size = 1 + (rand.nextInt(7) < 2 ? 1 : 0) + (rand.nextInt(7) < 2 ? 1 : 0) + rand.nextInt(2);
        final int height = 4 * size + 1;

        if (isPoorGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int jn = 0; jn <= height; jn++)
        {
            if (jn != height) placeLog(world, x, y + jn, z);

            if (size == 1 && jn > 0) for (int dX = -1; dX <= 1; dX++)
                for (int dZ = -1; dZ <= 1; dZ++)
                    if (Math.abs(dX) != 1 || Math.abs(dZ) != 1) placeLeaves(world, x + dX, y + jn, z + dZ);

            if (size == 2 && jn > 0) for (int dX = -2; dX <= 2; dX++)
                for (int dZ = -2; dZ <= 2; dZ++)
                {
                    if (Math.abs(dX) <= 1 && Math.abs(dZ) <= 1)
                    {
                        if (Math.abs(dX) != 1 || Math.abs(dZ) != 1) placeLeaves(world, x + dX, y + jn, z + dZ);

                        if (jn == 7) placeLeaves(world, x + dX, y + 7, z + dZ);
                    }

                    if ((Math.abs(dX) != 2 || Math.abs(dZ) != 2) && (Math.abs(dX) != 2 || Math.abs(dZ) != 1) && (Math.abs(dX) != 1 || Math.abs(dZ) != 2) && inRangeInclusive(jn, 2, 6))
                        placeLeaves(world, x + dX, y + jn, z + dZ);
                }

            if (size == 3 && jn >= 1) for (int dX = -2; dX <= 2; dX++)
                for (int dZ = -2; dZ <= 2; dZ++)
                {
                    if (Math.abs(dX) <= 1 && Math.abs(dZ) <= 1 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                        placeLeaves(world, x + dX, y + jn, z + dZ);

                    if ((Math.abs(dX) != 2 || Math.abs(dZ) != 2) && (Math.abs(dX) != 2 || Math.abs(dZ) != 1) && (Math.abs(dX) != 1 || Math.abs(dZ) != 2) && inRangeInclusive(jn, 2, 10))
                        placeLeaves(world, x + dX, y + jn, z + dZ);
                }

            if (size == 4 && jn >= 1) for (int dX = -3; dX <= 3; dX++)
                for (int dZ = -3; dZ <= 3; dZ++)
                {
                    if (Math.abs(dX) <= 1 && Math.abs(dZ) <= 1 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                        placeLeaves(world, x + dX, y + jn, z + dZ);

                    if (Math.abs(dX) <= 1 && Math.abs(dZ) <= 1 && jn <= 14 && jn >= 2)
                        placeLeaves(world, x + dX, y + jn, z + dZ);

                    if (Math.abs(dX) <= 2 && Math.abs(dZ) <= 2 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2) && (jn == 112 || jn == 11 || jn == 3))
                        placeLeaves(world, x + dX, y + jn, z + dZ);

                    if ((Math.abs(dX) != 3 || Math.abs(dZ) != 3) && (Math.abs(dX) != 3 || Math.abs(dZ) != 2) && (Math.abs(dX) != 2 || Math.abs(dZ) != 3) && inRangeInclusive(jn, 4, 10))
                        placeLeaves(world, x + dX, y + jn, z + dZ);
                }

            if (jn == height)
            {
                placeLeaves(world, x, y + jn + 1, z);

                if (size == 3 || size == 4) placeLeaves(world, x, y + jn + 2, z);
            }
        }

        return true;
    }
}
