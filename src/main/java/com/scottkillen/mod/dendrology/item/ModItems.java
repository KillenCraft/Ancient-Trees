package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.config.Settings;
import cpw.mods.fml.common.registry.GameRegistry;
import cpw.mods.fml.common.registry.GameRegistry.ObjectHolder;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.WeightedRandomChestContent;
import net.minecraftforge.common.ChestGenHooks;

@ObjectHolder(TheMod.MOD_ID)
public final class ModItems
{
    public static final Item parcel = new SaplingParcel();

    @SuppressWarnings("MethodMayBeStatic")
    public void loadContent()
    {
        GameRegistry.registerItem(parcel, "parcel");

        addParcelToChests();
    }

    private static void addParcelToChests()
    {
        for (final String chestType : Settings.chestTypes())
            addParcelToChest(chestType);
    }

    private static void addParcelToChest(String chestType)
    {
        final int rarity = Settings.INSTANCE.chestRarity(chestType);
        if (rarity <= 0) return;

        final ItemStack parcelStack = new ItemStack(parcel);
        final WeightedRandomChestContent chestContent = new WeightedRandomChestContent(parcelStack, 1, 2, rarity);

        final ChestGenHooks chestGenInfo = ChestGenHooks.getInfo(chestType);
        chestGenInfo.addItem(chestContent);
    }
}
