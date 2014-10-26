package com.scottkillen.mod.dendrology.world.gen;

import com.google.common.base.Objects;
import com.google.common.collect.ImmutableSet;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.util.world.BiomeDictionaryProxy;
import com.scottkillen.mod.dendrology.world.gen.feature.CerasuTree;
import cpw.mods.fml.common.IWorldGenerator;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import java.util.Random;

import static net.minecraftforge.common.BiomeDictionary.Type.COLD;
import static net.minecraftforge.common.BiomeDictionary.Type.HOT;
import static net.minecraftforge.common.BiomeDictionary.Type.WATER;
import static net.minecraftforge.common.BiomeDictionary.Type.WET;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

@SuppressWarnings("NonSerializableFieldInSerializableClass")
public enum CerasuTreeGenerator implements IWorldGenerator
{
    INSTANCE;

    private final WorldGenAbstractTree treeGen = new CerasuTree(false);

    @SuppressWarnings("MethodWithMoreThanThreeNegations")
    private static boolean isIdealHabitat(ImmutableSet<BiomeDictionary.Type> biomeTags)
    {
        //noinspection OverlyComplexBooleanExpression
        return !biomeTags.contains(COLD) && !biomeTags.contains(HOT) && !biomeTags.contains(WET) && !biomeTags.contains(WATER);
    }

    public static void init()
    {
        MinecraftForge.TERRAIN_GEN_BUS.register(INSTANCE);
    }

    @SubscribeEvent
    public void onDecorateBiome(DecorateBiomeEvent.Decorate event)
    {
        if (event.type != TREE) return;

        generate(event.rand, event.chunkX, event.chunkZ, event.world, null, null);
    }

    @Override
    public void generate(Random random, int chunkX, int chunkZ, World world, IChunkProvider chunkGenerator, IChunkProvider chunkProvider)
    {
        if (!Settings.TreeGen.doCerasuTreeGeneration()) return;

        final ImmutableSet<BiomeDictionary.Type> biomeTags = ImmutableSet.copyOf(BiomeDictionaryProxy.getBiomeTags(world, chunkX, chunkZ));

        if (isIdealHabitat(biomeTags)) generateTree(world, random, chunkX, chunkZ);
    }

    private void generateTree(World world, Random rand, int chunkX, int chunkZ)
    {
        if (rand.nextInt(10) > 1) return;

        final int numAttempts = rand.nextInt(9);

        for (int attempt = 0; attempt < numAttempts; attempt++)
        {
            final int x = chunkX + rand.nextInt(16) + 8;
            final int z = chunkZ + rand.nextInt(16) + 8;
            final int y = world.getHeightValue(x, z);

            if (treeGen.generate(world, rand, x, y, z)) treeGen.func_150524_b(world, rand, x, y, z);
        }
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("treeGen", treeGen).toString();
    }
}
