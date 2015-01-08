package com.scottkillen.mod.dendrology.world.gen.feature.hekur;

import net.minecraft.world.World;
import java.util.Random;

public class LargeHekurTree extends NormalHekurTree
{
    public LargeHekurTree(boolean fromSapling) { super(fromSapling); }

    @Override
    protected void growTrunk(World world, Random random, int i1, int j1, int k1)
    {
        placeLog(world, i1, j1, k1);

        switch (random.nextInt(4))
        {
            case 0:
                placeLog(world, i1 + 1, j1, k1);
                placeLog(world, i1 + 1, j1 + 1, k1);
                largeDirect(world, random, 1, 0, i1, j1 + 1, k1, 2, 5, 4, 3);
                break;

            case 1:
                placeLog(world, i1, j1, k1 + 1);
                placeLog(world, i1, j1 + 1, k1 + 1);
                largeDirect(world, random, 0, 1, i1, j1 + 1, k1, 2, 5, 4, 3);
                break;

            case 2:
                placeLog(world, i1 - 1, j1, k1);
                placeLog(world, i1 - 1, j1 + 1, k1);
                largeDirect(world, random, -1, 0, i1, j1 + 1, k1, 2, 5, 4, 3);
                break;

            default:
                placeLog(world, i1, j1, k1 - 1);
                placeLog(world, i1, j1 + 1, k1 - 1);
                largeDirect(world, random, 0, -1, i1, j1 + 1, k1, 2, 5, 4, 3);
        }
    }
}
