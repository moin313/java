package HibernateAssignment;


//Solution for Q - 10

import org.hibernate.Session;
import org.hibernate.Transaction;

public class StoreDriver {
    public static void main(String[] args) {
        Session session = Utility.getStoreSession();
//        Initialize(session);
//        addItem(session);
//        removeItem();
        removeStore(session);
    }
    private static void Initialize(Session session) {
        Transaction transaction = session.beginTransaction();
        Store store =new Store("Mart-2","Indore2");
        session.persist(store);
        Product product =new Product("item-2",store);
        session.persist(product);
        transaction.commit();
        session.close();
    }
    private static void removeStore(Session session) {
        Transaction transaction = session.beginTransaction();
        Store store = session.get(Store.class,1);
        session.remove(store);
        transaction.commit();
        session.close();
    }

    private static void addItem(Session session) {
        Transaction transaction = session.beginTransaction();
        Store store =session.get(Store.class,2);
        Product product =new Product("item-3",store);
        session.persist(product);
        transaction.commit();
        session.close();
    }
    private static void removeItem() {
//        Store store = new Store();
//        store.setId(101);

        Product product1 = new Product();
        product1.setId(2);
        Session session = Utility.getStoreSession();
        Transaction transaction = session.beginTransaction();
        session.remove(product1);
        transaction.commit();
        session.close();
    }

}
