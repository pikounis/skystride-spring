package com.sky.skystride.services;

import com.sky.skystride.entities.Achievement;
import com.sky.skystride.entities.AchievementRepo;
import com.sky.skystride.entities.Activity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AchievementService {
    private AchievementRepo achievementRepo;

    public AchievementService(AchievementRepo achievementRepo) {
        this.achievementRepo = achievementRepo;
    }

    public List<Achievement> findTop3ClosestAchievements(int skyUserId) {
        Pageable top3 = PageRequest.of(0, 3);
        return this.achievementRepo.getClosestUserAchievement(skyUserId, top3);
    }

    public List<Achievement> getAllUserAchievements(int skyUserId) {
        return this.achievementRepo.getAllUserAchievement(skyUserId);
    }

    public Achievement getMostRecentUserAchievement(int skyUserId) {
        List<Achievement> achievements = this.achievementRepo.getAllUserAchievement(skyUserId);
        Collections.sort(achievements, Comparator.comparingInt(Achievement::getPointsNeeded).reversed());
        return achievements.get(0);
    }

    public List<Achievement> getAllAchievements() {
        return this.achievementRepo.findAll();
    }

    public Achievement addAchievement(Achievement achievement) {
        return this.achievementRepo.save(achievement);
    }

    public Achievement updateAchievement(Achievement achievement) {
        Optional<Achievement> existingAchievement = this.achievementRepo.findById(achievement.getId());
        Achievement existing = existingAchievement.get();

        existing.setName(achievement.getName());
        existing.setImg(achievement.getImg());
        existing.setPointsNeeded(achievement.getPointsNeeded());

        return existing;
    }

    public boolean deleteAchievement(int id){
        this.achievementRepo.deleteById(id);
        return !this.achievementRepo.existsById(id);
    }

    public List<Map<String, Object>> findTop3ClosestAchievementsWithPointsDiff(int skyUserId) {
        Pageable top3 = PageRequest.of(0, 3);
        List<Object[]> achievementWithDiffs = this.achievementRepo.findClosestAchievementsWithPointsDifference(skyUserId, top3);

        // Create a list of achievements with the points difference
        return achievementWithDiffs.stream()
                .map(result -> {
                    Map<String, Object> achievementData = new HashMap<>();
                    achievementData.put("achievement", (Achievement) result[0]);
                    achievementData.put("pointsDifference", (Integer) result[1]);
                    return achievementData;
                })
                .collect(Collectors.toList());
    }
}
