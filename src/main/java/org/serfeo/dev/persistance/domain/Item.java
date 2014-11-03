package org.serfeo.dev.persistance.domain;

public class Item
{
    public Item() {}

    public Item(String name,
                long countNameId,
                float price)
    {
        this.name = name;
        this.countNameId = countNameId;
        this.price = price;
    }

    private long id;
    private String name;
    private long countNameId;
    private float price;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getName() { return name; }
    public void setName( String name ) { this.name = name; }

    public float getPrice() { return price; }
    public void setPrice( float price ) { this.price = price; }

    public long getCountNameId() { return countNameId; }
    public void setCountNameId( long countNameId ) { this.countNameId = countNameId; }

}
