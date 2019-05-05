package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.UserDao;
import com.epamlab.model.User;
import com.epamlab.service.interfaces.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService<User> {
    private final UserDao<User> dao;

    public UserServiceImpl(UserDao<User> dao) {
        this.dao = dao;
    }

    @Override
    public void addUser(User user) {
        dao.add(user);
    }

    @Override
    public void updateUser(User user, int id) {
        dao.update(user, id);
    }

    @Override
    public void removeUser(int id) {
        dao.remove(id);
    }

    @Override
    public User getUser(String login) {
        return dao.get(login);
    }

    @Override
    public List<User> getListUsers() {
        return dao.getList();
    }

    @Override
    public User getCurrentUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return getUser(auth.getName());
    }
}
