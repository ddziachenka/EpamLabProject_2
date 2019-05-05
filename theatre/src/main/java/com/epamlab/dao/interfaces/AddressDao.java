package com.epamlab.dao.interfaces;

import java.util.List;

public interface AddressDao<T> {

    void add(T address);

    void update(T address, int id);

    void remove(int id);

    T get(int id);

    List<T> getList();
}

