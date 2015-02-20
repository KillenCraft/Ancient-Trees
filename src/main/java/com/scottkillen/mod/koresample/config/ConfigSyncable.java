package com.scottkillen.mod.koresample.config;

import net.minecraftforge.common.config.Configuration;

public interface ConfigSyncable
{
    void convertOldConfig(Configuration oldConfig);

    void syncConfig(Configuration config);
}
