package com.makehackvoid.govhack2016.datasets.roaddeaths;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Encapsulates a road death.
 *
 * @author Yiannis Paschalidis
 */
public class RoadDeath implements TimeBasedEvent
{
    private final String State;
    private final long time;
    private final String type;
    private final int speedLimit;
    private final String user;
    private final String gender;
    private final int age;

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

    /**
     * @return the state
     */
    public String getState()
    {
        return State;
    }

    /**
     * @return the time
     */
    public long getTime()
    {
        return time;
    }

    /**
     * @return the type
     */
    public String getType()
    {
        return type;
    }

    /**
     * @return the speedLimit
     */
    public int getSpeedLimit()
    {
        return speedLimit;
    }

    /**
     * @return the user
     */
    public String getUser()
    {
        return user;
    }

    /**
     * @return the gender
     */
    public String getGender()
    {
        return gender;
    }

    /**
     * @return the age
     */
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
