package com.scottkillen.mod.dendrology.world.gen.feature.kulist;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import java.util.Random;

public class LargeKulistTree extends NormalKulistTree
{
    public LargeKulistTree(boolean isFromSapling)
    {
        super(isFromSapling);
    }

    @SuppressWarnings({ "OverlyComplexMethod", "OverlyLongMethod" })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int height = rng.nextInt(9) + 9;

        if (isPoorGrowthConditions(world, x, y, z, height, ModBlocks.sapling0)) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = 0; level <= height; level++)
        {
            placeLog(world, x, y + level, z);

            if (level == height)
                leafGen(world, x, y + level, z);

            if (level > 3 && level < height)
            {
                final int branchRarity = height / level + 1;

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, -1, 0);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, 1, 0);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, 0, -1);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, 0, 1);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, -1, 1);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, -1, -1);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, 1, 1);

                if (rng.nextInt(branchRarity) == 0)
                    branch(world, rng, x, y, z, height, level, 1, -1);
            }
        }
        return true;
    }

    @Override
    protected Block getLeavesBlock()
    {
        return ModBlocks.leaves1;
    }

    @Override
    protected int getLeavesMetadata()
    {
        return 1;
    }
}
