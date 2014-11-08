package com.scottkillen.mod.dendrology.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import java.util.Random;

import static com.scottkillen.mod.dendrology.reference.Tree.TUOPA;

public class NucisTree extends AbstractTree
{
    @SuppressWarnings("OverlyComplexMethod")
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int h = rng.nextInt(15) + 8;

        if (isPoorGrowthConditions(world, x, y, z, h, TUOPA.getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = 0; level < h; level++)
        {
            if (level > 3)
            {
                final int branchRarity = h / (level - 2) + 1;

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, -1, 0);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, 1, 0);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, 0, -1);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, 0, 1);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, -1, 1);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, -1, -1);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, 1, 1);

                if (rng.nextInt(branchRarity) == 0) branch(world, rng, x, y, z, h, level, 1, -1);
            }
        }

        leafGen(world, x, y + h, z);

        return true;
    }

    @SuppressWarnings("OverlyComplexMethod")
    private void branch(World world, Random random, int x, int y, int z, int height, int level, int dX, int dZ)
    {
        int level1 = level + y;
        final int lengthToGo = height - level1;

        int x1 = x;
        int z1 = z;

        int index = 0;
        while (index <= lengthToGo)
        {
            if (dX == -1 && random.nextInt(3) > 0)
            {
                x1--;

                if (dZ == 0 && random.nextInt(4) == 0) z1 = z1 + random.nextInt(3) - 1;
            }

            if (dX == 1 && random.nextInt(3) > 0)
            {
                x1++;

                if (dZ == 0 && random.nextInt(4) == 0) z1 = z1 + random.nextInt(3) - 1;
            }

            if (dZ == -1 && random.nextInt(3) > 0)
            {
                z1--;

                if (dX == 0 && random.nextInt(4) == 0) x1 = x1 + random.nextInt(3) - 1;
            } else if (dZ == 1 && random.nextInt(3) > 0)
            {
                z1++;

                if (dX == 0 && random.nextInt(4) == 0) x1 = x1 + random.nextInt(3) - 1;
            }

            placeLog(world, x1, level1, z1);

            if (random.nextInt(3) > 0) level1++;

            if (index == lengthToGo || random.nextInt(6) == 0)
            {
                placeLog(world, x1, level1, z1);
                leafGen(world, x1, level1, z1);
            }

            index++;
        }
    }

    @SuppressWarnings({
            "MethodWithMoreThanThreeNegations",
            "MethodWithMultipleLoops",
            "OverlyComplexBooleanExpression"
    })
    private void leafGen(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
        {
            for (int dZ = -3; dZ <= 3; dZ++)
            {
                if ((Math.abs(dX) != 3 || Math.abs(dZ) != 3) && (Math.abs(dX) != 2 || Math.abs(dZ) != 3) &&
                        (Math.abs(dX) != 3 || Math.abs(dZ) != 2)) placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) < 3 && Math.abs(dZ) < 3 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                {
                    placeLeaves(world, x + dX, y + 1, z + dZ);
                    placeLeaves(world, x + dX, y - 1, z + dZ);
                }

                if (Math.abs(dX) + Math.abs(dZ) < 2)
                {
                    placeLeaves(world, x + dX, y + 2, z + dZ);
                    placeLeaves(world, x + dX, y - 2, z + dZ);
                }
            }
        }
    }

}
