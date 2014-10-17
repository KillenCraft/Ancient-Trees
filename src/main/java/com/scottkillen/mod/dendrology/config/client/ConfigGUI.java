package com.scottkillen.mod.dendrology.config.client;

import com.google.common.collect.Lists;
import com.scottkillen.mod.dendrology.TheMod;
import com.scottkillen.mod.dendrology.config.ConfigHandler;
import cpw.mods.fml.client.config.GuiConfig;
import cpw.mods.fml.client.config.IConfigElement;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import java.util.List;

public class ConfigGUI extends GuiConfig
{
    public ConfigGUI(GuiScreen parent)
    {
        super(parent, getConfigElements(), TheMod.MOD_ID, false, false,
                GuiConfig.getAbridgedConfigPath(ConfigHandler.INSTANCE.getConfig().toString()));
    }

    @SuppressWarnings("unchecked")
    private static List<IConfigElement> getConfigElements()
    {
        final List<IConfigElement> configElements = Lists.newArrayList();

        final Configuration config = ConfigHandler.INSTANCE.getConfig();
        final ConfigElement general = new ConfigElement(config.getCategory(Configuration.CATEGORY_GENERAL));
        configElements.addAll(general.getChildElements());

        return configElements;
    }
}
