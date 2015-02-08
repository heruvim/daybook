angular.module( "daybook" )
.factory( "itemStatService", function( $resource ) {
    return $resource( "rest/private/stat/items", {}, {
        getStatistic: { method: "GET", isArray: true }
    } );
} )
