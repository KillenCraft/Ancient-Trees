package com.scottkillen.mod.dendrology.config;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.config.ConfigSyncable;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

import static net.minecraftforge.common.config.Configuration.CATEGORY_GENERAL;

public enum Settings implements ConfigSyncable
{
    INSTANCE;

    private int blacksmithRarity = 0;
    private int bonusChestRarity = 0;
    private int desertTempleRarity = 1;
    private int dungeonRarity = 1;
    private int jungleTempleRarity = 1;
    private int jungleTempleDispenserRarity = 0;
    private int mineshaftCorridorRarity = 1;
    private int strongholdCorridorRarity = 1;
    private int strongholdCrossingRarity = 1;
    private int strongholdLibraryRarity = 1;

    private static int get(Configuration config, String settingName, String category, int defaultValue, int minValue,
                           int maxValue)
    {
        return config.getInt(settingName, category, defaultValue, minValue, maxValue, getLocalizedComment(settingName));
    }

    private static String getLocalizedComment(String settingName)
    {
        return StatCollector.translateToLocal("config." + TheMod.MOD_ID + ':' + settingName);
    }

    public int blacksmithRarity() { return blacksmithRarity; }

    public int bonusChestRarity() { return bonusChestRarity; }

    public int desertTempleRarity() { return desertTempleRarity; }

    public int dungeonRarity() { return dungeonRarity; }

    public int jungleTempleRarity() { return jungleTempleRarity; }

    public int jungleTempleDispenserRarity() { return jungleTempleDispenserRarity; }

    public int mineshaftCorridorRarity() { return mineshaftCorridorRarity; }

    public int strongholdCorridorRarity() { return strongholdCorridorRarity; }

    public int strongholdCrossingRarity() { return strongholdCrossingRarity; }

    public int strongholdLibraryRarity() { return strongholdLibraryRarity; }

    @Override
    public void syncConfig(Configuration config)
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

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("blacksmithRarity", blacksmithRarity)
                .add("bonusChestRarity", bonusChestRarity).add("desertTempleRarity", desertTempleRarity)
                .add("dungeonRarity", dungeonRarity).add("jungleTempleRarity", jungleTempleRarity)
                .add("jungleTempleDispenserRarity", jungleTempleDispenserRarity)
                .add("mineshaftCorridorRarity", mineshaftCorridorRarity)
                .add("strongholdCorridorRarity", strongholdCorridorRarity)
                .add("strongholdCrossingRarity", strongholdCrossingRarity)
                .add("strongholdLibraryRarity", strongholdLibraryRarity).toString();
    }
}
