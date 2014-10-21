package com.scottkillen.mod.dendrology.world.gen;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.util.world.BiomeDictionaryProxy;
import com.scottkillen.mod.dendrology.world.gen.feature.Cedar;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import java.util.Random;

import static net.minecraftforge.common.BiomeDictionary.Type.*;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum CedarGenerator implements IWorldGenerator
{
    INSTANCE;

    private final WorldGenAbstractTree treeGen = new Cedar(false);

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (!Settings.TreeGen.doCedarTreeGeneration()) return;

        final ImmutableSet<BiomeDictionary.Type> biomeTags = ImmutableSet.copyOf(BiomeDictionaryProxy.getBiomeTags(world, chunkX, chunkZ));

        if (isIdealHabitat(biomeTags))
        {
            generateCedar(world, random, chunkX, chunkZ);
        }
    }

    private void generateCedar(World world, Random rand, int chunkX, int chunkZ)
    {
            final int numAttempts = rand.nextInt(3) + 1;

            for (int attempt = 0; attempt < numAttempts; attempt ++)
            {
                final int x = chunkX + rand.nextInt(16) + 8;
                final int z = chunkZ + rand.nextInt(16) + 8;
                final int y = world.getHeightValue(x, z);

                if (treeGen.generate(world, rand, x, y, z))
                    treeGen.func_150524_b(world, rand, x, y, z);
            }
    }

    @SuppressWarnings("SimplifiableIfStatement")
    private static boolean isIdealHabitat(ImmutableSet<BiomeDictionary.Type> biomeTags) {
        if (biomeTags.contains(DRY)) return false;
        return biomeTags.contains(WET) || biomeTags.contains(DENSE) || biomeTags.contains(FOREST);
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
