package com.sky.skystride.controller;

import com.sky.skystride.entities.Sport;
import com.sky.skystride.services.SportService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://35.176.106.162:3000", "http://localhost:3000"})
@RequestMapping("/sport")
public class SportController {
    private SportService service;

    public SportController(SportService service) {
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Sport> getAllSports() {
        return this.service.getSports();
    }

    @PostMapping("/create")
    public Sport addSport(@RequestBody @Valid Sport sport) {
        return this.service.addSport(sport);
    }

    @PutMapping("/update")
    public Sport updateSport(@RequestBody @Valid Sport sport) {
        return this.service.updateSport(sport);
    }

    @DeleteMapping("/delete/{sportId}")
    public boolean deleteSport(@PathVariable int sportId) {
        return this.service.removeSport(sportId);
    }
}
