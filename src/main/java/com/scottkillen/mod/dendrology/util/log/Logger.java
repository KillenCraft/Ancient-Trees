package com.scottkillen.mod.dendrology.util.log;

import com.google.common.base.Objects;
import com.scottkillen.mod.dendrology.TheMod;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;

@SuppressWarnings("WeakerAccess")
public enum Logger
{
    INSTANCE;

    @SuppressWarnings({ "NonSerializableFieldInSerializableClass", "InstanceVariableMayNotBeInitialized" })
    private org.apache.logging.log4j.Logger logger;

    public static void info(final String format, final Object... args)
    {
        INSTANCE.log(Level.INFO, format, args);
    }

    public static void log(final Level level, final Throwable exception, final String format, final Object... args)
    {
        //noinspection ChainedMethodCall
        INSTANCE.getLogger().log(level, String.format(format, args), exception);
    }

    public static void severe(final String format, final Object... args)
    {
        INSTANCE.log(Level.ERROR, format, args);
    }

    public static void warning(final String format, final Object... args)
    {
        INSTANCE.log(Level.WARN, format, args);
    }

    public org.apache.logging.log4j.Logger getLogger()
    {
        if (logger == null)
        {
            init();
        }

        return logger;
    }

    private void init()
    {
        if (logger == null)
        {
            logger = LogManager.getLogger(TheMod.MOD_ID);
        }
    }

    private void log(final Level level, final String format, final Object... data)
    {
        //noinspection ChainedMethodCall
        getLogger().log(level, String.format(format, data));
    }

    @Override
    public String toString()
    {
        return Objects.toStringHelper(this).add("logger", logger).toString();
    }
}
