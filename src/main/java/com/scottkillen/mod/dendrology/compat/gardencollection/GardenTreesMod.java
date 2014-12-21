package com.scottkillen.mod.dendrology.compat.gardencollection;

import com.jaquadro.minecraft.gardencore.api.SaplingRegistry;
import com.jaquadro.minecraft.gardencore.util.UniqueMetaIdentifier;
import com.jaquadro.minecraft.gardentrees.world.gen.OrnamentalTreeFactory;
import com.jaquadro.minecraft.gardentrees.world.gen.OrnamentalTreeRegistry;
import com.scottkillen.mod.dendrology.content.OverworldTreeSpecies;
import com.scottkillen.mod.dendrology.util.log.Logger;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.Item;

public enum GardenTreesMod
{
    ;
    private static final String GARDEN_TREES = "GardenTrees";

    public static void integrate()
    {
        if (Loader.isModLoaded(GARDEN_TREES))
        {
            registerSmallTrees();
        } else Logger.info("GardenTrees mod not present. Integration skipped.");
    }

    @Method(modid = GARDEN_TREES)
    private static void registerSmallTrees()
    {
        Logger.info("Registering small trees with GardenTrees.");

        final SaplingRegistry saplingReg = SaplingRegistry.instance();

        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            final String ornamentalTreeType = getOrnametalTreeType(tree);

            final OrnamentalTreeFactory factory = OrnamentalTreeRegistry.getTree(ornamentalTreeType);
            if (factory == null)
                //noinspection ContinueStatement
                continue;

            final Item sapling = Item.getItemFromBlock(tree.getSaplingBlock());
            final int saplingMeta = tree.getSaplingMeta();

            final UniqueMetaIdentifier logBlock = saplingReg.getWoodForSapling(sapling, saplingMeta);
            final UniqueMetaIdentifier leavesBlock = saplingReg.getLeavesForSapling(sapling, saplingMeta);

            //noinspection ConstantConditions
            if (logBlock == null && leavesBlock == null)
                //noinspection ContinueStatement
                continue;

            saplingReg.putExtendedData(sapling, saplingMeta, "sm_generator", factory.create(logBlock.getBlock(), logBlock.meta, leavesBlock.getBlock(), leavesBlock.meta));
        }
    }

    @SuppressWarnings({ "OverlyComplexMethod", "SwitchStatementWithTooManyBranches" })
    private static String getOrnametalTreeType(OverworldTreeSpecies tree)
    {
        switch (tree)
        {
            case CEDRUM:
                return "small_spruce";
            case DELNAS:
                return "small_palm";
            case EWCALY:
                return "small_mahogany";
            case HEKUR:
                return "small_acacia";
            case KIPARIS:
                return "small_pine";
            case KULIST:
                return "large_oak";
            case LATA:
                return "small_canopy";
            case NUCIS:
                return "tall_small_oak";
            case SALYX:
                return "small_willow";
            case TUOPA:
                return "small_pine";
            default:
                return "small_oak";
        }
    }
}
