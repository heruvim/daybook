package org.serfeo.dev.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Scopes;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.serfeo.dev.rest.BuyListResource;
import org.serfeo.dev.rest.CalendarResource;

public class ContextListener extends GuiceServletContextListener
{
    @Override
    protected Injector getInjector()
    {
        return Guice.createInjector( new ServletModule()
        {
            @Override
            protected void configureServlets()
            {
                bind( CalendarResource.class );
                bind( BuyListResource.class );

                bind( GuiceContainer.class );
                bind( JacksonJsonProvider.class ).in( Scopes.SINGLETON );

                serve("/rest*").with( GuiceContainer.class );

                
            }
        } );
    }
}
