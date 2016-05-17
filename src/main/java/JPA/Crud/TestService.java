package JPA.Crud;

import JPA.Entity.Test;
import JPA.Services.ITestService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public class TestService implements ITestService {

    public EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public Test add(Test item) {
        em.getTransaction().begin();
        Test testFromDB = em.merge(item);
        em.getTransaction().commit();
        return testFromDB;
    }

    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Test get(int id) {
        return em.find(Test.class, id);
    }

    public void update(Test item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public List<Test> getAll() {
        TypedQuery<Test> namedQuery = em.createNamedQuery("Test.getAll", Test.class);
        return namedQuery.getResultList();
    }
}
