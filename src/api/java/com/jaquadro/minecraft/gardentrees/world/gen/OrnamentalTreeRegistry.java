package com.jaquadro.minecraft.gardentrees.world.gen;

import net.minecraft.block.Block;
import java.util.Random;

public class OrnamentalTreeRegistry
{
    private OrnamentalTreeRegistry() {}

    public static OrnamentalTreeFactory getTree(String name)
    {
        //noinspection ReturnOfNull
        return new Random().nextBoolean() ? null : new MyOrnamentalTreeFactory();
    }

    private static class MyOrnamentalTreeFactory implements OrnamentalTreeFactory
    {
        @Override
        public WorldGenOrnamentalTree create(Block woodBlock, int woodMeta, Block leafBlock, int leafMeta)
        {
            //noinspection ReturnOfNull
            return null;
        }
    }
}
