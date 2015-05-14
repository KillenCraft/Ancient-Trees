package com.scottkillen.mod.dendrology.item;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.ParcelManager;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ChatComponentText;
import net.minecraft.util.StatCollector;
import net.minecraft.world.World;
import java.util.List;

public class SaplingParcel extends Item
{
    public SaplingParcel()
    {
        setCreativeTab(TheMod.INSTANCE.creativeTab());
        setUnlocalizedName("parcel");
    }

    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List information, boolean unused)
    {
        //noinspection unchecked
        information.add(StatCollector.translateToLocal(String.format("%s%s", TheMod.getResourcePrefix(), "parcel.tooltip")));
    }

    private static String getUnwrappedUnlocalizedName(String unlocalizedName)
    {
        return unlocalizedName.substring(unlocalizedName.indexOf('.') + 1);
    }

    @Override
    public String getUnlocalizedName()
    {
        return String.format("item.%s%s", TheMod.getResourcePrefix(),
                getUnwrappedUnlocalizedName(super.getUnlocalizedName()));
    }

    @Override
    public ItemStack onItemRightClick(ItemStack itemStack, World world, EntityPlayer player)
    {
        if (!world.isRemote)
        {
            final ItemStack content = ParcelManager.INSTANCE.randomItem();

            final String message;
            if (content == null)
                message = StatCollector.translateToLocal("dendrology:parcel.empty");
            else
            {
                final String itemName = StatCollector.translateToLocal(content.getItem().getUnlocalizedName(content) + ".name");
                message = StatCollector.translateToLocalFormatted("dendrology:parcel.full", itemName);

                final EntityItem entityItem = player.dropPlayerItemWithRandomChoice(content, false);
                if (entityItem != null)
                {
                    entityItem.delayBeforeCanPickup = 0;
                    entityItem.func_145797_a(player.getCommandSenderName());
                }
            }

            player.addChatMessage(new ChatComponentText(message));
            itemStack.stackSize--;
        }
        return itemStack;
    }

    @Override
    public String getUnlocalizedName(ItemStack unused) { return getUnlocalizedName(); }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerIcons(IIconRegister iconRegister)
    {
        itemIcon = iconRegister.registerIcon(getUnlocalizedName().substring(getUnlocalizedName().indexOf('.') + 1));
    }
}
