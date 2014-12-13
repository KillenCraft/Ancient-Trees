package com.scottkillen.mod.dendrology.config;

import com.scottkillen.mod.dendrology.TheMod;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public enum Settings
{
    ;

    private static int blacksmithRarity = 0;
    private static int bonusChestRarity = 0;
    private static int desertTempleRarity = 1;
    private static int dungeonRarity = 1;
    private static int jungleTempleRarity = 1;
    private static int jungleTempleDispenserRarity = 0;
    private static int mineshaftCorridorRarity = 1;
    private static int strongholdCorridorRarity = 1;
    private static int strongholdCrossingRarity = 1;
    private static int strongholdLibraryRarity = 1;

    public static int getBlacksmithRarity()
    {
        return blacksmithRarity;
    }

    public static int getBonusChestRarity()
    {
        return bonusChestRarity;
    }

    public static int getDesertTempleRarity()
    {
        return desertTempleRarity;
    }

    public static int getDungeonRarity()
    {
        return dungeonRarity;
    }

    public static int getJungleTempleRarity()
    {
        return jungleTempleRarity;
    }

    public static int getJungleTempleDispenserRarity()
    {
        return jungleTempleDispenserRarity;
    }

    public static int getMineshaftCorridorRarity()
    {
        return mineshaftCorridorRarity;
    }

    public static int getStrongholdCorridorRarity()
    {
        return strongholdCorridorRarity;
    }

    public static int getStrongholdCrossingRarity()
    {
        return strongholdCrossingRarity;
    }

    public static int getStrongholdLibraryRarity()
    {
        return strongholdLibraryRarity;
    }

    public static void syncConfig(Configuration config)
    {
        blacksmithRarity = get(config, "blacksmithRarity", CATEGORY_GENERAL, blacksmithRarity, 0, Integer.MAX_VALUE);
        bonusChestRarity = get(config, "bonusChestRarity", CATEGORY_GENERAL, bonusChestRarity, 0, Integer.MAX_VALUE);
        desertTempleRarity =
                get(config, "desertTempleRarity", CATEGORY_GENERAL, desertTempleRarity, 0, Integer.MAX_VALUE);
        dungeonRarity = get(config, "dungeonRarity", CATEGORY_GENERAL, dungeonRarity, 0, Integer.MAX_VALUE);
        jungleTempleRarity =
                get(config, "jungleTempleRarity", CATEGORY_GENERAL, jungleTempleRarity, 0, Integer.MAX_VALUE);
        jungleTempleDispenserRarity =
                get(config, "jungleTempleDispenserRarity", CATEGORY_GENERAL, jungleTempleDispenserRarity, 0,
                        Integer.MAX_VALUE);
        mineshaftCorridorRarity =
                get(config, "mineshaftCorridorRarity", CATEGORY_GENERAL, mineshaftCorridorRarity, 0, Integer.MAX_VALUE);
        strongholdCorridorRarity =
                get(config, "strongholdCorridorRarity", CATEGORY_GENERAL, strongholdCorridorRarity, 0,
                        Integer.MAX_VALUE);
        strongholdCrossingRarity =
                get(config, "strongholdCrossingRarity", CATEGORY_GENERAL, strongholdCrossingRarity, 0,
                        Integer.MAX_VALUE);
        strongholdLibraryRarity =
                get(config, "strongholdLibraryRarity", CATEGORY_GENERAL, strongholdLibraryRarity, 0, Integer.MAX_VALUE);
    }

    private static int get(Configuration config, String settingName, String category, int defaultValue, int minValue,
                           int maxValue)
    {
        return config.getInt(settingName, category, defaultValue, minValue, maxValue, getLocalizedComment(settingName));
    }

    private static String getLocalizedComment(String settingName)
    {
        return StatCollector.translateToLocal("config." + TheMod.MOD_ID + ':' + settingName);
    }
}
