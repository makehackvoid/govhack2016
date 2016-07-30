package com.makehackvoid.govhack2016.util;

import java.util.Comparator;

/**
 * Inteface for time-based events.
 *
 * @author Yiannis Paschalidis
 */
public interface TimeBasedEvent
{
    /** A comparator to use for sorting the events in ascending time order. */
    public static final Comparator<TimeBasedEvent> COMPARATOR = new Comparator<TimeBasedEvent>()
    {
        @Override
        public int compare(final TimeBasedEvent o1, final TimeBasedEvent o2)
        {
            long diff = o1.getTime() - o2.getTime();

            if (diff < 0)
            {
                return -1;
            }

            return diff == 0 ? 0 : 1;
        }
    };

    /** @return the timestamp for when the event occurred. */
    long getTime();
}
