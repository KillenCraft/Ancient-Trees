package com.scottkillen.mod.dendrology.compat.gardencollection;

import com.jaquadro.minecraft.gardencore.api.SaplingRegistry;
import com.jaquadro.minecraft.gardencore.api.WoodRegistry;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.kore.compat.Integrator;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.kore.common.util.log.Logger;
import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.Item;

public final class GardenCoreMod extends Integrator
{
    private static final String MOD_ID = "GardenCore";
    private static final String MOD_NAME = MOD_ID;

    private static final Logger logger = Logger.forMod(TheMod.MOD_ID);

    @Method(modid = MOD_ID)
    private static void registerWood()
    {
        logger.info("Registering wood with GardenCore.");

        final WoodRegistry woodReg = WoodRegistry.instance();
        final SaplingRegistry saplingReg = SaplingRegistry.instance();

        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            final ModLogBlock logBlock = tree.getLogBlock();
            final int logMeta = tree.getLogMeta();
            woodReg.registerWoodType(logBlock, logMeta);
            saplingReg.registerSapling(Item.getItemFromBlock(tree.getSaplingBlock()), tree.getSaplingMeta(), logBlock,
                    logMeta, tree.getLeavesBlock(), tree.getLeavesMeta());
        }
    }

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID) && modState == ModState.INITIALIZED) registerWood();
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
