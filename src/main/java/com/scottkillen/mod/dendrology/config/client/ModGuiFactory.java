package com.scottkillen.mod.dendrology.config.client;

import cpw.mods.fml.client.IModGuiFactory;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiScreen;
import java.util.Set;

@SuppressWarnings({ "WeakerAccess", "UnusedDeclaration" })
public final class ModGuiFactory implements IModGuiFactory
{
    @Override
    public void initialize(Minecraft minecraftInstance) { }

    @Override
    public Class<? extends GuiScreen> mainConfigGuiClass() { return ConfigGUI.class; }

    @SuppressWarnings("ReturnOfNull")
    @Override
    public Set<RuntimeOptionCategoryElement> runtimeGuiCategories() { return null; }

    @SuppressWarnings("ReturnOfNull")
    @Override
    public RuntimeOptionGuiHandler getHandlerFor(RuntimeOptionCategoryElement element) { return null; }
}
