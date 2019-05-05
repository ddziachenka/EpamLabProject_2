package com.epamlab.service.interfaces;

import java.util.List;

public interface ManagerService<T> {

    void addManager(T manager);

    void updateManager(T manager, int id);

    void removeManager(int id);

    T getManager(int id);

    List<T> getListManagers();
}
