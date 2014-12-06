angular.module( "daybook" )
.controller( "BuyListController",
    function( $scope,
              $modal,
              buyListService,
              browseBuyListService,
              itemCompletionService ) {

    // private functions
    var showConfirm = function( message, onSuccess, onCancel ) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/modal/confirm.html',
            controller: 'ConfirmController',
            size: "sm",
            resolve: { message: function() { return message; } }
        });

        modalInstance.result.then( onSuccess, onCancel );
    }
    // end private functions

    $scope.currentListItems = [];
    $scope.creatingListItem = {};
    $scope.prevBuyLists = [];
    $scope.order = "";
    $scope.reverse = false;

    browseBuyListService.getAllListInformation( {}, function( data ) {
        if ( data )
            $scope.prevBuyLists = data;
        else
            $scope.prevBuyLists = [];
    } );

    $scope.getTotalPrice = function() {
        var totalPrice = 0.0;
        for ( var itemIndex = 0; itemIndex < $scope.currentListItems.length; itemIndex++ )
        {
            var currentItem = $scope.currentListItems[ itemIndex ];
            var addedPrice = currentItem.price * ( currentItem.count ? currentItem.count : 1 );
            totalPrice += addedPrice;
        }

        return totalPrice.toFixed( 2 );
    }

    $scope.addNewItem = function( creatingListItem ) {
        for ( var i = 0; i < $scope.currentListItems.length; i++ ) {
            if ( $scope.currentListItems[ i ].name === creatingListItem.name ) {
                alert( "This item already in buy list" );
                return;
            }
        }

        $scope.currentListItems.push( creatingListItem );
        $scope.creatingListItem = {};
    }

    $scope.saveBuyList = function() {
        var newBuyList = { date: new Date().getTime(),
                           items: $scope.currentListItems }
        buyListService.save( newBuyList,
        function( data ) {
            if ( data ) {
                $scope.prevBuyLists.push( data );
                $scope.currentListItems = [];
                $scope.creatingListItem = {};
            }
        },
        function() {
            alert( "Something going wrong on server side" );
        } );
    }

    $scope.deleteListItem = function( item ) {
        showConfirm( "Do you really want to delete this item?", function() {
            for ( var i = 0; i < $scope.currentListItems.length; i++ ) {
                if ( $scope.currentListItems[ i ].name === item.name ) {
                    $scope.currentListItems.splice( i, 1 );
                    return;
                }
            }
        } );
    }

    $scope.getBuyListById = function( id ) {
        buyListService.getBuyList( { id: id }, function( data ) {
            $scope.currentListItems = data.items;
            $scope.creatingListItem = {};
        }, function() { alert( "Something going wrong on server side" ) } );
    }

    $scope.deleteBuyList = function( buyList ) {
        showConfirm( "Do you really want to delete this buy-list?", function() {
            buyListService.delBuyList( { id: buyList.id },
            function() {
                for ( var i = 0; i < $scope.prevBuyLists.length; i++ ) {
                    if ( $scope.prevBuyLists[ i ].id === buyList.id ) {
                        $scope.prevBuyLists.splice( i, 1 );
                        $scope.currentListItems = [];
                        break;
                    }
                } },
            function() { alert( "error" ) } );
        } );
    }

    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'app/views/modal/addOrderItem.html',
            controller: 'AddOrderController',
            windowClass: 'add_new_item_window',
            size: size,
        });

        modalInstance.result.then( function( data ) {
            for ( var i = 0; i < data.length; i++ )
                $scope.addNewItem( data[ i ] );
        }, function() {} );
    }

    $scope.changeOrder = function( property ) {
        $scope.order = property;
        $scope.reverse = !$scope.reverse;
    }
} );