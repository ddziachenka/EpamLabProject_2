package com.epamlab.service.interfaces;

import com.epamlab.model.EventInfo;

import java.util.List;

public interface PerformanceShowingService<T> {

    void addPerformanceShowing(T performanceShowing);

    void updatePerformanceShowing(T performanceShowing, int id);

    void removePerformanceShowing(int id);

    T getPerformanceShowing(int id);

    List<T> getListPerformanceShowings();

    List<EventInfo> getFullEventsInfo();

    EventInfo getEventInfoByIdFromEventsInfoList(int id);
}
