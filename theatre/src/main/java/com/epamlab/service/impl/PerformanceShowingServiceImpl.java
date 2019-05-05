package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.PerformanceShowingDao;
import com.epamlab.model.EventInfo;
import com.epamlab.model.PerformanceShowing;
import com.epamlab.service.interfaces.PerformanceShowingService;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;

@Service
public class PerformanceShowingServiceImpl implements PerformanceShowingService<PerformanceShowing> {
    private final PerformanceShowingDao<PerformanceShowing> dao;

    public PerformanceShowingServiceImpl(PerformanceShowingDao<PerformanceShowing> dao) {
        this.dao = dao;
    }

    @Override
    public void addPerformanceShowing(PerformanceShowing performanceShowing) {
        this.dao.add(performanceShowing);
    }

    @Override
    public void updatePerformanceShowing(PerformanceShowing performanceShowing, int id) {
        this.dao.update(performanceShowing, id);
    }

    @Override
    public void removePerformanceShowing(int id) {
        this.dao.remove(id);
    }

    @Override
    public PerformanceShowing getPerformanceShowing(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<PerformanceShowing> getListPerformanceShowings() {
        return this.dao.getList();
    }

    @Override
    public List<EventInfo> getFullEventsInfo() {
        List<EventInfo> eventsInfo = this.dao.getFullEventsInfo();
        eventsInfo.sort(Comparator.comparingInt(EventInfo::getIdPerformanceShowing));
        return eventsInfo;
    }

    @Override
    public EventInfo getEventInfoByIdFromEventsInfoList(int id) {
        List<EventInfo> eventInfoList = getFullEventsInfo();
        EventInfo event = null;
        for (EventInfo e : eventInfoList) {
            if (e.getIdPerformanceShowing() == id) {
                event = e;
                break;
            }
        }
        return event;
    }
}
