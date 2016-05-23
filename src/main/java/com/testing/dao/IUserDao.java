package com.testing.dao;

import com.testing.model.User;

/**
 * Created by Study on 17.05.2016.
 */
public interface IUserDao extends IBaseDao<User> {

    User get(String nickName, String pass);
}
