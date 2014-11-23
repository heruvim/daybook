angular.module( "daybook" )
.controller( "AddEventController", function( $scope, $modalInstance ) {
    $scope.newEvent = {};
    $scope.complete = function() {
        $modalInstance.close( $scope.newEvent );
    }

    $scope.close = function() {
        $modalInstance.dismiss();
    }
} )
.controller( "AddOrderController", function( $scope, $modalInstance, $timeout, itemCompletionService ) {
    $scope.creatingListItem = {};
    $scope.itemNames = [];
    $scope.items = [];

    $timeout( function() {
        $scope.isInputFocused = true;
    }, 100 );

    itemCompletionService.getItemNames( {}, function( names ) {
        $scope.itemNames = names;
    }, function() { alert( "error!" ) } );


    $scope.onItemSelected = function( item ) {
        $scope.creatingListItem = item;
    }

    $scope.addNewItem = function() {
        $scope.items.push( $scope.creatingListItem );

        for ( var i = 0; i < $scope.itemNames.length; i++ ) {
            if ( $scope.itemNames[ i ].id === $scope.creatingListItem.id ) {
                $scope.itemNames.splice( i, 1 );
                break;
            }
        }

        $scope.creatingListItem = {};
        $scope.addItemForm.$setPristine();

        $scope.isInputFocused = true;
    }

    $scope.saveAndClose = function() {
        $modalInstance.close( $scope.items );
    }

    $scope.close = function() {
        $modalInstance.dismiss();
    }
} )
.controller( "ConfirmController", function( $scope, $modalInstance, message ) {
    $scope.message = message;

    $scope.confirm = function() {
        $modalInstance.close();
    }

    $scope.cancel = function() {
        $modalInstance.dismiss();
    }
} );