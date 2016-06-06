package com.testing.dao.impl;

import com.testing.dao.IBaseDao;
import com.testing.model.helpers.TestAssociation;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Study on 05.06.2016.
 */
public class TestAssociationImpl implements IBaseDao<TestAssociation>, Serializable {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public TestAssociationImpl(){}


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
}
