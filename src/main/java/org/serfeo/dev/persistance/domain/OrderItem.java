package org.serfeo.dev.persistance.domain;

public class OrderItem
{
    public OrderItem() {}

    public OrderItem( int count,
                      long itemId,
                      long orderId ) {
        this.count = count;
        this.itemId = itemId;
        this.orderId = orderId;
    }

    private long id;
    private int count;
    private long itemId;
    private long orderId;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public int getCount() { return count; }
    public void setCount( int count ) { this.count = count; }

    public long getItemId() { return itemId; }
    public void setItemId( long itemId ) { this.itemId = itemId; }

    public long getOrderId() { return orderId; }
    public void setOrderId( long orderId ) { this.orderId = orderId; }
}
