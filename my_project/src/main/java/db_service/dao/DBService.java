package db_service.dao;

import db_service.tables.Author;
import db_service.tables.Book;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

/**
 * @author IvanLis
 * @version 1.0
 * @since 02/01/2016.
 * <p/>
 * mysql_ex
 */
public class DBService {

    private static SessionFactory sessionFactory;

    private DBService(){}

    static {
        Configuration configuration = new Configuration().configure();
        sessionFactory = createSessionFactory(configuration);
    }

    private static SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
