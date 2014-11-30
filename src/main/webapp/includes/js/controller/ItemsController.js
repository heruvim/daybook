angular.module( "daybook")
.controller( "ItemsController", function ( $scope, $modal, itemCompletionService ) {
    // private function
    var showConfirm = function( message, onSuccess, onCancel ) {
        var modalInstance = $modal.open({
            templateUrl: 'includes/js/views/modal/confirm.html',
            controller: 'ConfirmController',
            size: "sm",
            resolve: { message: function() { return message; } }
        });

        modalInstance.result.then( onSuccess, onCancel );
    }
    // end private function

    $scope.listItems = [];
    $scope.item = {};
    $scope.order = "name";
    $scope.reverse = false;
    $scope.addLabel = "Add";
    $scope.alertMessage = "";

    itemCompletionService.getItemNames( {}, function( items ) {
        $scope.listItems = items;
    },
    function(){ alert( "error" ) } );

    $scope.addNewItem = function() {
        if ( $scope.addLabel === "Add" ) {
            itemCompletionService.save( $scope.item, function( item ) {
                for ( var i = 0; i < $scope.listItems.length; i++ ) {
                    if ( $scope.listItems[ i ].name === item.name ) {
                        $scope.listItems.splice( i, 1 );
                    }
                }

                $scope.listItems.push( item );
                $scope.item = {};
            }, function() { alert( "error" ) } );
        } else {
            itemCompletionService.updateItem( $scope.item, function( item ) {
                $scope.item = {};
                $scope.addLabel = "Add";
            }, function() {
                alert( "error" );
                $scope.addLabel = "Add";
            } );
        }

        $scope.addItemForm.$setPristine()
    }

    $scope.editItem = function( item ) {
        $scope.item = item;
        $scope.addLabel = "Edit";
    }

    $scope.deleteItem = function( item ) {
        showConfirm( "Do you really want to delete this item", function() {
            itemCompletionService.deleteItem( { id: item.id }, function() {
            for ( var i = 0; i < $scope.listItems.length; i++ ) {
                if ( $scope.listItems[ i ].id === item.id ) {
                    $scope.listItems.splice( i, 1 );
                    return;
                }
            } }, function() { alert( "error" ); } );
        } );
    }

    $scope.changeOrder = function( property ) {
        $scope.order = property;
        $scope.reverse = !$scope.reverse;
    }

    $scope.hasRequiredError = function( controlName ) {
        return $scope.addItemForm[ controlName ].$dirty && $scope.addItemForm[ controlName ].$error.required
    }
} );