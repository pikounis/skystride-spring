package com.sky.skystride.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sky.skystride.utils.Office;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.bind.DefaultValue;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
public class SkyUser implements java.io.Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 5)
    @Email
    @Column(unique = true)
    private String email;

    @NotNull
    @Size(min = 8)
    private String userPassword;

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

    @NotNull
    private Office office;

    // Many-to-Many relationship with Team, mapped by "team_members"
//    @ManyToMany(mappedBy = "members", fetch = FetchType.LAZY) // SkyUser is already mapped by Team's "members"
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JsonIgnore
//    @JoinTable(
//            name = "team_members", // Name of the join table
//            joinColumns = @JoinColumn(name = "skyuser_id"),
//            inverseJoinColumns = @JoinColumn(name = "team_id")
//    )
//    private List<Team> teams = new ArrayList<>();
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "team_members", // Name of the join table
            joinColumns = @JoinColumn(name = "skyuser_id"),
            inverseJoinColumns = @JoinColumn(name = "team_id")
    )
    private List<Team> teams = new ArrayList<>();

    public SkyUser(String firstName, String lastName, String email, Office office, String userPassword) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.currentTimerRunning = false;
        this.points = 0;
        this.office = office;
        this.userPassword = userPassword;
    }
}
