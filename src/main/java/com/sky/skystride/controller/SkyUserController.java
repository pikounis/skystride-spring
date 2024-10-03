package com.sky.skystride.controller;
import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.entities.Sport;
import com.sky.skystride.entities.TimerStatusDTO;
import com.sky.skystride.services.SkyUserService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://35.176.106.162", "http://localhost:3000"})
public class SkyUserController {

    private SkyUserService service;

    public SkyUserController(SkyUserService service) {
        super();
        this.service = service;
    }

    @PostMapping("/user/create")
    public SkyUser addSkyUser(@RequestBody @Valid SkyUser user) {
        return this.service.addSkyUser(user);
    }

    @GetMapping("/user/getAll")
    public List<SkyUser> getAllSkyUsers() {
        return this.service.getAllSkyUsers();
    }

    @PostMapping("/user/{skyUserId}/startTimer/{sportId}")
    public SkyUser startTimer(@PathVariable int skyUserId, @PathVariable int sportId) {
        return this.service.startTimer(skyUserId, sportId);
    }

    @PostMapping("/user/{skyUserId}/endTimer")
    public SkyUser endTimer(@PathVariable int skyUserId) {
        return this.service.endTimer(skyUserId);
    }

    @GetMapping("/user/{skyUserId}/getTimer")
    public TimerStatusDTO getTimerStatus(@PathVariable int skyUserId) {
        return this.service.getTimerStatus(skyUserId);
    }

}
