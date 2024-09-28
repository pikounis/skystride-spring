package com.sky.skystride.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 2, max = 20)
    @NotNull
    private String name;

    @CreatedDate
    private LocalDateTime dateCreated;

    private String imageURL;

    @Size(min = 2, max = 110)
    @NotNull
    private String description;

    // Many-to-Many relationship with SkyUser, mapped by the join table "team_members"
    @ManyToMany(fetch = FetchType.LAZY)
    @JsonIgnore
    @JoinTable(
            name = "team_members", // Name of the join table
            joinColumns = @JoinColumn(name = "team_id"), // Column representing Team in the join table
            inverseJoinColumns = @JoinColumn(name = "skyuser_id") // Column representing SkyUser in the join table
    )
    private List<SkyUser> members = new ArrayList<>();

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "created_by_id")
    private SkyUser createdBy;

    public Team(String name, String imageURL, String description) {
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.description = description;
    }
}