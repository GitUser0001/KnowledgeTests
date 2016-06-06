package com.testing.service;

import com.testing.dao.impl.TestAssociationImpl;
import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.Test;
import com.testing.model.User;
import com.testing.model.enums.UserRoleEnum;
import com.testing.model.helpers.TestAssociation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by Study on 22.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;
    @Autowired
    private TestAssociationImpl testAssociationDao;


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

        User user = new User(nickName, firstName, lastName, pass);

        userDao.add(user);
        return true;
    }

    public void addTest(User user,Test test, Date date, int mark) {
        TestAssociation association = new TestAssociation();

        association.setUserId(user.getId());
        association.setTestId(test.getId());
        association.setMark(mark);
        association.setPassedIn(date);

        association.setUser(user);
        association.setTest(test);

        association = new TestAssociationImpl().add(association);



        user.addTestAssociation(association);



        test.getUsers().add(association);
    }
}
