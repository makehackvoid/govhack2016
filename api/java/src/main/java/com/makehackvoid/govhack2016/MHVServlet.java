package com.makehackvoid.govhack2016;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.http.HttpStatus;

import com.makehackvoid.govhack2016.api.EventApi;
import com.makehackvoid.govhack2016.api.ParkingLotApi;

/**
 * MHV Main Servlet.
 * @author Yiannis Paschalidis
 */
public class MHVServlet extends HttpServlet
{
    /** {@inhheritDoc} */
    @Override
    protected void doGet(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        String path = req.getPathInfo();

        if ("/events".equals(path))
        {
            EventApi.handle(req, resp);
        }
        else if ("/lots".equals(path))
        {
            ParkingLotApi.handle(req, resp);
        }
    }

    /** {@inhheritDoc} */
    @Override
    protected void doDelete(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doHead(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doOptions(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doPost(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doPut(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }

    /** {@inhheritDoc} */
    @Override
    protected void doTrace(final HttpServletRequest req, final HttpServletResponse resp) throws ServletException, IOException
    {
        resp.sendError(HttpStatus.METHOD_NOT_ALLOWED_405);
    }
}
