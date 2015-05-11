package com.scottkillen.mod.dendrology.world;

import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.texture.TextureUtil;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.client.resources.IResourceManagerReloadListener;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.World;
import net.minecraft.world.storage.WorldInfo;
import java.io.IOException;

@SideOnly(Side.CLIENT)
public enum AcemusColorizer implements IResourceManagerReloadListener
{
    INSTANCE;
    @SuppressWarnings("StaticNonFinalField")
    private static int[] buffer = new int[256 * 256];

    public static int getInventoryColor()
    {
        return buffer[0x80 << 8 | 0x80];
    }

    public static int getColor(int x, int y)
    {
        int day = 0;
        final World world = Minecraft.getMinecraft().theWorld;
        if (world != null)
        {
            final WorldInfo info = world.getWorldInfo();
            if (info != null)
            {
                //noinspection NumericCastThatLosesPrecision
                day = (int) (info.getWorldTotalTime() / 24000L) & 0xff;
            }
        }

        final int i = (x << 3) + day & 0xff;
        final int j = (y << 3) + day & 0xff;
        return buffer[i << 8 | j];
    }

    @Override
    public void onResourceManagerReload(IResourceManager resourceManager)
    {
        try
        {
            //noinspection AssignmentToStaticFieldFromInstanceMethod
            buffer = TextureUtil.readImageData(resourceManager,
                    new ResourceLocation(TheMod.MOD_ID, "textures/colormap/acemus.png"));
        } catch (final IOException ignored)
        {
        }
    }
}
