package com.sky.skystride.services;

import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.entities.SkyUserRepo;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class SkyUserService {
    private SkyUserRepo repo;

    public SkyUserService(SkyUserRepo repo) {
        this.repo = repo;
    }

    public SkyUser addSkyUser(SkyUser skyUser) {
        return this.repo.save(skyUser);
    }

    public List<SkyUser> getAllSkyUsers() {
        return this.repo.findAll();
    }
}
