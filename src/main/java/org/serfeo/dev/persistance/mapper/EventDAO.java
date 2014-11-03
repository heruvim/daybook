package org.serfeo.dev.persistance.mapper;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.domain.CalendarEvent;

import java.util.List;

public class EventDAO {
    private final EventMapper mapper;

    @Inject
    public EventDAO( EventMapper eventMapper) {
        this.mapper = eventMapper;
    }

    public List< CalendarEvent > getEventsBetween( long dateFrom, long dateTo ) {
        return mapper.findEventsBetweenDates( dateFrom, dateTo );
    }

    public int insertEvent( CalendarEvent event ) {
        return mapper.insert( event );
    }

    public void deleteById( int id ) {
        mapper.deleteById( id );
    }
}