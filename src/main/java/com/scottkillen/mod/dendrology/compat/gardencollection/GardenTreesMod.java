package com.scottkillen.mod.dendrology.compat.gardencollection;

import com.jaquadro.minecraft.gardencore.api.SaplingRegistry;
import com.jaquadro.minecraft.gardencore.util.UniqueMetaIdentifier;
import com.jaquadro.minecraft.gardentrees.world.gen.OrnamentalTreeFactory;
import com.jaquadro.minecraft.gardentrees.world.gen.OrnamentalTreeRegistry;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.config.Settings;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.item.Item;

public final class GardenTreesMod extends Integrator
{
    private static final String MOD_ID = "GardenTrees";
    private static final String MOD_NAME = MOD_ID;

    @Method(modid = MOD_ID)
    private static void registerSmallTrees()
    {
        TheMod.logger().info("Registering small trees with GardenTrees.");

        final SaplingRegistry saplingReg = SaplingRegistry.instance();

        for (final OverworldTreeSpecies tree : OverworldTreeSpecies.values())
        {
            final String ornamentalTreeType = getOrnametalTreeType(tree);

            final OrnamentalTreeFactory factory = OrnamentalTreeRegistry.getTree(ornamentalTreeType);
            if (factory == null)
                //noinspection ContinueStatement
                continue;

            final Item sapling = Item.getItemFromBlock(tree.saplingBlock());
            final int saplingMeta = tree.saplingSubBlockIndex();

            final UniqueMetaIdentifier logBlock = saplingReg.getWoodForSapling(sapling, saplingMeta);
            final UniqueMetaIdentifier leavesBlock = saplingReg.getLeavesForSapling(sapling, saplingMeta);

            //noinspection ConstantConditions
            if (logBlock == null && leavesBlock == null)
                //noinspection ContinueStatement
                continue;

            saplingReg.putExtendedData(sapling, saplingMeta, "sm_generator",
                    factory.create(logBlock.getBlock(), logBlock.meta, leavesBlock.getBlock(), leavesBlock.meta));
        }
    }

    @SuppressWarnings({ "OverlyComplexMethod", "SwitchStatementWithTooManyBranches" })
    @Method(modid = MOD_ID)
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

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID) && Settings.INSTANCE.integrateGardenStuff() && modState == ModState.INITIALIZED)
            registerSmallTrees();
    }

    @Override
    protected String modID() { return MOD_ID; }

    @Override
    protected String modName() { return MOD_NAME; }
}
