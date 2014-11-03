package org.serfeo.dev.persistance.mapper;

import com.google.inject.Inject;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.ExecutorType;
import org.mybatis.guice.transactional.Transactional;
import org.serfeo.dev.persistance.domain.BuyList;
import org.serfeo.dev.persistance.domain.Item;
import org.serfeo.dev.persistance.domain.Order;
import org.serfeo.dev.persistance.domain.OrderItem;

import java.util.Collection;
import java.util.List;

public class OrderMapperDAO {
    private OrderMapper mapper;

    @Inject
    public OrderMapperDAO( OrderMapper mapper ) {
        this.mapper = mapper;
    }

    public List<Order> getAllOrders() {
        return mapper.getAllOrders();
    }

    public List<Item> getAllItems() {
        return mapper.getAllItems();
    }

    public void insertOrder( Order order ) {
        mapper.insertOrderData( order );
    }

    public void deleteBuyListById( int id ) { mapper.deleteBuyListById( id ); };

    public BuyList getBuyListById( int id ) { return mapper.getBuyListById( id ); }

    @Transactional( executorType = ExecutorType.BATCH )
    public void insertOrderItem( Collection<OrderItem> orderItems ) {
        for ( OrderItem orderItem: orderItems )
            mapper.insertOrderItem( orderItem );
    }

    @Transactional( executorType = ExecutorType.BATCH )
    public void insertItems(Collection<Item> items) {
        for ( Item item : items )
            mapper.insertItem( item );
    }

    public void insertItem( Item item ) {
        mapper.insertItem( item );
    }

    public void deleteItemById( int id ) {
        mapper.deleteItemById( id );
    }
}
