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
    $scope.items = [];
    angular.element( document.querySelector( '#name' ) ).focus();

    itemCompletionService.getItemNames( {}, function( names ) {
        $scope.itemNames = names;
    }, function() { alert( "error!" ) } );


    $scope.onItemSelected = function( item ) {
        $scope.creatingListItem = item;
    }

    $scope.addNewItem = function( needNext ) {
        $scope.items.push( $scope.creatingListItem );
        $scope.creatingListItem = {};
        $scope.addItemForm.$setPristine();
    }

    $scope.saveAndClose = function() {
        $modalInstance.close( $scope.items );
    }

    $scope.close = function() {
        $modalInstance.dismiss();
    }
} );