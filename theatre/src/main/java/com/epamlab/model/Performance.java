package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Performance {
    private String name;
    private String description;
    private String slogan;
    private int durationMinutes;
    private int id;
}
