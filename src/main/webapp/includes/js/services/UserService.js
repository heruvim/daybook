angular.module( "daybook" )
.factory( "registrationService", function( $resource ) {
    return $resource( "rest/user/register", {}, {
        register: { method: "POST" }
    } )
} )
.factory( "authService", function( $resource ) {
    return $resource( "rest/user/auth", {}, {
        auth: { method: "POST" }
    } )
} );