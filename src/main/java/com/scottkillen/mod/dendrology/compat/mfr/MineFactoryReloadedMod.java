package com.scottkillen.mod.dendrology.compat.mfr;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import com.scottkillen.mod.koresample.tree.DefinesSapling;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;
import powercrystals.minefactoryreloaded.api.IFactoryFertilizable;
import powercrystals.minefactoryreloaded.api.IFactoryHarvestable;
import powercrystals.minefactoryreloaded.api.IFactoryPlantable;

public final class MineFactoryReloadedMod extends Integrator
{
    static final String MOD_ID = "MineFactoryReloaded";
    private static final String MOD_NAME = MOD_ID;

    @SuppressWarnings("MethodWithMultipleLoops")
    public static void notifyNeighbors(World world, int x, int y, int z, Block block)
    {
        for (int xD = -1; xD < 2; xD++)
            for (int yD = -1; yD < 2; yD++)
                for (int zD = -1; zD < 2; zD++)
                    if (x != 0 || y != 0 || z != 0) notifyBlock(world, x + xD, y + yD, z + zD, block);
    }

    private static void notifyBlock(World world, int x, int y, int z, Block block)
    {
        final Block targetBlock = world.getBlock(x, y, z);
        if (!targetBlock.isLeaves(world, x, y, z)) world.notifyBlockOfNeighborChange(x, y, z, block);
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Method(modid = MOD_ID)
    private static void registerLeaves()
    {
        reportProgress("leaves");

        for (final BlockLeaves leavesBlock : ModBlocks.leavesBlocks())
            registerHarvestable(new MFRLeaves(leavesBlock));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Method(modid = MOD_ID)
    private static void registerLogs()
    {
        reportProgress("logs");

        for (final BlockLog woodBlock : ModBlocks.logBlocks())
            registerHarvestable(new MFRWood(woodBlock));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Method(modid = MOD_ID)
    private static void registerSaplings()
    {
        reportProgress("saplings");

        for (final BlockSapling saplingBlock : ModBlocks.saplingBlocks())
        {
            final MFRSapling sapling = new MFRSapling(saplingBlock);
            registerPlantable(sapling);
            registerFertilizable(sapling);
        }
    }

    @Method(modid = MOD_ID)
    private static void registerFertilizable(IFactoryFertilizable fertilizable)
    {
        FactoryRegistry.sendMessage("registerFertilizable", fertilizable);
    }

    @Method(modid = MOD_ID)
    private static void registerHarvestable(IFactoryHarvestable harvestable)
    {
        FactoryRegistry.sendMessage("registerHarvestable", harvestable);
    }

    @Method(modid = MOD_ID)
    private static void registerPlantable(IFactoryPlantable plantable)
    {
        FactoryRegistry.sendMessage("registerPlantable", plantable);
    }

    private static void reportProgress(String phase)
    {
        TheMod.logger().info("Registering %s with MineFactory Reloaded.", phase);
    }

    private static void init()
    {
        registerSaplings();
        registerLogs();
        registerLeaves();
    }

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID) && Settings.INSTANCE.integrateMFR())
        {
            switch (modState)
            {
                case INITIALIZED:
                    init();
                    break;
                default:
            }
        }
    }

    @Override
    protected String modID()
    {
        return MOD_ID;
    }

    @Override
    protected String modName()
    {
        return MOD_NAME;
    }
}
