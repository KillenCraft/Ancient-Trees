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

    @SuppressWarnings("StaticNonFinalField")
    public enum TreeGen
    {
        ;

        public static final String CATEGORY = Configuration.CATEGORY_GENERAL + ".treegen";

        // Defaults
        private static boolean genLataTrees = true;
        private static boolean genCedrumTrees = true;
        private static boolean genCerasuTrees = true;
        private static boolean genKiparisTrees = true;

        public static boolean doLataTreeGeneration() { return genLataTrees; }

        public static boolean doCedrumTreeGeneration() { return genCedrumTrees; }

        public static boolean doCerasuTreeGeneration() { return genCerasuTrees; }

        public static boolean doKiparisTreeGeneration() { return genKiparisTrees; }

        private static void syncConfig(Configuration config)
        {
            genLataTrees = get(config, "genLataTrees", CATEGORY, genLataTrees);
            genCedrumTrees = get(config, "genCedrumTrees", CATEGORY, genCedrumTrees);
            genCerasuTrees = get(config, "genCerasuTrees", CATEGORY, genCerasuTrees);
            genKiparisTrees = get(config, "genKiparisTrees", CATEGORY, genKiparisTrees);
        }
    }
}
