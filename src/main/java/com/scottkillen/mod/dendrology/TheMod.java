package com.scottkillen.mod.dendrology;

import com.google.common.base.Objects;
import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.compat.chisel.ChiselMod;
import com.scottkillen.mod.dendrology.compat.forestry.ForestryMod;
import com.scottkillen.mod.dendrology.compat.gardencollection.GardenCoreMod;
import com.scottkillen.mod.dendrology.compat.gardencollection.GardenTreesMod;
import com.scottkillen.mod.dendrology.compat.mfr.MineFactoryReloadedMod;
import com.scottkillen.mod.dendrology.compat.minechem.MinechemMod;
import com.scottkillen.mod.dendrology.compat.storagedrawers.StorageDrawersMod;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.ParcelManager;
import com.scottkillen.mod.dendrology.content.crafting.Crafter;
import com.scottkillen.mod.dendrology.content.crafting.OreDictHandler;
import com.scottkillen.mod.dendrology.content.crafting.Smelter;
import com.scottkillen.mod.dendrology.content.fuel.FuelHandler;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeGenerator;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.item.ModItems;
import com.scottkillen.mod.dendrology.proxy.Proxy;
import com.scottkillen.mod.koresample.common.util.log.Logger;
import com.scottkillen.mod.koresample.compat.Integrates;
import com.scottkillen.mod.koresample.config.ConfigEventHandler;
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
@Mod(modid = TheMod.MOD_ID, name = TheMod.MOD_NAME, version = TheMod.MOD_VERSION, useMetadata = true, guiFactory = TheMod.MOD_GUI_FACTORY)
public final class TheMod
{
    public static final String MOD_ID = "dendrology";
    static final String MOD_NAME = "Ancient Trees";
    static final String MOD_VERSION = "${mod_version}";
    static final String MOD_GUI_FACTORY = "com.scottkillen.mod.dendrology.config.client.ModGuiFactory";
    private static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ':';
    @SuppressWarnings("PublicField")
    @Instance(MOD_ID)
    public static TheMod INSTANCE;
    private final CreativeTabs creativeTab = new CreativeTabs(MOD_ID.toLowerCase())
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
    private final List<Integrates> integrators = Lists.newArrayList();
    private Optional<ConfigEventHandler> configEventHandler = Optional.absent();

    public static String getResourcePrefix() { return RESOURCE_PREFIX; }

    public static Logger logger() { return Logger.forMod(MOD_ID); }

    private void initIntegrators()
    {
        Logger.forMod(MOD_ID).info("Preparing integration with other mods.");
        integrators.add(new MinechemMod());
        integrators.add(new ForestryMod());
        integrators.add(new GardenCoreMod());
        integrators.add(new GardenTreesMod());
        integrators.add(new ChiselMod());
        integrators.add(new MineFactoryReloadedMod());
        integrators.add(new StorageDrawersMod());
    }

    public Configuration configuration()
    {
        if (configEventHandler.isPresent()) return configEventHandler.get().configuration();
        return new Configuration();
    }

    public CreativeTabs creativeTab()
    {
        return creativeTab;
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
        new ModItems().loadContent();
        initIntegrators();
        integrateMods(event.getModState());
    }

    @EventHandler
    public void onFMLInitialization(FMLInitializationEvent event)
    {
        Logger.forMod(MOD_ID).info("Adding recipes.");
        new OreDictHandler().registerBlocksWithOreDictinary();
        new Crafter().writeRecipes();
        new Smelter().registerSmeltings();
        integrateMods(event.getModState());
    }

    @EventHandler
    public void onFMLPostInitialization(FMLPostInitializationEvent event)
    {
        Proxy.render.postInit();
        FuelHandler.postInit();
        integrateMods(event.getModState());
        integrators.clear();
        ParcelManager.INSTANCE.init();

        new OverworldTreeGenerator().install();
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("creativeTab", creativeTab).add("integrators", integrators)
                .add("configEventHandler", configEventHandler).toString();
    }
}
