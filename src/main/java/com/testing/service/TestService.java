package com.testing.service;

import com.testing.dao.impl.TestDaoImpl;
import com.testing.model.Test;
import com.testing.model.helpers.StringEncoder;
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

    public boolean addTest(String testName, String testDescription) {

        if (testDao.getTestByName(testName) != null)
            return false;

        Test test = new Test(testName, testDescription);

        testDao.add(test);
        return true;
    }

    public Test getByName(String name) { return testDao.getTestByName(name); }

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
