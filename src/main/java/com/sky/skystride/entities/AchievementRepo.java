package com.sky.skystride.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
    @Query("SELECT a FROM Achievement a WHERE a.pointsNeeded <= (SELECT su.points FROM SkyUser su WHERE su.id = :skyUserId)")
    List<Achievement> getAllUserAchievement(int skyUserId);

    @Query("SELECT a FROM Achievement a WHERE a.pointsNeeded > (SELECT su.points FROM SkyUser su WHERE su.id = :skyUserId) " +
            "ORDER BY a.pointsNeeded ASC")
    List<Achievement> getClosestUserAchievement(int skyUserId, Pageable pageable);
}
