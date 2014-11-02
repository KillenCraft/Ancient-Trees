package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.world.gen.feature.acemus.LargeAcemusTree;
import com.scottkillen.mod.dendrology.world.gen.feature.acemus.NormalAcemusTree;
import com.scottkillen.mod.dendrology.world.gen.feature.porffor.LargePorfforTree;
import com.scottkillen.mod.dendrology.world.gen.feature.porffor.NormalPorfforTree;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import java.util.Random;

public class AcemusTree extends WorldGenAbstractTree
{
    private final WorldGenAbstractTree treeGen;
    private final WorldGenAbstractTree largeTreeGen;

    public AcemusTree()
    {
        super(true);
        treeGen = new NormalAcemusTree();
        largeTreeGen = new LargeAcemusTree();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextInt(10) < 9) return treeGen.generate(world, rand, x, y, z);

        return largeTreeGen.generate(world, rand, x, y, z);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGen", treeGen).add("largeTreeGen", largeTreeGen).toString();
    }
}
