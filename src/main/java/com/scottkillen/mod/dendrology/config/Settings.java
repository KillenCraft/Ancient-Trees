package com.scottkillen.mod.dendrology.config;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.config.ConfigSyncable;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;
import java.util.Map;

import static net.minecraftforge.common.ChestGenHooks.BONUS_CHEST;
import static net.minecraftforge.common.ChestGenHooks.DUNGEON_CHEST;
import static net.minecraftforge.common.ChestGenHooks.MINESHAFT_CORRIDOR;
import static net.minecraftforge.common.ChestGenHooks.PYRAMID_DESERT_CHEST;
import static net.minecraftforge.common.ChestGenHooks.PYRAMID_JUNGLE_CHEST;
import static net.minecraftforge.common.ChestGenHooks.PYRAMID_JUNGLE_DISPENSER;
import static net.minecraftforge.common.ChestGenHooks.STRONGHOLD_CORRIDOR;
import static net.minecraftforge.common.ChestGenHooks.STRONGHOLD_CROSSING;
import static net.minecraftforge.common.ChestGenHooks.STRONGHOLD_LIBRARY;
import static net.minecraftforge.common.ChestGenHooks.VILLAGE_BLACKSMITH;

public enum Settings implements ConfigSyncable
{
    INSTANCE;
    public static final String CONFIG_VERSION = "2";
    private static final int MAX_OVERWORLD_TREE_GEN_RARITY = 10000;
    private static final int DEFAULT_OVER_WORLD_TREE_GEN_RARITY = 1;
    private static final ImmutableMap<String, Integer> DEFAULT_CHEST_RARITIES =
            ImmutableMap.copyOf(defaultChestRarities());
    private static final ImmutableMap<String, String> CHEST_CONFIG_NAMES = chestConfigNames();
    private final Map<String, Integer> chestRarities = defaultChestRarities();

    private int overworldTreeGenRarity = DEFAULT_OVER_WORLD_TREE_GEN_RARITY;
    private int saplingDropRarity = 200;
    private boolean integrateChisel = true;
    private boolean integrateForestry = true;
    private boolean integrateGardenStuff = true;
    private boolean integrateMFR = true;
    private boolean integrateMinechem = true;
    private boolean integrateStorageDrawers = true;

    private static ImmutableMap<String, String> chestConfigNames()
    {
        final Map<String, String> map = Maps.newHashMap();
        map.put(VILLAGE_BLACKSMITH, "blacksmithRarity");
        map.put(BONUS_CHEST, "bonusChestRarity");
        map.put(PYRAMID_DESERT_CHEST, "desertTempleRarity");
        map.put(DUNGEON_CHEST, "dungeonRarity");
        map.put(PYRAMID_JUNGLE_CHEST, "jungleTempleRarity");
        map.put(PYRAMID_JUNGLE_DISPENSER, "jungleTempleDispenserRarity");
        map.put(MINESHAFT_CORRIDOR, "mineshaftCorridorRarity");
        map.put(STRONGHOLD_CORRIDOR, "strongholdCorridorRarity");
        map.put(STRONGHOLD_CROSSING, "strongholdCrossingRarity");
        map.put(STRONGHOLD_LIBRARY, "strongholdLibraryRarity");
        return ImmutableMap.copyOf(map);
    }

    private static Map<String, Integer> defaultChestRarities()
    {
        Map<String, Integer> map = Maps.newHashMap();
        map.put(VILLAGE_BLACKSMITH, 0);
        map.put(BONUS_CHEST, 0);
        map.put(PYRAMID_DESERT_CHEST, 1);
        map.put(DUNGEON_CHEST, 1);
        map.put(PYRAMID_JUNGLE_CHEST, 1);
        map.put(PYRAMID_JUNGLE_DISPENSER, 0);
        map.put(MINESHAFT_CORRIDOR, 1);
        map.put(STRONGHOLD_CORRIDOR, 1);
        map.put(STRONGHOLD_CROSSING, 1);
        map.put(STRONGHOLD_LIBRARY, 1);
        return map;
    }

    private static int get(Configuration config, String settingName, int defaultValue)
    {
        return get(config, settingName, defaultValue, 0, Integer.MAX_VALUE);
    }

