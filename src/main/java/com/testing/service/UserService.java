package com.testing.service;

import com.testing.dao.impl.GroupDaoImpl;
import com.testing.dao.impl.TestAssociationDaoImpl;
import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.Group;
import com.testing.model.Test;
import com.testing.model.User;
import com.testing.model.helpers.TestAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.validation.constraints.NotNull;
import java.util.Date;

/**
 * Created by Study on 22.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private TestAssociationDaoImpl testAssociationDao;
    @Autowired
    private GroupDaoImpl groupDao;


    public void addUser(User user) {
        userDao.add(user);
    }

    public User getByNick(String nick) { return userDao.get(nick); }

    public void updateUser(User user) { userDao.add(user); }

    public boolean LogIn(String nickName, String password) {
        return (userDao.get(nickName, password) != null);
    }

    public boolean Register(String nickName, String pass, String firstName, String lastName) {

        if (userDao.get(nickName) != null)
            return false;


        Group group = groupDao.get(1001);

        User user = new User(nickName, firstName, lastName, pass, group);

        userDao.add(user);
        return true;
    }

    public void addTest(User user, Test test, Date date) {

        TestAssociation association = new TestAssociation();

        association.setMark(-1);
        association.setPassedIn(date);
        association.setUser(user);
        association.setTest(test);

        association = testAssociationDao.add(association);

        user.addTestAssociation(association);
        test.addTestAssociation(association);
    }

    public void setTestMark(User user, Test test, Date date, int mark){
        TestAssociation testAssociation = testAssociationDao.getUserTest(test, user, date);
        testAssociation.setMark(mark);
    }
}
