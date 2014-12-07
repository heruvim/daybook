package org.serfeo.dev.guice.module;

import com.google.inject.Scopes;
import com.google.inject.servlet.ServletModule;
import com.sun.jersey.guice.spi.container.servlet.GuiceContainer;
import org.codehaus.jackson.jaxrs.JacksonJsonProvider;
import org.serfeo.dev.rest.UserResource;

public class PublicRestModule extends ServletModule {
    @Override
    protected void configureServlets() {
        bind( UserResource.class ).in( Scopes.SINGLETON );
        bind( JacksonJsonProvider.class ).in( Scopes.SINGLETON );

        serve("/rest/public/*").with( GuiceContainer.class );

    }
}
