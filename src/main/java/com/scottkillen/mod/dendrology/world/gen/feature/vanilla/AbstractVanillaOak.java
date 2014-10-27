package com.scottkillen.mod.dendrology.world.gen.feature.vanilla;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.world.gen.feature.AbstractTree;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import java.util.Random;

public abstract class AbstractVanillaOak extends AbstractTree
{
    private final boolean isFromSapling;

    protected AbstractVanillaOak(boolean isFromSapling)
    {
        super(isFromSapling);
        this.isFromSapling = isFromSapling;
    }

    @SuppressWarnings({ "NestedConditionalExpression", "MethodWithMultipleLoops", "NumericCastThatLosesPrecision" })
    @Override
    protected boolean hasRoomToGrow(World world, int x, int y, int z, int height)
    {
        for (int y1 = y; y1 <= y + 1 + height; ++y1)
        {
            final byte radius = (byte) (y1 >= y + 1 + height - 2 ? 2 : y1 == y ? 0 : 1);
            for (int x1 = x - radius; x1 <= x + radius; ++x1)
                for (int z1 = z - radius; z1 <= z + radius; ++z1)
                    if (y1 >= 0 && y1 < 256)
                    {
                        if (!isReplaceable(world, x1, y1, z1)) return false;
                    } else return false;
        }
        return true;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final int height = 4 + rand.nextInt(3) + (isFromSapling ? rand.nextInt(7) : 0);

        if (isPoorGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        placeCanopy(world, rand, x, y, z, height);

        for (int dY = 0; dY < height; ++dY)
            placeLog(world, x, y + dY, z);

        return true;
    }

    @SuppressWarnings({ "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops" })
    private void placeCanopy(World world, Random rand, int x, int y, int z, int height)
    {
        for (int y1 = y - 3 + height; y1 <= y + height; ++y1)
        {
            final int distanceToTopOfTrunk = y1 - (y + height);
            final int radius = 1 - distanceToTopOfTrunk / 2;

            for (int x1 = x - radius; x1 <= x + radius; ++x1)
            {
                final int dX = x1 - x;

                for (int z1 = z - radius; z1 <= z + radius; ++z1)
                {
                    final int dZ = z1 - z;

                    //noinspection OverlyComplexBooleanExpression
                    if (Math.abs(dX) != radius || Math.abs(dZ) != radius || rand.nextInt(2) != 0 && distanceToTopOfTrunk != 0)
                        placeLeaves(world, x1, y1, z1);
                }
            }
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("isFromSapling", isFromSapling).toString();
    }
}
