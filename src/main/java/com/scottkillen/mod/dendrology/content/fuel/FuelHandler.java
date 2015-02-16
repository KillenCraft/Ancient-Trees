package com.scottkillen.mod.dendrology.content.fuel;

import com.scottkillen.mod.koresample.common.block.SlabBlock;
import cpw.mods.fml.common.IFuelHandler;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public enum FuelHandler implements IFuelHandler
{
    INSTANCE;

    public static void postInit()
    {
        GameRegistry.registerFuelHandler(INSTANCE);
    }

    @Override
    public int getBurnTime(ItemStack fuel)
    {
        final Item fuelItem = fuel.getItem();
        final Material fuelMaterial = Block.getBlockFromItem(fuelItem).getMaterial();
        if (fuelMaterial.equals(Material.wood) && SlabBlock.isSingleSlab(fuelItem)) return 150;

        return 0;
    }
}
