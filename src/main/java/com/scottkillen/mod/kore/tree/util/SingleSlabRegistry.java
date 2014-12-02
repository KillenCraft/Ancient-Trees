package com.scottkillen.mod.kore.tree.util;

import com.google.common.collect.Sets;
import com.scottkillen.mod.kore.tree.block.ModWoodSlabBlock;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import java.util.Set;

import static com.google.common.base.Preconditions.checkNotNull;

public enum SingleSlabRegistry
{
    ;

    private static final Set<Block> slabBlocks = Sets.newHashSet();

    public static void add(ModWoodSlabBlock slabBlock) { slabBlocks.add(checkNotNull(slabBlock)); }

    public static boolean isSingleSlab(Block block) { return slabBlocks.contains(block); }

    public static boolean isSingleSlab(Item item) { return isSingleSlab(Block.getBlockFromItem(item)); }
}
