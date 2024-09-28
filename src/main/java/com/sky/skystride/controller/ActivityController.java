package com.sky.skystride.controller;

import com.sky.skystride.entities.Activity;
import com.sky.skystride.entities.Sport;
import com.sky.skystride.services.ActivityService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://35.176.106.162")
@RequestMapping("/activity")
public class ActivityController {
    private ActivityService service;

    public ActivityController(ActivityService service) {
        super();
        this.service = service;
    }

    @GetMapping("/getMyActivities/{skyUserId}")
    public List<Activity> getMyActivities(@PathVariable int skyUserId) {
        return this.service.getAllUserActivity(skyUserId);
    }

    @PostMapping("/create")
    public Activity addActivity(@RequestBody @Valid Activity activity) {
        return this.service.addUserActivity(activity);
    }

//    TODO: MAYBE CHANGE THIS TO NOT BE ALL PATH PARAMS - MAYBE REQUEST BODY STUFF
//    @PutMapping("/update/{activityId}/{sport}/{startDate}/{endDate}")
//    public Activity updateActivity(@PathVariable int activityId, @PathVariable Sport sport, @PathVariable LocalDateTime startDate, @PathVariable LocalDateTime endDate) {
    @PutMapping("/update")
    public Activity updateActivity(@RequestBody @Valid Activity activity) {
        return this.service.updateActivity(activity.getId(), activity.getSport(), activity.getStartTime(), activity.getEndTime());
    }

    @DeleteMapping("/delete/{activityId}")
    public boolean deleteActivity(@PathVariable int activityId) {
        return this.service.deleteActivity(activityId);
    }
}
