package com.sky.skystride.services;

import com.sky.skystride.dto.ProfileDTO;
import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.entities.SkyUserRepo;
import lombok.Data;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Data
public class SkyUserService {
    private SkyUserRepo repo;
    private ModelMapper mapper;

    public SkyUserService(SkyUserRepo repo, ModelMapper mapper) {
        super();
        this.mapper = mapper;
        this.repo = repo;
    }

    public SkyUser addSkyUser(SkyUser skyUser) {
        return this.repo.save(skyUser);
    }

    public List<SkyUser> getAllSkyUsers() {
        return this.repo.findAll();
    }

    public boolean getLoginCredentials(String email, String password) {
        SkyUser user = this.repo.findByEmail(email);
        return user.getUserPassword().equals(password);
    }

    public ProfileDTO getProfileByEmail(String email) {
        SkyUser savedUser = this.repo.findByEmail(email);
        return this.mapper.map(savedUser, ProfileDTO.class);
    }
}
