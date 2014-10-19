package com.scottkillen.mod.dendrology.world.gen;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.scottkillen.mod.dendrology.util.log.Logger;
import com.scottkillen.mod.dendrology.world.gen.feature.Beech;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.Random;

import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.WATER;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum BeechGenerator implements IWorldGenerator
{
    INSTANCE;

    private final WorldGenAbstractTree treeGen = new Beech(false);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        final ImmutableSet<BiomeDictionary.Type> biomeTags = ImmutableSet.copyOf(getBiomeTags(world, chunkX, chunkZ));

        // Temperate biomes that are not too wet
        if (isTemperateAndNotWet(biomeTags))
        {
            generateBeech(world, random, chunkX, chunkZ);
        }
    }

    private static ImmutableSet<BiomeDictionary.Type> getBiomeTags(World world, int x, int z)
    {
        final BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
        final EnumSet<BiomeDictionary.Type> set = EnumSet.noneOf(BiomeDictionary.Type.class);
        final BiomeDictionary.Type[] tags = BiomeDictionary.getTypesForBiome(biome);
        set.addAll(Arrays.asList(tags));
        return ImmutableSet.copyOf(set);
    }

    private void generateBeech(World world, Random rand, int chunkX, int chunkZ)
    {
        // gen 20% of the time
        if (rand.nextInt(10) < 2)
        {
            final int numAttempts = 1;

            for (int attempt = 0; attempt < numAttempts; attempt ++)
            {
                final int x = chunkX + rand.nextInt(16) + 8;
                final int z = chunkZ + rand.nextInt(16) + 8;
                final int y = world.getHeightValue(x, z);

                if (treeGen.generate(world, rand, x, y, z))
                {
                    treeGen.func_150524_b(world, rand, x, y, z);
                    Logger.info("Beech: %d, %d, %d", x, y, z);
                }
            }
        }
    }

    @SuppressWarnings({ "OverlyComplexBooleanExpression", "MethodWithMoreThanThreeNegations" })
    private static boolean isTemperateAndNotWet(ImmutableSet<BiomeDictionary.Type> biomeTags) {
        return !biomeTags.contains(COLD) && !biomeTags.contains(HOT) && !biomeTags.contains(WET) && !biomeTags.contains(WATER);
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGen", treeGen).toString();
    }

    public static void init()
    {
        GameRegistry.registerWorldGenerator(INSTANCE, 30);
    }
}
