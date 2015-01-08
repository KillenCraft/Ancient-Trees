package com.scottkillen.mod.dendrology.world.gen.feature;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import java.util.Random;

public class DelnasTree extends AbstractTree
{
    public DelnasTree(boolean fromSapling) { super(fromSapling); }

    public DelnasTree() { this(true); }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int height = rng.nextInt(5) + 6;

        if (isPoorGrowthConditions(world, x, y, z, height, getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        if (rng.nextInt(10) > 0) for (int dY = 0; dY <= height; dY++)
        {
            placeLog(world, x, y + dY, z);

            if (dY == height) leafGen(world, x, y + dY, z);
        }
        else switch (rand.nextInt(4))
        {
            case 0:
                growDirect(world, rng, x, y, z, 1, 0, height);
                break;

            case 1:
                growDirect(world, rng, x, y, z, 0, 1, height);
                break;

            case 2:
                growDirect(world, rng, x, y, z, -1, 0, height);
                break;

            default:
                growDirect(world, rng, x, y, z, 0, -1, height);
        }

        return true;
    }

    @SuppressWarnings({
            "MethodWithMoreThanThreeNegations", "MethodWithMultipleLoops", "OverlyComplexBooleanExpression"
    })
    private void leafGen(World world, int x, int y, int z)
    {
        for (int dX = -3; dX <= 3; dX++)
            for (int dZ = -3; dZ <= 3; dZ++)
            {
                if (Math.abs(dX) + Math.abs(dZ) <= 3 &&
                        !(Math.abs(dX) + Math.abs(dZ) == 3 && Math.abs(dX) != 0 && Math.abs(dZ) != 0))
                    placeLeaves(world, x + dX, y, z + dZ);
                if (Math.abs(dX) < 2 && Math.abs(dZ) < 2 && (Math.abs(dX) != 1 || Math.abs(dZ) != 1))
                    placeLeaves(world, x + dX, y + 1, z + dZ);
            }
    }

    private void growDirect(World world, Random rand, int x, int y, int z, int dX, int dZ, int hight)
    {
        int x1 = x;
        int z1 = z;

        placeLog(world, x1, y, z1);

        if (dX == 1) placeLog(world, x1 - 1, y, z1);

        if (dX == -1) placeLog(world, x1 + 1, y, z1);

        if (dZ == 1) placeLog(world, x1, y, z1 - 1);

        if (dZ == -1) placeLog(world, x1, y, z1 + 1);

        int addlRandomLengthX = 0;
        int addlRandomLengthZ = 0;
        for (int level = 0; level <= hight; level++)
        {
            if (dX == 1 && rand.nextInt(2 + addlRandomLengthX) == 0) x1++;

            if (dX == -1 && rand.nextInt(2 + addlRandomLengthX) == 0) x1--;

            if (dZ == 1 && rand.nextInt(2 + addlRandomLengthZ) == 0) z1++;

            if (dZ == -1 && rand.nextInt(2 + addlRandomLengthZ) == 0) z1--;

            addlRandomLengthX++;
            addlRandomLengthZ++;
            placeLog(world, x1, level + y, z1);
        }
        leafGen(world, x1, hight + y, z1);
    }
}
