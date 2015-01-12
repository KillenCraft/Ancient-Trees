package com.scottkillen.mod.koresample.common.util.event;

import cpw.mods.fml.common.eventhandler.EventBus;

@SuppressWarnings({ "AbstractClassNeverImplemented", "WeakerAccess" })
public abstract class ForgeEventListener
{
    public void listen(EventBus eventBus)
    {
        eventBus.register(this);
    }
}
