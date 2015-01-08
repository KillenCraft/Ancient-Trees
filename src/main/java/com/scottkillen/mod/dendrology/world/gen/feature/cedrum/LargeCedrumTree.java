package com.scottkillen.mod.dendrology.world.gen.feature.cedrum;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import java.util.Random;

public class LargeCedrumTree extends NormalCedrumTree
{
    public LargeCedrumTree(boolean fromSapling) { super(fromSapling); }

    @SuppressWarnings({ "MethodWithMultipleLoops", "OverlyComplexMethod" })
    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        final Random rng = new Random();
        rng.setSeed(rand.nextLong());

        final int height = rng.nextInt(12) + 12;

        if (isPoorGrowthConditions(world, x, y, z, height, getSaplingBlock())) return false;

        final Block block = world.getBlock(x, y - 1, z);
        block.onPlantGrow(world, x, y - 1, z, x, y, z);

        for (int level = height; level >= 0; level--)
        {
            placeLog(world, x, y + level, z);

            if (level > 5 && level < height)
            {
                if (level == height - 1)
                {
                    leafGen(world, 2, x, y + level, z);
                }

                //noinspection OverlyComplexBooleanExpression
                if (level == height - 4 || level == height - 7 || level == height - 10 || level == height - 13)
                {
                    for (int next = 1; next < 3; next++)
                    {
                        logDirection = 4;
                        placeLog(world, x + next, y + level - 2, z);
                        placeLog(world, x - next, y + level - 2, z);
                        logDirection = 8;
                        placeLog(world, x, y + level - 2, z + next);
                        placeLog(world, x, y + level - 2, z - next);
                        logDirection = 0;
                    }
                    //noinspection NestedConditionalExpression
                    final int size = level == height - 4 ? 3 :
                            level == height - 7 ? 4 : level == height - 10 ? 5 : rng.nextInt(3) + 2;
                    leafGen(world, size, x, y + level, z);
                }
            }

            if (level == height) leafTop(world, x, y + level, z);
        }

        return true;
    }
}
