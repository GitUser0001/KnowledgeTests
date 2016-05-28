package com.testing.service;

import com.testing.dao.impl.TestDaoImpl;
import com.testing.model.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Study on 22.05.2016.
 */
@Service
public class TestService {

    @Autowired
    private TestDaoImpl testDao;

    public void addTest(Test test) {
        testDao.add(test);
    }

    public void updateTest(Test test) {
        testDao.update(test);
    }

    public void deleteTest(int id) {
        testDao.delete(id);
    }

    public List<Test> getAllTests() {
        return testDao.getAll();
    }
}
