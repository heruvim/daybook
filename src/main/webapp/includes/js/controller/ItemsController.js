angular.module( "daybook")
.controller( "ItemsController", function ( $scope,
                                           $modal,
                                           itemCompletionService ) {
    $scope.listItems = [];
    $scope.item = {};

    itemCompletionService.getItemNames( {}, function( items ) {
        $scope.listItems = items;
    },
    function(){ alert( "error" ) } );

    $scope.addNewItem = function() {
        itemCompletionService.save( $scope.item, function( item ) {
            for ( var i = 0; i < $scope.listItems.length; i++ ) {
                if ( $scope.listItems[ i ].name === item.name ) {
                    $scope.listItems.splice( i, 1 );
                }
            }

            $scope.listItems.unshift( item );
            $scope.item = {};
        }, function() { alert( "error" ) } );
    }

    $scope.deleteItem = function( item ) {
        itemCompletionService.deleteItem( { id: item.id }, function() {
        for ( var i = 0; i < $scope.listItems.length; i++ ) {
            if ( $scope.listItems[ i ].id === item.id ) {
                $scope.listItems.splice( i, 1 );
                return;
            }
        } }, function() { alert( "error" ); } );
    }
} );