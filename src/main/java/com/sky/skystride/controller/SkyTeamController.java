package com.sky.skystride.controller;

import com.sky.skystride.entities.Team;
import com.sky.skystride.dto.TeamWithAveragePointsDTO;
import com.sky.skystride.services.TeamService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = {"http://35.176.106.162:3000", "http://localhost:3000"})
@RequestMapping("/team")
public class SkyTeamController {

    private TeamService service;

    public SkyTeamController(TeamService service) {
        super();
        this.service = service;
    }

    @GetMapping("/getAll")
    public List<Team> getAll() {
        return this.service.getAllTeams();
    }

    @GetMapping("/getAllWithAveragePoints")
    public List<TeamWithAveragePointsDTO> getAllTeamsWithAveragePoints() {
        return this.service.getAllTeamsWithAveragePoints();
    }

    @GetMapping("/getMyTeams/{skyUserId}")
    public List<Team> getMyTeams(@PathVariable int skyUserId) {
        return this.service.getMyTeams(skyUserId);
    }

    @PostMapping("/create/{skyUserId}")
    public Team addTeam(@RequestBody @Valid Team team, @PathVariable int skyUserId) {
        return this.service.addTeam(team.getName(), team.getImageURL(), team.getDescription(), skyUserId);
    }

    @DeleteMapping("/delete/{teamId}")
    public Boolean deleteTeam(@PathVariable int teamId) {
        return this.service.deleteTeam(teamId);
    }

    @PutMapping("/update/{skyUserId}")
    public Team updateTeam(@PathVariable int skyUserId, @RequestBody @Valid Team team) {
        return this.service.updateTeam(skyUserId, team);
    }

    @PostMapping("/{teamId}/addMember/{skyUserId}")
    public ResponseEntity<Team> addMember(@PathVariable int teamId, @PathVariable int skyUserId) {
        Team updatedTeam = this.service.addMember(teamId, skyUserId);
        return ResponseEntity.ok(updatedTeam);
    }

    @DeleteMapping("/{teamId}/removeMember/{skyUserId}")
    public ResponseEntity<Team> removeMember(@PathVariable int teamId, @PathVariable int skyUserId) {
        Team updatedTeam = this.service.removeMember(teamId, skyUserId);
        return ResponseEntity.ok(updatedTeam);
    }


}
