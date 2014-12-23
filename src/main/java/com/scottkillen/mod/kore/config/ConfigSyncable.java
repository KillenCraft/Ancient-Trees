package com.scottkillen.mod.kore.config;

import net.minecraftforge.common.config.Configuration;

public interface ConfigSyncable
{
    void convertOldConfig(Configuration oldConfig);

    void syncConfig(Configuration config);
}
