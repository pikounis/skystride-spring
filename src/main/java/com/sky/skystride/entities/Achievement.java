package com.sky.skystride.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class Achievement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    private String name;

    @NotNull
    private String img;

    @NotNull
    private int pointsNeeded;

    public Achievement(String name, String img, int pointsNeeded) {
        super();
        this.name = name;
        this.img = img;
        this.pointsNeeded = pointsNeeded;
    }
}
