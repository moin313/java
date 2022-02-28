package HibernateAssignment;


//  Solution for Q- 9

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.cfg.Configuration;

@Entity
class Cat{
    @Id
    @GeneratedValue(generator="custom-generator")
    @GenericGenerator(name = "custom-generator", strategy = "Day8.CustomId")

    @Column(name = "Cat_Id")
    private int Id;
    @Column(name = "Cat_Name")
    private String name;
    @Column(name = "Cat_weight")
    private float weight;
    @Column(name = "Cat_age")
    private int age;

    public Cat(){}

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Cat{" +
                "Id=" + Id +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", age=" + age +
                '}';
    }
}

public class CatMain {
    public static void main(String[] args) {
        Session session = getSession();
        CriteriaQuery<Cat> critertiaQuery = getResult(session);
        System.out.println(session.createQuery(critertiaQuery).getResultList());
//        newCat();
    }

    private static void newCat() {
        Cat cat = new Cat();
        cat.setAge(5); cat.setWeight(4); cat.setName("Cat-2");
        Session session = getSession();
        Transaction transaction = session.beginTransaction();
        session.persist(cat);
        transaction.commit();
        session.close();
    }

    public static CriteriaQuery<Cat> getResult(Session session){
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Cat> critertiaQuery = criteriaBuilder.createQuery(Cat.class);
        Root<Cat> root = critertiaQuery.from(Cat.class);
//        String name = root.get("name").;
//        Criterion c1 = Restrictions.like("stateName", "Virg", MatchMode.START);
        critertiaQuery.select(root).where(criteriaBuilder.and(criteriaBuilder.like(root.get("name"), "c%")), criteriaBuilder.gt(root.get("weight"), 2));
        critertiaQuery.orderBy(criteriaBuilder.desc(root.get("age")));
        return critertiaQuery;

    }

    private static Session getSession() {
        Configuration configuration = new Configuration();
        configuration.configure();
        configuration.addAnnotatedClass(Cat.class);
        SessionFactory sessionFactory = configuration.buildSessionFactory();
        Session session = sessionFactory.openSession();
        return session;
    }

}
