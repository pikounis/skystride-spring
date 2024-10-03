package com.sky.skystride.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimerStatusDTO {
    private LocalDateTime timerStartTime;
    private boolean currentTimerRunning;
    private int startedSport;
}
