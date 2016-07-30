package com.makehackvoid.govhack2016.api;

import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.makehackvoid.govhack2016.datasets.parking.ParkingLot;
import com.makehackvoid.govhack2016.datasets.parking.ParkingLots;

/**
 * Parking Lot API - returns parking lots.
 *
 * @author Yiannis Paschalidis
 */
public class ParkingLotApi
{
    /**
     * Handles a request to the Parking lot API.
     * @param req the HTTP request.
     * @param resp the HTTP response.
     * @throws IOException if there is an i/o error.
     */
    public static void handle(final HttpServletRequest req, final HttpServletResponse resp) throws IOException
    {
        int lotCode = -1;

        try
        {
            lotCode = Integer.parseInt(req.getParameter("lot"));
        }
        catch (Exception e)
        {
        }

        List<ParkingLot> lots = null;

        if (lotCode < 0)
        {
            lots = new ArrayList<ParkingLot>(ParkingLots.getLots());
        }
        else
        {
            ParkingLot lot = ParkingLots.getLot(lotCode);
            lots = (List<ParkingLot>) (lot == null ? Collections.emptyList() : Arrays.asList(lot));
        }

        final int n = lots.size();

        resp.setContentType("application/json");
        Writer writer = new OutputStreamWriter(resp.getOutputStream(), "UTF-8");
        writer.write("[");

        for (int i = 0; i < n; i++)
        {
            ParkingLot lot = lots.get(i);
            writer.write(i > 0 ? ",\n" : "\n");

            writer.write("{\n bayCount: ");
            writer.write(String.valueOf(lot.getBayCount()));
            writer.write(",\n bayType:\"");
            writer.write(lot.getBayType());
            writer.write("\",\n city:\"");
            writer.write(lot.getCity());
            writer.write("\",\n latitude:\"");
            writer.write(String.valueOf(lot.getLatitude()));
            writer.write("\",\n longitude:\"");
            writer.write(String.valueOf(lot.getLongitude()));
            writer.write("\",\n lotCode:");
            writer.write(String.valueOf(lot.getLotCode()));
            writer.write(",\n maxStayPeriod:");
            writer.write(String.valueOf(lot.getMaxStayPeriod()));
            writer.write(",\n operatingHourCode:\"");
            writer.write(lot.getOperatingHourCode());
            writer.write("\",\n street:\"");
            writer.write(lot.getStreet());
            writer.write("\",\n subZone:");
            writer.write(String.valueOf(lot.getSubZone()));
            writer.write(",\n tariffCode:\"");
            writer.write(lot.getTariffCode());
            writer.write("\",\n ward:\"");
            writer.write(lot.getWard());
            writer.write("\",\n zone:");
            writer.write(String.valueOf(lot.getZone()));
            writer.write(",\n location:\"");
            writer.write(lot.getLocation().replace('\n', ' '));
            writer.write("\"\n}");
        }

        writer.write("\n]\n");
        writer.close();
    }
}
