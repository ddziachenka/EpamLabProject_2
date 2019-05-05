package com.epamlab.service.interfaces;

import java.util.List;

public interface AddressService<T> {

    void addAddress(T address);

    void updateAddress(T address, int id);

    void removeAddress(int id);

    T getAddress(int id);

    List<T> getListAddresses();
}




