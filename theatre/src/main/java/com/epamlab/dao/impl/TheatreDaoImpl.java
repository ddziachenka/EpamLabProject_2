package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.TheatreDao;
import com.epamlab.model.Theatre;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class TheatreDaoImpl implements TheatreDao<Theatre> {
    private static final String THEATRE_ID = "theatre_id";
    private static final String QUANTITY_SEATS = "quantity_seats";
    private static final String ADDRESS_ID = "address_id";
    private static final String MANAGER_ID = "manager_id";
    private static final String NAME = "name";
    private static final String GET_THEATRE = "SELECT theatre_id, name, quantity_seats, manager_id, address_id FROM theatres WHERE theatre_id = ?";
    private static final String GET_ALL_THEATRES = "SELECT theatre_id, name, quantity_seats, manager_id, address_id FROM theatres";
    private static final String REMOVE_THEATRE = "DELETE FROM theatres WHERE theatre_id = ?";
    private static final String ADD_THEATRE = "INSERT INTO theatres (name, quantity_seats, manager_id, address_id) VALUES (?,?,?,?)";
    private static final String UPDATE_THEATRE = "UPDATE theatres SET name = ?, quantity_seats = ?, manager_id = ?, address_id = ? WHERE theatre_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public TheatreDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Theatre theatre) {
        jdbcTemplate.update(ADD_THEATRE, theatre.getName(), theatre.getQuantitySeats(),
                theatre.getIdManager(), theatre.getIdAddress());
    }

    @Override
    public void update(Theatre theatre, int id) {
        jdbcTemplate.update(UPDATE_THEATRE, theatre.getName(), theatre.getQuantitySeats(),
                theatre.getIdManager(), theatre.getIdAddress(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_THEATRE, id);
    }

    @Override
    public Theatre get(int id) {
        return jdbcTemplate.queryForObject(GET_THEATRE, new Object[]{id}, this::getPerformanceFromRs);
    }

    @Override
    public List<Theatre> getList() {
        return jdbcTemplate.query(GET_ALL_THEATRES, this::getPerformanceFromRs);
    }

    private Theatre getPerformanceFromRs(ResultSet rs, int i) throws SQLException {
        return Theatre.builder()
                .id(rs.getInt(THEATRE_ID))
                .name(rs.getString(NAME))
                .quantitySeats(rs.getInt(QUANTITY_SEATS))
                .idManager(rs.getInt(MANAGER_ID))
                .idAddress(rs.getInt(ADDRESS_ID))
                .build();
    }
}

