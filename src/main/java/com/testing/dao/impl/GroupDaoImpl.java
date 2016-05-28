package com.testing.dao.impl;

import com.testing.dao.IGroupDao;
import com.testing.model.Group;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;

/**
 * Created by Study on 24.05.2016.
 */
public class GroupDaoImpl implements IGroupDao {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    @Override
    public Group add(Group item) {
        em.getTransaction().begin();
        Group groupFromDB = em.merge(item);
        em.getTransaction().commit();
        return groupFromDB;
    }

    @Override
    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    @Override
    public Group get(int id) { return em.find(Group.class, id); }

    @Override
    public void update(Group item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    @Override
    public List<Group> getAll() {
        TypedQuery<Group> namedQuery = em.createNamedQuery("Group.getAll", Group.class);
        return namedQuery.getResultList();
    }
}
