package com.scottkillen.mod.dendrology.config.client;

import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public final class ConfigGUI extends GuiConfig
{
    public ConfigGUI(GuiScreen parent)
    {
        super(parent, getConfigElements(), TheMod.MOD_ID, false, false,
                GuiConfig.getAbridgedConfigPath(TheMod.INSTANCE.configuration().toString()));
    }

    @SuppressWarnings("unchecked")
    private static List<IConfigElement> getConfigElements()
    {
        final List<IConfigElement> configElements = Lists.newArrayList();

        final Configuration config = TheMod.INSTANCE.configuration();
        final ConfigElement general = new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL));
        configElements.addAll(general.getChildElements());

        return configElements;
    }
}
