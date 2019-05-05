package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.ManagerDao;
import com.epamlab.model.Manager;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class ManagerDaoImpl implements ManagerDao<Manager> {
    private static final String MANAGER_ID = "manager_id";
    private static final String NAME = "name";
    private static final String SURNAME = "surname";
    private static final String MOBILE = "mobile";
    private static final String AGE = "age";
    private static final String HAS_HIGHER_EDUCATION = "has_higher_education";
    private static final String GET_MANAGER = "SELECT manager_id, name, surname, mobile, age, has_higher_education FROM manager WHERE manager_id = ?";
    private static final String GET_ALL_MANAGERS = "SELECT manager_id, name, surname, mobile, age, has_higher_education FROM manager";
    private static final String REMOVE_MANAGER = "DELETE FROM manager WHERE manager_id = ?";
    private static final String ADD_MANAGER = "INSERT INTO manager (name, surname, mobile, age, has_higher_education) VALUES (?,?,?,?,?)";
    private static final String UPDATE_MANAGER = "UPDATE manager SET name = ?, surname = ?, mobile = ?, age = ?, has_higher_education = ? WHERE manager_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public ManagerDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Manager manager) {
        jdbcTemplate.update(ADD_MANAGER, manager.getName(), manager.getSurname(), manager.getMobile(),
                manager.getAge(), manager.isHigherEducation());
    }

    @Override
    public void update(Manager manager, int id) {
        jdbcTemplate.update(UPDATE_MANAGER, manager.getName(), manager.getSurname(), manager.getMobile(),
                manager.getAge(), manager.isHigherEducation(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_MANAGER, id);
    }

    @Override
    public Manager get(int id) {
        return jdbcTemplate.queryForObject(GET_MANAGER, new Object[]{id}, this::getManagerFromRs);

    }

    @Override
    public List<Manager> getList() {
        return jdbcTemplate.query(GET_ALL_MANAGERS, this::getManagerFromRs);
    }

    private Manager getManagerFromRs(ResultSet rs, int i) throws SQLException {
        return Manager.builder()
                .id(rs.getInt(MANAGER_ID))
                .name(rs.getString(NAME))
                .surname(rs.getString(SURNAME))
                .mobile(rs.getString(MOBILE))
                .age(rs.getInt(AGE))
                .isHigherEducation(rs.getBoolean(HAS_HIGHER_EDUCATION))
                .build();
    }
}
