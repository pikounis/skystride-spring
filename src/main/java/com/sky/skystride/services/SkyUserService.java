package com.sky.skystride.services;

import com.sky.skystride.dto.ProfileDTO;
import com.sky.skystride.entities.*;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
@Data
public class SkyUserService {
    private ModelMapper mapper;
    private final SkyUserRepo skyUserRepo;
    private final SportRepo sportRepo;
    private final ActivityService activityService;

    public SkyUserService(SkyUserRepo skyUserRepo, SportRepo sportRepo, ActivityService activityService, ModelMapper mapper) {
        super();
        this.mapper = mapper;
        this.skyUserRepo = skyUserRepo;
        this.sportRepo = sportRepo;
        this.activityService = activityService;
    }

    public SkyUser addSkyUser(SkyUser skyUser) {
        return this.skyUserRepo.save(skyUser);
    }

    public SkyUser getSkyUserById(int id) {
        return this.skyUserRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("SkyUser not found with id: " + id));
    }

    public SkyUser startTimer(int skyUserId, int sportId) {
        // Find the SkyUser and Sport
        SkyUser skyUser = getSkyUserById(skyUserId);
        Sport sport = this.sportRepo.findById(sportId)
                .orElseThrow(() -> new RuntimeException("Sport not found with id: " + sportId));

        // Ensure no active timer is already running
        if (skyUser.isCurrentTimerRunning()) {
            throw new RuntimeException("Timer is already running for this user.");
        }

        // Set the timer start and the sport
        skyUser.setCurrentTimerRunning(true);
        skyUser.setTimerStartTime(LocalDateTime.now());
        skyUser.setStartedSport(sport.getId());

        // Save the updated SkyUser
        return this.skyUserRepo.save(skyUser);
    }

    public SkyUser endTimer(int skyUserId) {
        // Find the SkyUser
        SkyUser skyUser = getSkyUserById(skyUserId);

        // Ensure a timer is running
        if (!skyUser.isCurrentTimerRunning()) {
            throw new RuntimeException("No active timer found for this user.");
        }

        // End the timer
        LocalDateTime startTime = skyUser.getTimerStartTime();
        LocalDateTime endTime = LocalDateTime.now();
        int sportId = skyUser.getStartedSport();

        Sport sport = this.sportRepo.findById(sportId)
                .orElseThrow(() -> new RuntimeException("Sport not found with id: " + sportId));

        // Reset the timer and leave sport field in SkyUser
        skyUser.setCurrentTimerRunning(false);
        skyUser.setTimerStartTime(null);
        skyUser.setStartedSport(0);

        // Save the updated SkyUser before creating the Activity
        this.skyUserRepo.save(skyUser);

        // Create an Activity and save it using the existing ActivityService
        Activity activity = new Activity(skyUser, sport, startTime, endTime);
        activityService.addUserActivity(activity); // This handles points calculation and saving

        // Return the updated SkyUser
        return skyUser;
    }

    public TimerStatusDTO getTimerStatus(int skyUserId) {
        SkyUser skyUser = getSkyUserById(skyUserId);

        TimerStatusDTO timerStatusDTO = new TimerStatusDTO();
        timerStatusDTO.setTimerStartTime(skyUser.getTimerStartTime());
        timerStatusDTO.setCurrentTimerRunning(skyUser.isCurrentTimerRunning());
        timerStatusDTO.setStartedSport(skyUser.getStartedSport());

        return timerStatusDTO;
    }

    public List<SkyUser> getAllSkyUsers() {
        return this.skyUserRepo.findAll();
    }

    public boolean getLoginCredentials(String email, String password) {
        SkyUser user = this.skyUserRepo.findByEmail(email);
        return user.getUserPassword().equals(password);
    }

    public ProfileDTO getProfileByEmail(String email) {
        SkyUser savedUser = this.skyUserRepo.findByEmail(email);
        return this.mapper.map(savedUser, ProfileDTO.class);
    }
}
