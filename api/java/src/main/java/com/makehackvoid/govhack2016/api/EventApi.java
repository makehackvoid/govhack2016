package com.makehackvoid.govhack2016.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.makehackvoid.govhack2016.datasets.parking.ParkingEvent;
import com.makehackvoid.govhack2016.datasets.parking.ParkingEvents;

/**
 * Event API - returns events between the given timestamps.
 *
 * @author Yiannis Paschalidis
 */
public class EventApi
{
    public static void handle(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
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
            resp.sendError(HttpStatus.BAD_REQUEST_400);
            return;
        }

        List<ParkingEvent> events = ParkingEvents.getEvents(from, to);
        final int n = events.size();

        Writer writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
        writer.write("[");

        for (int i=0 ; i<n ; i++)
        {
            ParkingEvent event = events.get(i);
            writer.write(i > 0 ? ",\n" : "\n");

            writer.write("{\n eventType:\"park\"");
            writer.write("\n lotCode: ");
            writer.write(String.valueOf(event.getLotCode()));
            writer.write(",\n bayNumber:");
            writer.write(String.valueOf(event.getBayNumber()));
            writer.write(",\n bayName:\"");
            writer.write(event.getBayName());
            writer.write("\",\n type:");
            writer.write(event.getType().name().toLowerCase());
            writer.write(",\n time:");
            writer.write(String.valueOf(event.getTime()));
            writer.write("\n}");
        }

        writer.write("\n]\n");
        writer.close();
    }
}
