package com.scottkillen.mod.dendrology.compat.gardencollection;

import com.jaquadro.minecraft.gardencore.api.SaplingRegistry;
import com.jaquadro.minecraft.gardencore.api.WoodRegistry;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.kore.common.util.log.Logger;
import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.Item;

public enum GardenCoreMod
{
    ;

    private static final String MOD_ID = "GardenCore";
    private static Logger logger = Logger.forMod(TheMod.MOD_ID);

    public static void integrate()
    {
        if (Loader.isModLoaded(MOD_ID))
        {
            registerWood();
        } else logger.info("GardenCore mod not present. Integration skipped.");
    }

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
}
