package org.serfeo.dev.persistance.domain;

public class Order
{
    public Order() {}

    public Order( long date,
                  float totalPrice,
                  int totalCount ) {
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
    }

    public Order( Long id,
                  long date,
                  float totalPrice,
                  int totalCount ) {
        this.id = id;
        this.date = date;
        this.totalPrice = totalPrice;
        this.totalCount = totalCount;
    }

    private long id;
    private long date;
    private float totalPrice;
    private int  totalCount;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public long getDate() { return date; }
    public void setDate( long date ) { this.date = date; }

    public float getTotalPrice() { return totalPrice; }
    public void setTotalPrice( float totalPrice ) { this.totalPrice = totalPrice; }

    public int getTotalCount() { return totalCount; }
    public void setTotalCount( int totalCount ) { this.totalCount = totalCount; }
}
