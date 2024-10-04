package com.sky.skystride.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class TeamWithAveragePointsDTO implements Serializable {
    private int id;
    private String name;
    private String description;
    private String imageURL;
    private double averagePoints;

    // Constructor
    public TeamWithAveragePointsDTO(int id, String name, String description, String imageURL, double averagePoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.imageURL = imageURL;
        this.averagePoints = averagePoints;
    }
}
