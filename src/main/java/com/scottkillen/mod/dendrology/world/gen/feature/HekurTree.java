package com.scottkillen.mod.dendrology.world.gen.feature;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.content.ISpecies;
import com.scottkillen.mod.dendrology.world.gen.feature.hekur.LargeHekurTree;
import com.scottkillen.mod.dendrology.world.gen.feature.hekur.NormalHekurTree;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import java.util.Random;

public class HekurTree extends AbstractTree
{
    private final AbstractTree treeGen;
    private final AbstractTree largeTreeGen;

    public HekurTree()
    {
        treeGen = new NormalHekurTree();
        largeTreeGen = new LargeHekurTree();
    }

    @Override
    public void setTree(ISpecies tree)
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
        int y1 = y;
        while (world.getBlock(x, y1 - 1, z).getMaterial().equals(Material.water)) y1--;

        if (rand.nextInt(10) < 9) return treeGen.generate(world, rand, x, y1, z);

        return largeTreeGen.generate(world, rand, x, y1, z);
    }
}
