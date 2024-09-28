package com.sky.skystride.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ActivityRepo extends JpaRepository<Activity, Integer> {
    @Query("SELECT a FROM Activity a WHERE a.skyUser.id = :skyUserId")
    List<Activity> findBySkyUserId(@Param("skyUserId") int skyUserId);
}
