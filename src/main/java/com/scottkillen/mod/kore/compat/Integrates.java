package com.scottkillen.mod.kore.compat;

import cpw.mods.fml.common.LoaderState.ModState;

public interface Integrates
{
    void integrate(ModState modState);
}
