package com.sky.skystride.services;

import com.sky.skystride.entities.Achievement;
import com.sky.skystride.entities.AchievementRepo;
import com.sky.skystride.entities.Activity;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

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
}
