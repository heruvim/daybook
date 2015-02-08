package org.serfeo.dev.rest;

import com.google.inject.Inject;
import org.serfeo.dev.persistance.mapper.OrderMapperDAO;
import org.serfeo.dev.persistance.domain.ItemPriceStat;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path( "/stat" )
public class ItemStatisticResource {

    private final OrderMapperDAO orderMapperDAO;

    @Inject
    public ItemStatisticResource( OrderMapperDAO orderMapperDAO ) { this.orderMapperDAO = orderMapperDAO; }

    @GET
    @Produces( MediaType.APPLICATION_JSON )
    @Path( "/items" )
    public List< ItemPriceStat > getItemPriceStatistic( @Context HttpServletRequest request ) {
        return orderMapperDAO.getItemStatByUserId( SessionManager.getUserId(request) );
    }
}
