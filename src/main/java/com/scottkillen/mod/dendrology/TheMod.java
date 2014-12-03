package com.scottkillen.mod.dendrology;

import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.config.ConfigHandler;
import com.scottkillen.mod.dendrology.content.crafting.OreDict;
import com.scottkillen.mod.dendrology.content.crafting.Recipes;
import com.scottkillen.mod.dendrology.content.fuel.FuelHandler;
import com.scottkillen.mod.dendrology.item.ModItems;
import com.scottkillen.mod.dendrology.proxy.Proxy;
import com.scottkillen.mod.kore.common.OrganizesResources;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;

@Mod(modid = TheMod.MOD_ID, name = TheMod.MOD_NAME, version = TheMod.MOD_VERSION, useMetadata = true, guiFactory = TheMod.MOD_GUI_FACTORY)
public class TheMod implements OrganizesResources
{
    public static final String MOD_ID = "dendrology";
    public static final String MOD_NAME = "Ancient Trees";
    private static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ':';

    @SuppressWarnings({
            "StaticNonFinalField",
            "StaticVariableMayNotBeInitialized",
            "NonConstantFieldWithUpperCaseName"
    })
    @Instance(MOD_ID)
    public static TheMod INSTANCE;

    @SuppressWarnings("AnonymousInnerClass")
    private static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem()
        {
            return Item.getItemFromBlock(Blocks.sapling);
        }
    };
    @SuppressWarnings("WeakerAccess")
    static final String MOD_VERSION = "${mod_version}";
    @SuppressWarnings("WeakerAccess")
    static final String MOD_GUI_FACTORY = "com.scottkillen.mod.dendrology.config.client.ModGuiFactory";

    @SuppressWarnings("MethodMayBeStatic")
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event)
    {
        ConfigHandler.init(event.getSuggestedConfigurationFile());

        ModItems.init();
        ModBlocks.init();
    }

    @SuppressWarnings({ "MethodMayBeStatic", "UnusedParameters" })
    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent unused)
    {
        FMLCommonHandler.instance().bus().register(ConfigHandler.INSTANCE);

        OreDict.registerOres();
        Recipes.init();
    }

    @SuppressWarnings({ "MethodMayBeStatic", "UnusedParameters" })
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event)
    {
        Proxy.render.init();
        FuelHandler.register();
    }

    @Override
    public CreativeTabs getCreativeTab()
    {
        return CREATIVE_TAB;
    }

    @Override
    public String getResourcePrefix()
    {
        return RESOURCE_PREFIX;
    }
}
