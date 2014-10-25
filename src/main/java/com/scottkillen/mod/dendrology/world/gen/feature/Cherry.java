package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.cherry.LargePinkCherry;
import com.scottkillen.mod.dendrology.world.gen.feature.cherry.LargeWhiteCherry;
import com.scottkillen.mod.dendrology.world.gen.feature.cherry.NormalPinkCherry;
import com.scottkillen.mod.dendrology.world.gen.feature.cherry.NormalWhiteCherry;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import java.util.Random;

public class Cherry extends WorldGenAbstractTree
{
    private final WorldGenAbstractTree treeGenPink;
    private final WorldGenAbstractTree treeGenWhite;
    private final WorldGenAbstractTree largeTreeGenPink;
    private final WorldGenAbstractTree largeTreeGenWhite;

    public Cherry(boolean isFromSapling)
    {
        super(isFromSapling);
        treeGenPink = new NormalPinkCherry(isFromSapling);
        largeTreeGenPink = new LargePinkCherry(isFromSapling);
        treeGenWhite = new NormalWhiteCherry(isFromSapling);
        largeTreeGenWhite = new LargeWhiteCherry(isFromSapling);
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextInt(10) < 9)
        {
            if (rand.nextInt(10) == 0) return treeGenWhite.generate(world, rand, x, y, z);

            return treeGenPink.generate(world, rand, x, y, z);
        }

        if (rand.nextInt(10) == 0) return largeTreeGenWhite.generate(world, rand, x, y, z);

        return largeTreeGenPink.generate(world, rand, x, y, z);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGenPink", treeGenPink).add("treeGenWhite", treeGenWhite).add("largeTreeGenPink", largeTreeGenPink).add("largeTreeGenWhite", largeTreeGenWhite).toString();
    }
}
