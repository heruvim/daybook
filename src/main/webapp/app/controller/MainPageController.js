angular.module( "daybook" )
.controller( "MainPageController", function( $scope, $modal, authService, AuthSession ) {
    $scope.credentials = {};

    $scope.logIn = function() {
        var credentials = {};
        credentials.login = $scope.credentials.login;

        var password = CryptoJS.SHA1( $scope.credentials.password );
        for ( var bytes = [], b = 0; b < password.words.length * 32; b += 8 )
            bytes.push( ( password.words[ b >>> 5 ] >>> ( 24 - b % 32 ) ) & 0xFF );

        credentials.verifier = bytes;

        authService.auth( credentials, function( data ) {
            if ( data.status ) {
                if ( data.status === "ok" )
                    AuthSession.login( { nickname: data.params[ 0 ].value } );
                else if ( data.status === "error" )
                    alert( data.params[ 0 ].value );
            }
        }, function() { alert( "error" ); } );
    }

    $scope.signIn = function() {
        var modalInstance = $modal.open({
             templateUrl: 'app/views/modal/registration.html',
             controller: 'RegistrationController',
             size: "m"
        } );
    }
} );