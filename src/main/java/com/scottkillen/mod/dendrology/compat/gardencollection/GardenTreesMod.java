package com.scottkillen.mod.dendrology.compat.gardencollection;

import com.jaquadro.minecraft.gardencore.api.SaplingRegistry;
import com.jaquadro.minecraft.gardencore.api.WoodRegistry;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.util.log.Logger;
import com.scottkillen.mod.kore.tree.block.ModLogBlock;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.Item;

public enum GardenTreesMod
{
    ;
    private static final String GARDEN_CORE = "GardenCore";

    public static void integrate()
    {
        if (Loader.isModLoaded(GARDEN_CORE))
        {
            registerWood();
        } else Logger.info("GardenCore mod not present. Integration skipped.");
    }

    @Method(modid = GARDEN_CORE)
    private static void registerWood()
    {
        Logger.info("Registering wood with GardenCore.");

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
