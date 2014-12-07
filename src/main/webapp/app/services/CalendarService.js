angular.module( 'daybook' ).
factory( "headerService", function( $resource ) {
    return $resource( "rest/private/calendar/header", {}, {
        getHeaders: { method: "GET" }
    } ) ;
} ).
factory( "eventService", function( $resource ) {
    return $resource( "rest/private/calendar/events/:year/:month/:id", {}, {
        getEventData: { method: "GET", isArray: true },
        delEvent:     { method: "DELETE" },
        updateEvent:  { method: "POST" }
    } );
} );
