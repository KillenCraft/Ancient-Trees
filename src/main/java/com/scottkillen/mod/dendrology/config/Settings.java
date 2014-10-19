package com.scottkillen.mod.dendrology.config;

import com.scottkillen.mod.dendrology.TheMod;
import net.minecraft.util.StatCollector;
import net.minecraftforge.common.config.Configuration;

public enum Settings
{
    ;

    public static void syncConfig(Configuration config)
    {
        TreeGen.syncConfig(config);
    }

    @SuppressWarnings("BooleanMethodNameMustStartWithQuestion")
    private static boolean get(Configuration config, String settingName, String category, boolean defaultValue)
    {
        return config.getBoolean(settingName, category, defaultValue, getLocalizedComment(settingName));
    }

    private static String getLocalizedComment(String settingName)
    {
        return StatCollector.translateToLocal("config." + TheMod.MOD_ID + ':' + settingName);
    }

    public enum TreeGen
    {
        ;

        public static final String CATEGORY = Configuration.CATEGORY_GENERAL + ".treegen";

        // Defaults

        private static void syncConfig(Configuration config)
        {
        }
    }
}
