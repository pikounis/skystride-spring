package com.sky.skystride.services;

import com.sky.skystride.entities.*;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class AchievementService {
    private AchievementRepo achievementRepo;
    private SportRepo sportRepo;

    public AchievementService(AchievementRepo achievementRepo, SportRepo sportRepo) {
        this.achievementRepo = achievementRepo;
        this.sportRepo = sportRepo;
    }

    public List<Achievement> findTop3ClosestAchievements(int skyUserId) {
        Pageable top3 = PageRequest.of(0, 3);
        return this.achievementRepo.getClosestUserAchievement(skyUserId, top3);
    }

//    public List<Achievement> getAllUserAchievements(int skyUserId) {
//        return this.achievementRepo.getAllUserAchievement(skyUserId);
//    }

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
        List<Object[]> achievementWithDiffs = achievementRepo.findClosestAchievementsWithPointsDifference(skyUserId, top3);

        // Process the results, assuming pointsDiff is a Long
        return achievementWithDiffs.stream().map(result -> {
            Achievement achievement = (Achievement) result[0];
            Long pointsDiff = (Long) result[1]; // Cast to Long

            Map<String, Object> achievementData = new HashMap<>();
            achievementData.put("achievement", achievement);
            achievementData.put("pointsDifference", pointsDiff);

            return achievementData;
        }).collect(Collectors.toList());
    }

//    Optimal method:
    public List<Achievement> getAllUserAchievements(int skyUserId) {
        return this.achievementRepo.findAllUserAchievements(skyUserId);
    }

//    Backup method
//    public List<Achievement> getUserAchievementsBySport(int skyUserId, int sportId) {
//        return this.achievementRepo.findUserAchievementsBySport(skyUserId, sportId);
//    }
//
//    public List<Achievement> getUserAchievementsAcrossAllSports(int skyUserId) {
//        // Fetch all sports the user has been active in
//        List<Sport> sports = this.sportRepo.findAll();  // Assuming you have a SportRepo to fetch all sports
//        List<Achievement> earnedAchievements = new ArrayList<>();
//
//        // Loop through each sport and get the achievements the user has earned
//        for (Sport sport : sports) {
//            List<Achievement> achievementsBySport = this.achievementRepo.findUserAchievementsBySport(skyUserId, sport.getId());
//            earnedAchievements.addAll(achievementsBySport);
//        }
//
//        return earnedAchievements;
//    }
}
