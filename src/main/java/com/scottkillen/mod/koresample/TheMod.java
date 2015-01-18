package com.scottkillen.mod.koresample;

import com.scottkillen.mod.koresample.compat.versionchecker.Versioned;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInterModComms;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@SuppressWarnings({
        "StaticNonFinalField",
        "WeakerAccess",
        "StaticVariableMayNotBeInitialized",
        "NonConstantFieldWithUpperCaseName",
        "MethodMayBeStatic"
})
@Mod(modid = TheMod.MOD_ID, name = TheMod.MOD_NAME, version = TheMod.MOD_VERSION, useMetadata = true)
public final class TheMod implements Versioned
{
    static final String MOD_ID = "koresample";
    static final String MOD_NAME = "Kore Sample";
    static final String MOD_VERSION = "${mod_version}";
    private static final String RESOURCE_PREFIX = MOD_ID.toLowerCase() + ':';
    private static final String VERSIONCHECK_URL =
            "https://raw.githubusercontent.com/ScottKillen/glowing-ninja/master/KoreSample.json";
    @Instance(MOD_ID)
    public static TheMod INSTANCE;

    public static String resourcePrefix() { return RESOURCE_PREFIX; }

    @EventHandler
    public void onFMLPreInitialization(FMLPreInitializationEvent event) { addVersionCheck(this); }

    public void addVersionCheck(Versioned versionCheck)
    {
        FMLInterModComms.sendRuntimeMessage(versionCheck.modID(), "VersionChecker", "addVersionCheck",
                versionCheck.versionInfoURL());
    }

    @Override
    public String modID() { return MOD_ID; }

    @Override
    public String versionInfoURL() { return VERSIONCHECK_URL; }
}
