angular.module( "daybook")
.controller( "ItemStatController", function ( $scope, $filter, itemStatService ) {
    // private function
    var init = function() {
        $scope.items = [];
        $scope.selectedItem = {};

        // chart init
        $scope.labels = [];
        $scope.series = [];
        $scope.data = [];

        // chart global init
        Chart.defaults.global.scaleFontSize = 18;

        itemStatService.getStatistic( function( data ) {
            console.log( data );
            $scope.items = data;
        }, function( error ) {
            console.log( error );
        } )
    }

    init();

    // public functions
    $scope.onItemSelected = function( item ) {
        console.log( item );
        $scope.selectedItem = item;

        $scope.labels = [];
        $scope.series = [];
        $scope.data = [];

        if ( item.prices.length > 1 ) {
            for ( var i = 0; i < item.prices.length; i++ ) {
                $scope.labels.push( $filter( 'date' )( new Date( item.prices[ i ].time ), "dd.MM.yyyy" ) )

                if ( !$scope.data[ 0 ] )
                    $scope.data[ 0 ] = [];

                $scope.data[ 0 ].push( item.prices[ i ].price )
            }

            $scope.series = [ item.name ];
        }
    }
} );