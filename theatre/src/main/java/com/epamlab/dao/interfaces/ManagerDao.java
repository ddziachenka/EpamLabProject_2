package com.epamlab.dao.interfaces;

import java.util.List;

public interface ManagerDao<T> {

    void add(T manager);

    void update(T manager, int id);

    void remove(int id);

    T get(int id);

    List<T> getList();
}

