package com.makehackvoid.govhack2016.datasets.roaddeaths;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Encapsulates a road death.
 *
 * @author Yiannis Paschalidis
 */
public class RoadDeath implements TimeBasedEvent
{
    /** The state where the event occurred. */
    private final String State;

    /** The timestamp for when the event occurred. */
    private final long time;

    /** The type of event. */
    private final String type;

    /** The speed limit where the event occurred, or 0 if unlimited. */
    private final int speedLimit;

    /** The type of road user involved. */
    private final String user;

    /** The road user's gender. */
    private final String gender;

    /** The road user's age. */
    private final int age;

    /**
     * Creates a RoadDeath.
     *
     * @param state the state where the event occurred.
     * @param time the timestamp for when the event occurred.
     * @param type the type of event.
     * @param speedLimit the speed limit where the event occurred, or 0 if unlimited.
     * @param user the type of road user involved.
     * @param gender the road user's gender.
     * @param age the road user's age.
     */
    public RoadDeath(final String state, final long time, final String type, final int speedLimit, final String user, final String gender, final int age)
    {
        State = state;
        this.time = time;
        this.type = type;
        this.speedLimit = speedLimit;
        this.user = user;
        this.gender = gender;
        this.age = age;
    }

    /** @return the state where the event occurred. */
    public String getState()
    {
        return State;
    }

    /** @return the timestamp for when the event occurred. */
    public long getTime()
    {
        return time;
    }

    /** @return the type of event. */
    public String getType()
    {
        return type;
    }

    /** @return the speed limit where the event occurred, or 0 if unlimited. */
    public int getSpeedLimit()
    {
        return speedLimit;
    }

    /** @return the type of road user involved. */
    public String getUser()
    {
        return user;
    }

    /** @return the road user's gender. */
    public String getGender()
    {
        return gender;
    }

    /** @return the road user's age. */
    public int getAge()
    {
        return age;
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "RoadDeath [State=" + State + ", time=" + time + ", type=" + type + ", speedLimit=" + speedLimit
                + ", user=" + user + ", gender=" + gender + ", age=" + age + "]";
    }
}
