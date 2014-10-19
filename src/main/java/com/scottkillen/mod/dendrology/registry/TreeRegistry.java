package com.scottkillen.mod.dendrology.registry;

import com.google.common.base.Objects;
import com.google.common.collect.Maps;
import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Map;
import java.util.Random;

public enum TreeRegistry
{
    ;

    private static final Map<Leaves, ImmutablePair<Item, Integer>> mapping = Maps.newHashMap();

    public static void addSaplingForLeaves(Block leaves, int leavesMetadata, Block sapling, int saplingMetadata)
    {
        mapping.put(new Leaves(leaves, leavesMetadata), ImmutablePair.of(Item.getItemFromBlock(sapling), saplingMetadata));
    }

    public static ImmutablePair<Item, Integer> getSapling(ModLeavesBlock leaves, int metadata)
    {
        return mapping.get(new Leaves(leaves, metadata));
    }

    public static void growTree(World world, int x, int y, int z, Random rand)
    {

    }

    public static void generateTrees(World world, Random rand, int chunkX, int chunkZ)
    {

    }

    private static final class Leaves
    {
        private final Block leaves;
        private final int metadata;

        private Leaves(Block leaves, int metadata)
        {
            this.leaves = leaves;
            this.metadata = metadata;
        }

        @Override
        public int hashCode()
        {
            return Objects.hashCode(leaves, metadata);
        }

        @Override
        public boolean equals(Object o)
        {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            final Leaves that = (Leaves) o;
            return metadata == that.metadata && leaves.equals(that.leaves);
        }

        @Override
        public String toString()
        {
            return Objects.toStringHelper(this).add("leaves", leaves).add("metadata", metadata).toString();
        }
    }
}
