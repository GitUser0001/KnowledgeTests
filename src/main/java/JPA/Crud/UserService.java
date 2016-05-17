package JPA.Crud;

import JPA.Entity.User;
import JPA.Services.IUserService;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public class UserService implements IUserService {

    public EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public User add(User item) {
        em.getTransaction().begin();
        User userFromDB = em.merge(item);
        em.getTransaction().commit();
        return userFromDB;
    }

    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public User get(int id) {
        return em.find(User.class, id);
    }

    public void update(User item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public List<User> getAll() {
        TypedQuery<User> namedQuery = em.createNamedQuery("User.getAll", User.class);
        return namedQuery.getResultList();
    }
}
