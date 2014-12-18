package com.scottkillen.mod.kore.compat.minechem;

import com.google.common.base.Optional;
import com.scottkillen.mod.dendrology.util.log.Logger;
import net.minecraft.item.ItemStack;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MinechemRecipeAPI
{
    private static final String API_FAILURE = "Failed to load Minechem API. Minechem recipe integration disabled.";

    /*
     * public static boolean addDecompositionRecipe(ItemStack input, String... outputs)
    */
    private Optional<Method> addDecompositionRecipe = Optional.absent();

    MinechemRecipeAPI()
    {
        Class<?> cls;
        try
        {
            cls = Class.forName("minechem.api.RecipeAPI");
            addDecompositionRecipe = Optional.fromNullable(cls.getMethod("addDecompositionRecipe", ItemStack.class, String[].class));
        }
        catch (final Exception e)
        {
            Logger.warning(API_FAILURE);
            addDecompositionRecipe = Optional.absent();
        }
    }

    boolean addDecompositionRecipe(ItemStack input, String... outputs)
    {
        if (addDecompositionRecipe.isPresent())
        {
            try
            {
                return (Boolean)addDecompositionRecipe.get().invoke(input, outputs);
            } catch (IllegalAccessException e)
            {
                Logger.warning(API_FAILURE);
                addDecompositionRecipe = Optional.absent();
            } catch (InvocationTargetException e)
            {
                Logger.warning(API_FAILURE);
                addDecompositionRecipe = Optional.absent();
            }
        }
        return false;
    }
}
