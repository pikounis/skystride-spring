package com.sky.skystride.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "sky_user_id")
    private SkyUser skyUser;

    @NotNull
    @ManyToOne()
    @JoinColumn(name = "sport_id")
    private Sport sport;

    @NotNull
    private LocalDateTime startTime;

    @NotNull
    private LocalDateTime endTime;

    @NotNull
    private int pointsEarned;

    public Activity(SkyUser skyUser, Sport sport, LocalDateTime startTime, LocalDateTime endTime) {
        super();
        this.skyUser = skyUser;
        this.sport = sport;
        this.startTime = startTime;
        this.endTime = endTime;
    }
}
