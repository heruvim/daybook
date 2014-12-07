angular.module( "daybook" )
.factory( "buyListService", function( $resource ) {
    return $resource( "rest/private/buylist/:id", {}, {
        save:       { method: "POST" },
        getBuyList: { method: "GET" },
        delBuyList: { method: "DELETE" }
    } );
} )
.factory( "browseBuyListService", function( $resource ) {
    return $resource( "rest/private/buylist/orders", {}, {
        getAllListInformation: { method: "GET", isArray: true }
    } );
} ).
factory( "itemCompletionService", function( $resource ) {
    return $resource( "rest/private/buylist/items/:id", {}, {
        updateItem:   { method: "PUT" },
        deleteItem:   { method: "DELETE" },
        getItemNames: { method: "GET", isArray: true }
    } );
} );