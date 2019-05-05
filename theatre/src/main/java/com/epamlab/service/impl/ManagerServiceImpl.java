package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.ManagerDao;
import com.epamlab.model.Manager;
import com.epamlab.service.interfaces.ManagerService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService<Manager> {
    private final ManagerDao<Manager> dao;

    public ManagerServiceImpl(ManagerDao<Manager> dao) {
        this.dao = dao;
    }

    @Override
    public void addManager(Manager manager) {
        this.dao.add(manager);
    }

    @Override
    public void updateManager(Manager manager, int id) {
        this.dao.update(manager, id);
    }

    @Override
    public void removeManager(int id) {
        this.dao.remove(id);
    }

    @Override
    public Manager getManager(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<Manager> getListManagers() {
        return this.dao.getList();
    }
}
