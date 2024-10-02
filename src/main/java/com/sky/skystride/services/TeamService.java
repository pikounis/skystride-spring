package com.sky.skystride.services;

import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.entities.SkyUserRepo;
import com.sky.skystride.entities.Team;
import com.sky.skystride.entities.TeamRepo;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TeamService {
    private TeamRepo teamRepo;
    private SkyUserRepo skyUserRepo;

    public TeamService(TeamRepo teamRepo, SkyUserRepo skyUserRepo) {
        super();
        this.teamRepo = teamRepo;
        this.skyUserRepo = skyUserRepo;
    }

    public Team addTeam(Team team) {
        return this.teamRepo.save(team);
    }

    public Team addTeam(String name, String imageURL, String description, int createdById) {
        // Fetch SkyUser using createdById
        SkyUser createdBy = skyUserRepo.findById(createdById)
                .orElseThrow(() -> new RuntimeException("SkyUser not found with id: " + createdById));

        // Create the team and set the creator
        Team team = new Team(name, imageURL, description);
        team.setCreatedBy(createdBy);

        Team saved = teamRepo.save(team);

        addMember(saved.getId(), createdById);
        // Save the new team
        return saved;
    }

    public Boolean deleteTeam(int id) {
        this.teamRepo.deleteById(id);
        return !this.teamRepo.existsById(id);
    }

    public List<Team> getAllTeams() {
        return this.teamRepo.findAll();
    }

    public List<Team> getMyTeams(int skyUserId) {
        return this.teamRepo.findTeamsByMemberId(skyUserId);
    }

    public Team updateTeam(int id, Team team){
        Optional<Team> existingTeam = this.teamRepo.findById(id);
        Team existing = existingTeam.get();
        existing.setName(team.getName());
        existing.setDescription(team.getDescription());
        existing.setImageURL(team.getImageURL());
        existing.setMembers(team.getMembers());
        return this.teamRepo.save(existing);
    }

//    public Team addMember(int teamId, int skyUserId) {
//        Optional<Team> existingTeam = this.teamRepo.findById(teamId);
//        Team existing = existingTeam.orElseThrow(() -> new RuntimeException("Team not found"));
//
//        // Fetch the SkyUser using skyUserId
//        Optional<SkyUser> skyUser = this.skyUserRepo.findById(skyUserId);
//        SkyUser user = skyUser.orElseThrow(() -> new RuntimeException("SkyUser not found"));
//
//        // Add the SkyUser to the members list
////        System.out.println(existing.getMembers()); -> THIS PRINT PRODUCES A STACKOVERFLOW ERROR: NULL
//        List<SkyUser> members = existing.getMembers();
//        if (!members.contains(user)) { // To avoid duplicates
//            members.add(user);
//        }
//
//        existing.setMembers(members);
//        Team saved = this.teamRepo.save(existing);
////        System.out.println(saved);
//        return saved;
//    }

    public Team addMember(int teamId, int skyUserId) {
        Optional<Team> existingTeam = this.teamRepo.findById(teamId);
        Team existing = existingTeam.orElseThrow(() -> new RuntimeException("Team not found"));

        // Fetch the SkyUser using skyUserId
        Optional<SkyUser> skyUser = this.skyUserRepo.findById(skyUserId);
        SkyUser user = skyUser.orElseThrow(() -> new RuntimeException("SkyUser not found"));

        // Add the SkyUser to the members list
//        existing.getMembers().forEach(member -> {
//            System.out.println("Member ID: " + member.getId());
//        });
        List<SkyUser> members = existing.getMembers();
        if (!members.contains(user)) {
            members.add(user);
            existing.setMembers(members); // Set the updated members list

            // Also add the team to the user's teams list to sync both sides
            List<Team> userTeams = user.getTeams();
            if (!userTeams.contains(existing)) {
                userTeams.add(existing);
                user.setTeams(userTeams); // Set the updated teams list
            }
        }

        // Save the SkyUser and Team to ensure persistence of both sides of the relationship
        this.skyUserRepo.save(user);
        return this.teamRepo.save(existing);
    }

    public Team removeMember(int teamId, int skyUserId) {
        Optional<Team> existingTeam = this.teamRepo.findById(teamId);
        Team existing = existingTeam.orElseThrow(() -> new RuntimeException("Team not found"));

        // Fetch the SkyUser using skyUserId
        Optional<SkyUser> skyUser = this.skyUserRepo.findById(skyUserId);
        SkyUser user = skyUser.orElseThrow(() -> new RuntimeException("SkyUser not found"));

        // Remove the SkyUser from the members list
        List<SkyUser> members = existing.getMembers();
        if (members.contains(user)) {
            members.remove(user);
        } else {
            throw new RuntimeException("SkyUser not found in team members");
        }

        existing.setMembers(members);
        return this.teamRepo.save(existing);
    }



}
