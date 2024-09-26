package com.sky.skystride.entities;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkyUserRepo extends JpaRepository<SkyUser, Integer> {
}
