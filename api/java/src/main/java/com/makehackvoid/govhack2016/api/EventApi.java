package com.makehackvoid.govhack2016.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.makehackvoid.govhack2016.MHVServlet;
import com.makehackvoid.govhack2016.datasets.abcnews.NewsArticle;
import com.makehackvoid.govhack2016.datasets.abcnews.NewsArticles;
import com.makehackvoid.govhack2016.datasets.parking.ParkingEvent;
import com.makehackvoid.govhack2016.datasets.parking.ParkingEvents;
import com.makehackvoid.govhack2016.datasets.roaddeaths.RoadDeath;
import com.makehackvoid.govhack2016.datasets.roaddeaths.RoadDeaths;
import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * Event API - returns events between the given timestamps.
 *
 * @author Yiannis Paschalidis
 */
public class EventApi
{
    /**
     * Handles a request to the Event API.
     * @param req the HTTP request.
     * @param resp the HTTP response.
     * @throws IOException if there is an i/o error.
     */
    public static void handle(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
    {
        if (req.getParameter("closest") != null)
        {
            handleClosestEventRequest(req, resp);
        }
        else
        {
            handleTimeRangeRequest(req, resp);
        }
    }

    /**
     * Handles a request for the event closest to the given time.
     * @param req the HTTP request.
     * @param resp the HTTP response.
     * @throws IOException if there is an i/o error.
     */
    private static void handleClosestEventRequest(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
    {
        long target = -1;

        try
        {
            target = Long.parseLong(req.getParameter("closest"));
        }
        catch (Exception e)
        {
        }

        if (target < 0)
        {
            MHVServlet.sendError(HttpStatus.BAD_REQUEST_400, resp);
            return;
        }

        long oneHour = 60 * 60 * 1000;
        long from = target - oneHour;
        long to = target + oneHour;

        //List<ParkingEvent> parkingEvents = ParkingEvents.getEvents(from, to);
        List<RoadDeath> roadDeaths = RoadDeaths.getEvents(from, to);
        List<NewsArticle> newsArticles = NewsArticles.getEvents(from, to);

        List<TimeBasedEvent> combined = new ArrayList<TimeBasedEvent>();
        //combined.addAll(parkingEvents);
        combined.addAll(roadDeaths);
        combined.addAll(newsArticles);

        long closestDiff = Long.MAX_VALUE;
        TimeBasedEvent closestEvent = null;

        // TODO: Performance - binary search
        for (TimeBasedEvent event : combined)
        {
            long diff = Math.abs(event.getTime() - target);

            if (diff < closestDiff)
            {
                closestDiff = diff;
                closestEvent = event;
            }
            else
            {
                // The events are sorted by time, so there won't be any closer ones.
                break;
            }
        }

        resp.setContentType("application/json");
        Writer writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
        writer.write("[");

        if (closestEvent != null)
        {
            writeEvent(closestEvent, writer);
        }

        writer.write("\n]\n");
        writer.close();
    }

    /**
     * Handles a request for a list of events within the given time range.
     * @param req the HTTP request.
     * @param resp the HTTP response.
     * @throws IOException if there is an i/o error.
     */
    private static void handleTimeRangeRequest(final HttpServletRequest req, final HttpServletResponse resp)
            throws IOException, UnsupportedEncodingException
    {
        long from = -1;
        long to = -1;

        try
        {
            from = Long.parseLong(req.getParameter("from"));
            to = Long.parseLong(req.getParameter("to"));
        }
        catch (Exception e)
        {
        }

        if (from < 0 || to < 0 || to < from)
        {
            MHVServlet.sendError(HttpStatus.BAD_REQUEST_400, resp);
            return;
        }

        List<ParkingEvent> parkingEvents = ParkingEvents.getEvents(from, to);
        List<RoadDeath> roadDeaths = RoadDeaths.getEvents(from, to);
        List<NewsArticle> newsArticles = NewsArticles.getEvents(from, to);

        List<TimeBasedEvent> combined = new ArrayList<TimeBasedEvent>();
        combined.addAll(parkingEvents);
        combined.addAll(roadDeaths);
        combined.addAll(newsArticles);

        Collections.sort(combined, TimeBasedEvent.COMPARATOR);

        final int n = combined.size();

        resp.setContentType("application/json");
        Writer writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
        writer.write("[");

        for (int i=0 ; i<n ; i++)
        {
            TimeBasedEvent event = combined.get(i);
            writer.write(i > 0 ? ",\n" : "\n");

            writeEvent(event, writer);
        }

        writer.write("\n]\n");
        writer.close();
    }

    /**
     * Writes JSON for the given event.
     * @param roadDeath the event data.
     * @param writer the writer to send the JSON to.
     * @throws IOException if there is an i/o error writing the data.
     */
    private static void writeEvent(final TimeBasedEvent event, final Writer writer) throws IOException
    {
        if (event instanceof ParkingEvent)
        {
            writeParkingEvent((ParkingEvent) event, writer);
        }
        else if (event instanceof RoadDeath)
        {
            writeRoadDeathJson((RoadDeath) event, writer);
        }
        else if (event instanceof NewsArticle)
        {
            writeNewsArticleJson((NewsArticle) event, writer);
        }
    }

    /**
     * Writes JSON for the given parking event.
     * @param parkingEvent the parking event data.
     * @param writer the writer to send the JSON to.
     * @throws IOException if there is an i/o error writing the data.
     */
    private static void writeParkingEvent(final ParkingEvent parkingEvent, final Writer writer) throws IOException
    {
        writer.write("{\n \"eventType\":\"park\"");
        writer.write(",\n \"lotCode\":");
        writer.write(String.valueOf(parkingEvent.getLotCode()));
        writer.write(",\n \"bayNumber\":");
        writer.write(String.valueOf(parkingEvent.getBayNumber()));
        writer.write(",\n \"bayName\":\"");
        writer.write(parkingEvent.getBayName());
        writer.write("\",\n \"type\":\"");
        writer.write(parkingEvent.getType().name().toLowerCase());
        writer.write("\",\n \"time\":");
        writer.write(String.valueOf(parkingEvent.getTime()));
        writer.write("\n}");
    }

    /**
     * Writes JSON for the given road death.
     * @param roadDeath the road death data.
     * @param writer the writer to send the JSON to.
     * @throws IOException if there is an i/o error writing the data.
     */
    private static void writeRoadDeathJson(final RoadDeath roadDeath, final Writer writer) throws IOException
    {
        writer.write("{\n \"eventType\":\"roadDeath\"");
        writer.write(",\n \"state\":\"");
        writer.write(roadDeath.getState());
        writer.write("\",\n \"time\":");
        writer.write(String.valueOf(roadDeath.getTime()));
        writer.write(",\n \"type\":\"");
        writer.write(roadDeath.getType());
        writer.write("\",\n \"speedLimit\":");
        writer.write(String.valueOf(roadDeath.getSpeedLimit()));
        writer.write(",\n \"user\":\"");
        writer.write(roadDeath.getUser());
        writer.write("\",\n \"gender\":\"");
        writer.write(roadDeath.getGender());
        writer.write("\",\n \"age\":");
        writer.write(String.valueOf(roadDeath.getAge()));
        writer.write("\n}");
    }

    /**
     * Writes JSON for the given news article summary.
     * @param article the news article.
     * @param writer the writer to send the JSON to.
     * @throws IOException if there is an i/o error writing the data.
     */
    private static void writeNewsArticleJson(final NewsArticle article, final Writer writer) throws IOException
    {
        writer.write("{\n \"eventType\":\"news\"");
        writer.write(",\n \"firstPublished\": ");
        writer.write(String.valueOf(article.getFirstPublished()));
        writer.write(",\n \"contentId\":\"");
        writer.write(article.getContentId());
        writer.write("\",\n \"headLine\":\"");
        writer.write(article.getHeadLine().replace('"', '\''));
        writer.write("\",\n \"latitude\":\"");
        writer.write(String.valueOf(article.getLatitude()));
        writer.write("\",\n \"longitude\":\"");
        writer.write(String.valueOf(article.getLongitude()));
        writer.write("\"\n}");
    }
}