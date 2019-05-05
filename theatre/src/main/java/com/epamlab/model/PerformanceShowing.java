package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PerformanceShowing {
    private Date date;
    private boolean isPremiere;
    private int idPerformance;
    private int idTheatre;
    private int id;
    private int tickets;
    private int price;
}
