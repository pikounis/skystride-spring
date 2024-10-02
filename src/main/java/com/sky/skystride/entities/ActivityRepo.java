package com.sky.skystride.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {
    @Query("SELECT a FROM Activity a WHERE a.skyUser.id = :skyUserId ORDER BY a.startTime DESC")
    List<Activity> findBySkyUserId(@Param("skyUserId") int skyUserId);

    @Query("SELECT SUM(a.pointsEarned) FROM Activity a WHERE a.skyUser.id = :skyUserId AND a.startTime BETWEEN :startDate AND :endDate")
    Integer getPointsEarnedBetweenDates(@Param("skyUserId") int skyUserId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);

    @Query("SELECT SUM(TIMESTAMPDIFF(MINUTE, a.startTime, a.endTime)) FROM Activity a WHERE a.skyUser.id = :skyUserId AND a.startTime BETWEEN :startDate AND :endDate")
    Integer getMinutesWorkedOutBetweenDates(@Param("skyUserId") int skyUserId, @Param("startDate") LocalDateTime startDate, @Param("endDate") LocalDateTime endDate);
}
