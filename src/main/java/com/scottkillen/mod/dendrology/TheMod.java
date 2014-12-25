package com.scottkillen.mod.dendrology;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.compat.forestry.ForestryMod;
import com.scottkillen.mod.dendrology.compat.gardencollection.GardenCoreMod;
import com.scottkillen.mod.dendrology.compat.gardencollection.GardenTreesMod;
import com.scottkillen.mod.dendrology.compat.minechem.MinechemMod;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.crafting.OreDictHandler;
import com.scottkillen.mod.dendrology.content.crafting.Recipes;
import com.scottkillen.mod.dendrology.content.fuel.FuelHandler;
import com.scottkillen.mod.dendrology.proxy.Proxy;
import com.scottkillen.mod.kore.compat.Integrates;
import com.scottkillen.mod.kore.config.ConfigEventHandler;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraftforge.common.config.Configuration;
import java.util.List;

@Mod(modid = TheMod.MOD_ID, name = TheMod.MOD_NAME, version = TheMod.MOD_VERSION, useMetadata = true,
        dependencies = TheMod.MOD_DEPENDENCIES, guiFactory = TheMod.MOD_GUI_FACTORY)
public class TheMod
{
    public static final String MOD_ID = "dendrology";
    @SuppressWarnings("WeakerAccess")
    public static final String MOD_NAME = "Ancient Trees";
    @SuppressWarnings("AnonymousInnerClass")
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID.toLowerCase())
    {
        @Override
        public Item getTabIconItem() { return Item.getItemFromBlock(Blocks.sapling); }
    };
    @SuppressWarnings("WeakerAccess")
    static final String MOD_VERSION = "${mod_version}";
    @SuppressWarnings("WeakerAccess")
    static final String MOD_GUI_FACTORY = "com.scottkillen.mod.dendrology.config.client.ModGuiFactory";
    @SuppressWarnings("WeakerAccess")
    static final String MOD_DEPENDENCIES = "after:Forestry;after:minechem";
    private static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ':';
    private static final List<Integrates> integrators = Lists.newArrayList();
    @SuppressWarnings({
            "StaticNonFinalField", "StaticVariableMayNotBeInitialized", "NonConstantFieldWithUpperCaseName"
    })
    @Instance(MOD_ID)
    public static TheMod INSTANCE;
    @SuppressWarnings("StaticNonFinalField")
    private static Optional<ConfigEventHandler> configEventHandler = Optional.absent();

    private static void initIntegrators()
    {
        integrators.add(new MinechemMod());
        integrators.add(new ForestryMod());
        integrators.add(new GardenCoreMod());
        integrators.add(new GardenTreesMod());
    }

    public static Configuration configuration()
    {
        if (configEventHandler.isPresent()) return configEventHandler.get().configuration();
        return new Configuration();
    }

    private static void integrateMods(ModState modState)
    {
        for (final Integrates integrator : integrators)
            integrator.integrate(modState);
    }

    public static String getResourcePrefix() { return RESOURCE_PREFIX; }

    @SuppressWarnings("MethodMayBeStatic")
    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event)
    {
        //noinspection AssignmentToStaticFieldFromInstanceMethod
        configEventHandler = Optional.of(
                new ConfigEventHandler(MOD_ID, event.getSuggestedConfigurationFile(), Settings.INSTANCE,
                        Settings.CONFIG_VERSION));
        configEventHandler.get().activate();

        new ModBlocks().loadContent();
        initIntegrators();
        integrateMods(event.getModState());
    }

    @SuppressWarnings("MethodMayBeStatic")
    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event)
    {
        OreDictHandler.init();
        Recipes.init();
        integrateMods(event.getModState());
    }

    @SuppressWarnings("MethodMayBeStatic")
    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event)
    {
        Proxy.render.postInit();
        FuelHandler.postInit();
        integrateMods(event.getModState());
        integrators.clear();
    }
}
