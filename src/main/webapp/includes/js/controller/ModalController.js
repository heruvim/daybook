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
.controller( "AddOrderController", function( $scope, $modalInstance, itemCompletionService ) {
    $scope.creatingListItem = {};
    $scope.itemNames = [];
    $scope.autocomplete = {};
    $scope.items = [];

    itemCompletionService.getItemNames( {}, function( names ) {
        $scope.itemNames = names;
    }, function() { alert( "error!" ) } );

    $scope.$watch( "autocomplete", function() {
        if ( $scope.autocomplete && $scope.autocomplete.originalObject ) {
            var originalObject = $scope.autocomplete.originalObject;
            originalObject.id = undefined;
            $scope.creatingListItem = originalObject;
        }
    } );

    $scope.addNewItem = function( needNext ) {
        $scope.creatingListItem.name = angular.element( document.querySelector( '#name_value' ) ).val();
        $scope.items.push( $scope.creatingListItem );

        if ( needNext ) {
            $scope.creatingListItem = {};
            angular.element( document.querySelector( '#name_value' ) ).val( "" );
            $scope.addItemForm.$setPristine()
        } else {
            $modalInstance.close( $scope.items );
        }
    }

    $scope.close = function() {
        $modalInstance.dismiss();
    }
} );