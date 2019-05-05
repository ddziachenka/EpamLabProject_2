package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.OrderDao;
import com.epamlab.model.Order;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class OrderDaoImpl implements OrderDao<Order> {
    private static final String ID = "id";
    private static final String USER_ID = "user_id";
    private static final String PERFORMANCE_SHOWING_ID = "performance_showing_id";
    private static final String COUNT_TICKETS = "count_tickets";
    private static final String IS_PAY = "is_pay";
    private static final String GET_ORDER_BY_ID = "SELECT id, user_id, performance_showing_id, count_tickets, is_pay FROM \"ORDER\" WHERE id = ?";
    private static final String GET_ORDERS_BY_ID_USER = "SELECT id, user_id, performance_showing_id, count_tickets, is_pay FROM \"ORDER\" where user_id = ?";
    private static final String GET_ALL_ORDERS = "SELECT id, user_id, performance_showing_id, count_tickets, is_pay FROM \"ORDER\"";
    private static final String REMOVE_ORDER = "DELETE FROM \"ORDER\" WHERE id = ?";
    private static final String ADD_ORDER = "insert into \"ORDER\" (user_id, performance_showing_id, count_tickets, is_pay) VALUES (?, ?, ?, ?)";
    private static final String UPDATE_ORDER = "UPDATE \"ORDER\" SET user_id = ?, performance_showing_id = ?, count_tickets = ?, is_pay = ? WHERE id = ?";
    private final JdbcTemplate jdbcTemplate;

    public OrderDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Order order) {
        jdbcTemplate.update(ADD_ORDER, order.getIdUser(), order.getIdPerformanceShowing(), order.getCountTickets(), order.isPay());
    }

    @Override
    public void update(Order order, int id) {
        jdbcTemplate.update(UPDATE_ORDER, order.getIdUser(), order.getIdPerformanceShowing(), order.getCountTickets(), order.isPay(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_ORDER, id);
    }

    @Override
    public Order get(int id) {
        return jdbcTemplate.queryForObject(GET_ORDER_BY_ID, new Object[]{id}, this::getOrderFromRs);
    }

    @Override
    public List<Order> getByIdUser(int id) {
        return jdbcTemplate.query(GET_ORDERS_BY_ID_USER, new Object[]{id}, this::getOrderFromRs);
    }

    @Override
    public List<Order> getListObjects() {
        return jdbcTemplate.query(GET_ALL_ORDERS, this::getOrderFromRs);
    }

    private Order getOrderFromRs(ResultSet rs, int i) throws SQLException {
        return Order.builder()
                .id(rs.getInt(ID))
                .idUser(rs.getInt(USER_ID))
                .idPerformanceShowing(rs.getInt(PERFORMANCE_SHOWING_ID))
                .countTickets(rs.getInt(COUNT_TICKETS))
                .isPay(rs.getBoolean(IS_PAY))
                .build();
    }
}
