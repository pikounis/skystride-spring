package com.sky.skystride.entities;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AchievementRepo extends JpaRepository<Achievement, Integer> {
    @Query("SELECT a FROM Achievement a WHERE a.pointsNeeded <= (SELECT su.points FROM SkyUser su WHERE su.id = :skyUserId)")
    List<Achievement> getAllUserAchievement(int skyUserId);

    @Query("SELECT a FROM Achievement a WHERE a.pointsNeeded > (SELECT su.points FROM SkyUser su WHERE su.id = :skyUserId) " +
            "ORDER BY a.pointsNeeded ASC")
    List<Achievement> getClosestUserAchievement(int skyUserId, Pageable pageable);

    @Query("SELECT a FROM Achievement a WHERE a.pointsNeeded <= " +
            "(SELECT SUM(act.pointsEarned) FROM Activity act WHERE act.skyUser.id = :skyUserId AND act.sport.id = :sportId) " +
            "AND a.sport.id = :sportId")
    List<Achievement> findAchievementsByUserAndSport(@Param("skyUserId") int skyUserId, @Param("sportId") int sportId);

    @Query("SELECT a, " +
            "(a.pointsNeeded - COALESCE((SELECT SUM(act.pointsEarned) FROM Activity act WHERE act.skyUser.id = :skyUserId AND act.sport.id = a.sport.id), 0)) AS pointsDiff " +
            "FROM Achievement a " +
            "WHERE (a.pointsNeeded - (SELECT SUM(act.pointsEarned) FROM Activity act WHERE act.skyUser.id = :skyUserId AND act.sport.id = a.sport.id)) > 0 " +
            "ORDER BY pointsDiff ASC")
    List<Object[]> findClosestAchievementsWithPointsDifference(@Param("skyUserId") int skyUserId, Pageable pageable);
}
