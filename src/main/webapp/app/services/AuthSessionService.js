angular.module( "daybook" )
.factory( "AuthSession", function( $cookies ) {
    var user;
    if ( $cookies.nickname )
        user = { nickname: $cookies.nickname };

    return {
        login: function( value ) {
            user = value;
            $cookies.nickname = value.nickname;
        },

        logout: function() {
            user = undefined;
            delete $cookies.nickname;
        },

        isLoggedIn: function() { return user !== undefined; },

        getNickname: function() { return user ? user.nickname : undefined; }
    }
} );