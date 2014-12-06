package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.common.Const;
import org.serfeo.dev.persistance.domain.*;
import org.serfeo.dev.persistance.mapper.OrderMapperDAO;
import org.serfeo.dev.rest.response.CommonResponse;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
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
        orderMapperDAO.insertOrder( newOrder, getUserId( request ) );

        if ( newOrder.getId() == 0 )
            throw new RuntimeException( "Error during inserting order id. Check SQL expression." );

        orderMapperDAO.insertItems( items, getUserId( request ) );
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
        return orderMapperDAO.getBuyListById( id, getUserId( request ) );
    }

    @DELETE
    @Path( "/{id}" )
    public CommonResponse deleteBuyList( @PathParam( "id" ) int id,
                                         @Context HttpServletRequest request ) {
        orderMapperDAO.deleteBuyListById( id, getUserId( request ) );
        return CommonResponse.ok();
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/orders" )
    public List<Order> getAllOrder( @Context HttpServletRequest request ) {
        return orderMapperDAO.getAllOrders( getUserId( request ) );
    }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public List<Item> getAllItems( @Context HttpServletRequest request ) {
        return orderMapperDAO.getAllItems( getUserId( request ) );
    }

    @POST
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public Item saveItem( Item item, @Context HttpServletRequest request ) {
        orderMapperDAO.insertItem( item, getUserId( request ) );
        return item;
    }

    @PUT
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public CommonResponse updateItem( Item item, @Context HttpServletRequest request ) {
        orderMapperDAO.updateItem( item, getUserId( request ) );
        return CommonResponse.ok();
    }

    @DELETE
    @Consumes( MediaType.APPLICATION_JSON )
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items/{id}" )
    public CommonResponse deleteItem( @PathParam( "id" ) int id,
                                      @Context HttpServletRequest request ) {
        orderMapperDAO.deleteItemById( id, getUserId( request ) );
        return CommonResponse.ok();
    }

    private long getUserId( HttpServletRequest request ) {
        HttpSession session = request.getSession( false );
        if ( session != null ) {
            Object credentials = session.getAttribute( Const.sessionCredentials );
            if (credentials != null && credentials instanceof User)
                return ( ( User ) credentials ).getId();
        }

        return -1;
    }
}
