package com.makehackvoid.govhack2016;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.InetSocketAddress;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerCollection;
import org.eclipse.jetty.webapp.WebAppContext;

import com.makehackvoid.govhack2016.datasets.parking.ParkingEvents;
import com.makehackvoid.govhack2016.datasets.parking.ParkingLots;
import com.makehackvoid.govhack2016.datasets.roaddeaths.RoadDeaths;
import com.makehackvoid.govhack2016.util.Util;

/**
 * MHV GovHack 2016 web app main entry point.
 *
 * @author Yiannis Paschalidis
 */
public class MHVApp
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(MHVApp.class.getName());

    /** The application configuration. */
    private static final Properties CONFIG = getProperties();

    /**
     * Application main entry point.
     * @param args command-line arguments, ignored.
     * @throws Exception on error
     */
    public static void main( final String[] args) throws Exception
    {
        // Initialise data sets. This takes a while...
        initialiseDataSets();

        // Create & configure the server
        String hostStr = CONFIG.getProperty("host");
        String portStr = CONFIG.getProperty("port");
        int port = 80;

        if (portStr != null)
        {
            try
            {
                port = Integer.parseInt(portStr.trim());
            }
            catch (NumberFormatException e)
            {
                log.log(Level.SEVERE, "Invalid port: " + portStr, e);
            }
        }

        Server server = null;

        if (hostStr == null)
        {
            log.log(Level.INFO, "Starting server on port " + port);
            server = new Server(port);
        }
        else
        {
            hostStr = hostStr.trim();
            log.log(Level.INFO, "Starting server on " + hostStr + ":" + port);
            server = new Server(new InetSocketAddress(hostStr, port));
        }

        // Register the MVHServlet with the server
        HandlerCollection handlers = new HandlerCollection();
        WebAppContext webapp = new WebAppContext();
        webapp.addServlet(MHVServlet.class, "/*");
        webapp.setResourceBase(".");
        handlers.addHandler(webapp);
        server.setHandler(handlers);

        // Finally, start the server
        server.start();
        log.log(Level.INFO, "Server started");
    }

    /**
     * Initialises the data sets.
     */
    private static void initialiseDataSets()
    {
        // Initialise the data sets.
        new Thread()
        {
            @Override
            public void run()
            {
                ParkingLots.getLots();
            }
        }.start();

        new Thread()
        {
            @Override
            public void run()
            {
                ParkingEvents.getEvents(0, 0);
            }
        }.start();

        new Thread()
        {
            @Override
            public void run()
            {
                RoadDeaths.getEvents(0, 0);
            }
        }.start();
    }

    /**
     * Retrieves the application properties.
     * @return the application properties.
     */
    private static Properties getProperties()
    {
        Properties props = new Properties();
        InputStream is = null;

        try
        {
            // Try on classpath first
            is = MHVApp.class.getResourceAsStream("/mhvapp.properties");

            if (is == null)
            {
                is = new FileInputStream("mhvapp.properties");
            }

            props.load(is);
        }
        catch (Exception e)
        {
            log.warning("Failed to read app properties - using defaults");
        }
        finally
        {
             Util.safeClose(is);
        }

        return props;
    }

    public static String getConfigParam(final String key, final String defolt)
    {
        return CONFIG.getProperty(key, defolt);
    }
}
