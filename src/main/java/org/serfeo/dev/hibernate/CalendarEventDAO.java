package org.serfeo.dev.hibernate;

import com.google.inject.Inject;
import org.hibernate.Query;
import org.hibernate.Session;
import org.serfeo.dev.beans.CalendarEvent;

import java.util.List;

public class CalendarEventDAO
{
    @Inject
    public CalendarEventDAO(){}

    public List< CalendarEvent > getEventsByMonthAndYear( long startTime, long endTime )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String strQuery = "from CalendarEvent where date>=:dateFrom and date<=:dateTo";
        Query query = session.createQuery(strQuery).setLong("dateFrom", startTime).setLong("dateTo", endTime);
        List< CalendarEvent > listCalendarEvents = ( List< CalendarEvent > )query.list();
        session.getTransaction().commit();

        return listCalendarEvents;
    }

    public Long addCalendarEvent( CalendarEvent calendarEvent )
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        session.save( calendarEvent );
        session.getTransaction().commit();

        return calendarEvent.getId();
    }
}
