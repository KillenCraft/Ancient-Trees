package com.scottkillen.mod.dendrology.content.fuel;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;

public enum FuelHandler implements IFuelHandler
{
    INSTANCE;

    public static void register()
    {
        GameRegistry.registerFuelHandler(INSTANCE);
    }

    @SuppressWarnings("SuspiciousMethodCalls")
    @Override
    public int getBurnTime(ItemStack fuel)
    {
        final Block block = Block.getBlockFromItem(fuel.getItem());

        if (ModBlocks.SINGLE_SLAB_BLOCKS.contains(block)) return 150;

        return 0;
    }
}
