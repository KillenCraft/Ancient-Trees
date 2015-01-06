package com.scottkillen.mod.dendrology.world.gen.feature.hekur;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

@SuppressWarnings("OverlyComplexClass")
public class NormalHekurTree extends AbstractTree
{
    private int logDirection = 0;

    public NormalHekurTree(boolean fromSapling) { super(fromSapling); }

    @Override
    protected boolean isPoorGrowthConditions(World world, int x, int y, int z, int unused, IPlantable plantable)
    {
        final Block block = world.getBlock(x, y - 1, z);
        return !block.canSustainPlant(world, x, y - 1, z, UP, plantable);
    }

    @Override
    protected int getLogMetadata()
    {
        return super.getLogMetadata() | logDirection;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random random = new Random();
        random.setSeed(rand.nextLong());

        if (isPoorGrowthConditions(world, x, y, z, 0, getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        genRoots(world, random, x, y, z);
        growTrunk(world, random, x, y, z);

        return true;
    }

    private void genRoots(World world, Random random, int x, int y, int z)
    {
        for (final ImmutablePair<Integer, Integer> branchDirection : BRANCH_DIRECTIONS)
            if (random.nextInt(3) == 0)
                genRoot(world, random, x, y, z, branchDirection.getLeft(), branchDirection.getRight());

        genRoot(world, random, x, y, z, 0, 0);
    }

    void genRoot(World world, Random rand, int x, int y, int z, int dX, int dZ)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;

        setLogDirection(dX, dZ);

        for (int i = 0; i < 6; i++)
        {
            if (rand.nextInt(3) == 0)
            {
                if (dX == -1) x1--;

                if (dX == 1) x1++;

                if (dZ == -1) z1--;

                if (dZ == 1) z1++;
            }

            placeRoot(world, x1, y1, z1);

            y1--;
        }

        clearLogDirection();
    }

    private void setLogDirection(int dX, int dZ)
    {
        if (dX != 0) logDirection = 4;

        if (dZ != 0) logDirection = 8;
    }

    boolean canBeReplacedByRoot(World world, int x, int y, int z)
    {
        final Block block = world.getBlock(x, y, z);
        final Material material = block.getMaterial();

        return canBeReplacedByLog(world, x, y, z) || material.equals(Material.sand) || material.equals(Material.ground);
    }

    @SuppressWarnings("UnusedReturnValue")
    boolean placeRoot(World world, int x, int y, int z)
    {
        if (canBeReplacedByRoot(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLogBlock(), getLogMetadata());
            return true;
        }
        return false;
    }

    void growTrunk(World world, Random random, int i1, int j1, int k1)
    {
        placeLog(world, i1, j1, k1);

        switch (random.nextInt(4))
        {
            case 0:
                placeLog(world, i1, j1 + 2, k1);
                placeLog(world, i1 - 1, j1 + 1, k1);
                largeDirect(world, random, 1, 0, i1, j1 + 2, k1, 1, 2, 0, 2);
                break;

            case 1:
                placeLog(world, i1, j1 + 1, k1);
                placeLog(world, i1, j1 + 2, k1);
                placeLog(world, i1, j1 + 1, k1 - 1);
                largeDirect(world, random, 0, 1, i1, j1 + 2, k1, 1, 2, 0, 2);
                break;

            case 2:
                placeLog(world, i1, j1 + 1, k1);
                placeLog(world, i1, j1 + 2, k1);
                placeLog(world, i1 + 1, j1 + 1, k1);
                largeDirect(world, random, -1, 0, i1, j1 + 2, k1, 1, 2, 0, 2);
                break;

            default:
                placeLog(world, i1, j1 + 1, k1);
                placeLog(world, i1, j1 + 2, k1);
                placeLog(world, i1, j1 + 1, k1 + 1);
                largeDirect(world, random, 0, -1, i1, j1 + 1, k1, 1, 2, 0, 2);
        }
    }

    @SuppressWarnings({ "OverlyComplexMethod", "OverlyLongMethod" })
    void largeDirect(World world, Random rand, int dX, int dZ, int x, int y, int z, int size, int splitCount,
                     int splitCount1, int splitCount2)
    {
        int z1 = z;
        int x1 = x;
        int y1 = y;
        setLogDirection(dX, dZ);

        int dSize = 0;

        if (size == 2) dSize = 2;

        for (int next = 0; next <= 5 * size; next++)
        {
            if (size == 1) y1++;

            placeLog(world, x1, y1, z1);

            if (next <= 9 && size == 2) placeLog(world, x1 - dX, y1, z1 - dZ);

            if (next == 5 * size) branchAndLeaf(world, x1, y1 + 1, z1);

            if (size == 2) y1++;

            x1 += dX;
            z1 += dZ;

            if (next == splitCount)
            {
                firstBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount);
                secondBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount);
            }

            if (next == 3 * size && size == 2)
            {
                fifthBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount1);
                sixthBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount1);
            }

            if (next == 3 * size)
            {
                thirdBranchSplit(world, rand, x1, y1, z1, dX, dZ, 4 * size - dSize);
                fourthBranchSplit(world, rand, x1, y1, z1, dX, dZ, 4 * size - dSize);
            }

            if (next == 4 * size)
            {
                fifthBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount2);
                sixthBranchSplit(world, rand, x1, y1, z1, dX, dZ, splitCount2);
            }
        }
        clearLogDirection();
    }

    private void firstBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int splitCount)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;
        for (int i = 0; i <= splitCount; i++)
        {
            if (dX != 0)
            {
                if (rand.nextInt(5) > 0) if (dX == 1) x1--;
                else x1++;
                z1 += rand.nextInt(2);
            }

            if (dZ == 1)
            {
                x1 -= rand.nextInt(2);

                if (rand.nextInt(5) > 0) z1--;
            } else if (dZ == -1)
            {
                x1 += rand.nextInt(2);

                if (rand.nextInt(5) > 0) z1++;
            }

            y1++;
            placeLog(world, x1, y1, z1);

            if (i == splitCount) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void secondBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int splitCount)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;
        for (int i = 0; i <= splitCount; i++)
        {
            if (dX != 0)
            {
                if (rand.nextInt(5) > 0) if (dX == 1) x1--;
                else x1++;
                z1 -= rand.nextInt(2);
            }

            if (dZ == 1)
            {
                x1 += rand.nextInt(2);

                if (rand.nextInt(5) > 0) z1--;
            } else if (dZ == -1)
            {
                x1 -= rand.nextInt(2);

                if (rand.nextInt(5) > 0) z1++;
            }

            y1++;
            placeLog(world, x1, y1, z1);

            if (i == splitCount) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void thirdBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int length)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;

        for (int i = 0; i <= length; i++)
        {
            if (dX != 0)
            {
                if (dX == 1) x1 += rand.nextInt(2);
                else x1 -= rand.nextInt(2);
                z1 += rand.nextInt(2);
            }

            if (dZ != 0)
            {
                if (dZ == 1) z1 += rand.nextInt(2);
                else z1 -= rand.nextInt(2);
                x1 += rand.nextInt(2);
            }

            if (i >= 3) y1 += rand.nextInt(2);

            placeLog(world, x1, y1, z1);

            if (i == length) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void fourthBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int length)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;

        for (int i = 0; i <= length; i++)
        {
            if (dX != 0)
            {
                if (dX == 1) x1 += rand.nextInt(2);
                else x1 -= rand.nextInt(2);
                z1 -= rand.nextInt(2);
            }

            if (dZ != 0)
            {
                if (dZ == 1) z1 += rand.nextInt(2);
                else z1 -= rand.nextInt(2);

                x1 -= rand.nextInt(2);
            }
            if (i >= 3) y1 += rand.nextInt(2);

            placeLog(world, x1, y1, z1);

            if (i == length) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void fifthBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int splitCount2)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;

        for (int i = 0; i <= splitCount2; i++)
        {
            if (dX == 1)
            {
                x1 -= rand.nextInt(2);
                z1 += rand.nextInt(2);
            }

            if (dX == -1)
            {
                x1 += rand.nextInt(2);
                z1 += rand.nextInt(2);
            }

            if (dZ == 1)
            {
                x1 -= rand.nextInt(2);
                z1 -= rand.nextInt(2);
            }

            if (dZ == -1)
            {
                x1 += rand.nextInt(2);
                z1 += rand.nextInt(2);
            }

            y1++;
            placeLog(world, x1, y1, z1);

            if (i == splitCount2) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void sixthBranchSplit(World world, Random rand, int x, int y, int z, int dX, int dZ, int splitCount2)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;
        for (int i = 0; i <= splitCount2; i++)
        {
            if (dX != 0)
            {
                if (dX == 1)
                {
                    x1 -= rand.nextInt(2);
                } else
                {
                    x1 += rand.nextInt(2);
                }
                z1 -= rand.nextInt(2);
            }

            if (dZ == 1)
            {
                x1 += rand.nextInt(2);
                z1 -= rand.nextInt(2);
            } else if (dZ == -1)
            {
                x1 -= rand.nextInt(2);
                z1 += rand.nextInt(2);
            }

            y1++;
            placeLog(world, x1, y1, z1);

            if (i == splitCount2) branchAndLeaf(world, x1, y1, z1);
        }
    }

    private void clearLogDirection() {logDirection = 0;}

    @SuppressWarnings({
            "OverlyComplexBooleanExpression", "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops"
    })
    void branchAndLeaf(World world, int x, int y, int z)
    {
        placeLog(world, x, y, z);

        for (int dX = -3; dX <= 3; dX++)
        {
            for (int dZ = -3; dZ <= 3; dZ++)
            {
                if ((Math.abs(dX) != 3 || Math.abs(dZ) != 3) && (Math.abs(dX) != 2 || Math.abs(dZ) != 3) &&
                        (Math.abs(dX) != 3 || Math.abs(dZ) != 2)) placeLeaves(world, x + dX, y, z + dZ);

                if (Math.abs(dX) < 3 && Math.abs(dZ) < 3 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                    placeLeaves(world, x + dX, y + 1, z + dZ);
            }
        }
    }
}
