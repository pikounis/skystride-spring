package com.sky.skystride.entities;

import jakarta.persistence.*;
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

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "sport_id")
    private Sport sport;

    public Achievement(String name, String img, int pointsNeeded, Sport sport) {
        super();
        this.name = name;
        this.img = img;
        this.pointsNeeded = pointsNeeded;
    }
}
