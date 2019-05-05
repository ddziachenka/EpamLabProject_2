package com.epamlab.service.interfaces;

import com.epamlab.model.User;

import java.util.List;

public interface UserService<T> {

    void addUser(T user);

    void updateUser(T user, int id);

    void removeUser(int id);

    T getUser(String login);

    List<T> getListUsers();

    User getCurrentUser();
}
