package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.domain.*;
import org.serfeo.dev.persistance.mapper.OrderMapperDAO;
import org.serfeo.dev.rest.response.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
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
    public Order saveBuyList( BuyList buyList,
                              @Context HttpServletRequest request ) {
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

        Order newOrder = new Order( buyList.getDate(), totalPrice, buyList.getItems().size() );
        orderMapperDAO.insertOrder( newOrder, SessionManager.getUserId( request ) );

        if ( newOrder.getId() == 0 )
            throw new RuntimeException( "Error during inserting order id. Check SQL expression." );

        orderMapperDAO.insertItems( items, SessionManager.getUserId( request ) );
        List<OrderItem> orderItemList = new LinkedList<>();
        for ( Item item: items ) {
            if ( item.getId() == 0 )
                throw new RuntimeException( "Error during inserting item id. Check SQL expression." );

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
    public BuyList getItemsByBuyList( @PathParam( "id" ) int id,
                                      @Context HttpServletRequest request ) {
        return orderMapperDAO.getBuyListById( id, SessionManager.getUserId( request ) );
    }

    @DELETE
    @Path( "/{id}" )
    public CommonResponse deleteBuyList( @PathParam( "id" ) int id,
                                         @Context HttpServletRequest request ) {
        orderMapperDAO.deleteBuyListById( id, SessionManager.getUserId( request ) );
        return CommonResponse.ok();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/orders" )
    public List<Order> getAllOrder( @Context HttpServletRequest request ) {
        return orderMapperDAO.getAllOrders( SessionManager.getUserId( request ) );
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public List<Item> getAllItems( @Context HttpServletRequest request ) {
        return orderMapperDAO.getAllItems( SessionManager.getUserId( request ) );
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public Item saveItem( Item item, @Context HttpServletRequest request ) {
        orderMapperDAO.insertItem( item, SessionManager.getUserId( request ) );
        orderMapperDAO.addItemPriceStat( item );

        return item;
    }

    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public CommonResponse updateItem( Item item, @Context HttpServletRequest request ) {
        Item oldItem = orderMapperDAO.findItemById( item.getId() );
        orderMapperDAO.updateItem( item, SessionManager.getUserId( request ) );

        if ( !oldItem.getPrice().equals( item.getPrice() ) )
            orderMapperDAO.addItemPriceStat( item );

        return CommonResponse.ok();
    }

    @DELETE
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items/{id}" )
    public CommonResponse deleteItem( @PathParam( "id" ) int id,
                                      @Context HttpServletRequest request ) {
        orderMapperDAO.deleteItemById( id, SessionManager.getUserId( request ) );
        return CommonResponse.ok();
    }
}
