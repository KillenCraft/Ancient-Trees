package com.scottkillen.mod.dendrology.proxy.render;

import com.scottkillen.mod.dendrology.world.AcemusColorizer;
import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResourceManager;

public class ClientRenderProxy extends RenderProxy
{
    @Override
    public void init()
    {
        final IResourceManager resourceManager = Minecraft.getMinecraft().getResourceManager();
        if (resourceManager instanceof IReloadableResourceManager)
            ((IReloadableResourceManager) resourceManager).registerReloadListener(AcemusColorizer.INSTANCE);
    }
}
