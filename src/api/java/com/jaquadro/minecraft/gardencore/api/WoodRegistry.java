package com.jaquadro.minecraft.gardencore.api;

import com.jaquadro.minecraft.gardencore.util.UniqueMetaIdentifier;
import net.minecraft.block.Block;
import java.util.Map.Entry;
import java.util.Set;

public final class WoodRegistry
{
    private static WoodRegistry instance;

    static
    {
        instance = new WoodRegistry();
    }

    private WoodRegistry() { }

    public static WoodRegistry instance() { return instance; }

    public void registerWoodType(Block block, int meta) { }

    public Set<Entry<UniqueMetaIdentifier, Block>> registeredTypes() { return null; }

    public boolean contains(Block wood, int woodMeta) { return true; }

    public boolean contains(UniqueMetaIdentifier wood) { return true; }
}
