package com.sky.skystride.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepo extends JpaRepository<Team, Integer> {

    @Query("SELECT t FROM Team t JOIN t.members m WHERE m.id = :skyUserId")
    List<Team> findTeamsByMemberId(int skyUserId);
}
