package com.sky.skystride.controller;

import com.sky.skystride.entities.SkyUser;
import com.sky.skystride.services.AuthService;
import com.sky.skystride.services.SkyUserService;
import com.sky.skystride.security.JwtUtil;
import com.sky.skystride.dto.LoginDTO;

import com.sky.skystride.utils.AuthenticationResponse;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@NoArgsConstructor
@CrossOrigin(origins = {"http://35.176.106.162:3000", "http://localhost:3000"})
public class AuthController {

    @Autowired
    private AuthService authService;

    @Autowired
    private SkyUserService userService;

    private JwtUtil jwtUtil = new JwtUtil();

    @PostMapping("/register")
    public ResponseEntity<?> createAccount(@RequestBody @Valid SkyUser user) {
        System.out.println("Trying to print user");
        return ResponseEntity.ok(this.authService.register(user));
    }

    @GetMapping("/authhealth")
    public String health() {
        System.out.println("Health");
        return "AuthController is running";
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDTO credentials){
        LoginDTO existingUser = this.authService.getProfileByEmail(credentials.getEmail());
        System.out.println("user: " + existingUser.getEmail());
        if (existingUser != null && this.authService.getPasswordEncoder().matches(credentials.getUserPassword(), existingUser.getUserPassword())){
            String token = JwtUtil.generateToken(existingUser.getEmail(), existingUser.getId());
//            return ResponseEntity.ok(token, existingUser.getId());
            return ResponseEntity.ok(new AuthenticationResponse(token, existingUser.getId()));

        }
        return ResponseEntity.status(401).body("Invalid Credentials");
    }

    @GetMapping("/getOrganiser")
    public Long getOrganiserId(@RequestParam(name = "token", defaultValue = "") String token){
        System.out.println("Organiser");
        String email = this.jwtUtil.extractEmail(token);
        return this.userService.getProfileByEmail(email).getId();
    }

    @GetMapping("/getEmailById")
    public String getEmailFromId(@RequestParam(name = "id", defaultValue = "") String token){
        return this.jwtUtil.extractEmail(token);

    }
}