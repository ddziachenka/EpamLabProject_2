package com.epamlab.dao.interfaces;

import com.epamlab.model.EventInfo;

import java.util.List;

public interface PerformanceShowingDao<T> {

    void add(T performanceShowing);

    void update(T performanceShowing, int id);

    void remove(int id);

    T get(int id);

    List<T> getList();

    List<EventInfo> getFullEventsInfo();
}
