package com.testing.service;

import com.testing.dao.impl.QuestionDaoImpl;
import com.testing.model.Question;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Study on 05.06.2016.
 */
@Service
public class QuestionService {

    @Autowired
    private QuestionDaoImpl questionDao;

    public Question addQuestion(Question question) {
        return questionDao.add(question);
    }


    public void updateQuestion(Question question) {
        questionDao.update(question);
    }

    public void deleteTest(int id) {
        questionDao.delete(id);
    }

    public List<Question> getAllTests() {
        return questionDao.getAll();
    }
}
