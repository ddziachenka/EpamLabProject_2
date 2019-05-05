package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.PerformanceShowingDao;
import com.epamlab.model.EventInfo;
import com.epamlab.model.PerformanceShowing;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class PerformanceShowingDaoImpl implements PerformanceShowingDao<PerformanceShowing> {
    private static final String PERFORMANCE_ID = "perfomance_id";
    private static final String DESCRIPTION = "description";
    private static final String SLOGAN = "slogan";
    private static final String DURATION_MINUTES = "duration_minutes";
    private static final String PERFORMANCE_SHOWINGS_ID = "perfomance_showings_id";
    private static final String DATE = "date";
    private static final String IS_PREMIERE = "is_premiere";
    private static final String THEATRE_ID = "theatre_id";
    private static final String TICKETS = "tickets";
    private static final String PRICE = "price";
    private static final String NAME_PERF = "NAME_PERF";
    private static final String NAME_H = "NAME";
    private static final String GET_PERFORMANCE_SHOWING = "SELECT perfomance_showings_id, date, is_premiere, perfomance_id, theatre_id, tickets, price FROM perfomance_showings WHERE perfomance_showings_id = ?";
    private static final String GET_ALL_PERFORMANCE_SHOWINGS = "SELECT perfomance_showings_id, date, is_premiere, perfomance_id, theatre_id, tickets, price FROM perfomance_showings";
    private static final String REMOVE_PERFORMANCE_SHOWING = "DELETE FROM perfomance_showings WHERE perfomance_showings_id = ?";
    private static final String ADD_PERFORMANCE_SHOWING = "INSERT INTO perfomance_showings (date, is_premiere, perfomance_id, theatre_id, tickets, price) VALUES (?,?,?,?,?,?)";
    private static final String UPDATE_PERFORMANCE_SHOWING = "UPDATE perfomance_showings SET date = ?, is_premiere = ?, perfomance_id = ?, theatre_id = ?, tickets = ?, price = ? WHERE perfomance_showings_id = ?";
    private static final String GET_FULL_INFO_EVENTS = "SELECT ps.perfomance_showings_id, ps.date, ps.is_premiere, p.slogan, p.NAME_PERF,t.NAME, p.description, p.duration_minutes, ps.tickets, ps.price FROM perfomance_showings as ps INNER JOIN perfomance as p ON ps.perfomance_id = p.perfomance_id INNER JOIN theatres as t ON ps.theatre_id = t.theatre_id";
    private final JdbcTemplate jdbcTemplate;

    public PerformanceShowingDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(PerformanceShowing performanceShowing) {
        jdbcTemplate.update(ADD_PERFORMANCE_SHOWING, performanceShowing.getDate(), performanceShowing.isPremiere(),
                performanceShowing.getIdPerformance(), performanceShowing.getIdTheatre(), performanceShowing.getTickets(), performanceShowing.getPrice());
    }

    @Override
    public void update(PerformanceShowing performanceShowing, int id) {
        jdbcTemplate.update(UPDATE_PERFORMANCE_SHOWING, performanceShowing.getDate(), performanceShowing.isPremiere(),
                performanceShowing.getIdPerformance(), performanceShowing.getIdTheatre(), performanceShowing.getTickets(), performanceShowing.getPrice(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_PERFORMANCE_SHOWING, id);
    }

    @Override
    public PerformanceShowing get(int id) {
        return jdbcTemplate.queryForObject(GET_PERFORMANCE_SHOWING, new Object[]{id}, this::getPerformanceShowingFromRs);
    }

    @Override
    public List<PerformanceShowing> getList() {
        return jdbcTemplate.query(GET_ALL_PERFORMANCE_SHOWINGS, this::getPerformanceShowingFromRs);
    }

    @Override
    public List<EventInfo> getFullEventsInfo() {
        return jdbcTemplate.query(GET_FULL_INFO_EVENTS, this::getEventInfoFromRs);
    }

    private PerformanceShowing getPerformanceShowingFromRs(ResultSet rs, int i) throws SQLException {
        return PerformanceShowing.builder()
                .id(rs.getInt(PERFORMANCE_SHOWINGS_ID))
                .date(rs.getDate(DATE))
                .isPremiere(rs.getBoolean(IS_PREMIERE))
                .idPerformance(rs.getInt(PERFORMANCE_ID))
                .idTheatre(rs.getInt(THEATRE_ID))
                .tickets(rs.getInt(TICKETS))
                .price(rs.getInt(PRICE))
                .build();
    }

    private EventInfo getEventInfoFromRs(ResultSet rs, int i) throws SQLException {
        return EventInfo.builder()
                .idPerformanceShowing(rs.getInt(PERFORMANCE_SHOWINGS_ID))
                .date(rs.getDate(DATE))
                .isPremiere(rs.getBoolean(IS_PREMIERE))
                .slogan(rs.getString(SLOGAN))
                .namePerformance(rs.getString(NAME_PERF))
                .nameTheatre(rs.getString(NAME_H))
                .description(rs.getString(DESCRIPTION))
                .duration(rs.getInt(DURATION_MINUTES))
                .tickets(rs.getInt(TICKETS))
                .price(rs.getInt(PRICE))
                .build();
    }
}

