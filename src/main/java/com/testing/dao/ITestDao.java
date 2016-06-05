package com.testing.dao;


import com.testing.model.Test;

/**
 * Created by Study on 17.05.2016.
 */
public interface ITestDao extends IBaseDao<Test> {

    Test getTestByName(String testName);
}
