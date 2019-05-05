package com.epamlab.service.interfaces;

import java.util.List;

public interface TheatreService<T> {

    void addTheatre(T theatre);

    void updateTheatre(T theatre, int id);

    void removeTheatre(int id);

    T getTheatre(int id);

    List<T> getListTheatres();
}
