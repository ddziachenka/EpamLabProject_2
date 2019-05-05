package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.UserDao;
import com.epamlab.model.User;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class UserDaoImpl implements UserDao<User> {
    private static final String GET_USER = "SELECT id, name, surname, login, password FROM user WHERE login = ?";
    private static final String GET_ALL_USERS = "SELECT id, name, surname, login, password FROM user";
    private static final String REMOVE_USER = "DELETE FROM user WHERE id = ?";
    private static final String ADD_USER = "INSERT INTO user (name, surname, login, password) VALUES (?,?,?,?)";
    private static final String UPDATE_USER = "UPDATE user SET name = ?, surname = ?, login = ?, password = ? WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    public UserDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(User user) {
        jdbcTemplate.update(ADD_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword());
    }

    @Override
    public void update(User user, int id) {
        jdbcTemplate.update(UPDATE_USER, user.getName(), user.getSurname(), user.getLogin(), user.getPassword(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_USER, id);
    }

    @Override
    public User get(String login) {
        return jdbcTemplate.queryForObject(GET_USER, new Object[]{login}, new BeanPropertyRowMapper<>(User.class));
    }

    @Override
    public List<User> getList() {
        return jdbcTemplate.query(GET_ALL_USERS, new BeanPropertyRowMapper<>(User.class));
    }
}
