var dayBookModule = angular.module( "daybook",
[ "ngRoute",
  "ngResource",
  "angucomplete",
  "ui.bootstrap" ] );

dayBookModule.config( [ "$routeProvider", function( $routeProvider )
{
    $routeProvider.when( "/calendar",
    {
        templateUrl: "partials/calendar.html",
        controller: "CalendarController"
    } ).when( "/buylist",
    {
        templateUrl: "partials/buylist.html",
        controller: "BuyListController"
    } ).when( "/",
    {
        templateUrl: "partials/index.html",
        controller: "MainPageController"
    } ).when( "/items",
   {
       templateUrl: "partials/items.html",
       controller: "ItemsController"
   } );
} ] );

