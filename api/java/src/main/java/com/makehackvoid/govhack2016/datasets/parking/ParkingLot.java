package com.makehackvoid.govhack2016.datasets.parking;

/**
 * Encapsulates a parking lot.
 *
 * @author Yiannis Paschalidis
 */
public class ParkingLot
{
    private final int bayCount;
    private final String bayType;
    private final String city;
    private final double latitude;
    private final double longitude;
    private final int lotCode;
    private final int maxStayPeriod;
    private final String operatingHourCode;
    private final String street;
    private final int subZone;
    private final String tariffCode;
    private final String ward;
    private final int zone;
    private final String location;

    public ParkingLot(final int bayCount, final String bayType, final String city, final double latitude, final double longitude, final int lotCode,
            final int maxStayPeriod, final String operatingHourCode, final String street, final int subZone, final String tariffCode, final String ward,
            final int zone, final String location)
    {
        this.bayCount = bayCount;
        this.bayType = bayType;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.lotCode = lotCode;
        this.maxStayPeriod = maxStayPeriod;
        this.operatingHourCode = operatingHourCode;
        this.street = street;
        this.subZone = subZone;
        this.tariffCode = tariffCode;
        this.ward = ward;
        this.zone = zone;
        this.location = location;
    }

    /**
     * @return the bayCount
     */
    public int getBayCount()
    {
        return bayCount;
    }

    /**
     * @return the bayType
     */
    public String getBayType()
    {
        return bayType;
    }

    /**
     * @return the city
     */
    public String getCity()
    {
        return city;
    }

    /**
     * @return the latitude
     */
    public double getLatitude()
    {
        return latitude;
    }

    /**
     * @return the longitude
     */
    public double getLongitude()
    {
        return longitude;
    }

    /**
     * @return the lotCode
     */
    public int getLotCode()
    {
        return lotCode;
    }

    /**
     * @return the maxStayPeriod
     */
    public int getMaxStayPeriod()
    {
        return maxStayPeriod;
    }

    /**
     * @return the operatingHourCode
     */
    public String getOperatingHourCode()
    {
        return operatingHourCode;
    }

    /**
     * @return the street
     */
    public String getStreet()
    {
        return street;
    }

    /**
     * @return the subZone
     */
    public int getSubZone()
    {
        return subZone;
    }

    /**
     * @return the tariffCode
     */
    public String getTariffCode()
    {
        return tariffCode;
    }

    /**
     * @return the ward
     */
    public String getWard()
    {
        return ward;
    }

    /**
     * @return the zone
     */
    public int getZone()
    {
        return zone;
    }

    /**
     * @return the location
     */
    public String getLocation()
    {
        return location;
    }
}
