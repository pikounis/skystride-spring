package com.sky.skystride.services;

import com.sky.skystride.entities.*;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.*;

@Service
public class ActivityService {
    private ActivityRepo activityRepo;
    private SportRepo sportRepo;
    private SkyUserRepo skyUserRepo;

    public ActivityService(ActivityRepo activityRepo, SportRepo sportRepo, SkyUserRepo skyUserRepo) {
        this.activityRepo = activityRepo;
        this.sportRepo = sportRepo;
        this.skyUserRepo = skyUserRepo;
    }

    private void calculatePointsEarned(Activity activity) {
        activity.setPointsEarned(
                (int) Math.round(
                        Duration.between(activity.getStartTime(), activity.getEndTime())
                                .toMinutes() * activity.getSport().getPpm()));
    }

    public List<Activity> getAllUserActivity(int skyUserId) {
        return this.activityRepo.findBySkyUserId(skyUserId);
    }

    public Activity addUserActivity(Activity activity) {
//        calculate points
        activity.setSkyUser(this.skyUserRepo.findById(activity.getSkyUser().getId()).get());
        activity.setSport(this.sportRepo.findById(activity.getSport().getId()).get());

        System.out.println(activity);
        calculatePointsEarned(activity);

        SkyUser user = activity.getSkyUser();
        user.setPoints(user.getPoints() + activity.getPointsEarned());

        return this.activityRepo.save(activity);
    }


    public Activity updateActivity(int activityId, Sport sport, LocalDateTime startTime, LocalDateTime endTime) {
//        Get existing activity and update time done
        Optional<Activity> existingActivity = this.activityRepo.findById(activityId);
        Activity existing = existingActivity.get();

        existing.setSport(sport);
        existing.setStartTime(startTime);
        existing.setEndTime(endTime);
        calculatePointsEarned(existing);
        return existing; // create update
    }

    public boolean deleteActivity(int activityId) {
        this.activityRepo.deleteById(activityId);
        return !this.activityRepo.existsById(activityId);
    }

    public List<Map<String, Object>> getPointsHistoryForLast5Days(int skyUserId, LocalDateTime date) {
        List<Map<String, Object>> pointsHistory = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LocalDateTime startDate = date.minusDays(i).withHour(0).withMinute(0).withSecond(0);
            LocalDateTime endDate = startDate.withHour(23).withMinute(59).withSecond(59);
            Integer pointsEarned = this.activityRepo.getPointsEarnedBetweenDates(skyUserId, startDate, endDate);

            // Prepare a map with date and points
            Map<String, Object> entry = new HashMap<>();
            entry.put("date", startDate.toLocalDate());
            entry.put("points", pointsEarned != null ? pointsEarned : 0);  // Handle null values

            pointsHistory.add(entry);
        }

        return pointsHistory;
    }

    public List<Map<String, Object>> getWorkoutHoursHistoryForLast5Days(int skyUserId, LocalDateTime date) {
        List<Map<String, Object>> workoutHistory = new ArrayList<>();

        for (int i = 0; i < 5; i++) {
            LocalDateTime startDate = date.minusDays(i).withHour(0).withMinute(0).withSecond(0);
            LocalDateTime endDate = startDate.withHour(23).withMinute(59).withSecond(59);
            Integer minutesWorkedOut = this.activityRepo.getMinutesWorkedOutBetweenDates(skyUserId, startDate, endDate);

            // Prepare a map with date and hours worked out
            Map<String, Object> entry = new HashMap<>();
            entry.put("date", startDate.toLocalDate());
            entry.put("hours", minutesWorkedOut != null ? minutesWorkedOut / 60 : 0);  // Convert minutes to hours

            workoutHistory.add(entry);
        }

        return workoutHistory;
    }
}
