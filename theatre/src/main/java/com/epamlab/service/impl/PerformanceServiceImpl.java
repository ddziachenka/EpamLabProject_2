package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.PerformanceDao;
import com.epamlab.model.Performance;
import com.epamlab.service.interfaces.PerformanceService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PerformanceServiceImpl implements PerformanceService<Performance> {
    private final PerformanceDao<Performance> dao;

    public PerformanceServiceImpl(PerformanceDao<Performance> dao) {
        this.dao = dao;
    }

    @Override
    public void addPerformance(Performance performance) {
        this.dao.add(performance);
    }

    @Override
    public void updatePerformance(Performance performance, int id) {
        this.dao.update(performance, id);
    }

    @Override
    public void removePerformance(int id) {
        this.dao.remove(id);
    }

    @Override
    public Performance getPerformance(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<Performance> getListPerformances() {
        return this.dao.getList();
    }
}
