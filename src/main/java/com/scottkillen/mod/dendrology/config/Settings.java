package com.scottkillen.mod.dendrology.config;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.config.ConfigSyncable;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public enum Settings implements ConfigSyncable
{
    INSTANCE;
    public static final String CONFIG_VERSION = "1";

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

    private static int get(Configuration config, String settingName, int defaultValue)
    {
        return config.getInt(settingName, Configuration.CATEGORY_GENERAL, defaultValue, 0, Integer.MAX_VALUE, getLocalizedComment(settingName));
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
    public void convertOldConfig(Configuration oldConfig)
    {
        // Handle old config versions (none yet)

        syncConfig(TheMod.configuration());
    }

    @Override
    public void syncConfig(Configuration config)
    {
        blacksmithRarity = get(config, "blacksmithRarity", blacksmithRarity);
        bonusChestRarity = get(config, "bonusChestRarity", bonusChestRarity);
        desertTempleRarity =
                get(config, "desertTempleRarity", desertTempleRarity);
        dungeonRarity = get(config, "dungeonRarity", dungeonRarity);
        jungleTempleRarity =
                get(config, "jungleTempleRarity", jungleTempleRarity);
        jungleTempleDispenserRarity =
                get(config, "jungleTempleDispenserRarity", jungleTempleDispenserRarity);
        mineshaftCorridorRarity =
                get(config, "mineshaftCorridorRarity", mineshaftCorridorRarity);
        strongholdCorridorRarity =
                get(config, "strongholdCorridorRarity", strongholdCorridorRarity);
        strongholdCrossingRarity =
                get(config, "strongholdCrossingRarity", strongholdCrossingRarity);
        strongholdLibraryRarity =
                get(config, "strongholdLibraryRarity", strongholdLibraryRarity);
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
