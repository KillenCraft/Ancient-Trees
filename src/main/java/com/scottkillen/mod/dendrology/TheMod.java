package com.scottkillen.mod.dendrology;

import com.google.common.base.Objects;
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
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.proxy.Proxy;
import com.scottkillen.mod.dendrology.kore.compat.Integrates;
import com.scottkillen.mod.dendrology.kore.config.ConfigEventHandler;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.config.Configuration;
import java.util.List;

@SuppressWarnings({
        "AnonymousInnerClass",
        "StaticNonFinalField",
        "WeakerAccess",
        "StaticVariableMayNotBeInitialized",
        "NonConstantFieldWithUpperCaseName"
})
@Mod(modid = TheMod.MOD_ID, name = TheMod.MOD_NAME, version = TheMod.MOD_VERSION, useMetadata = true,
        dependencies = TheMod.MOD_DEPENDENCIES, guiFactory = TheMod.MOD_GUI_FACTORY)
public class TheMod
{
    public static final String MOD_ID = "dendrology";
    public static final String MOD_NAME = "Ancient Trees";
    public static final String MOD_VERSION = "${mod_version}";
    public static final String MOD_GUI_FACTORY = "com.scottkillen.mod.dendrology.config.client.ModGuiFactory";
    public static final String MOD_DEPENDENCIES = "after:Forestry;after:minechem";
    public static final CreativeTabs CREATIVE_TAB = new CreativeTabs(MOD_ID.toLowerCase())
    {
        private final OverworldTreeSpecies ICON = OverworldTreeSpecies.PORFFOR;

        @SideOnly(Side.CLIENT)
        @Override
        public ItemStack getIconItemStack()
        {
            return new ItemStack(ICON.saplingBlock(), 1, ICON.saplingSubBlockIndex());
        }

        @SuppressWarnings("ReturnOfNull")
        @SideOnly(Side.CLIENT)
        @Override
        public Item getTabIconItem() { return null; }
    };
    private static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ':';
    @Instance(MOD_ID)
    public static TheMod INSTANCE;
    private final List<Integrates> integrators = Lists.newArrayList();
    private Optional<ConfigEventHandler> configEventHandler = Optional.absent();

    public static String getResourcePrefix() { return RESOURCE_PREFIX; }

    private void initIntegrators()
    {
        integrators.add(new MinechemMod());
        integrators.add(new ForestryMod());
        integrators.add(new GardenCoreMod());
        integrators.add(new GardenTreesMod());
    }

    public Configuration configuration()
    {
        if (configEventHandler.isPresent()) return configEventHandler.get().configuration();
        return new Configuration();
    }

    private void integrateMods(ModState modState)
    {
        for (final Integrates integrator : integrators)
            integrator.integrate(modState);
    }

    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event)
    {
        configEventHandler = Optional.of(
                new ConfigEventHandler(MOD_ID, event.getSuggestedConfigurationFile(), Settings.INSTANCE,
                        Settings.CONFIG_VERSION));
        configEventHandler.get().activate();

        new ModBlocks().loadContent();
        initIntegrators();
        integrateMods(event.getModState());
    }

    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event)
    {
        new OreDictHandler().registerBlocksWithOreDictinary();
        new Recipes().writeRecipesInCraftingManager();
        integrateMods(event.getModState());
    }

    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event)
    {
        Proxy.render.postInit();
        FuelHandler.postInit();
        integrateMods(event.getModState());
        integrators.clear();
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("integrators", integrators)
                .add("configEventHandler", configEventHandler).toString();
    }
}
