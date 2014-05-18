package org.serfeo.dev.beans;

public class CalendarEvent
{
    private Long id;
    private String name;
    private String comment;
    private Long date;
    private Long userId;

    public CalendarEvent() {}

    public String getName() { return name; }
    public void setName( String name ) { this.name = name; }

    public String getComment() { return comment; }
    public void setComment( String comment ) { this.comment = comment; }

    public Long getDate() { return date; }
    public void setDate( Long date ) { this.date = date; }

    public Long getId() { return id; }
    public void setId( Long id ) { this.id = id; }

    public Long getUserId() { return userId; }
    public void setUserId( Long userId ) { this.userId = userId; }
}
