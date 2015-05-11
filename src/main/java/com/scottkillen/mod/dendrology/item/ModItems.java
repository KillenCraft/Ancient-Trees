package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraft.item.Item;

@ObjectHolder(TheMod.MOD_ID)
public final class ModItems
{
    public static final Item parcel = new SaplingParcel();

    @SuppressWarnings("MethodMayBeStatic")
    public void loadContent()
    {
        GameRegistry.registerItem(parcel, "parcel");
    }
}
