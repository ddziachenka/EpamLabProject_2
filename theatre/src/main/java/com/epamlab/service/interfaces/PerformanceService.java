package com.epamlab.service.interfaces;

import java.util.List;

public interface PerformanceService<T> {

    void addPerformance(T performance);

    void updatePerformance(T performance, int id);

    void removePerformance(int id);

    T getPerformance(int id);

    List<T> getListPerformances();
}
