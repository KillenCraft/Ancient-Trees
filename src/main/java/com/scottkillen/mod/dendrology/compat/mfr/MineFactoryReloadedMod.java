package com.scottkillen.mod.dendrology.compat.mfr;

import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.block.ModBlocks;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockSapling;
import net.minecraft.world.World;
import powercrystals.minefactoryreloaded.api.FactoryRegistry;

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
        {
            final MFRLeaves mfrLeaves = new MFRLeaves(leavesBlock);
            FactoryRegistry.sendMessage("registerHarvestable", mfrLeaves);
        }
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Method(modid = MOD_ID)
    private static void registerLogs()
    {
        reportProgress("logs");

        for (final BlockLog woodBlock : ModBlocks.logBlocks())
            FactoryRegistry.sendMessage("registerHarvestable", new MFRWood(woodBlock));
    }

    @SuppressWarnings("ObjectAllocationInLoop")
    @Method(modid = MOD_ID)
    private static void registerSaplings()
    {
        reportProgress("saplings");

        for (final BlockSapling saplingBlock : ModBlocks.saplingBlocks())
        {
            final MFRSapling mfrSapling = new MFRSapling(saplingBlock);
            FactoryRegistry.sendMessage("registerPlantable", mfrSapling);
            FactoryRegistry.sendMessage("registerFertilizable", mfrSapling);
        }
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
