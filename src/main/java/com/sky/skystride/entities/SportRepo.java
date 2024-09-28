package com.sky.skystride.entities;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SportRepo extends JpaRepository<Sport, Integer> {
    Sport findByName(String name);
}