    private static int get(Configuration config, String settingName, String category, int defaultValue)
    {
        return get(config, settingName, category, defaultValue, 0, Integer.MAX_VALUE);
    }

    private static boolean get(Configuration config, String settingName, String category, boolean defaultValue)
    {
        return config.getBoolean(settingName, category, defaultValue, getLocalizedComment(settingName));
    }

    private static int get(Configuration config, String settingName, int defaultValue, int minumumValue,
                           int maximumValue)
    {
        return get(config, settingName, Configuration.CATEGORY_GENERAL, defaultValue, minumumValue, maximumValue);
    }

    private static int get(Configuration config, String settingName, String category, int defaultValue,
                           int minumumValue, int maximumValue)
    {
        return config.getInt(settingName, category, defaultValue, minumumValue, maximumValue,
                getLocalizedComment(settingName));
    }

    private static String getLocalizedComment(String settingName)
    {
        return StatCollector.translateToLocal("config." + TheMod.MOD_ID + ':' + settingName);
    }

    public static Iterable<String> chestTypes()
    {
        return DEFAULT_CHEST_RARITIES.keySet();
    }

    private void loadChestRarity(Configuration config, String category, String chestType)
    {
        final int defaultRarity = DEFAULT_CHEST_RARITIES.get(chestType);
        final String settingName = CHEST_CONFIG_NAMES.get(chestType);
        final int rarity = get(config, settingName, category, defaultRarity);
        chestRarities.put(chestType, rarity);
    }

    public int chestRarity(String chestType)
    {
        final Integer rarity = chestRarities.get(chestType);
        return rarity == null ? 0 : rarity;
    }

    public boolean isOverworldTreeGenEnabled() { return overworldTreeGenRarity != 0; }

    public int overworldTreeGenRarity() { return MAX_OVERWORLD_TREE_GEN_RARITY - overworldTreeGenRarity + 1; }

    @Override
    public void convertOldConfig(Configuration oldConfig)
    {
        if ("1".equals(oldConfig.getLoadedConfigVersion()))
        {
            for (final String chestType : DEFAULT_CHEST_RARITIES.keySet())
            {
                loadChestRarity(oldConfig, Configuration.CATEGORY_GENERAL, chestType);
            }

            overworldTreeGenRarity = get(oldConfig, "overworldTreeGenRarity", DEFAULT_OVER_WORLD_TREE_GEN_RARITY, 0,
                    MAX_OVERWORLD_TREE_GEN_RARITY);
        }

        syncConfig(TheMod.INSTANCE.configuration());
    }

    @Override
    public void syncConfig(Configuration config)
    {
        saplingDropRarity = get(config, "saplingDropRarity", Configuration.CATEGORY_GENERAL, saplingDropRarity);

        final String chestsCategory = Configuration.CATEGORY_GENERAL + ".chests";
        for (final String chestType : chestRarities.keySet())
        {
            loadChestRarity(config, chestsCategory, chestType);
        }

        final String worldGenCategory = Configuration.CATEGORY_GENERAL + ".worldgen";
        overworldTreeGenRarity =
                get(config, "overworldTreeGenRarity", worldGenCategory, DEFAULT_OVER_WORLD_TREE_GEN_RARITY, 0,
                        MAX_OVERWORLD_TREE_GEN_RARITY);

        final String integrationCategory = Configuration.CATEGORY_GENERAL + ".integration";
        integrateChisel = get(config, "integrateChisel", integrationCategory, true);
        integrateForestry = get(config, "integrateForestry", integrationCategory, true);
        integrateGardenStuff = get(config, "integrateGardenStuff", integrationCategory, true);
        integrateMFR = get(config, "integrateMFR", integrationCategory, true);
        integrateMinechem = get(config, "integrateMinechem", integrationCategory, true);
        integrateStorageDrawers = get(config, "integrateStorageDrawers", integrationCategory, true);
    }

    public int saplingDropRarity()
    {
        return saplingDropRarity;
    }

    public boolean integrateChisel() { return integrateChisel; }

    public boolean integrateForestry() { return integrateForestry; }

    public boolean integrateGardenStuff() { return integrateGardenStuff; }

    public boolean integrateMFR() { return integrateMFR; }

    public boolean integrateMinechem() { return integrateMinechem; }

    public boolean integrateStorageDrawers() { return integrateStorageDrawers; }
}
