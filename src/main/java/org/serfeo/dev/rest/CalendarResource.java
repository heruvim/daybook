package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.domain.CalendarEvent;
import org.serfeo.dev.persistance.mapper.EventDAO;
import org.serfeo.dev.rest.beans.CalendarHeader;
import org.serfeo.dev.rest.response.CommonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.*;

@Path( "/calendar" )
public class CalendarResource {
    private final CalendarHeader calendarHeader;
    private final EventDAO calendarEventDAO;

    @Inject
    private CalendarResource( CalendarHeader calendarHeader, EventDAO calendarEventDAO ) {
        this.calendarHeader = calendarHeader;
        this.calendarEventDAO = calendarEventDAO;
    }

    @POST
    @Path( "/events" )
    @Produces( MediaType.APPLICATION_JSON )
    @Consumes( MediaType.APPLICATION_JSON )
    public CommonResponse addEvent( CalendarEvent calendarEvent ) {
        calendarEventDAO.insertEvent( calendarEvent );
        CommonResponse.Param[] params = new CommonResponse.Param[]{ new CommonResponse.Param( "id", calendarEvent.getId() ) };
        return CommonResponse.ok( params );
    }

    @DELETE
    @Path( "/events/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public CommonResponse deleteEvent( @PathParam( "id" ) int id ) {
        calendarEventDAO.deleteById( id );
        CommonResponse.Param[] params = new CommonResponse.Param[]{ new CommonResponse.Param( "id", id ) };
        return CommonResponse.ok( params );
    }

    @GET
    @Path( "/events/{year}/{month}" )
    @Produces( MediaType.APPLICATION_JSON )
    public List< CalendarEvent > getCalendarEvents( @PathParam( "year" )  int year,
                                                    @PathParam( "month" ) int month ) {
        Calendar workCalendar = new GregorianCalendar( year, month, 1, 0, 0, 0 );
        Long beginTime = workCalendar.getTimeInMillis();

        workCalendar.roll( Calendar.MONTH, 1 );
        workCalendar.roll( Calendar.MINUTE, -1 );

        Long endTime = workCalendar.getTimeInMillis();
        return calendarEventDAO.getEventsBetween( beginTime, endTime );
    }

    @GET
    @Path( "/header" )
    @Produces( MediaType.APPLICATION_JSON )
    public CalendarHeader getCalendarHeader()
    {
      return calendarHeader;
    }
}
