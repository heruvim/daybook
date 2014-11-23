package org.serfeo.dev.persistance.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.serfeo.dev.persistance.domain.BuyList;
import org.serfeo.dev.persistance.domain.Item;
import org.serfeo.dev.persistance.domain.Order;
import org.serfeo.dev.persistance.domain.OrderItem;

import java.util.List;

public interface OrderMapper {
    @Select( "select * from `orders` where `isDeleted`=0" )
    public List< Order > getAllOrders();

    @Select( "select * from items" )
    public List< Item > getAllItems();

    public BuyList getBuyListById( @Param( "id" ) int id );

    public void insertOrderItem( OrderItem orderItem );
    public void insertOrderData( Order order );
    public void insertItem( Item item );

    void deleteBuyListById( @Param( "id" ) int id );

    void deleteItemById( @Param( "id" ) int id );

    void updateItem( Item item );
}
