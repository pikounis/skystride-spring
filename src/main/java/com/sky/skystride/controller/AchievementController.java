package com.sky.skystride.controller;

import com.sky.skystride.entities.Achievement;
import com.sky.skystride.entities.Team;
import com.sky.skystride.services.AchievementService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://35.176.106.162", "http://localhost:3000"})
@RequestMapping("/achievement")
public class AchievementController {
    private AchievementService service;

    public AchievementController(AchievementService service) {
        super();
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Achievement> getAllAchievements() {
        return this.service.getAllAchievements();
    }

    @PostMapping("/addAchievement")
    public Achievement addAchievement(@RequestBody @Valid Achievement achievement) {
        return this.service.addAchievement(achievement);
    }

    @PutMapping("/update")
    public Achievement updateAchievement(@RequestBody @Valid Achievement achievement) {
        return this.service.updateAchievement(achievement);
    }

    @DeleteMapping("/delete/{achievementId}")
    public boolean deleteAchievement(@PathVariable int achievementId) {
        return this.service.deleteAchievement(achievementId);
    }

    @GetMapping("/getMyAchievements/{skyUserId}")
    public List<Achievement> getMyAchievements(@PathVariable int skyUserId) {
        return this.service.getAllUserAchievements(skyUserId);
    }

    @GetMapping("/getMostRecent/{skyUserId}")
    public Achievement getMostRecent(@PathVariable int skyUserId) {
        return this.service.getMostRecentUserAchievement(skyUserId);
    }

    @GetMapping("/getTopThree/{skyUserId}")
    public List<Achievement> getTopThreeRecent(@PathVariable int skyUserId) {
        return this.service.findTop3ClosestAchievements(skyUserId);
    }
}
