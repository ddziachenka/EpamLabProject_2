package com.epamlab.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
    private String name;
    private String surname;
    private String mobile;
    private int age;
    private boolean isHigherEducation;
    private int id;
}
