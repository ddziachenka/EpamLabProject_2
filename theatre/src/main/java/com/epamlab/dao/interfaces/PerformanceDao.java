package com.epamlab.dao.interfaces;

import java.util.List;

public interface PerformanceDao<T> {

    void add(T performance);

    void update(T performance, int id);

    void remove(int id);

    T get(int id);

    List<T> getList();
}
