package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private int id;
    private int idUser;
    private int idPerformanceShowing;
    private int countTickets;
    private boolean isPay;
    private String name;
    private int price;
}
