var dayBookModule = angular.module( 'daybook',
[ 'ngResource', 'ui.bootstrap', 'ui.router' ] );

dayBookModule.config( function( $stateProvider, $urlRouterProvider ) {
    $urlRouterProvider.otherwise('/');

    $stateProvider
        .state( 'main', {
            url: '/',
            templateUrl: 'app/views/index.html',
            controller: 'MainPageController'
        } )
        .state( 'buylist', {
            url: '/buylist',
            templateUrl: 'app/views/buylist.html',
            controller: 'BuyListController'
        } )
        .state( 'items', {
            url: '/items',
            templateUrl: 'app/views/items.html',
            controller: 'ItemsController'
        } )
        .state( 'calendar', {
            url: '/calendar',
            templateUrl: 'app/views/calendar.html',
            controller: 'CalendarController'
        });
} )

.run( function( $rootScope, $state, $stateParams, AuthSession ) {
    $rootScope.$state = $state;
    $rootScope.$stateParams = $stateParams;

    $rootScope.$on( '$stateChangeStart',
        function( event, toState, toParams, fromState, fromParams ){
            if ( !AuthSession.isLoggedIn() && toState.name !== "main" ) {
                event.preventDefault();
                $state.transitionTo( "main" );
            }
        } );
} );

