package com.testing.service;

import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Study on 22.05.2016.
 */
@Component
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public void addUser(User user) {
        userDao.add(user);
    }

    public boolean LogIn(String nickName, String password) {
        return (userDao.get(nickName, password) != null);
    }

    public boolean Register(String nickName, String pass, String firstName, String lastName, String secret) {

        if (userDao.get(nickName) != null)
            return false;

        User user = new User(nickName, firstName, lastName, pass, secret);

        userDao.add(user);
        return true;
    }
}
