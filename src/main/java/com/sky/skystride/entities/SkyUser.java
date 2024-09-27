package com.sky.skystride.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SkyUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 5)
    @Email
    private String email;

    @Size(min = 2, max = 30)
    @NotNull
    private String firstName;

    @Size(min = 2, max = 30)
    @NotNull
    private String lastName;

    @NotNull
    private int points;

    @NotNull
    private boolean currentTimerRunning;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date timerStartTime;

    private String startedSport;

    // Many-to-Many relationship with Team, mapped by "team_members"
    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY) // SkyUser is already mapped by Team's "members"
    private List<Team> teams = new ArrayList<>();

    public SkyUser(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentTimerRunning = false;
        this.points = 0;
    }
}
