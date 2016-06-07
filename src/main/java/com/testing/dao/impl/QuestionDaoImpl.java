package com.testing.dao.impl;

import com.testing.model.Question;
import com.testing.dao.IBaseDao;
import org.springframework.stereotype.Repository;

import javax.faces.bean.SessionScoped;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.io.Serializable;
import java.util.List;

@Repository
@SessionScoped
public class QuestionDaoImpl implements IBaseDao<Question>, Serializable {

    private EntityManager em = Persistence.createEntityManagerFactory("PostgreSQL").createEntityManager();

    public Question add(Question item) {
        em.getTransaction().begin();
        Question questionFromDB = em.merge(item);
        em.getTransaction().commit();
        return questionFromDB;
    }

    public void delete(int id) {
        em.getTransaction().begin();
        em.remove(get(id));
        em.getTransaction().commit();
    }

    public Question get(int id) {
        return em.find(Question.class, id);
    }

    public void update(Question item) {
        em.getTransaction().begin();
        em.merge(item);
        em.getTransaction().commit();
    }

    public List<Question> getAll() {
        TypedQuery<Question> namedQuery = em.createNamedQuery("Question.getAll", Question.class);
        return namedQuery.getResultList();
    }
}
