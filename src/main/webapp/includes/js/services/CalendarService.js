angular.module( "CalendarEventService", [ "ngResource" ] ).
factory( "headerService", function( $resource )
{
    return $resource( "rest/calendar/header", {},
    {
        getHeaders: { method: "GET" }
    } ) ;
} ).
factory( "eventService", function( $resource )
{
    return $resource( "rest/calendar/events/:year/:month", {},
    {
        getEventData: { method: "GET", isArray: true },
        delEvent:     { method: "DELETE" },
        updateEvent:  { method: "POST" }
    } );
} );