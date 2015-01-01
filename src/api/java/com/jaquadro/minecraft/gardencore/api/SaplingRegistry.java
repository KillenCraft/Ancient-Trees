package com.jaquadro.minecraft.gardencore.api;

import com.jaquadro.minecraft.gardencore.util.UniqueMetaIdentifier;
import net.minecraft.block.Block;
import net.minecraft.item.Item;

public final class SaplingRegistry
{
    private static final SaplingRegistry instance;

    static
    {
        instance = new SaplingRegistry();
    }

    private SaplingRegistry() { }

    public static SaplingRegistry instance() { return instance; }

    public void registerSapling(Item sapling, int saplingMeta, Block wood, int woodMeta, Block leaf, int leafMeta)
    {
    }

    public void registerSapling(UniqueMetaIdentifier sapling, UniqueMetaIdentifier wood, UniqueMetaIdentifier leaf)
    {
    }

    public UniqueMetaIdentifier getLeavesForSapling(Item sapling) { return null; }

    public UniqueMetaIdentifier getLeavesForSapling(Item sapling, int saplingMeta) { return null; }

    public UniqueMetaIdentifier getLeavesForSapling(UniqueMetaIdentifier sapling) { return null; }

    public UniqueMetaIdentifier getWoodForSapling(Item sapling) { return null; }

    public UniqueMetaIdentifier getWoodForSapling(Item sapling, int saplingMeta) { return null; }

    public UniqueMetaIdentifier getWoodForSapling(UniqueMetaIdentifier sapling) { return null; }

    public Object getExtendedData(Item sapling, String key) { return null; }

    public Object getExtendedData(Item sapling, int saplingMeta, String key) { return null; }

    public Object getExtendedData(UniqueMetaIdentifier sapling, String key) { return null; }

    public void putExtendedData(Item sapling, String key, Object data) { }

    public void putExtendedData(Item sapling, int saplingMeta, String key, Object data) { }

    public void putExtendedData(UniqueMetaIdentifier sapling, String key, Object data) { }
}
