package com.makehackvoid.govhack2016;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.http.HttpStatus.Code;

import com.makehackvoid.govhack2016.api.EventApi;
import com.makehackvoid.govhack2016.api.ParkingLotApi;

/**
 * MHV Java API Main Servlet.
 *
 * @author Yiannis Paschalidis
 */
public class MHVServlet extends HttpServlet
{
    /** The logger instance for this class. */
    private static final Logger log = Logger.getLogger(MHVServlet.class.getName());

    /** {@inhheritDoc} */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        String path = req.getPathInfo();

        if ("/events".equals(path))
        {
            log.log(Level.INFO, req.getRemoteAddr() + " requested events");
            EventApi.handle(req, resp);
        }
        else if ("/lots".equals(path))
        {
            log.log(Level.INFO, req.getRemoteAddr() + " requested parking lots");
            ParkingLotApi.handle(req, resp);
        }
        else
        {
            sendError(HttpStatus.BAD_REQUEST_400, resp);
            log.log(Level.WARNING, "Unknown API request: " + path + " from " + req.getRemoteAddr());
        }
    }

    /** {@inhheritDoc} */
    @Override
    protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doHead(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doOptions(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doTrace(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        sendError(HttpStatus.METHOD_NOT_ALLOWED_405, resp);
    }

    /**
     * Sends a basic error response.
     * @param code the HTTP response error code.
     * @param resp the HTTP response.
     * @throws IOException on error
     */
    public static void sendError(final int code, final HttpServletResponse resp) throws IOException
    {
        resp.setStatus(code);
        resp.setContentType("Text/plain");

        Code codeObj = HttpStatus.getCode(code);

        if (codeObj == null)
        {
            resp.getWriter().write("Error " + code);
        }
        else
        {
            resp.getWriter().write("Error " + code + " - " + codeObj.getMessage());
        }
    }
}
