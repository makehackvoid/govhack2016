package com.makehackvoid.govhack2016.datasets.parking;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import com.makehackvoid.govhack2016.datasets.parking.ParkingEvent.Type;
import com.makehackvoid.govhack2016.util.TimeBasedEvent;
import com.makehackvoid.govhack2016.util.Util;

import au.com.bytecode.opencsv.CSVReader;

/**
 * ACT SmartParking history.
 *
 * @author Yiannis Paschalidis
 */
public class ParkingEvents
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(ParkingEvents.class.getName());

    /** Parking events, ordered by timestamp. */
    private static final List<ParkingEvent> EVENTS = new ArrayList<ParkingEvent>();

    static
    {
        log.log(Level.INFO, "Reading parking events");
        readData("/datasets/parking/SmartParking_History.zip");
    }

    /** Prevent instantiation of this utility class. */
    private ParkingEvents()
    {
    }

    /**
     * Reads the parking lot history from the given path.
     * @param path the path to read from.
     */
    private static void readData(final String path)
    {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa Z");
        CSVReader reader = null;

        try
        {
            ZipInputStream zis = new ZipInputStream(ParkingEvents.class.getResourceAsStream(path));

            for (ZipEntry entry = zis.getNextEntry(); entry.getName() == null || !entry.getName().toLowerCase().endsWith("csv") ; entry = zis.getNextEntry())
            {
            }

            reader = new CSVReader(new InputStreamReader(zis));

            // Skip header
            reader.readNext();
            int lineNum = 1;

            for (String[] line = reader.readNext() ; line != null ; line = reader.readNext())
            {
                lineNum++;

                try
                {
                    int lotCode = Integer.parseInt(line[2]);
                    int bayNumber = Integer.parseInt(line[3]);
                    String bayName = line[4];
                    Type type = null;

                    if ("Arrival".equals(line[5]))
                    {
                        type = Type.ARRIVAL;
                    }
                    else if ("Vacated".equals(line[5]))
                    {
                        type = Type.DEPARTURE;
                    }
                    else if ("Overstay".equals(line[5]))
                    {
                        type = Type.OVERSTAY;
                    }
                    else
                    {
                        // Flat battery? Overstay cancel?
                        continue;
                    }

                    long time = dateFormat.parse(line[6] + " +1000").getTime();
                    EVENTS.add(new ParkingEvent(lotCode, bayNumber, bayName, type, time));
                }
                catch (Exception e)
                {
                    log.log(Level.WARNING, "Error reading line " + lineNum, e);
                }
            }
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Error reading parking lot data", e);
        }
        finally
        {
            Util.safeClose(reader);
        }

        Collections.sort(EVENTS, TimeBasedEvent.COMPARATOR);

        long min = EVENTS.get(0).getTime();
        long max = EVENTS.get(EVENTS.size() - 1).getTime();

        log.log(Level.INFO, "Read " + EVENTS.size() + " events, "
               + " first = " + dateFormat.format(min) + " (" + min
               + "), last = " + dateFormat.format(max) + " (" + max + ')');
    }

    public static List<ParkingEvent> getEvents(final long from, final long to)
    {
        List<ParkingEvent> results = new ArrayList<ParkingEvent>();
        final int n = EVENTS.size();

        // TODO: Performance - binary search
        for (int i=0 ; i < n ; i++)
        {
            ParkingEvent event = EVENTS.get(i);
            long eventTime = event.getTime();

            if (eventTime >= from && eventTime <= to)
            {
                results.add(event);
            }

            if (eventTime > to)
            {
                // Sorted by timestamp, so we've reached the end.
                break;
            }
        }

        return results;
    }
}
