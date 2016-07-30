package com.makehackvoid.govhack2016.datasets.abcnews;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Encapsulates a news summary.
 *
 * @author Yiannis Paschalidis
 */
public class NewsArticle implements TimeBasedEvent
{
    private final long firstPublished;
    private final String contentId;
    private final String headLine;
    private final double latitude;
    private final double longitude;

    public NewsArticle(final long firstPublished, final String contentId, final String headLine, final double latitude, final double longitude)
    {
        this.firstPublished = firstPublished;
        this.contentId = contentId;
        this.headLine = headLine;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /**
     * @return the firstPublished
     */
    public long getFirstPublished()
    {
        return firstPublished;
    }

    @Override
    public long getTime()
    {
        return firstPublished;
    }

    /**
     * @return the contentId
     */
    public String getContentId()
    {
        return contentId;
    }

    /**
     * @return the headLine
     */
    public String getHeadLine()
    {
        return headLine;
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

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "NewsArticle [firstPublished=" + firstPublished + ", contentId=" + contentId + ", headLine=" + headLine
                + ", latitude=" + latitude + ", longitude=" + longitude + "]";
    }
}
