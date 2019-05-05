package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.TheatreDao;
import com.epamlab.model.Theatre;
import com.epamlab.service.interfaces.TheatreService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TheatreServiceImpl implements TheatreService<Theatre> {
    private final TheatreDao<Theatre> dao;

    public TheatreServiceImpl(TheatreDao<Theatre> dao) {
        this.dao = dao;
    }

    @Override
    public void addTheatre(Theatre theatre) {
        this.dao.add(theatre);
    }

    @Override
    public void updateTheatre(Theatre theatre, int id) {
        this.dao.update(theatre, id);
    }

    @Override
    public void removeTheatre(int id) {
        this.dao.remove(id);
    }

    @Override
    public Theatre getTheatre(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<Theatre> getListTheatres() {
        return this.dao.getList();
    }
}
