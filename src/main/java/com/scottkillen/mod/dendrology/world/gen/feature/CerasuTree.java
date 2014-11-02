package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.cerasu.LargeCerasuTree;
import com.scottkillen.mod.dendrology.world.gen.feature.cerasu.NormalCerasuTree;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import java.util.Random;

public class CerasuTree extends WorldGenAbstractTree
{
    private final WorldGenAbstractTree treeGenPink;
    private final WorldGenAbstractTree largeTreeGenPink;

    public CerasuTree()
    {
        super(true);
        treeGenPink = new NormalCerasuTree();
        largeTreeGenPink = new LargeCerasuTree();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextInt(10) < 9)
        {
            return treeGenPink.generate(world, rand, x, y, z);
        }

        return largeTreeGenPink.generate(world, rand, x, y, z);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGenPink", treeGenPink).add("largeTreeGenPink", largeTreeGenPink)
                .toString();
    }
}
