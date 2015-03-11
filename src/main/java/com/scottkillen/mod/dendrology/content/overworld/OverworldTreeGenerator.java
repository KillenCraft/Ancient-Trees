package com.scottkillen.mod.dendrology.content.overworld;

import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.config.Settings;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import java.util.List;
import java.util.Random;

public class OverworldTreeGenerator implements IWorldGenerator
{
    private static final int GENERATOR_WEIGHT = 0;

    public void install()
    {
        GameRegistry.registerWorldGenerator(this, GENERATOR_WEIGHT);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator,
                         IChunkProvider chunkProvider)
    {
        final Settings settings = Settings.INSTANCE;
        if (settings.isOverworldTreeGenEnabled())
        {
            final int x = (chunkX << 4) + 8 + random.nextInt(16);
            final int z = (chunkZ << 4) + 8 + random.nextInt(16);

            final BiomeGenBase biome = world.getBiomeGenForCoords(x, z);
            final List<Type> biomeTypes = ImmutableList.copyOf(BiomeDictionary.getTypesForBiome(biome));

            if (!(biomeTypes.contains(Type.NETHER) || biomeTypes.contains(Type.END)))
                if (random.nextInt(settings.overworldTreeGenRarity()) == 0)
                {
                    final OverworldTreeSpecies species =
                            OverworldTreeSpecies.values()[random.nextInt(OverworldTreeSpecies.values().length)];
                    final WorldGenerator tree = species.worldTreeGenerator();
                    final int maxY = world.getHeightValue(x, z) * 2;
                    final int y = maxY > 0 ? random.nextInt(maxY) : 0;
                    tree.generate(world, random, x, y, z);
                }
        }
    }
}
