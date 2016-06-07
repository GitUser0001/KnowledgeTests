package com.testing.dao.impl;

import com.testing.dao.IBaseDao;
import com.testing.model.Test;
import com.testing.model.User;
import com.testing.model.helpers.TestAssociation;
import org.springframework.stereotype.Repository;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * Created by Study on 05.06.2016.
 */

@Repository
@SessionScoped
public class TestAssociationDaoImpl implements IBaseDao<TestAssociation>, Serializable {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public TestAssociationDaoImpl(){}


    @Override
    public TestAssociation add(TestAssociation item) {
        em.getTransaction().begin();
        TestAssociation testAsFromDB = em.merge(item);
        em.getTransaction().commit();
        return testAsFromDB;
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public TestAssociation get(int id) {
        return em.find(TestAssociation.class, id);
    }

    @Override
    public void update(TestAssociation item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    @Override
    public List<TestAssociation> getAll() {
        TypedQuery<TestAssociation> namedQuery = em.createNamedQuery("TestAssociation.getAll", TestAssociation.class);
        return namedQuery.getResultList();
    }

    public TestAssociation getUserTest(Test test, User user, Date date){
        try {
            TypedQuery<TestAssociation> namedQuery = em.createNamedQuery("TestAssociation.getByUserTestDate", TestAssociation.class)
                    .setParameter("user", user)
                    .setParameter("test", test)
                    .setParameter("passedIn", date);
            return namedQuery.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
