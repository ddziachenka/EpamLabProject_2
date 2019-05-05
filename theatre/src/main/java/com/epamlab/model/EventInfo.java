package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class EventInfo {
    private int idPerformanceShowing;
    private String namePerformance;
    private String nameTheatre;
    private Date date;
    private boolean isPremiere;
    private String slogan;
    private String description;
    private int duration;
    private int tickets;
    private int price;
}
