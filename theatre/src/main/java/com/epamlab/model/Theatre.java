package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Theatre {
    private String name;
    private int quantitySeats;
    private int idManager;
    private int idAddress;
    private int id;
}
