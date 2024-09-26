package com.sky.skystride.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
public class SkyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @NotNull
    private boolean currentTimerRunning;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timerStartTime;

    private String startedSport;

    public SkyUser(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.currentTimerRunning = false;
    }
}
