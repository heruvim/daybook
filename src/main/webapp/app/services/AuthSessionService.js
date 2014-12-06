angular.module( "daybook" )
.factory( "AuthSession", function() {
    var user;
    return {
        login: function( value ) { user = value; },
        logout: function() { user = undefined; },
        isLoggedIn: function() { return user !== undefined; }
    }
} );