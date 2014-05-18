package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.beans.CalendarEvent;
import org.serfeo.dev.beans.CalendarHeader;
import org.serfeo.dev.hibernate.CalendarEventDAO;
import org.serfeo.dev.rest.response.CommonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path( "/calendar" )
public class CalendarResource
{
    private final CalendarHeader calendarHeader;
    private final CalendarEventDAO calendarEventDAO;

    @Inject
    public CalendarResource( CalendarHeader calendarHeader, CalendarEventDAO calendarEventDAO )
    {
        this.calendarHeader = calendarHeader;
        this.calendarEventDAO = calendarEventDAO;
    }

    @POST
    @Path( "/events" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public CommonResponse addEvent( CalendarEvent calendarEvent )
    {
        Long newEventId = calendarEventDAO.addCalendarEvent( calendarEvent );
        CommonResponse.Param[] params = new CommonResponse.Param[]{ new CommonResponse.Param( "id", newEventId ) };
        return CommonResponse.ok( params );
    }

    @GET
    @Path( "/events/{year}/{month}" )
    @Produces( MediaType.APPLICATION_JSON )
    public List<CalendarEvent> getCalendarEvents( @PathParam( "year" )  int year,
                                                  @PathParam( "month" ) int month )
    {
        Calendar workCalendar = new GregorianCalendar( year, month, 1, 0, 0, 0 );
        Long beginTime = workCalendar.getTimeInMillis();

        workCalendar.roll( Calendar.MONTH, 1 );
        workCalendar.roll( Calendar.MINUTE, -1 );

        Long endTime = workCalendar.getTimeInMillis();
        return calendarEventDAO.getEventsByMonthAndYear( beginTime, endTime );
    }

    @GET
    @Path( "/header" )
    @Produces( MediaType.APPLICATION_JSON )
    public CalendarHeader getCalendarHeader()
    {
      return calendarHeader;
    }
}
