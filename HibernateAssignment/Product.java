package HibernateAssignment;


import jakarta.persistence.*;

@Entity
@Table(name = "product_association")
public class Product {
    @Id
    @Column(name = "product_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "product_name")
    private String name;

    @ManyToOne(cascade = CascadeType.ALL)
    private Store store;

    public Product() {
    }

    public Product(String name, Store store) {
        this.name = name;
        this.store = store;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }
}
