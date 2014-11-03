package org.serfeo.dev.persistance.domain;

public class Order
{
    public Order(){}

    private long id;
    private long date;
    private int  totalPrice;
    private int  totalCount;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public long getDate() { return date; }
    public void setDate( long date ) { this.date = date; }

    public int getTotalPrice() { return totalPrice; }
    public void setTotalPrice( int totalPrice ) { this.totalPrice = totalPrice; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount( int totalCount ) { this.totalCount = totalCount; }
}
