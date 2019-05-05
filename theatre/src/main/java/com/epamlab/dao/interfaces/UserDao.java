package com.epamlab.dao.interfaces;

import java.util.List;

public interface UserDao<T> {

    void add(T user);

    void update(T user, int id);

    void remove(int id);

    T get(String login);

    List<T> getList();
}