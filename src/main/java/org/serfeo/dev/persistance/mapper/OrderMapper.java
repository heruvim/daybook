package org.serfeo.dev.persistance.mapper;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.serfeo.dev.persistance.domain.BuyList;
import org.serfeo.dev.persistance.domain.Item;
import org.serfeo.dev.persistance.domain.Order;
import org.serfeo.dev.persistance.domain.OrderItem;

import java.util.List;

public interface OrderMapper {
    @Select( "select * from `orders` where `isDeleted`=0 and `userId`=#{userId}" )
    public List< Order > getAllOrders( @Param( "userId" ) long userId );

    @Select( "select * from items where `userId`=#{userId}" )
    public List< Item > getAllItems( @Param( "userId" ) long userId );

    public BuyList getBuyListById( @Param( "id" ) int id, @Param( "userId" ) long userId );

    public void insertOrderItem( OrderItem orderItem );
    public void insertOrderData( @Param( "order" )Order order, @Param( "userId" ) long userId );
    public void insertItem( @Param( "item" ) Item item, @Param( "userId" ) long userId );

    void deleteBuyListById( @Param( "id" ) int id, @Param( "userId" ) long userId );

    void deleteItemById( @Param( "id" ) int id, @Param( "userId" ) long userId );

    void updateItem( @Param( "item" ) Item item, @Param( "userId" ) long userId );
}
