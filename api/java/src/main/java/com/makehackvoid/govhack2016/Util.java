package com.makehackvoid.govhack2016;

import java.io.Closeable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * General utility methods.
 *
 * @author Yiannis Paschalidis
 */
public final class Util
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(Util.class.getName());

    /** Prevent instantiation of this utility class. */
    private Util()
    {
    }

    /**
     * Attempts to close the given Closeable.
     * Logs an error if it could not be closed.
     *
     * @param closeable the Closeable to close.
     */
    public static void safeClose(final Closeable closeable)
    {
        if (closeable != null)
        {
            try
            {
                closeable.close();
            }
            catch (Exception e)
            {
                log.log(Level.FINER, "Couldn't close " + closeable.getClass(), e);
            }
        }
    }
}
