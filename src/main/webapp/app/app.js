var dayBookModule = angular.module( 'daybook',
[ 'daybook.localization', 'ngResource', 'ngCookies', 'ui.bootstrap', 'ui.router', 'chart.js' ] );

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
        })
        .state( 'itemsStat', {
            url: '/items/stat',
            templateUrl: 'app/views/itemStat.html',
            controller: 'ItemStatController'
        } );
} )

.config( function( $httpProvider ) {
    $httpProvider.interceptors.push( function( $q, $injector ) {
        return {
            'responseError': function( rejection ) {
                if ( rejection.status === 403 ) {
                    $injector.get( "AuthSession" ).logout();
                    $injector.get( "$state" ).transitionTo( "main" );
                    return new Promise( function() {} );
                }

                return $q.reject( rejection );
            }
        };
    } );
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

