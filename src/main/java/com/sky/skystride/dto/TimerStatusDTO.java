package com.sky.skystride.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimerStatusDTO implements Serializable {
    private LocalDateTime timerStartTime;
    private boolean currentTimerRunning;
    private int startedSport;
}
