package com.scottkillen.mod.dendrology.world.gen.feature.hekur;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Random;

import static net.minecraftforge.common.util.ForgeDirection.UP;

public class NormalHekurTree extends AbstractTree
{
    protected int logDirection = 0;

    public NormalHekurTree() { super(); }

    @Override
    protected boolean isPoorGrowthConditions(World world, int x, int y, int z, int unused, IPlantable plantable)
    {
        final Block block = world.getBlock(x, y - 1, z);
        return !block.canSustainPlant(world, x, y - 1, z, UP, plantable);
    }

    @Override
    protected Block getLeavesBlock()
    {
        return ModBlocks.leaves1;
    }

    @Override
    protected int getLeavesMetadata()
    {
        return 0;
    }

    @Override
    protected Block getLogBlock()
    {
        return ModBlocks.logs1;
    }

    @Override
    protected int getLogMetadata()
    {
        return 2 | logDirection;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random random = new Random();
        random.setSeed(rand.nextLong());

        if (isPoorGrowthConditions(world, x, y, z, 0, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (final ImmutablePair<Integer, Integer> branchDirection : BRANCH_DIRECTIONS)
            if (random.nextInt(3) == 0) rootGen(world, random, x, y, z, branchDirection.getLeft(), branchDirection.getRight());

        rootGen(world, random, x, y, z, 0, 0);
        growTrunk(world, random, x, y, z);

        return true;
    }

    protected void rootGen(World world, Random rand, int x, int y, int z, int dX, int dZ)
    {
        int x1 = x;
        int y1 = y;
        int z1 = z;

        if (dX != 0)
            logDirection = 4;

        if (dZ != 0)
            logDirection = 8;

        for (int i = 0; i < 6; i++)
        {
            if (rand.nextInt(3) == 0)
            {
                if (dX == -1) x1--;

                if (dX == 1 && rand.nextInt(3) == 0) x1++;

                if (dZ == -1 && rand.nextInt(3) == 0) z1--;

                if (dZ == 1 && rand.nextInt(3) == 0) z1++;
            }

            placeRoot(world, x1, y1, z1);

            y1--;
        }

        logDirection = 0;
    }

    protected boolean canBeReplacedByRoot(World world, int x, int y, int z)
    {
        final Block block = world.getBlock(x, y, z);
        final Material material = block.getMaterial();

        return canBeReplacedByLog(world, x, y, z) || material.equals(Material.sand) || material.equals(Material.ground);
    }

    protected boolean placeRoot(World world, int x, int y, int z)
    {
        if (canBeReplacedByRoot(world, x, y, z))
        {
            setBlockAndNotifyAdequately(world, x, y, z, getLogBlock(), getLogMetadata());
            return true;
        }
        return false;
    }

    protected void growTrunk(World world, Random random, int i1, int j1, int k1)
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

    @SuppressWarnings({ "ConstantConditions", "MethodWithMultipleLoops", "OverlyComplexMethod", "OverlyLongMethod" })
    protected void largeDirect(World world, Random rand, int dX, int dZ, int x, int y, int z, int size, int splitCount,
                               int splitCount1, int splitCount2)
    {
        int z1 = z;
        int x1 = x;
        int y1 = y;
        if (dX != 0) logDirection = 4;
        if (dZ != 0) logDirection = 8;

        int dSize = 0;

        if (size == 2)
        {
            dSize = 2;
        }

        for (int next = 0; next <= 5 * size; next++)
        {
            if (size == 1) y1++;

            placeLog(world, x1, y1, z1);

            if (next <= 9 && size == 2) placeLog(world, x1 - dX, y1, z1 - dZ);

            if (next == 5 * size) branchAndLeaf(world, x1, y1 + 1, z1);

            if (size == 2) y1++;

            x1 += dX;
            z1 += dZ;

            int i3;
            int j3;
            int k3;
            if (next == splitCount)
            {
                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount; i++)
                {
                    if (dX == 1)
                    {
                        if (rand.nextInt(5) > 0) i3--;

                        k3 += rand.nextInt(2);
                    } else if (dX == -1)
                    {
                        if (rand.nextInt(5) > 0) i3++;

                        k3 += rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 -= rand.nextInt(2);

                        if (rand.nextInt(5) > 0) k3--;
                    } else if (dZ == -1)
                    {
                        i3 += rand.nextInt(2);

                        if (rand.nextInt(5) > 0) k3++;
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount) branchAndLeaf(world, i3, j3, k3);
                }

                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount; i++)
                {
                    if (dX == 1)
                    {
                        if (rand.nextInt(5) > 0) i3--;

                        k3 -= rand.nextInt(2);
                    } else if (dX == -1)
                    {
                        if (rand.nextInt(5) > 0) i3++;

                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 += rand.nextInt(2);

                        if (rand.nextInt(5) > 0) k3--;
                    } else if (dZ == -1)
                    {
                        i3 -= rand.nextInt(2);

                        if (rand.nextInt(5) > 0) k3++;
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount) branchAndLeaf(world, i3, j3, k3);
                }
            }

            if (next == 3 * size && size == 2)
            {
                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount1; i++)
                {
                    if (dX == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount1) branchAndLeaf(world, i3, j3, k3);
                }

                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount1; i++)
                {
                    if (dX == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount1) branchAndLeaf(world, i3, j3, k3);
                }
            }

            if (next == 3 * size)
            {
                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= 4 * size - dSize; i++)
                {
                    if (dX == 1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (i >= 3) j3 += rand.nextInt(2);

                    placeLog(world, i3, j3, k3);

                    if (i == 4 * size - dSize) branchAndLeaf(world, i3, j3, k3);
                }

                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= 4 * size - dSize; i++)
                {
                    if (dX == 1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (i >= 3) j3 += rand.nextInt(2);

                    placeLog(world, i3, j3, k3);

                    if (i == 4 * size - dSize) branchAndLeaf(world, i3, j3, k3);
                }
            }

            if (next == 4 * size)
            {
                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount2; i++)
                {
                    if (dX == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount2) branchAndLeaf(world, i3, j3, k3);
                }

                i3 = x1;
                j3 = y1;
                k3 = z1;

                for (int i = 0; i <= splitCount2; i++)
                {
                    if (dX == 1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dX == -1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == 1)
                    {
                        i3 += rand.nextInt(2);
                        k3 -= rand.nextInt(2);
                    }

                    if (dZ == -1)
                    {
                        i3 -= rand.nextInt(2);
                        k3 += rand.nextInt(2);
                    }

                    j3++;
                    placeLog(world, i3, j3, k3);

                    if (i == splitCount2) branchAndLeaf(world, i3, j3, k3);
                }
            }
        }
        logDirection = 0;
    }

    protected void branchAndLeaf(World world, int x, int y, int z)
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

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }
}
