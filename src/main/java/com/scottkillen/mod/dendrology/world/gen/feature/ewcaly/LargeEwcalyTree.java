package com.scottkillen.mod.dendrology.world.gen.feature.ewcaly;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import java.util.Random;

@SuppressWarnings("MethodWithMoreThanThreeNegations")
public class LargeEwcalyTree extends NormalEwcalyTree
{
    private static final ImmutableList<ImmutableSet<ForgeDirection>> branchDirections = ImmutableList.of(WEST, EAST, NORTH, SOUTH, SOUTHWEST, NORTHWEST, SOUTHEAST, NORTHEAST);

    public LargeEwcalyTree(boolean isFromSapling)
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
        final int height = rand.nextInt(24) + 8;

        if (isPoorGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int dY = 0; dY <= height; dY++)
            placeLog(world, x, y + dY, z);

        int size = 1;

        for (int y1 = y + height / 2; y1 <= y + height; y1++)
            if (y1 == y + height || rand.nextInt(5) > 2)
            {
                if (rand.nextInt(20) < 1) size = 2;

                if (rand.nextInt(4) == 0 && y1 - y > 10 && y1 - y < 20) size = 2;

                if (y1 - y >= 20) size = 3;

                for (int dX = -size; dX <= size; dX++)
                    for (int dZ = -size; dZ <= size; dZ++)
                    {
                        placeLeaves(world, x + dX, y1, z + dZ);

                        //noinspection OverlyComplexBooleanExpression
                        if (!(size == 3 && (Math.abs(dX) == 3 && Math.abs(dZ) == 2 || Math.abs(dX) == 2 && Math.abs(dZ) == 3)) && y1 == y + height && Math.abs(dX) < 3 && Math.abs(dZ) < 3 && (Math.abs(dX) != 2 || Math.abs(dZ) != 2))
                        {
                            if (size > 1) placeLeaves(world, x + dX, y1 + 1, z + dZ);

                            if (size == 1 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                                placeLeaves(world, x + dX, y1 + 1, z + dZ);
                        }
                    }
            }

        for (int level = height / 2; level <= height - 5; level++)
            generateBranches(world, rand, x, y, z, height, level);

        return true;
    }

    @Override
    protected Block getLeavesBlock() { return ModBlocks.leaves0; }

    @Override
    protected int getLeavesMetadata() { return 3; }

    @Override
    protected Block getLogBlock() { return ModBlocks.logs1; }

    @Override
    protected int getLogMetadata() { return logDirection; }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logDirection", logDirection).toString();
    }
}
