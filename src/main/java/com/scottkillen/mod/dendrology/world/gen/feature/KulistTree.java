package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.kulist.LargeKulistTree;
import com.scottkillen.mod.dendrology.world.gen.feature.kulist.LargeYellowKulistTree;
import com.scottkillen.mod.dendrology.world.gen.feature.kulist.NormalKulistTree;
import com.scottkillen.mod.dendrology.world.gen.feature.kulist.NormalYellowKulisttree;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import java.util.Random;

public class KulistTree extends WorldGenAbstractTree
{
    private final WorldGenAbstractTree treeGen;
    private final WorldGenAbstractTree treeGenYellow;
    private final WorldGenAbstractTree largeTreeGen;
    private final WorldGenAbstractTree largeTreeGenYellow;

    public KulistTree()
    {
        super(true);
        treeGen = new NormalKulistTree();
        treeGenYellow = new NormalYellowKulisttree();
        largeTreeGen = new LargeKulistTree();
        largeTreeGenYellow = new LargeYellowKulistTree();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextBoolean())
        {
            if (rand.nextInt(10) < 9) return treeGenYellow.generate(world, rand, x, y, z);

            return largeTreeGenYellow.generate(world, rand, x, y, z);
        }
        if (rand.nextInt(10) < 9) return treeGen.generate(world, rand, x, y, z);

        return largeTreeGen.generate(world, rand, x, y, z);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGen", treeGen).add("treeGenYellow", treeGenYellow).add(
                "largeTreeGen", largeTreeGen).add("largeTreeGenYellow", largeTreeGenYellow).toString();
    }
}
