angular.module( "daybook" )
.directive( "dbDefaultFocus", function() {
    return {
        restrict: "A",
        link: function( $scope, element, attrs ) {
            $scope.$watch( "isInputFocused", function() {
                if ( $scope.isInputFocused ) {
                    element[ 0 ].focus();
                    $scope.isInputFocused = false;
                }
            } );
        }
    }
} )
.directive( "dbEnterSubmit", function() {
    return {
        restrict: "A",
        link: function( $scope, element, attrs ) {
            element.bind( "keydown", function( event ) {
                if ( !$scope[ attrs[ "name" ] ].$invalid && event.keyCode === 13 /*Enter*/ ) {
                    $scope[ attrs[ "dbEnterSubmit" ].split( "(" )[ 0 ] ]();
                    $scope.$apply();
                }
            } );
        }
    }
} );