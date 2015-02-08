package org.serfeo.dev.persistance.domain;

import org.codehaus.jackson.annotate.JsonSetter;

import java.util.Date;
import java.util.List;

public class ItemPriceStat {
    private long id;
    private String name;
    private List< ItemPrice > prices;

    public ItemPriceStat() {};

    public long getId() { return id; }
    public void setId( long id ) { this.id = id; }

    public String getName() { return name; }
    public void setName( String name ) { this.name = name; }

    public List<ItemPrice> getPrices() { return prices; }
    public void setPrices( List<ItemPrice> prices ) { this.prices = prices; }

    public static class ItemPrice {
        private double price;
        private Date time;

        public ItemPrice(){}

        public double getPrice() { return price; }
        public void setPrice( double price ) { this.price = price; }

        public long getTime() { return time.getTime(); }
        public void setTime( Date time ) { this.time = time; }
    }
}
