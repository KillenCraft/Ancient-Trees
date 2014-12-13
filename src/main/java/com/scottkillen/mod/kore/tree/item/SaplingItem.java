package com.scottkillen.mod.kore.tree.item;

import com.google.common.base.Objects;
import com.scottkillen.mod.kore.tree.block.ModSaplingBlock;
import net.minecraft.block.Block;
import net.minecraft.item.ItemMultiTexture;
import net.minecraft.item.ItemStack;

public class SaplingItem extends ItemMultiTexture
{
    private final ModSaplingBlock sapling;
    public SaplingItem(Block block, ModSaplingBlock sapling, String[] names)
    {
        super(block, sapling, names);
        this.sapling = sapling;
    }

    @Override
    public String getPotionEffect(ItemStack itemStack) {
        return sapling.getPotionEffect(itemStack);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("sapling", sapling).toString();
    }
}
