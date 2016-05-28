package com.testing.service;

import com.testing.dao.impl.UserDaoImpl;
import com.testing.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by Study on 25.05.2016.
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Autowired
    private UserDaoImpl userDao;

    @Override
    public UserDetails loadUserByUsername(String nick) throws UsernameNotFoundException {
        User user = userDao.get(nick);

        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(user.getNick(),
                        user.getPassword(),
                        user.getRolesGrantedAuthority());
        return userDetails;
    }
}
