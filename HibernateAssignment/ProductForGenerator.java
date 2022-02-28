package HibernateAssignment;

//  Solution for que 7

import jakarta.persistence.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class ProductForGenerator{
    public static void main(String[] args) {
        ProductForGeneratorUtil productForGeneratorUtil = new ProductForGeneratorUtil();
        productForGeneratorUtil.setPrice(165);
        productForGeneratorUtil.setName("Oil");

        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(ProductForGeneratorUtil.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.persist(productForGeneratorUtil);
        transaction.commit();
        session.close();
    }
}

@Entity
@Table(name = "ProductForGenerator")
class ProductForGeneratorUtil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Product_id")
    private int id;

    @Column(name = "Product_price", nullable = false)
    private int price;
    @Column(name = "Product_name", nullable = false)
    private String name;

    public ProductForGeneratorUtil(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ProductForGeneratorUtil{" +
                "id=" + id +
                ", price=" + price +
                ", name='" + name + '\'' +
                '}';
    }
}
