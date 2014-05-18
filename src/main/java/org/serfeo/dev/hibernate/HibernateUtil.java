package org.serfeo.dev.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil
{
    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory()
    {
        try
        {
            Configuration hibernateConfiguration = new Configuration();
            hibernateConfiguration.configure();

            StandardServiceRegistryBuilder serviceRegistryBuilder = new StandardServiceRegistryBuilder();
            serviceRegistryBuilder.applySettings( hibernateConfiguration.getProperties() );

            StandardServiceRegistry serviceRegistry = serviceRegistryBuilder.build();
            return hibernateConfiguration.buildSessionFactory( serviceRegistry );
        }
        catch ( Throwable ex )
        {
            System.err.println( "Initial SessionFactory creation failed." + ex );
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}