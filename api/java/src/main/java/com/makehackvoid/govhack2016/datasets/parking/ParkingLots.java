package com.makehackvoid.govhack2016.datasets.parking;

import java.io.InputStreamReader;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.makehackvoid.govhack2016.util.Util;

import au.com.bytecode.opencsv.CSVReader;

/**
 * Collection of ACT SmartParking lots.
 *
 * @author Yiannis Paschalidis
 */
public class ParkingLots
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(ParkingLots.class.getName());

    /** Parking lots keyed by lot code. */
    private static final Map<Integer, ParkingLot> LOTS_BY_LOT_CODE = new HashMap<Integer, ParkingLot>();

    static
    {
        log.log(Level.INFO, "Reading parking lot data");
        readData("/datasets/parking/SmartParking_Lots.csv");
        log.log(Level.INFO, "Read " + LOTS_BY_LOT_CODE.size() + " lots.");
    }

    /**
     * Reads the parking lot data from the given path.
     * @param path the path to read from.
     */
    private static void readData(final String path)
    {
        CSVReader reader = null;

        try
        {
            reader = new CSVReader(new InputStreamReader(ParkingLots.class.getResourceAsStream(path)));

            // Skip header
            reader.readNext();
            int lineNum = 1;

            for (String[] line = reader.readNext() ; line != null ; line = reader.readNext())
            {
                lineNum++;

                try
                {
                    int col = 0;

                    int bayCount = Integer.parseInt(line[col++]);
                    String bayType = line[col++];
                    String city = line[col++];
                    double latitude = Double.parseDouble(line[col++]);
                    double longitude = Double.parseDouble(line[col++]);
                    int lotCode = Integer.parseInt(line[col++]);
                    int maxStayPeriod = Integer.parseInt(line[col++]);
                    String operatingHourCode = line[col++];
                    String street = line[col++];
                    int subZone = Integer.parseInt(line[col++]);
                    String tariffCode = line[col++];
                    String ward = line[col++];
                    int zone = Integer.parseInt(line[col++]);
                    String location = line[col++];

                    ParkingLot lot = new ParkingLot(bayCount, bayType, city, latitude, longitude, lotCode, maxStayPeriod, operatingHourCode, street, subZone, tariffCode, ward, zone, location);
                    LOTS_BY_LOT_CODE.put(lotCode, lot);
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
    }

    /**
     * Retrieves a parking lot by its lot code.
     * @param lotCode the lot code.
     * @return the ParkingLot for the given lot code, or null if not found.
     */
    public static ParkingLot getLot(final int lotCode)
    {
        return LOTS_BY_LOT_CODE.get(lotCode);
    }

    /**
     * @return a read-only view of the entire set of {@link ParkingLot}s.
     */
    public static Collection<ParkingLot> getLots()
    {
        return Collections.unmodifiableCollection(LOTS_BY_LOT_CODE.values());
    }
}
