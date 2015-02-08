angular.module( "daybook" )
.controller( "MainPageController", function( $scope, $modal, $state, authService, logoutService, AuthSession ) {
    $scope.credentials = {};
    $scope.nickname = AuthSession.getNickname();

    $scope.login = function() {
        var credentials = {};

        credentials.login = $scope.credentials.login;
        var hex = ( new jsSHA( $scope.credentials.password, "TEXT" ) ).getHash( "SHA-512", "HEX" );

        var bytes = [];
        for ( var i = 0; i < hex.length; i = i + 2 ) bytes.push( parseInt( hex.substr( i, 2 ), 16 ) );
        credentials.verifier = bytes;

        $scope.credentials = {};

        authService.auth( credentials, function( data ) {
            if ( data.status ) {
                if ( data.status === "ok" ) {
                    AuthSession.login( { nickname: data.params[ 0 ].value } );
                    $scope.nickname = AuthSession.getNickname();
                    $state.transitionTo( "buylist" );
                }
                else if ( data.status === "error" )
                    alert( data.params[ 0 ].value );
            }
        }, function() { alert( "error" ); } );
    };

    $scope.logout = function() {
        logoutService.logout( {}, function() {
            AuthSession.logout();
            $scope.nickname = undefined;
            $state.transitionTo( "main" );
        }, function() { alert( "error" ) } );

    };

    $scope.isLoggedIn = function() {
        return AuthSession.isLoggedIn();
    };

    $scope.signIn = function() {
        var modalInstance = $modal.open({
             templateUrl: 'app/views/modal/registration.html',
             controller:  'RegistrationController',
             windowClass: 'registration-modal',
             size:        'm'
        } );
    };
} );