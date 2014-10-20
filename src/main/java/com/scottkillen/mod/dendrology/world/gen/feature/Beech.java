package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.Random;

public class Beech extends ModWorldGenAbstractTree
{
    private static final ImmutableList<ImmutableSet<ForgeDirection>> branchDirections = ImmutableList.of(WEST, EAST, NORTH, SOUTH, SOUTHWEST, NORTHWEST, SOUTHEAST, NORTHEAST);
    private int logDirection = 0;

    public Beech(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final int height = rand.nextInt(15) + 6;

        if (!goodGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = 0; level <= height; level++)
        {
            placeLog(world, x, y + level, z);

            if (level == height) leafGen(world, x, y + level, z);

            if (level > 3 && level < height)
            {
                final int hl = height / level + 1;
                generateBranches(world, rand, x, y, z, height, level, hl);
            }
        }

        return true;
    }

    private void generateBranches(World world, Random rand, int x, int y, int z, int height, int level, int hl)
    {
        for (final ImmutableSet<ForgeDirection> direction : branchDirections)
            if (rand.nextInt(hl) == 0) branch(world, rand, height, x, y, level, z, direction);
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    private void branch(World world, Random rand, int height, int x, int y, int level, int z, ImmutableSet<ForgeDirection> directions)
    {
        final int length = height - level;
        level += y;

        logDirection = 4; // EAST/WEST

        int offsetX = 0;
        int offsetZ = 0;

        for (final ForgeDirection direction : directions)
        {
            offsetX += direction.offsetZ;
            offsetZ += direction.offsetX;

            if (direction == ForgeDirection.NORTH || direction == ForgeDirection.SOUTH)
                logDirection = 8;
        }

        extendBranch(world, rand, x, level, z, length, offsetX, offsetZ);

        logDirection = 0; // UP/DOWN
    }

    @SuppressWarnings("AssignmentToMethodParameter")
    private void extendBranch(World world, Random rand, int x, int level, int z, int branchLength, int offsetX, int offsetZ)
    {
        int index = 0;
        while (index <= branchLength)
        {
            x += offsetX;
            z += offsetZ;

            if (offsetX == 0 && rand.nextInt(4) == 0)
            {
                x += rand.nextInt(3) - 1;
            }
            if (offsetZ == 0 && rand.nextInt(4) == 0)
            {
                z += rand.nextInt(3) - 1;
            }

            placeLog(world, x, level, z);

            if (rand.nextInt(3) == 0)
            {
                leafGen(world, x, level, z);
            }

            if (rand.nextInt(3) > 0)
            {
                level++;
            }

            if (index == branchLength)
            {
                placeLog(world, x, level, z);
                leafGen(world, x, level, z);
            }

            index++;
        }
    }

    @SuppressWarnings({ "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops" })
    private void leafGen(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
        {
            for (int dZ = -3; dZ <= 3; dZ++)
            {
                //noinspection OverlyComplexBooleanExpression
                if ((Math.abs(dX) != 3 || Math.abs(dZ) != 3) && (Math.abs(dX) != 2 || Math.abs(dZ) != 3) && (Math.abs(dX) != 3 || Math.abs(dZ) != 2))
                {
                    placeLeaves(world, x + dX, y, z + dZ);
                }

                //noinspection OverlyComplexBooleanExpression
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

    @Override
    protected Block getLeavesBlock() {return ModBlocks.leaves0;}

    @Override
    protected int getLeavesMetadata() {return 0;}

    @Override
    protected Block getLogBlock() {return ModBlocks.logs0;}

    @Override
    protected int getLogMetadata() {return 0 | logDirection;}
}
