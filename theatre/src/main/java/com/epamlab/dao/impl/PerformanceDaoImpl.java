package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.PerformanceDao;
import com.epamlab.model.Performance;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PerformanceDaoImpl implements PerformanceDao<Performance> {
    private static final String NAME = "name";
    private static final String PERFORMANCE_ID = "perfomance_id";
    private static final String DESCRIPTION = "description";
    private static final String SLOGAN = "slogan";
    private static final String DURATION_MINUTES = "duration_minutes";
    private static final String GET_PERFORMANCE = "SELECT perfomance_id, NAME_PERF, description, slogan, duration_minutes FROM perfomance WHERE perfomance_id = ?";
    private static final String GET_ALL_PERFORMANCES = "SELECT perfomance_id, NAME_PERF, description, slogan, duration_minutes FROM perfomance";
    private static final String REMOVE_PERFORMANCE = "DELETE FROM perfomance WHERE perfomance_id = ?";
    private static final String ADD_PERFORMANCE = "INSERT INTO perfomance (NAME_PERF, description, slogan, duration_minutes) VALUES (?,?,?,?)";
    private static final String UPDATE_PERFORMANCE = "UPDATE perfomance SET NAME_PERF = ?, description = ?, slogan = ?, duration_minutes = ? WHERE perfomance_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public PerformanceDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Performance performance) {
        jdbcTemplate.update(ADD_PERFORMANCE, performance.getName(), performance.getDescription(),
                performance.getSlogan(), performance.getDurationMinutes());
    }

    @Override
    public void update(Performance performance, int id) {
        jdbcTemplate.update(UPDATE_PERFORMANCE, performance.getName(), performance.getDescription(),
                performance.getSlogan(), performance.getDurationMinutes(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_PERFORMANCE, id);
    }

    @Override
    public Performance get(int id) {
        return jdbcTemplate.queryForObject(GET_PERFORMANCE, new Object[]{id}, this::getPerformanceFromRs);
    }

    @Override
    public List<Performance> getList() {
        return jdbcTemplate.query(GET_ALL_PERFORMANCES, this::getPerformanceFromRs);
    }

    private Performance getPerformanceFromRs(ResultSet rs, int i) throws SQLException {
        return Performance.builder()
                .id(rs.getInt(PERFORMANCE_ID))
                .name(rs.getString(NAME))
                .description(rs.getString(DESCRIPTION))
                .slogan(rs.getString(SLOGAN))
                .durationMinutes(rs.getInt(DURATION_MINUTES))
                .build();
    }
}

