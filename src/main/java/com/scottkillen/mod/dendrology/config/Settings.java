package com.scottkillen.mod.dendrology.config;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.koresample.config.ConfigSyncable;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public enum Settings implements ConfigSyncable
{
    INSTANCE;
    public static final String CONFIG_VERSION = "2";
    private static final int MAX_OVERWORLD_TREE_GEN_RARITY = 10000;
    private int blacksmithRarity = 0;
    private int bonusChestRarity = 0;
    private int desertTempleRarity = 1;
    private int dungeonRarity = 1;
    private int jungleTempleRarity = 1;
    private int jungleTempleDispenserRarity = 0;
    private int mineshaftCorridorRarity = 1;
    private int overworldTreeGenRarity = 1;
    private int strongholdCorridorRarity = 1;
    private int strongholdCrossingRarity = 1;
    private int strongholdLibraryRarity = 1;

    private static int get(Configuration config, String settingName, int defaultValue)
    {
        return get(config, settingName, defaultValue, 0, Integer.MAX_VALUE);
    }

    private static int get(Configuration config, String settingName, String category, int defaultValue)
    {
        return get(config, settingName, category, defaultValue, 0, Integer.MAX_VALUE);
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
        return StatCollector.translateToLocal("config." + TheMod.INSTANCE.modID() + ':' + settingName);
    }

    public int blacksmithRarity() { return blacksmithRarity; }

    public int bonusChestRarity() { return bonusChestRarity; }

    public int desertTempleRarity() { return desertTempleRarity; }

    public int dungeonRarity() { return dungeonRarity; }

    public int jungleTempleRarity() { return jungleTempleRarity; }

    public int jungleTempleDispenserRarity() { return jungleTempleDispenserRarity; }

    public int mineshaftCorridorRarity() { return mineshaftCorridorRarity; }

    public boolean isOverworldTreeGenEnabled() { return overworldTreeGenRarity != 0; }

    public int overworldTreeGenRarity() { return MAX_OVERWORLD_TREE_GEN_RARITY - overworldTreeGenRarity + 1; }

    public int strongholdCorridorRarity() { return strongholdCorridorRarity; }

    public int strongholdCrossingRarity() { return strongholdCrossingRarity; }

    public int strongholdLibraryRarity() { return strongholdLibraryRarity; }

    @Override
    public void convertOldConfig(Configuration oldConfig)
    {
        if ("1".equals(oldConfig.getLoadedConfigVersion()))
        {
            blacksmithRarity = get(oldConfig, "blacksmithRarity", blacksmithRarity);
            bonusChestRarity = get(oldConfig, "bonusChestRarity", bonusChestRarity);
            desertTempleRarity = get(oldConfig, "desertTempleRarity", desertTempleRarity);
            dungeonRarity = get(oldConfig, "dungeonRarity", dungeonRarity);
            jungleTempleRarity = get(oldConfig, "jungleTempleRarity", jungleTempleRarity);
            jungleTempleDispenserRarity = get(oldConfig, "jungleTempleDispenserRarity", jungleTempleDispenserRarity);
            mineshaftCorridorRarity = get(oldConfig, "mineshaftCorridorRarity", mineshaftCorridorRarity);
            overworldTreeGenRarity =
                    get(oldConfig, "overworldTreeGenRarity", overworldTreeGenRarity, 0, MAX_OVERWORLD_TREE_GEN_RARITY);
            strongholdCorridorRarity = get(oldConfig, "strongholdCorridorRarity", strongholdCorridorRarity);
            strongholdCrossingRarity = get(oldConfig, "strongholdCrossingRarity", strongholdCrossingRarity);
            strongholdLibraryRarity = get(oldConfig, "strongholdLibraryRarity", strongholdLibraryRarity);
        }

        syncConfig(TheMod.INSTANCE.configuration());
    }

    @Override
    public void syncConfig(Configuration config)
    {
        final String chestsCategory = Configuration.CATEGORY_GENERAL + ".chests";
        blacksmithRarity = get(config, "blacksmithRarity", chestsCategory, blacksmithRarity);
        bonusChestRarity = get(config, "bonusChestRarity", chestsCategory, bonusChestRarity);
        desertTempleRarity = get(config, "desertTempleRarity", chestsCategory, desertTempleRarity);
        dungeonRarity = get(config, "dungeonRarity", chestsCategory, dungeonRarity);
        jungleTempleRarity = get(config, "jungleTempleRarity", chestsCategory, jungleTempleRarity);
        jungleTempleDispenserRarity =
                get(config, "jungleTempleDispenserRarity", chestsCategory, jungleTempleDispenserRarity);
        mineshaftCorridorRarity = get(config, "mineshaftCorridorRarity", chestsCategory, mineshaftCorridorRarity);
        strongholdCorridorRarity = get(config, "strongholdCorridorRarity", chestsCategory, strongholdCorridorRarity);
        strongholdCrossingRarity = get(config, "strongholdCrossingRarity", chestsCategory, strongholdCrossingRarity);
        strongholdLibraryRarity = get(config, "strongholdLibraryRarity", chestsCategory, strongholdLibraryRarity);

        final String worldGenCategory = Configuration.CATEGORY_GENERAL + ".worldgen";
        overworldTreeGenRarity = get(config, "overworldTreeGenRarity", worldGenCategory, overworldTreeGenRarity, 0,
                MAX_OVERWORLD_TREE_GEN_RARITY);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("blacksmithRarity", blacksmithRarity)
                .add("bonusChestRarity", bonusChestRarity).add("desertTempleRarity", desertTempleRarity)
                .add("dungeonRarity", dungeonRarity).add("jungleTempleRarity", jungleTempleRarity)
                .add("jungleTempleDispenserRarity", jungleTempleDispenserRarity)
                .add("mineshaftCorridorRarity", mineshaftCorridorRarity)
                .add("overworldTreeGenRarity", overworldTreeGenRarity)
                .add("strongholdCorridorRarity", strongholdCorridorRarity)
                .add("strongholdCrossingRarity", strongholdCrossingRarity)
                .add("strongholdLibraryRarity", strongholdLibraryRarity).toString();
    }
}
