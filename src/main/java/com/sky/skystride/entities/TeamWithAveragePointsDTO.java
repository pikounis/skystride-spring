package com.sky.skystride.entities;

import lombok.Data;

@Data
public class TeamWithAveragePointsDTO {
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
