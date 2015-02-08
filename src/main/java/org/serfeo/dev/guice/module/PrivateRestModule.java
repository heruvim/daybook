package org.serfeo.dev.guice.module;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.serfeo.dev.rest.BuyListResource;
import org.serfeo.dev.rest.CalendarResource;
import org.serfeo.dev.rest.ItemStatisticResource;
import org.serfeo.dev.security.AuthFilter;

public class PrivateRestModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind( CalendarResource.class ).in( Scopes.SINGLETON );
        bind( BuyListResource.class ).in( Scopes.SINGLETON );
        bind( ItemStatisticResource.class ).in( Scopes.SINGLETON );

        bind( JacksonJsonProvider.class ).in( Scopes.SINGLETON );
        bind( AuthFilter.class ).in( Scopes.SINGLETON );

        serve( "/rest/private/*" ).with( GuiceContainer.class );
        filter( "/rest/private/*" ).through( AuthFilter.class );
    }
}
