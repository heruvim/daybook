var dayBookModule = angular.module( "daybook",
[ "ngRoute",
  "ngResource",
  "ui.bootstrap" ] );

dayBookModule.config( [ "$routeProvider", function( $routeProvider )
{
    $routeProvider.when( "/calendar",
    {
        templateUrl: "includes/js/views/calendar.html",
        controller: "CalendarController"
    } ).when( "/buylist",
    {
        templateUrl: "includes/js/views//buylist.html",
        controller: "BuyListController"
    } ).when( "/",
    {
        templateUrl: "includes/js/views//index.html",
        controller: "MainPageController"
    } ).when( "/items",
   {
       templateUrl: "includes/js/views//items.html",
       controller: "ItemsController"
   } );
} ] );

