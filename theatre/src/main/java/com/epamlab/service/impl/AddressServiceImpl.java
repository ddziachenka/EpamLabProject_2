package com.epamlab.service.impl;

import com.epamlab.dao.interfaces.AddressDao;
import com.epamlab.model.Address;
import com.epamlab.service.interfaces.AddressService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService<Address> {
    private final AddressDao<Address> dao;

    public AddressServiceImpl(AddressDao<Address> dao) {
        this.dao = dao;
    }

    @Override
    public void addAddress(Address address) {
        this.dao.add(address);
    }

    @Override
    public void updateAddress(Address address, int id) {
        this.dao.update(address, id);
    }

    @Override
    public void removeAddress(int id) {
        this.dao.remove(id);
    }

    @Override
    public Address getAddress(int id) {
        return this.dao.get(id);
    }

    @Override
    public List<Address> getListAddresses() {
        return this.dao.getList();
    }
}