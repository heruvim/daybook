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
    public OrderMapperDAO( OrderMapper mapper ) { this.mapper = mapper; }

    public List<Order> getAllOrders( long userId ) { return mapper.getAllOrders( userId ); }

    public List<Item> getAllItems( long userId ) { return mapper.getAllItems( userId ); }

    public void insertOrder( Order order, long userId ) { mapper.insertOrderData( order, userId ); }

    public void deleteBuyListById( int id, long userId ) { mapper.deleteBuyListById( id, userId ); }

    public BuyList getBuyListById( int id, long userId ) { return mapper.getBuyListById( id, userId ); }

    @Transactional( executorType = ExecutorType.BATCH )
    public void insertOrderItem( Collection<OrderItem> orderItems ) {
        for ( OrderItem orderItem: orderItems )
            mapper.insertOrderItem( orderItem );
    }

    @Transactional( executorType = ExecutorType.BATCH )
    public void insertItems(Collection<Item> items, long userId ) {
        for ( Item item : items )
            mapper.insertItem( item, userId );
    }

    public void insertItem( Item item, long userId ) { mapper.insertItem( item, userId ); }

    public void deleteItemById( int id, long userId ) { mapper.deleteItemById( id, userId ); }

    public void updateItem( Item item, long userId ) { mapper.updateItem( item, userId ); }
}
