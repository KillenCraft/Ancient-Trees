package com.scottkillen.mod.dendrology.util.world;

import com.google.common.collect.ImmutableSet;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import java.util.Arrays;
import java.util.EnumSet;

public enum BiomeDictionaryProxy
{
    ;

    public static ImmutableSet<BiomeDictionary.Type> getBiomeTags(World world, int x, int z)
    {
        final BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        final EnumSet<BiomeDictionary.Type> set = EnumSet.noneOf(BiomeDictionary.Type.class);
        final BiomeDictionary.Type[] tags = BiomeDictionary.getTypesForBiome(biome);
        set.addAll(Arrays.asList(tags));
        return ImmutableSet.copyOf(set);
    }
}
