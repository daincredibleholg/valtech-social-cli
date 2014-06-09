package technology.steinhauer.demo.valtech.persistence;


import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Simple utility to get a valid Hibernate session.
 * This code is based on https://docs.jboss.org/hibernate/core/4.3/quickstart/en-US/html_single.
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            return new Configuration().configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Make sure you log the exception, as it might be swallowed
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}