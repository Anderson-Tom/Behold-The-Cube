package db_service;

import db_service.dao.UsersDAO;
import db_service.data_sets.UserDataSet;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.service.ServiceRegistry;

import java.sql.Connection;
import java.sql.SQLException;

/**
 * @author IvanLis
 * @version 1.0
 * @since 26/12/2015.
 * <p/>
 * version2
 */
public class DBService {
    private static final String hibernate_show_sql = "true";
    private static final String hibernate_hbm2ddl_auto = "validate";
    private static final String url = "jdbc:h2:./src/db/h2db";
    private static final String dialect = "org.hibernate.dialect.H2Dialect";
    private static final String driver = "org.h2.Driver";
    private static final String user = "ivan";
    private static final String pass = "qwerty";

    private static class LazyHolder {
        private static final DBService INSTANCE = new DBService();
    }

    private SessionFactory sessionFactory;

    private DBService() {
        Configuration configuration = getConfiguration(dialect, driver, url, user, pass);
        sessionFactory = createSessionFactory(configuration);
    }

//    @SuppressWarnings("UnusedDeclaration")
//    private Configuration getMySqlConfiguration() {
//        Configuration configuration = new Configuration();
//        configuration.addAnnotatedClass(UserDataSet.class);
//
//        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MYSQLDialect");
//        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
//        configuration.setProperty("hibernate.connection.url", "jdbc:mysql:://localhost:3306/db_example");
//        configuration.setProperty("hibernate.connection.username", "ivan");
//        configuration.setProperty("hibernate.connection.password", "qwerty");
//        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
//        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
//        return configuration;
//    }

    public static DBService getInstance() {
        return LazyHolder.INSTANCE;
    }

    private Configuration getConfiguration(String dialect, String driver, String url, String user, String pass) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.setProperty("hibernate.dialect", dialect);
        configuration.setProperty("hibernate.connection.driver_class", driver);
        configuration.setProperty("hibernate.connection.url", url);
        configuration.setProperty("hibernate.connection.username", user);
        configuration.setProperty("hibernate.connection.password", pass);
        configuration.setProperty("hibernate.show_sql", hibernate_show_sql);
        configuration.setProperty("hibernate.hbm2ddl.auto", hibernate_hbm2ddl_auto);
        return configuration;

    }

    public UserDataSet getUserById(long id) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UserDataSet dataSet = dao.getById(id);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public UserDataSet getUserByName(String name) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            UsersDAO dao = new UsersDAO(session);
            UserDataSet dataSet = dao.getByName(name);
            session.close();
            return dataSet;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public long addUser(String name, String pass) throws DBException {
        try {
            Session session = sessionFactory.openSession();
            Transaction transaction = session.beginTransaction();
            UsersDAO dao = new UsersDAO(session);
            long id = dao.insertUser(name, pass);
            transaction.commit();
            session.close();
            return id;
        } catch (HibernateException e) {
            throw new DBException(e);
        }
    }

    public void printConnectInfo () {
        try {
            SessionFactoryImpl session = (SessionFactoryImpl) sessionFactory;
            Connection connection = session.getConnectionProvider().getConnection();
            System.out.println("DB name: " + connection.getMetaData().getDatabaseProductName());
            System.out.println("DB version: " + connection.getMetaData().getDatabaseProductVersion());
            System.out.println("Driver: " + connection.getMetaData().getDriverName());
            System.out.println("Autocommit: " + connection.getAutoCommit());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static SessionFactory createSessionFactory (Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
