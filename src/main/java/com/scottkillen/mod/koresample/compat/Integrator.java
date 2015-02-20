package com.scottkillen.mod.koresample.compat;

import com.scottkillen.mod.koresample.common.util.log.Logger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;

public abstract class Integrator implements Integrates
{

    protected abstract void doIntegration(ModState modState);

    @Override
    public final void integrate(ModState modState)
    {
        if (Loader.isModLoaded(modID()))
        {
            doIntegration(modState);
        } else Logger.forMod(modID()).info("%s not present. %s state integration skipped.", modName(), modState);
    }

    protected abstract String modID();

    protected abstract String modName();
}
