angular.module( 'daybook' ).controller( 'CalendarController',
function ( $scope, $http, $modal, headerService, eventService ) {
    var selectEventMessage = { name: "Please, select event to see description" };
    var allEvents = {};

    function init() {
        $scope.currentDate = new Date();
        $scope.currentEvents = [ selectEventMessage ];
        $scope.events = [];

        headerService.getHeaders( {},
        function( data ) {
            $scope.dayNames = data.dayNames;
            $scope.monthNames = data.monthNames;
        } );

        $scope.$watch( "currentDate", function() {
            eventService.getEventData( { year:  $scope.currentDate.getFullYear(),
                                         month: $scope.currentDate.getMonth() },
            function( data ) {
                $scope.events = [];
                for ( var recordIndex = 0; recordIndex < data.length; recordIndex++ ) {
                    var currentRecord = data[ recordIndex ];
                    var currentRecordDate = new Date( currentRecord.date );
                    var currentRecordCode = getDateCode( currentRecordDate );
                    currentRecord.dateCode = currentRecordCode;

                    $scope.events.push( currentRecord );
                }

                $scope.weeksArray = getWeeksArray();
            } );
        } );
    }

    init();

    $scope.changeMonth = function( delta ) {
        $scope.currentDate = new Date( $scope.currentDate.getFullYear(),
                                       $scope.currentDate.getMonth() + delta, 1 );
    }

    $scope.chooseEvent = function( currentDate ) {
        if ( currentDate.hasEvent )
            showEvents( currentDate.date );
        else
            $scope.currentEvents = [ selectEventMessage ];

        if ( currentDate.date )
            $scope.currentDate.setDate( currentDate.date );
    }

    function showEvents( date ) {
        var currentDate = new Date( $scope.currentDate );
        currentDate.setDate( date );
        var currentDateCode = getDateCode( currentDate );

        var currentEvents = [];
        for ( var i = 0; i < $scope.events.length; i++ ) {
            var currentEvent = $scope.events[ i ];
            var currentEventDateCode = getDateCode( new Date( currentEvent.date ) );
            if ( currentEventDateCode === currentDateCode )
                currentEvents.push( currentEvent );
        }

        if ( currentEvents.length === 0 )
            $scope.currentEvents = [ selectEventMessage ];
        else
            $scope.currentEvents = currentEvents;
    }

    $scope.isSelectedDate = function( currentDate ) {
        if ( $scope.currentDate && $scope.currentDate.getDate() === currentDate )
            return true;
        else
            return false;
    }

    $scope.addEvent = function( outEvent ) {
        var newEventDate = new Date( $scope.currentDate );
        if ( !outEvent.time )
            outEvent.time = new Date();

        newEventDate.setHours( outEvent.time.getHours() );
        newEventDate.setMinutes( outEvent.time.getMinutes() );
        newEventDate.setSeconds( 0 );

        var newEvent = {};
        newEvent.name = outEvent.name;
        newEvent.comment = outEvent.comment;
        newEvent.date = newEventDate.getTime();

        eventService.save( newEvent,
        function( data ) {
            var eventId = data.params[ 0 ].value;
            newEvent.id = eventId;
            newEvent.date = new Date( newEvent.date );

            var newEventCode = getDateCode( newEvent.date );
            newEvent.dateCode = newEventCode;

            $scope.events.push( newEvent );
            $scope.weeksArray = getWeeksArray();
            showEvents( newEvent.date.getDate() );
        },
        function( data ) {
            alert( "Error" );
        } );
    }

    $scope.deleteEvent = function( id ) {
        eventService.delete( { id: id },
        function( data ) {
            var eventId = data.params[ 0 ].value;
            for ( var i = 0; i < $scope.events.length; i++ ) {
                if ( $scope.events[ i ].id === id ) {
                    $scope.events.splice( i, 1 );
                    break;
                }
            }

            for ( var i = 0; i < $scope.events.length; i++ ) {
                if ( $scope.currentEvents[ i ].id === id ) {
                    $scope.currentEvents.splice( i, 1 );
                    break;
                }
            }
        } );
    }

    function getWeeksArray() {
        var currentDate = $scope.currentDate;
        var year = currentDate.getFullYear();
        var month = currentDate.getMonth();

        var weeksArray = [];
        var currentWeek = [];

        var lastDate = new Date( currentDate.getFullYear(), currentDate.getMonth() + 1, 0 ).getDate();
        for ( var date = 1; date <= lastDate; date++ ) {
            currentDate = new Date( year, month, date );
            var currentDateCode = getDateCode( currentDate );

            var dateObject = { date: date };
            var currentDay = currentDate.getDay()
            if ( currentDay === 0 || currentDay === 6 )
                dateObject.isHoliday = true;

            for ( var i = 0; i < $scope.events.length; i++ ) {
                var currentEvent = $scope.events[ i ];
                if ( currentEvent.dateCode == currentDateCode ) {
                    dateObject.hasEvent = true;
                    break;
                }
            }

            currentWeek.push( dateObject );

            if ( currentDate.getDay() === 0 || date === lastDate ) {
                if ( date !== lastDate ) {
                    while( currentWeek.length !== 7 )
                        currentWeek.unshift( {} );
                }
                weeksArray.push( currentWeek );
                currentWeek = [];
            }
        }

        return weeksArray;
    }

    $scope.getTimeStringFromDate = function( date ) {
        var date = new Date( date );
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        var timeString = "hh:mm:ss".replace( "hh", hours )
                                   .replace( "mm", minutes )
                                   .replace( "ss", seconds );
        return timeString;
    }

    $scope.open = function (size) {
        var modalInstance = $modal.open({
            templateUrl: 'partials/modal/addCalendarEvent.html',
            controller: 'AddEventController',
            size: size,
        });

        modalInstance.result.then( function( result ) {
            $scope.addEvent( result );
        }, function() {} )
    }

    function getDateCode( date ) {
        var month = date.getMonth();
        var date = date.getDate();
        var code = "{1}{2}".replace( "{1}", month < 10 ? "0" + month : month )
                           .replace( "{2}", date < 10 ? "0" + date : date );
        return code;
    }
} );