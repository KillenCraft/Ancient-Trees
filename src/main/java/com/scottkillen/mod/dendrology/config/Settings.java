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
        private static boolean genCedarTrees = true;
        private static boolean genCherryTrees = true;
        private static boolean genCypressTrees = true;

        public static boolean doLataTreeGeneration() { return genLataTrees; }

        public static boolean doCedarTreeGeneration() { return genCedarTrees; }

        public static boolean doCherryTreeGeneration() { return genCherryTrees; }

        public static boolean doCypressTreeGeneration() { return genCypressTrees; }

        private static void syncConfig(Configuration config)
        {
            genLataTrees = get(config, "genLataTrees", CATEGORY, genLataTrees);
            genCedarTrees = get(config, "genCedarTrees", CATEGORY, genCedarTrees);
            genCherryTrees = get(config, "genCherryTrees", CATEGORY, genCherryTrees);
            genCypressTrees = get(config, "genCypressTrees", CATEGORY, genCypressTrees);
        }
    }
}
