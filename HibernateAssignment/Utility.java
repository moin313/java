package HibernateAssignment;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Utility {

    public static Session getStoreSession(){
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Store.class);
        configuration.addAnnotatedClass(Product.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }
}
