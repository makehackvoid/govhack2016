package com.makehackvoid.govhack2016.datasets.parking;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Encapsulates a parking event.
 *
 * @author Yiannis Paschalidis
 */
public class ParkingEvent implements TimeBasedEvent
{
    public enum Type
    {
        ARRIVAL,
        DEPARTURE,
        OVERSTAY
    }

    private final int lotCode;
    private final int bayNumber;
    private final String bayName;
    private final Type type;
    private final long time;

    public ParkingEvent(final int lotCode, final int bayNumber, final String bayName, final Type type, final long time)
    {
        this.lotCode = lotCode;
        this.bayNumber = bayNumber;
        this.bayName = bayName;
        this.type = type;
        this.time = time;
    }

    /** @return the lot code */
    public int getLotCode()
    {
        return lotCode;
    }

    /** @return the bay number */
    public int getBayNumber()
    {
        return bayNumber;
    }

    /** @return the bay name */
    public String getBayName()
    {
        return bayName;
    }

    /** @return the event type */
    public Type getType()
    {
        return type;
    }

    /** @return the time when this event occurred. */
    public long getTime()
    {
        return time;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "ParkingEvent [lotCode=" + lotCode + ", bayNumber=" + bayNumber + ", bayName=" + bayName + ", type="
                + type + ", time=" + time + "]";
    }
}
