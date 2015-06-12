package com.scottkillen.mod.dendrology.compat.storagedrawers;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;

public final class StorageDrawersMod extends Integrator
{
    static final String MOD_ID = "StorageDrawers";
    private static final String MOD_NAME = "Storage Drawers";
    private SDBlocks blocks = null;
    private SDRefinedRelocationBlocks rrBlocks = null;

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID) && Settings.INSTANCE.integrateStorageDrawers())
        {
            switch (modState)
            {
                case PREINITIALIZED:
                    preInit();
                    break;
                case INITIALIZED:
                    init();
                    break;
                case POSTINITIALIZED:
                    postInit();
                    break;
                default:
            }
        }
    }

    @SuppressWarnings("AssignmentToNull")
    private void postInit()
    {
        rrBlocks.writeRecipes(blocks);

        blocks = null;
        rrBlocks = null;
    }

    @Method(modid = MOD_ID)
    private void init()
    {
        rrBlocks = new SDRefinedRelocationBlocks();
        rrBlocks.setup(blocks);
        blocks.writeRecipes();
    }

    @Method(modid = MOD_ID)
    private void preInit()
    {
        blocks = new SDBlocks();
        blocks.setup();
    }

    @Override
    protected String modID()
    {
        return MOD_ID;
    }

    @Override
    protected String modName()
    {
        return MOD_NAME;
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("blocks", blocks).add("rrBlocks", rrBlocks).toString();
    }
}
