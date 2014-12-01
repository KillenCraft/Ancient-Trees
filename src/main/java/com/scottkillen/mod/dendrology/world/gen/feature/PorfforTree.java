package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.content.ITreeSpecies;
import com.scottkillen.mod.dendrology.world.gen.feature.porffor.LargePorfforTree;
import com.scottkillen.mod.dendrology.world.gen.feature.vanilla.VanillaTree;
import net.minecraft.world.World;
import java.util.Random;

public class PorfforTree extends AbstractTree
{
    private final AbstractTree treeGen;
    private final AbstractTree largeTreeGen;

    public PorfforTree()
    {
        treeGen = new VanillaTree();
        largeTreeGen = new LargePorfforTree();
    }

    @Override
    public void setTree(ITreeSpecies tree)
    {
        treeGen.setTree(tree);
        largeTreeGen.setTree(tree);
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
