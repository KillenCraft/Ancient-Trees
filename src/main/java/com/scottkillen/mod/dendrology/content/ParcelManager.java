package com.scottkillen.mod.dendrology.content;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.common.util.WeightedSet;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;

public enum ParcelManager
{
    INSTANCE;

    @SuppressWarnings("NonSerializableFieldInSerializableClass")
    private final WeightedSet<ItemStack> potentialContent = WeightedSet.newWeightedSet();

    public void add(ItemStack itemStack, int weight)
    {
        final ItemStack element;
        if (itemStack == null)
            element = null;
        else
        {
            element = itemStack.copy();
            element.stackSize = 1;
        }
        potentialContent.setWeight(element, weight);
    }

    public ItemStack randomItem() { return potentialContent.randomPick(); }

    @SuppressWarnings({ "MethodWithMultipleLoops", "ObjectAllocationInLoop" })
    public void init()
    {
        add(null, 600);
        for (int i = 0; i < BlockSapling.field_149882_a.length; i++)
            add(new ItemStack(Blocks.sapling, 1, i), 10);

        for (final OverworldTreeSpecies species : OverworldTreeSpecies.values())
            add(new ItemStack(species.saplingBlock(), 1, species.saplingSubBlockIndex()), 10);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("potentialContent", potentialContent).toString();
    }
}
