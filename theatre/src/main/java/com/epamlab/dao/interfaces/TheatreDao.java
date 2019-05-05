package com.epamlab.dao.interfaces;

import java.util.List;

public interface TheatreDao<T> {

    void add(T theatre);

    void update(T theatre, int id);

    void remove(int id);

    T get(int id);

    List<T> getList();
}

