package com.scottkillen.mod.dendrology.kore.config;

import net.minecraftforge.common.config.Configuration;

public interface ConfigSyncable
{
    void convertOldConfig(Configuration oldConfig);

    void syncConfig(Configuration config);
}
