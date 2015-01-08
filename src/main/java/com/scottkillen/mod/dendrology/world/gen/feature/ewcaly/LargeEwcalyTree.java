package com.scottkillen.mod.dendrology.world.gen.feature.ewcaly;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import java.util.Random;

public class LargeEwcalyTree extends AbstractTree
{
    private int logDirection = 0;

    public LargeEwcalyTree(boolean fromSapling) { super(fromSapling); }

    @SuppressWarnings({
            "OverlyComplexBooleanExpression",
            "MethodWithMoreThanThreeNegations",
            "MethodWithMultipleLoops",
            "OverlyComplexMethod",
            "OverlyLongMethod",
            "OverlyNestedMethod"
    })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int height = rng.nextInt(24) + 8;

        if (isPoorGrowthConditions(world, x, y, z, height, getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int dy = 0; dy <= height; dy++)
            placeLog(world, x, y + dy, z);

        int size = 1;

        for (int y1 = y + height / 2; y1 <= y + height; y1++)
        {
            if (rng.nextInt(5) > 2 || y1 == y + height)
            {
                if (rng.nextInt(20) < 1) size = 2;

                if (rng.nextInt(4) == 0 && y1 - y > 10 && y1 - y < 20) size = 2;

                if (y1 - y >= 20) size = 3;

                for (int dX = -size; dX <= size; dX++)
                    for (int dZ = -size; dZ <= size; dZ++)
                    {
                        placeLeaves(world, x + dX, y1, z + dZ);
                        if (size != Math.abs(dX) || size != Math.abs(dZ)) placeLeaves(world, x + dX, y1, z + dZ);

                        if (size == 3 &&
                                (Math.abs(dX) == 3 && Math.abs(dZ) == 2 || Math.abs(dX) == 2 && Math.abs(dZ) == 3))
                            setBlockAndNotifyAdequately(world, x + dX, y1, z + dZ, Blocks.air, 0);

                        if (y1 == y + height && Math.abs(dX) < 3 && Math.abs(dZ) < 3 &&
                                (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                        {
                            if (size > 1) placeLeaves(world, x + dX, y1 + 1, z + dZ);

                            if (size == 1 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                                placeLeaves(world, x + dX, y1 + 1, z + dZ);
                        }
                    }
            }
        }

        for (int dY = height / 2; dY <= height - 5; dY++)
        {
            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, -1, 0, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, 1, 0, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, 0, -1, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, 0, 1, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, -1, 1, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, -1, -1, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, 1, 1, height);

            if (rng.nextInt(9) == 0) branches(world, x, y + dY, z, 1, -1, height);
        }

        return true;
    }

    void branches(World world, int x, int y, int z, int dX, int dZ, int height)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;
        for (int i = 0; i < 8; i++)
        {
            if (dX == -1)
            {
                x1--;
                logDirection = 4;
            }

            if (dX == 1)
            {
                x1++;
                logDirection = 4;
            }

            if (dZ == -1)
            {
                z1--;
                logDirection = 8;
            }

            if (dZ == 1)
            {
                z1++;
                logDirection = 8;
            }

            placeLog(world, x1, y1, z1);
            logDirection = 0;

            if ((i == 4 || i == 7) && height >= 13) genLeaves(world, x1, y1, z1);

            if ((i == 4 || i == 7) && height < 13) genLeavesS(world, x1, y1, z1);

            y1++;
        }
    }

    @SuppressWarnings({
            "OverlyComplexBooleanExpression", "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops"
    })
    void genLeaves(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
        {
            for (int dY = -3; dY <= 3; dY++)
            {
                if ((Math.abs(dX) != 3 || Math.abs(dY) != 3) && (Math.abs(dX) != 2 || Math.abs(dY) != 3) &&
                        (Math.abs(dX) != 3 || Math.abs(dY) != 2)) placeLeaves(world, x + dX, y, z + dY);

                if (Math.abs(dX) < 3 && Math.abs(dY) < 3 && (Math.abs(dX) != 2 || Math.abs(dY) != 2))
                {
                    placeLeaves(world, x + dX, y - 1, z + dY);
                    placeLeaves(world, x + dX, y + 1, z + dY);
                }
            }
        }
    }

    @SuppressWarnings({
            "OverlyComplexBooleanExpression", "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops"
    })
    void genLeavesS(World world, int i3, int j3, int k3)
    {
        for (int x = -2; x <= 2; x++)
        {
            for (int y = -2; y <= 2; y++)
            {
                if (Math.abs(x) != 2 || Math.abs(y) != 2) placeLeaves(world, i3 + x, j3, k3 + y);

                if (Math.abs(x) < 2 && Math.abs(y) < 2 && (Math.abs(x) != 1 || Math.abs(y) != 1))
                {
                    placeLeaves(world, i3 + x, j3 + 1, k3 + y);
                    placeLeaves(world, i3 + x, j3 - 1, k3 + y);
                }
            }
        }
    }

    @Override
    protected int getLogMetadata() { return super.getLogMetadata() | logDirection; }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }
}
