angular.module( "daybook" )
.factory( "registrationService", function( $resource ) {
    return $resource( "rest/public/user/register", {}, {
        register: { method: "POST" }
    } )
} )
.factory( "authService", function( $resource ) {
    return $resource( "rest/public/user/auth", {}, {
        auth: { method: "POST" }
    } )
} )
.factory( "logoutService", function( $resource ) {
    return $resource( "rest/public/user/logout", {}, {
        logout: { method: "POST" }
    } )
} );