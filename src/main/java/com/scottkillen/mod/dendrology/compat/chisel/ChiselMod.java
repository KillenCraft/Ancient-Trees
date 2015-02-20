package com.scottkillen.mod.dendrology.compat.chisel;

import com.cricketcraft.chisel.block.BlockCarvable;
import com.cricketcraft.chisel.carving.Carving;
import com.cricketcraft.chisel.init.ChiselTabs;
import com.google.common.collect.ImmutableList;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.util.StatCollector;

public final class ChiselMod extends Integrator
{
    private static final String MOD_ID = "chisel";
    private static final String MOD_NAME = "Chisel 2";

    @Method(modid = MOD_ID)
    private static void preInit()
    {
        loadBlocks();
    }

    @Method(modid = MOD_ID)
    private static void loadBlocks()
    {
        final ImmutableList<String> variation = ImmutableList
                .of("clean", "Short", "fancy", "panel", "double", "crate", "cratefancy", "large", "vertical", "uneven",
                        "parquet", "blinds", "scaffold", "chaotic", "verticalchaotic");
        final ImmutableList<Integer> metadata = ImmutableList.of(1, 2, 6, 8, 9, 10, 11, 13, 3, 4, 5, 7, 12, 14, 15);
        final ImmutableList<String> texture = ImmutableList
                .of("clean", "short", "fancy", "panel-nails", "double", "crate", "crate-fancy", "large", "vertical",
                        "vertical-uneven", "parquet", "blinds", "crateex", "chaotic-hor", "chaotic");

        int count = 0;
        for (final OverworldTreeSpecies species : OverworldTreeSpecies.values())
        {
            final String name = String.format("%s%s", species.speciesName(), "_planks");

            final BlockCarvable carvablePlanks = newCarvablePlank();
            carvablePlanks.setCreativeTab(ChiselTabs.tabWoodChiselBlocks).setHardness(2.0F).setResistance(5.0F)
                    .setStepSound(Block.soundTypeWood);
            final String speciesName = species.speciesName();
            carvablePlanks.carverHelper.setChiselBlockName(planksChiselBlockName(speciesName));
            for (int i = 0; i < variation.size(); i++)
            {
                carvablePlanks.carverHelper
                        .addVariation(planksChiselVariationName(speciesName, variation.get(i)), metadata.get(i),
                                "planks-" + speciesName + '/' + texture.get(i));
            }
            carvablePlanks.carverHelper.registerAll(carvablePlanks, name);
            Carving.chisel.addVariation(name, species.woodBlock(), count, 0);
            carvablePlanks.setHarvestLevel("axe", 0);
            Carving.chisel.registerOre(name, "wood");
            Carving.chisel.setVariationSound(name, MOD_ID + ":chisel.wood");
            count++;
        }
    }

    @Method(modid = MOD_ID)
    private static String planksChiselBlockName(String speciesName)
    {
        return StatCollector
                .translateToLocal(String.format("tile.%s:wood.%s.name", TheMod.INSTANCE.modID(), speciesName));
    }

    @Method(modid = MOD_ID)
    private static String planksChiselVariationName(String speciesName, String variation)
    {
        return StatCollector.translateToLocal(
                String.format("chisel.%s:planks.%s.%s.name", TheMod.INSTANCE.modID(), speciesName, variation));
    }

    @Method(modid = MOD_ID)
    private static BlockCarvable newCarvablePlank() { return new BlockCarvable(Material.wood); }

    @Override
    public void doIntegration(ModState modState)
    {
        if (Loader.isModLoaded(MOD_ID))
        {
            switch (modState)
            {
                case PREINITIALIZED:
                    preInit();
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
