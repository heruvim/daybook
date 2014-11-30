package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.domain.Item;
import org.serfeo.dev.persistance.domain.Order;
import org.serfeo.dev.persistance.domain.OrderItem;
import org.serfeo.dev.persistance.mapper.OrderMapperDAO;
import org.serfeo.dev.persistance.domain.BuyList;
import org.serfeo.dev.rest.response.CommonResponse;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

@Path( "/buylist" )
public class BuyListResource
{
    private final OrderMapperDAO orderMapperDAO;

    @Inject
    public BuyListResource( OrderMapperDAO orderMapperDAO ) {
        this.orderMapperDAO = orderMapperDAO;
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    public Order saveBuyList( BuyList buyList ) {
        List< BuyList.BuyListItem > buyListItems = buyList.getItems();
        HashMap< String, Double > nameToCountMap = new HashMap<>();

        List< Item > items = new LinkedList<>();
        for ( BuyList.BuyListItem currentBuyListItem: buyListItems ) {
            items.add( new Item( currentBuyListItem.getName(),
                                 currentBuyListItem.getCountName(),
                                 currentBuyListItem.getPrice(),
                                 currentBuyListItem.getComment() ) );
            nameToCountMap.put( currentBuyListItem.getName(), currentBuyListItem.getCount() );
        }

        double totalPrice = 0.0d;
        for ( BuyList.BuyListItem item: buyList.getItems() ) {
            totalPrice += ( item.getCount() * item.getPrice() );
        }

        Order newOrder = new Order( buyList.getDate(), totalPrice, buyList.getItems().size());
        orderMapperDAO.insertOrder( newOrder );

        orderMapperDAO.insertItems( items );
        List<OrderItem> orderItemList = new LinkedList<>();
        for ( Item item: items ) {
            orderItemList.add( new OrderItem( nameToCountMap.get( item.getName() ),
                                              item.getId(),
                                              newOrder.getId() ) );
        }

        orderMapperDAO.insertOrderItem( orderItemList );

        return newOrder;
    }

    @GET
    @Path( "/{id}" )
    @Produces( MediaType.APPLICATION_JSON )
    public BuyList getItemsByBuyList( @PathParam( "id" ) int id ) {
        return orderMapperDAO.getBuyListById( id );
    }

    @DELETE
    @Path( "/{id}" )
    public CommonResponse deleteBuyList( @PathParam( "id" ) int id ) {
        orderMapperDAO.deleteBuyListById( id );
        return CommonResponse.ok();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/orders" )
    public List<Order> getAllOrder() {
        return orderMapperDAO.getAllOrders();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public List<Item> getAllItems() {
        return orderMapperDAO.getAllItems();
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public Item saveItem( Item item ) {
        orderMapperDAO.insertItem( item );
        return item;
    }

    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "items" )
    public CommonResponse updateItem( Item item ) {
        orderMapperDAO.updateItem( item );
        return CommonResponse.ok();
    }

    @DELETE
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items/{id}" )
    public CommonResponse deleteItem( @PathParam( "id" ) int id ) {
        orderMapperDAO.deleteItemById( id );
        return CommonResponse.ok();
    }
}
