package com.makehackvoid.govhack2016.datasets.roaddeaths;

import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.makehackvoid.govhack2016.util.TimeBasedEvent;
import com.makehackvoid.govhack2016.util.Util;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Collection of Australian Road Deaths Database entries.
 *
 * @author Yiannis Paschalidis
 */
public class RoadDeaths
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(RoadDeaths.class.getName());

    /** Parking events, ordered by timestamp. */
    private static final List<RoadDeath> EVENTS = new ArrayList<RoadDeath>();

    static
    {
        log.log(Level.INFO, "Reading parking events");
        readData("/datasets/road_deaths/FatalitiesMAY2016-csv.csv");
    }

    /** Prevent instantiation of this utility class. */
    private RoadDeaths()
    {
    }

    /**
     * Reads the parking lot history from the given path.
     * @param path the path to read from.
     */
    private static void readData(final String path)
    {
        List<String> months = Arrays.asList("January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December");
        CSVReader reader = null;

        try
        {
            reader = new CSVReader(new InputStreamReader(RoadDeaths.class.getResourceAsStream(path)));

            // Skip header
            reader.readNext();
            int lineNum = 1;

            Calendar cal = Calendar.getInstance();

            for (String[] line = reader.readNext() ; line != null ; line = reader.readNext())
            {
                lineNum++;

                try
                {

                    String state = line[0];
                    int day = Integer.parseInt(line[1]);
                    String monthName = line[2];
                    int year = Integer.parseInt(line[3]);
                    int hour = Integer.parseInt(line[4]);
                    int minute = Integer.parseInt(line[5]);
                    String type = line[6];
                    int speedLimit = "Unlimited".equals(line[7]) ? 0 : Integer.parseInt(line[7]);
                    String user = line[8];
                    String gender = line[9];
                    int age = Integer.parseInt(line[10]);

                    cal.set(Calendar.DAY_OF_MONTH, day);
                    cal.set(Calendar.MONTH, months.indexOf(monthName));
                    cal.set(Calendar.YEAR, year);
                    cal.set(Calendar.HOUR_OF_DAY, hour);
                    cal.set(Calendar.MINUTE, minute);

                    EVENTS.add(new RoadDeath(state, cal.getTimeInMillis(), type, speedLimit, user, gender, age));
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

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

        log.log(Level.INFO, "Read " + EVENTS.size() + " events, "
               + " first = " + dateFormat.format(min) + " (" + min
               + "), last = " + dateFormat.format(max) + " (" + max + ')');
    }

    public static List<RoadDeath> getEvents(final long from, final long to)
    {
        List<RoadDeath> results = new ArrayList<RoadDeath>();
        final int n = EVENTS.size();

System.out.println("### RoadDeaths.getEvents -> " + from + " - " + to);

        // TODO: Performance - binary search
        for (int i = 0; i < n; i++)
        {
            RoadDeath event = EVENTS.get(i);
            long eventTime = event.getTime();

System.out.println("### RoadDeaths[" + i +"].time -> " + eventTime);

            if (eventTime >= from && eventTime <= to)
            {
System.out.println("### Add");
                results.add(event);
            }
            else if (eventTime > to)
            {
                System.out.println("### Break");

                // Sorted by timestamp, so we've reached the end.
                break;
            }
        }

        return results;
    }
}
