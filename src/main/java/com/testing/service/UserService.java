package com.testing.service;

import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.User;
import com.testing.model.enums.UserRoleEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by Study on 22.05.2016.
 */
@Service
public class UserService {

    @Autowired
    private UserDaoImpl userDao;

    public void addUser(User user) {
        userDao.add(user);
    }

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
}
