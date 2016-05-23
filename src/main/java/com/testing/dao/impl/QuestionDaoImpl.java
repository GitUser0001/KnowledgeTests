package com.testing.dao.impl;

import com.testing.model.Question;
import com.testing.dao.IBaseDao;
import org.springframework.stereotype.Repository;

import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.List;

@Repository
@SessionScoped
public class QuestionDaoImpl implements IBaseDao<Question>, Serializable {


    public Question add(Question item) {
        return null;
    }

    public void delete(int id) {

    }

    public Question get(int id) {
        return null;
    }

    public void update(Question item) {

    }

    public List<Question> getAll() {
        return null;
    }
}
