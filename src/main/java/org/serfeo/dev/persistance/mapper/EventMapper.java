package org.serfeo.dev.persistance.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.serfeo.dev.persistance.domain.CalendarEvent;
import java.util.List;

public interface EventMapper {
    @Select( "select * from events where id=#{id}" )
    public CalendarEvent findEventById( @Param("id") int id );

    @Select( "select * from events where date>=#{dateFrom} and date<=#{dateTo}" )
    public List< CalendarEvent > findEventsBetweenDates( @Param("dateFrom") long dateFrom,
                                                         @Param("dateTo") long dateTo );

    public int insert( CalendarEvent event );

    @Delete( "delete from events where id=#{id}" )
    void deleteById( @Param("id") int id );
}
