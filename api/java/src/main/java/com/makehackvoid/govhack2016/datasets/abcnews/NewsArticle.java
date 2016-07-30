package com.makehackvoid.govhack2016.datasets.abcnews;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Encapsulates a news summary.
 *
 * @author Yiannis Paschalidis
 */
public class NewsArticle implements TimeBasedEvent
{
    /** When the article was first published. */
    private final long firstPublished;

    /** The article identifier. */
    private final String contentId;

    /** The article headline. */
    private final String headLine;

    /** The article latitude. */
    private final double latitude;

    /** The article longitude. */
    private final double longitude;

    /**
     * Creates a NewsArticle.
     *
     * @param firstPublished when the article was first published.
     * @param contentId the article identifier.
     * @param headLine the article headline.
     * @param latitude the latitude of the event the article is reporting on.
     * @param longitude the longitude of the event the article is reporting on.
     */
    public NewsArticle(final long firstPublished, final String contentId, final String headLine, final double latitude, final double longitude)
    {
        this.firstPublished = firstPublished;
        this.contentId = contentId;
        this.headLine = headLine;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    /** @return the timestamp for when the article was first published. */
    public long getFirstPublished()
    {
        return firstPublished;
    }

    /** {@inheritDoc} */
    @Override
    public long getTime()
    {
        return firstPublished;
    }

    /** @return the article identifier. */
    public String getContentId()
    {
        return contentId;
    }

    /** @return the article headline. */
    public String getHeadLine()
    {
        return headLine;
    }

    /** @return the latitude of the event the article is reporting on. */
    public double getLatitude()
    {
        return latitude;
    }

    /** @return the longitude of the event the article is reporting on. */
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
