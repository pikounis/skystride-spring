package com.sky.skystride.services;

import com.sky.skystride.entities.Achievement;
import com.sky.skystride.entities.SportRepo;
import org.springframework.stereotype.Service;
import com.sky.skystride.entities.Sport;

import java.util.List;
import java.util.Optional;

@Service
public class SportService {
    private SportRepo repo;

    public SportService(SportRepo repo) {
        this.repo = repo;
    }

    public List<Sport> getSports() {
        return this.repo.findAll();
    }

    public Sport addSport(Sport sport) {
        return this.repo.save(sport);
    }

    public Sport updateSport(Sport sport) {
        Optional<Sport> existingSport = this.repo.findById(sport.getId());
        Sport existing = existingSport.get();

        existing.setName(sport.getName());
        existing.setPpm(sport.getPpm());
        return existing;
    }

    public boolean removeSport(int id) {
        this.repo.deleteById(id);
        return !this.repo.existsById(id);
    }
}
