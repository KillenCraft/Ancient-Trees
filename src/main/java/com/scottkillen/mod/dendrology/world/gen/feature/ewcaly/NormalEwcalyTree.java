package com.scottkillen.mod.dendrology.world.gen.feature.ewcaly;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import java.util.Random;

@SuppressWarnings({
        "MethodWithMoreThanThreeNegations",
        "AssignmentToMethodParameter",
        "OverlyComplexBooleanExpression"
})
public class NormalEwcalyTree extends AbstractTree
{
    private int logDirection = 0;

    public NormalEwcalyTree(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    @SuppressWarnings({
            "MethodWithMoreThanThreeNegations",
            "MethodWithMultipleLoops",
            "OverlyComplexMethod",
            "OverlyLongMethod",
            "OverlyNestedMethod"
    })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final int height = rand.nextInt(24) + 2;

        if (isPoorGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int dY = 1; dY <= height; dY++)
            placeLog(world, x, y + dY, z);

        int size = 1;

        for (int y1 = y + height / 2; y1 <= y + height; y1++)
            if (rand.nextInt(5) > 2 || y1 == y + height)
            {
                if (rand.nextInt(20) < 1)
                    size = 2;

                if (rand.nextInt(4) == 0 && y1 - y > 10 && y1 - y < 20)
                    size = 2;

                if (y1 - y >= 20)
                    size = 3;

                for (int dX = -size; dX <= size; dX++)
                    for (int dZ = -size; dZ <= size; dZ++)
                    {
                        placeLeaves(world, x + dX, y1, z + dZ);

                        if (size == 3 && (Math.abs(dX) == 3 && Math.abs(dZ) == 2 || Math.abs(dX) == 2 && Math.abs(dZ) == 3))
                        {
                            setBlockAndNotifyAdequately(world, x + dX, y1, z + dZ, Blocks.air, 0);
                        }

                        if (y1 == y + height && Math.abs(dX) < 3 && Math.abs(dZ) < 3 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                        {
                            if (size > 1)
                            {
                                placeLeaves(world, x + dX, y1 + 1, z + dZ);
                            }

                            if (size == 1 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                            {
                                placeLeaves(world, x + dX, y1 + 1, z + dZ);
                            }
                        }
                    }
            }

        for (int dY = height / 2; dY <= height - 3; dY++)
        {
            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, -1, 0, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, 1, 0, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, 0, -1, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, 0, 1, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, -1, 1, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, -1, -1, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, 1, 1, height);
            }

            if (rand.nextInt(11) == 0)
            {
                branches(world, rand, x, y + dY, z, 1, -1, height);
            }
        }

        return true;
    }

    private void branches(World world, Random rand, int x, int y, int z, int dX, int dZ, int height)
    {
        for (int i = 0; i < 5; i++)
        {
            if (dX == -1 && rand.nextInt(3) == 0)
            {
                x--;
                logDirection = 4;
            }

            if (dX == 1 && rand.nextInt(3) == 0)
            {
                x++;
                logDirection = 4;
            }

            if (dZ == -1 && rand.nextInt(3) == 0)
            {
                z--;
                logDirection = 8;
            }

            if (dZ == 1 && rand.nextInt(3) == 0)
            {
                z++;
                logDirection = 8;
            }

            placeLog(world, x, y, z);
            logDirection = 0;

            if (i == 4 && height >= 18)
            {
                genLeaves(world, x, y, z);
            }

            if (i == 4 && height < 18)
            {
                genLeavesS(world, x, y, z);
            }

            y++;
        }
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    private void genLeaves(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
            for (int dZ = -3; dZ <= 3; dZ++)
            {
                if ((Math.abs(dX) != 3 || Math.abs(dZ) != 3) && (Math.abs(dX) != 2 || Math.abs(dZ) != 3) && (Math.abs(dX) != 3 || Math.abs(dZ) != 2))
                    placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) < 3 && Math.abs(dZ) < 3 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                {
                    placeLeaves(world, x + dX, y - 1, z + dZ);
                    placeLeaves(world, x + dX, y + 1, z + dZ);
                }
            }
    }

    @SuppressWarnings("MethodWithMultipleLoops")
    private void genLeavesS(World world, int x, int y, int z)
    {
        for (int dX = -2; dX <= 2; dX++)
            for (int dZ = -2; dZ <= 2; dZ++)
            {
                if (Math.abs(dX) != 2 || Math.abs(dZ) != 2)
                    placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) < 2 && Math.abs(dZ) < 2 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                {
                    placeLeaves(world, x + dX, y + 1, z + dZ);
                    placeLeaves(world, x + dX, y - 1, z + dZ);
                }
            }
    }

    @Override
    protected Block getLeavesBlock() {return ModBlocks.leaves0;}

    @Override
    protected int getLeavesMetadata() {return 3;}

    @Override
    protected Block getLogBlock() {return ModBlocks.logs1;}

    @Override
    protected int getLogMetadata() {return logDirection;}

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }
}
