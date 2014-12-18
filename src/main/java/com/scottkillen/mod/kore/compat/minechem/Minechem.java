package com.scottkillen.mod.kore.compat.minechem;

import cpw.mods.fml.common.Loader;
import net.minecraft.item.ItemStack;

public enum Minechem
{
    ;

    private static boolean isMineChemLoaded()
    {
        return Loader.isModLoaded("minechem");
    }

    public enum RecipeAPI
    {
        ;

        private static MinechemRecipeAPI RecipeAPI = null;

        private static void init()
        {
            if (isMineChemLoaded() && RecipeAPI == null)
                RecipeAPI = new MinechemRecipeAPI();
        }

        public static boolean addDecompositionRecipe(ItemStack input, String... outputs)
        {
            init();
            return RecipeAPI != null && RecipeAPI.addDecompositionRecipe(input, outputs);
        }
    }

}
