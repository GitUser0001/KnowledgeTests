package com.testing.dao;

import java.util.List;

/**
 * Created by Study on 17.05.2016.
 */
public interface IBaseDao<T> {

    T add(T item);

    void delete(int id);

    T get(int id);

    void update(T item);

    List<T> getAll();
}
