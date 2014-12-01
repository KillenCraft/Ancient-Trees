package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.content.ITreeSpecies;
import com.scottkillen.mod.dendrology.world.gen.feature.ewcaly.LargeEwcalyTree;
import com.scottkillen.mod.dendrology.world.gen.feature.ewcaly.NormalEwcalyTree;
import net.minecraft.world.World;
import java.util.Random;

public class EwcalyTree extends AbstractTree
{
    private final AbstractTree treeGen;
    private final AbstractTree largeTreeGen;

    public EwcalyTree()
    {
        treeGen = new NormalEwcalyTree();
        largeTreeGen = new LargeEwcalyTree();
    }

    @Override
    public void setTree(ITreeSpecies tree)
    {
        treeGen.setTree(tree);
        largeTreeGen.setTree(tree);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGen", treeGen).add("largeTreeGen", largeTreeGen).toString();
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
        if (rand.nextInt(7) > 1) return treeGen.generate(world, rand, x, y, z);

        return largeTreeGen.generate(world, rand, x, y, z);
    }
}
