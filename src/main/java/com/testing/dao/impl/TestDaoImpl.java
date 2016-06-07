package com.testing.dao.impl;

import com.testing.model.Test;
import com.testing.dao.ITestDao;
import org.springframework.stereotype.Repository;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */

@Repository
@SessionScoped
public class TestDaoImpl implements ITestDao, Serializable {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public TestDaoImpl() {  }

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

    @Override
    public Test getTestByName(String testName) {
        try {
            TypedQuery<Test> namedQuery = em.createNamedQuery("Test.findTestByName", Test.class)
                    .setParameter("name", testName);
            return namedQuery.getSingleResult();
        } catch (Exception e){
            return null;
        }
    }
}
