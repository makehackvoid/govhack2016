package com.makehackvoid.govhack2016.datasets.abcnews;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.makehackvoid.govhack2016.MHVApp;
import com.makehackvoid.govhack2016.util.TimeBasedEvent;

/**
 * News Articles Data source.
 *
 * @author Yiannis Paschalidis
 */
public final class NewsArticles
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(NewsArticles.class.getName());

    static
    {
        try
        {
            Class.forName("org.postgresql.Driver");
        }
        catch (Exception e)
        {
            log.log(Level.SEVERE, "Failed to load postgre driver", e);
        }
    }

    /** Prevent instantiation of this utility class. */
    private NewsArticles()
    {
    }

    /**
     * Retrieves a connection to the d/b.
     * @return a connection to the d/b.
     * @throws SQLException on error.
     */
    private static Connection getConnection() throws SQLException
    {
        String url = MHVApp.getConfigParam("newsarticles.jdbc.url", null);
        String user = MHVApp.getConfigParam("newsarticles.jdbc.user", null);
        String pass = MHVApp.getConfigParam("newsarticles.jdbc.password", null);

        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", pass);
        return DriverManager.getConnection(url, props);
    }

    /**
     * Retrieves the articles first published within the given time range.
     * Times are measured in millis past midnight, January 1, 1970 UTC.
     *
     * @param from the start of the time range.
     * @param to the end of the time range.
     * @return the events which occurred within the given time range.
     */
    public static List<NewsArticle> getEvents(final long from, final long to)
    {
        List<NewsArticle> results = new ArrayList<NewsArticle>();
        Connection conn = null;

        String sql = "select contentfirstpublished, contentid, contentheadline, contentlatitude, contentlongitude"
                + " from article_meta where contentfirstpublished > (?-3600000) and contentfirstpublished < (?+3600000)";

        try
        {
            conn = getConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.setLong(1, from);
            stmt.setLong(2, to);
            ResultSet rs = stmt.executeQuery();

            while (rs.next())
            {
                try
                {
                    results.add(new NewsArticle(rs.getLong(1), rs.getString(2), rs.getString(3), rs.getDouble(4), rs.getDouble(5)));
                }
                catch (SQLException e)
                {
                    log.log(Level.WARNING, "Bad data", e);
                }
            }

            Collections.sort(results, TimeBasedEvent.COMPARATOR);

            if (!results.isEmpty())
            {
                long min = results.get(0).getTime();
                long max = results.get(results.size() - 1).getTime();

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss aa Z");
                log.log(Level.INFO, "Read " + results.size() + " events, "
                        + " first = " + dateFormat.format(min) + " (" + min
                        + "), last = " + dateFormat.format(max) + " (" + max + ')');
            }
            else
            {
                log.log(Level.INFO, "No articles found");
            }
        }
        catch (SQLException e)
        {
            log.log(Level.WARNING, "Failed to read news articles", e);
        }
        finally
        {
            try
            {
                if (conn != null)
                {
                    conn.close();
                }
            }
            catch (Exception e)
            {
                log.log(Level.FINE, "Failed to close connection.", e);
            }
        }

        return results;
    }
}
