package HibernateAssignment;

//Solution for Q - 10

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "Store_association")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;
    @Column(name = "Store_Name")
    private String name;
    @Column(name = "Address")
    private String address;

    @OneToMany(mappedBy = "store", cascade = CascadeType.REMOVE)
    private Set<Product> products= new HashSet<>();

    public Store(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Store() {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
