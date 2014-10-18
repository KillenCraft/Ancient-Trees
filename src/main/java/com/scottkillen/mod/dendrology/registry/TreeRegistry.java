package com.scottkillen.mod.dendrology.registry;

import com.scottkillen.mod.dendrology.block.ModLeavesBlock;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import org.apache.commons.lang3.tuple.ImmutablePair;
import java.util.Random;

public enum TreeRegistry
{
    ;

    public static ImmutablePair<Item, Integer> getSapling(ModLeavesBlock leavesBlock, int metadata)
    {
        return null;
    }

    public static void growTree(World world, int x, int y, int z, Random rand)
    {

    }
}
