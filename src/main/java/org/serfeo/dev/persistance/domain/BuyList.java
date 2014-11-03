package org.serfeo.dev.persistance.domain;

import java.util.List;

public class BuyList
{
    private int id;
    private long date;
    private List< BuyListItem > items;

    public BuyList() {}

    public BuyList( int id,
                    long date,
                    List< BuyListItem > items ) {
        this.id = id;
        this.date = date;
        this.items = items;
    }

    public int getId() { return id; }
    public void setId( int id ) { this.id = id; }

    public long getDate() { return date; }
    public void setDate( long date ) { this.date = date; }

    public List< BuyListItem > getItems() { return items; }
    public void setItems( List< BuyListItem > items ) { this.items = items; }

    public static class BuyListItem
    {
        private String  name;
        private String  countName;
        private Integer count;
        private Float  price;

        public BuyListItem() {}

        public BuyListItem( String name,
                            String countName,
                            Integer count,
                            Float price ) {
            this.name = name;
            this.countName = countName;
            this.count = count;
            this.price = price;
        }

        public String getName() { return name; }
        public void setName( String name ) { this.name = name; }

        public String getCountName() { return countName; }
        public void setCountName( String countName ) { this.countName = countName; }

        public Integer getCount() { return count; }
        public void setCount( Integer count ) { this.count = count; }

        public Float getPrice() { return price; }
        public void setPrice( Float price ) { this.price = price; }
    }
}
