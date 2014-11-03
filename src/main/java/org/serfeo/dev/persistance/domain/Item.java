package org.serfeo.dev.persistance.domain;

public class Item
{
    public Item() {}

    public Item( String name,
                 String countName,
                 float price) {
        this.name = name;
        this.countName = countName;
        this.price = price;
    }

    private long id;
    private String name;
    private String countName;
    private float price;

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getName() { return name; }
    public void setName( String name ) { this.name = name; }

    public float getPrice() { return price; }
    public void setPrice( float price ) { this.price = price; }

    public String getCountName() { return countName; }
    public void setCountName( String countName ) { this.countName = countName; }

}
