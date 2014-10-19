package com.scottkillen.mod.dendrology.world.gen;

import com.scottkillen.mod.dendrology.registry.TreeRegistry;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public enum TreeGenerator
{
    INSTANCE;

    public static void init() { MinecraftForge.TERRAIN_GEN_BUS.register(INSTANCE); }

    @SuppressWarnings("MethodMayBeStatic")
    @SubscribeEvent
    public void onDecorate(DecorateBiomeEvent.Decorate event)
    {
        if (event.type == DecorateBiomeEvent.Decorate.EventType.TREE)
            TreeRegistry.generateTrees(event.world, event.rand, event.chunkX, event.chunkZ);
    }
}
