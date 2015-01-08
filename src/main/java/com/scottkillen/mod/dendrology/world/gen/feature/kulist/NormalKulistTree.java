package com.scottkillen.mod.dendrology.world.gen.feature.kulist;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;

public class NormalKulistTree extends AbstractTree
{
    private int logDirection = 0;

    public NormalKulistTree(boolean fromSapling) { super(fromSapling); }

    @Override
    protected boolean canBeReplacedByLog(World world, int x, int y, int z)
    {
        return super.canBeReplacedByLog(world, x, y, z) || world.getBlock(x, y, z).getMaterial().equals(Material.water);
    }

    @Override
    protected int getLogMetadata() {return super.getLogMetadata() | logDirection;}

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }

    @SuppressWarnings({ "OverlyComplexMethod", "OverlyLongMethod" })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int height = rng.nextInt(5) + 6;

        if (isPoorGrowthConditions(world, x, y, z, height, getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = 0; level <= height; level++)
        {
            placeLog(world, x, y + level, z);

            if (level == height) leafGen(world, x, y + level, z);

            if (level > 2 && level < height)
            {
                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, -1, 0);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, 1, 0);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, 0, -1);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, 0, 1);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, -1, 1);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, -1, -1);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, 1, 1);

                if (rng.nextInt(6) == 0) branch(world, rng, x, y, z, height, level, 1, -1);
            }
        }
        return true;
    }

    @SuppressWarnings({ "OverlyComplexMethod", "OverlyLongMethod" })
    void branch(World world, Random rand, int x, int y, int z, int height, int level, int dX, int dZ)
    {
        int x1 = x;
        int z1 = z;
        int level1 = level + y;
        final int length = height - level;

        for (int i = 0; i <= length; i++)
        {
            if (dX == -1 && rand.nextInt(3) > 0)
            {
                x1--;
                logDirection = 4;

                if (dZ == 0 && rand.nextInt(4) == 0) z1 += rand.nextInt(3) - 1;
            }

            if (dX == 1 && rand.nextInt(3) > 0)
            {
                x1++;
                logDirection = 4;

                if (dZ == 0 && rand.nextInt(4) == 0) z1 += rand.nextInt(3) - 1;
            }

            if (dZ == -1 && rand.nextInt(3) > 0)
            {
                z1--;
                logDirection = 8;

                if (dX == 0 && rand.nextInt(4) == 0) x1 += rand.nextInt(3) - 1;
            }

            if (dZ == 1 && rand.nextInt(3) > 0)
            {
                z1++;
                logDirection = 8;

                if (dX == 0 && rand.nextInt(4) == 0) x1 += rand.nextInt(3) - 1;
            }

            placeLog(world, x1, level1, z1);
            logDirection = 0;

            if (rand.nextInt(3) > 0) level1++;

            if (i == length)
            {
                placeLog(world, x1, level1, z1);
                leafGen(world, x1, level1, z1);
            }
        }
    }

    @SuppressWarnings({
            "OverlyComplexBooleanExpression", "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops"
    })
    void leafGen(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
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
