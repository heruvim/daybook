function CalendarController( $scope, $http, headerService, eventService )
{
    var selectEventMessage = { name: "Please, select event to see description" };
    var newEvent = {};

    var headerData = headerService.getHeaders( {},
    function( data )
    {
        $scope.dayNames = data.dayNames;
        $scope.monthNames = data.monthNames;
    } );

    $scope.currentDate = new Date();
    $scope.currentEvents = [ selectEventMessage ];

    getEvents();

    $scope.nextMonth = function()
    {
        var month = $scope.currentDate.getMonth();
        var year = $scope.currentDate.getFullYear();

        $scope.currentDate = new Date( year, month + 1, 1 );

        getEvents();
    }

    $scope.prevMonth = function()
    {
        var month = $scope.currentDate.getMonth();
        var year = $scope.currentDate.getFullYear();

        $scope.currentDate = new Date( year, month - 1, 1 );

        getEvents();
    }

    $scope.chooseEvent = function( eventIds, currentDate )
    {
        showEvents( eventIds );
        if ( currentDate )
            $scope.currentDate.setDate( currentDate );
    }

    $scope.hasEvent = function( currentDateObject )
    {
        if ( currentDateObject.eventIds && currentDateObject.eventIds.length > 0 )
            return true;
        else
            return false;
    }

    $scope.isSelectedDate = function( currentDate )
    {
        if ( $scope.currentDate && $scope.currentDate.getDate() === currentDate )
            return true;
        else
            return false;
    }

    $scope.addEvent = function()
    {
        var newEventDate = new Date( $scope.currentDate );
        if ( $scope.newEvent.hour )
            newEventDate.setHours( $scope.newEvent.hour );
        else
            newEventDate.setHours( 0 );

        if ( $scope.newEvent.minute )
            newEventDate.setMinutes( $scope.newEvent.minute );
        else
            newEventDate.setMinutes( 0 );

        newEventDate.setSeconds( 0 );

        var newEvent = {};
        newEvent.name = $scope.newEvent.name;
        newEvent.comment = $scope.newEvent.comment;
        newEvent.date = newEventDate.getTime();

        eventService.save( newEvent,
            function( data )
            {
                var eventId = data.params[ 0 ].id;
                newEvent.id = eventId;
                newEvent.date = new Date( newEvent.date );

                var newEventCode = getDateCode( newEvent.date );
                newEvent.dateCode = newEventCode;

                $scope.events.eventId = newEvent;
                $scope.weeksArray = getWeeksArray( $scope.events );
            },
            function( data )
            {
                alert( "Error" );
            } );
    }

    $scope.getTimeStringFromDate = function( date )
    {
        var date = new Date( date );
        var hours = date.getHours() < 10 ? "0" + date.getHours() : date.getHours();
        var minutes = date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes();
        var seconds = date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();

        var timeString = "hh:mm:ss".replace( "hh", hours ).replace( "mm", minutes ).replace( "ss", seconds );
        return timeString;
    }

    function getEvents()
    {
        var year = $scope.currentDate.getFullYear();
        var month = $scope.currentDate.getMonth();

        eventService.getEventData( { year: year, month: month },
        function( data )
        {
            var eventsObject = {};
            for ( var recordIndex = 0; recordIndex < data.length; recordIndex++ )
            {
                var currentRecord = data[ recordIndex ];
                var currentRecordDate = new Date( currentRecord.date );
                var currentRecordCode = getDateCode( currentRecordDate );
                currentRecord.dateCode = currentRecordCode;

                eventsObject[ currentRecord.id ] = currentRecord;
            }

            $scope.events = eventsObject;
            $scope.weeksArray = getWeeksArray( eventsObject );
        } );
    }

    function showEvents( eventIds )
    {
        var currentEvents = [];
        if ( eventIds )
        {
            for ( var index = 0; index < eventIds.length; index++ )
            {
                var currentEvent = $scope.events[ eventIds[ index ] ];
                if ( currentEvent != undefined )
                    currentEvents.push( currentEvent );
            }
        }

        if ( currentEvents.length === 0 )
            $scope.currentEvents = [ selectEventMessage ];
        else
            $scope.currentEvents = currentEvents
    }

    function getWeeksArray( eventsObject )
    {
        var currentDate = $scope.currentDate;
        var year = currentDate.getFullYear();
        var month = currentDate.getMonth();

        var weeksArray = [];
        var currentWeek = [];

        var lastDate = new Date( currentDate.getFullYear(), currentDate.getMonth() + 1, 0 ).getDate();
        for ( var date = 1; date <= lastDate; date++ )
        {
            currentDate = new Date( year, month, date );
            var currentDateCode = getDateCode( currentDate );

            var dateObject = { date: date };

            for ( var eventId in eventsObject )
            {
                var currentEvent = eventsObject[ eventId ];
                if ( currentEvent.dateCode == currentDateCode )
                {
                    if ( dateObject.eventIds )
                        dateObject.eventIds.push( eventId );
                    else
                        dateObject.eventIds = [ eventId ];
                }
            }

            currentWeek.push( dateObject );

            if ( currentDate.getDay() === 0 || date === lastDate )
            {
                if ( date !== lastDate )
                {
                    while( currentWeek.length !== 7 )
                        currentWeek.unshift( {} );
                }
                weeksArray.push( currentWeek );
                currentWeek = [];
            }
        }

        return weeksArray;
    }

    function getDateCode( date )
    {
        var month = date.getMonth() + 1;
        var date = date.getDate();
        var code = "{1}{2}".replace( "{1}", month < 10 ? "0" + month : month )
                           .replace( "{2}", date < 10 ? "0" + date : date );
        return code;
    }
}