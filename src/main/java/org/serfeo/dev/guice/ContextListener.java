package org.serfeo.dev.guice;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;
import org.serfeo.dev.guice.module.PersistenceModule;
import org.serfeo.dev.guice.module.PrivateRestModule;
import org.serfeo.dev.guice.module.PublicRestModule;

import java.util.LinkedList;
import java.util.List;

public class ContextListener extends GuiceServletContextListener {
    @Override
    protected Injector getInjector() {
        return Guice.createInjector( getModules() );
    }

    private List<Module> getModules() {
        List<Module> modules = new LinkedList<>();

        modules.add( new PrivateRestModule() );
        modules.add( new PublicRestModule() );
        modules.add( new PersistenceModule() );

        return modules;
    }
}
