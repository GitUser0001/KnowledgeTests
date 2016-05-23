package com.testing.dao.impl;

import com.testing.model.User;
import com.testing.dao.IUserDao;
import org.springframework.stereotype.Repository;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.security.cert.Extension;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */

@Repository
@SessionScoped
public class UserDaoImpl implements IUserDao, Serializable {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

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

    public User get(String nickName, String pass) {
        try {
            TypedQuery<User> namedQuery = em.createNamedQuery("User.findUser", User.class)
                    .setParameter("nickName", nickName)
                    .setParameter("password", pass);
            return namedQuery.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }

    public User get(String nickName) {
        try {
            TypedQuery<User> namedQuery = em.createNamedQuery("User.findUserByNickName", User.class)
                    .setParameter("nickName", nickName);
            return namedQuery.getSingleResult();
        } catch (Exception ex) {
            return null;
        }
    }
}
