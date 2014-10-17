package com.scottkillen.mod.dendrology.block;

import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.common.registry.GameRegistry;

@GameRegistry.ObjectHolder(TheMod.MOD_ID)
public class ModBlock
{
    // *******
    // * NOTE: @GameRegistry.ObjectHolder requires these fields to have the same name as the unlocalized name of the
    // *       object.
    // *

    private ModBlock()
    {
        throw new AssertionError();
    }

    public static void init()
    {
    }
}
