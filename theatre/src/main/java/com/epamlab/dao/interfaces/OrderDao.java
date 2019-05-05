package com.epamlab.dao.interfaces;

import java.util.List;

public interface OrderDao<T> {

    void add(T order);

    void update(T order, int id);

    void remove(int id);

    T get(int id);

    List<T> getByIdUser(int id);

    List<T> getListObjects();
}
