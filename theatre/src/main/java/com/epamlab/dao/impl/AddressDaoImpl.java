package com.epamlab.dao.impl;

import com.epamlab.dao.interfaces.AddressDao;
import com.epamlab.model.Address;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class AddressDaoImpl implements AddressDao<Address> {
    private static final String COUNTRY = "country";
    private static final String CITY = "city";
    private static final String STREET = "street";
    private static final String NUMBER_HOUSE = "number_house";
    private static final String ZIPCODE = "zipcode";
    private static final String ADDRESS_ID = "address_id";
    private static final String GET_ADDRESS = "SELECT address_id, country, city, street, number_house, zipcode FROM address WHERE address_id = ?";
    private static final String GET_ALL_ADDRESSES = "SELECT address_id, country, city, street, number_house, zipcode FROM address";
    private static final String REMOVE_ADDRESS = "DELETE FROM address WHERE address_id = ?";
    private static final String ADD_ADDRESS = "INSERT INTO address (country, city, street, number_house, zipcode) VALUES (?,?,?,?,?)";
    private static final String UPDATE_ADDRESS = "UPDATE address SET country = ?, city = ?, street = ?, number_house = ?, zipcode = ? WHERE address_id = ?";
    private final JdbcTemplate jdbcTemplate;

    public AddressDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Address address) {
        jdbcTemplate.update(ADD_ADDRESS, address.getCountry(), address.getCity(), address.getStreet(),
                address.getNumberHouse(), address.getZipCode());
    }

    @Override
    public void update(Address address, int id) {
        jdbcTemplate.update(UPDATE_ADDRESS, address.getCountry(), address.getCity(), address.getStreet(),
                address.getNumberHouse(), address.getZipCode(), id);
    }

    @Override
    public void remove(int id) {
        jdbcTemplate.update(REMOVE_ADDRESS, id);
    }

    @Override
    public Address get(int id) {
        return jdbcTemplate.queryForObject(GET_ADDRESS, new Object[]{id}, this::getAddressFromRs);
    }

    @Override
    public List<Address> getList() {
        return jdbcTemplate.query(GET_ALL_ADDRESSES, this::getAddressFromRs);
    }

    private Address getAddressFromRs(ResultSet rs, int i) throws SQLException {
        return Address.builder()
                .id(rs.getInt(ADDRESS_ID))
                .country(rs.getString(COUNTRY))
                .city(rs.getString(CITY))
                .street(rs.getString(STREET))
                .numberHouse(rs.getInt(NUMBER_HOUSE))
                .zipCode(rs.getInt(ZIPCODE))
                .build();
    }
}
