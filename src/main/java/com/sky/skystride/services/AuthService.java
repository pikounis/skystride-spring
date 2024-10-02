package com.sky.skystride.services;

import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.entities.SkyUserRepo;
import com.sky.skystride.dto.ProfileDTO;
import com.sky.skystride.dto.LoginDTO;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import lombok.Data;

@Service
@AllArgsConstructor
@Data
public class AuthService {

    @Autowired
    private SkyUserRepo userRepo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ModelMapper mapper;

    public ProfileDTO register(SkyUser user) {
        user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        SkyUser savedUser = this.userRepo.save(user);
        return this.mapper.map(savedUser, ProfileDTO.class);
    }

    public LoginDTO getProfileByEmail(String email) {
        System.out.println("Email: " + email);
        SkyUser savedUser = this.userRepo.findByEmail(email);

        //Change for better error handling
        if (savedUser == null) return new LoginDTO();

        return this.mapper.map(savedUser, LoginDTO.class);
    }
    public ProfileDTO getUserProfileByEmail(String email) {
        SkyUser savedUser = this.userRepo.findByEmail(email);

        //Change for better error handling
        if (savedUser == null) return new ProfileDTO();
        return this.mapper.map(savedUser, ProfileDTO.class);
    }


}
