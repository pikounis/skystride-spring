package com.sky.skystride.services;

import com.sky.skystride.entities.Achievement;
import com.sky.skystride.entities.AchievementRepo;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AchievementService {
    private AchievementRepo achievementRepo;

    public List<Achievement> findTop3ClosestAchievements(int skyUserId) {
        Pageable top3 = PageRequest.of(0, 3);
        return this.achievementRepo.getClosestUserAchievement(skyUserId, top3);
    }
}
