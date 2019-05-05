package com.epamlab.config;

import com.epamlab.dao.impl.*;
import com.epamlab.dao.interfaces.*;
import com.epamlab.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.jdbc.core.JdbcTemplate;

@Configuration
@ComponentScan("com.epamlab")
public class CoreConfig {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Bean
    public AddressDao<Address> getAddressDao() {
        return new AddressDaoImpl(jdbcTemplate);
    }

    @Bean
    public ManagerDao<Manager> getManagerDao() {
        return new ManagerDaoImpl(jdbcTemplate);
    }

    @Bean
    public PerformanceDao<Performance> getPerformanceDao() {
        return new PerformanceDaoImpl(jdbcTemplate);
    }

    @Bean
    public PerformanceShowingDao<PerformanceShowing> getPerformanceShowingDao() {
        return new PerformanceShowingDaoImpl(jdbcTemplate);
    }

    @Bean
    public TheatreDao<Theatre> getTheatreDaoImpl() {
        return new TheatreDaoImpl(jdbcTemplate);
    }

    @Bean
    public UserDao<User> getUserDao() {
        return new UserDaoImpl(jdbcTemplate);
    }

    @Bean
    public OrderDao<Order> getOrderDao() {
        return new OrderDaoImpl(jdbcTemplate);
    }

    @Bean(name = "order")
    @Scope("prototype")
    public Order getOrder() {
        return new Order();
    }
}
