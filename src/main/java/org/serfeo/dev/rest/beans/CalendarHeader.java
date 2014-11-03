package org.serfeo.dev.rest.beans;

import com.google.inject.Inject;

import java.text.DateFormatSymbols;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

public class CalendarHeader
{
    private List< String > dayNames = new LinkedList< String >();
    private List< String > monthNames = new LinkedList< String >();

    @Inject
    public CalendarHeader()
    {
        DateFormatSymbols dfs = new DateFormatSymbols( new Locale("ru") );
        String weekDays[] = dfs.getShortWeekdays();

        for ( int dayIndex = 2; dayIndex < weekDays.length; dayIndex++ )
        {
            String currentDay = weekDays[ dayIndex ];
            dayNames.add(currentDay);
        }

        dayNames.add(weekDays[1]);

        String[] months = dfs.getShortMonths();
        for ( String month: months )
        {
            if ( month.length() > 0 )
              monthNames.add( month );
        }
    }

    public List< String > getDayNames() { return dayNames; }
    public void setDayNames(List<String> dayNames) { this.dayNames = dayNames; }

    public List< String > getMonthNames() { return monthNames; }
    public void setMonthNames( List<String> monthNames ) { this.monthNames = monthNames; }
}
