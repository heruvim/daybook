var dayBookModule = angular.module( "daybook", [ "ngRoute", "CalendarEventService" ] );
dayBookModule.config( [ "$routeProvider", function( $routeProvider )
{
    $routeProvider.when( "/calendar",
    {
        templateUrl: "partials/calendar.html",
        controller: CalendarController
    } ).
    otherwise( { redirectTo: "/" } );
} ] );

