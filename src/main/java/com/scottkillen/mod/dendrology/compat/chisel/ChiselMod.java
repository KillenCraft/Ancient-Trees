package com.scottkillen.mod.dendrology.compat.chisel;

import com.cricketcraft.chisel.api.carving.CarvableHelper;
import com.cricketcraft.chisel.api.carving.CarvingUtils;
import com.cricketcraft.chisel.api.carving.ICarvingRegistry;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.content.overworld.OverworldTreeSpecies;
import com.scottkillen.mod.koresample.compat.Integrator;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.LoaderState.ModState;
import cpw.mods.fml.common.Optional.Method;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.oredict.OreDictionary;

import static net.minecraftforge.oredict.OreDictionary.WILDCARD_VALUE;

public final class ChiselMod extends Integrator
{
    private static final String MOD_ID = "chisel";
    private static final String MOD_NAME = "Chisel 2";

    @Method(modid = MOD_ID)
    private static void preInit()
    {
        loadBlocks();
    }

    private static CreativeTabs getChiselCreativeTab()
    {
        for (final CreativeTabs tab : CreativeTabs.creativeTabArray)
            if (tab.getTabLabel().equals("tabWoodChiselBlocks"))
                return tab;

        return TheMod.INSTANCE.creativeTab();
    }

    @Method(modid = MOD_ID)
    private static void loadBlocks()
    {
        for (final OverworldTreeSpecies species : OverworldTreeSpecies.values())
        {
            final String name = String.format("%s%s", species.speciesName(), "_planks");

            final String speciesName = species.speciesName();
            final ChiselWoodBlock carvablePlanks = new ChiselWoodBlock(speciesName);

            CarvableHelper carverHelper = new CarvableHelper(carvablePlanks);

            for (int i = 0; i < 15; i++)
                carverHelper.addVariation(carvablePlanks.getVariationName(i), i, carvablePlanks);

            carverHelper.registerAll(carvablePlanks, name);

            final ICarvingRegistry chisel = CarvingUtils.getChiselRegistry();

            chisel.addVariation(name, species.woodBlock(), species.woodSubBlockIndex(), 0);

            OreDictionary.registerOre("plankWood", new ItemStack(carvablePlanks, 1, WILDCARD_VALUE));

            carvablePlanks.setCreativeTab(getChiselCreativeTab());

            Blocks.fire.setFireInfo(carvablePlanks, 5, 20);

            chisel.setVariationSound(name, MOD_ID + ":chisel.wood");
        }
    }

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
