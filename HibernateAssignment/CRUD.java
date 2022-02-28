package HibernateAssignment;

import Day10.Person;
import Day10.PhoneNumber;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

/**
 *      FOURTH SOLUTION
 * CRUD stands for Create Read Update Delete this all are the key functionality what a user needs to work on data.
 * Create - use to create the table in database and define its table's structure,
 * Read - generally extract the data from the existing table
 * Update  - If tha matched data is present in table can be updated.
 * Delete - IF the matched data is present in table can is Deleted.
 */
public class CRUD {
    public static void main(String[] args) {
        addItem();
        updateItem();
        fetchItem();
        removeItem();
    }
    //CREATE AND INSERT
    private static void addItem() {
        Store store = new Store();
        store.setId(101); store.setName("Mart-1"); store.setAddress("Indore");

        Product product1 = new Product();
        product1.setName("Tea");
        product1.setId(202);
        product1.setStore(store);
        Session session = Utility.getStoreSession();
        Transaction transaction = session.beginTransaction();
        session.persist(store);
        session.persist(product1);
        transaction.commit();
        session.close();
    }
    //READ
    private static void fetchItem() {
        Session session = Utility.getStoreSession();
        List<Product> products = session.createQuery("from Product",Product.class).getResultList();
        System.out.println(products);
    }
    //UPDATE
    private static void updateItem(){
        Session session = Utility.getStoreSession();
        Transaction transaction=session.beginTransaction();
        Product product = session.get(Product.class,202);
        product.setName("Tea");
        session.persist(product);
        transaction.commit();
        session.close();
    }
    //Remove
    private static void removeItem() {
        Product product1 = new Product();
        product1.setId(202);
        Session session = Utility.getStoreSession();
        Transaction transaction = session.beginTransaction();
        session.remove(product1);
        transaction.commit();
        session.close();
    }

}






